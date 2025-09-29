package com.example.auroraai.service;

import com.example.auroraai.client.GeminiAPIClient;
import com.example.auroraai.dto.gemini.*;
import com.example.auroraai.dto.request.ChatRequestDTO;
import com.example.auroraai.dto.response.ChatResponseDTO;
import com.example.auroraai.entity.ChatEntity;
import com.example.auroraai.repository.ChatRepository;
import com.example.auroraai.service.impl.GeminiImpl;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.stringtemplate.v4.ST;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChatService implements GeminiImpl {
    private final ModelMapper modelMapper;
    private final ChatRepository repository;
    private final GeminiAPIClient client;
    @Value("${gemini.api.key}")
    private String apiKey;
    @Value("classpath:prompts/prompt.st")
    private Resource templateResource;
    private Root latestUpdates;


    @Override
    public Root processChatRequest(ChatRequestDTO dto) {
        ChatEntity entity = modelMapper.map(dto, ChatEntity.class);

        String instruction = constructInstruction(dto);
        Root updates = getLatestUpdates(instruction);
        String extractedText = extractedTextFromGeminiResponse(updates);

        latestUpdates = updates;
        log.info("{} ID of user's message has been received", entity.getId());
        repository.save(entity);
        return latestUpdates;
    }

    @Override
    public Root getLatestUpdates() {
        return latestUpdates;
    }

    private String constructInstruction(ChatRequestDTO dto) {
        try {
            String templateContent = Files.readString(templateResource.getFile().toPath(),
                    StandardCharsets.UTF_8);
            ST template = new ST(templateContent, '<', '>');

            template.add("chatLanguage", dto.getLanguage());
            template.add("message", dto.getMessage());

            return template.render();
        } catch (IOException e) {
            log.error("Error loading template file: {}", e.getMessage());
            throw new IllegalArgumentException("Something went wrong while fetching...");
        }
    }

    private Root getLatestUpdates(String instruction) {
        try {
            Part part = new Part();
            part.setText(instruction);

            Content content = new Content();
            content.setRole("user");
            content.setParts(List.of(part));

            GenerationConfig config = new GenerationConfig();
            config.setTemperature(1);
            config.setTopK(40);
            config.setTopP(0.95);
            config.setMaxOutputTokens(8192);
            config.setResponseMimeType("text/plain");

            GenerateContentRequest request = new GenerateContentRequest();
            request.setContents(List.of(content));
            request.setGenerationConfig(config);

            return client.generateContent(apiKey, request);
        } catch (Exception e) {
            log.error("Error while getting response from Gemini AI: {}", e.getMessage());
            throw e;
        }
    }

//    private Root getLatestUpdates(String instruction) {
//        try {
//            JsonObject json = new JsonObject();
//            JsonArray partsArray = new JsonArray();
//            JsonObject partsObject = new JsonObject();
//            JsonArray contentsArray = new JsonArray();
//            JsonObject contentsObject = new JsonObject();
//
//            partsObject.add("text", new JsonPrimitive(instruction));
//            partsArray.add(partsObject);
//            contentsObject.add("parts", partsArray);
//            contentsArray.add(contentsObject);
//            json.add("contents", contentsArray);
//
//            String content = json.toString();
//            return client.generateContent(apiKey, content);
//        } catch (Exception e) {
//            log.error("Error while getting response from Gemini AI: {}", e.getMessage());
//            throw e;
//        }
//    }

    private String extractedTextFromGeminiResponse(Root updates) {
        StringBuilder textBuilder = new StringBuilder();

        if (updates.getCandidates() != null && !updates.getCandidates().isEmpty()) {
            for (Candidate candidate : updates.getCandidates()) {
                String text = candidate.getContent().getParts().get(0).getText();
                text = text.replace("*", "");
                textBuilder.append(text).append("\n\n");
            }
        }

        String result = textBuilder.toString().trim();

        return result
                .replaceAll("(?i)\\bChat Language:\\b", "\nChat Language:\n")
                .replaceAll("(?i)\\bMessage:\\b", "\nMessage:\n");
    }

    @Transactional(readOnly = true)
    public List<ChatResponseDTO> readAll() {
        log.info("All chats responded");
        return repository
                .findAll()
                .stream()
                .map(m -> modelMapper.map(m, ChatResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Page<ChatResponseDTO> readChatByPaged(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ChatEntity> all = repository.findAll(pageable);

        log.info("Chat responded by page of {}, and size of {}", page, size);
        return all.map(m -> modelMapper.map(m, ChatResponseDTO.class));
    }

    @Transactional(readOnly = true)
    public ChatResponseDTO readChatByID(Long id) {
        ChatEntity chat = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Chat not found by ID - " + id));

        log.info("{} ID of chat has been responded", id);
        return modelMapper.map(chat, ChatResponseDTO.class);
    }
}
