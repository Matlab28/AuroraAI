package com.example.auroraai.controller;

import com.example.auroraai.dto.gemini.Root;
import com.example.auroraai.dto.request.ChatRequestDTO;
import com.example.auroraai.dto.response.ChatResponseDTO;
import com.example.auroraai.service.ChatService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatController {
    private final ChatService service;

    @PostMapping("/ask")
    public ResponseEntity<Root> processChatRequest(@Valid @RequestBody ChatRequestDTO dto) {
        return ResponseEntity.ok(service.processChatRequest(dto));
    }

    @GetMapping("/getUpdates")
    public ResponseEntity<Root> getLatestUpdates() {
        return ResponseEntity.ok(service.getLatestUpdates());
    }

    @GetMapping("/page/{page}")
    public ResponseEntity<Page<ChatResponseDTO>> readChatByPaged
            (@PathVariable int page,
             @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(service.readChatByPaged(page, size));
    }

    @GetMapping("/readAll")
    public ResponseEntity<List<ChatResponseDTO>> readAllChats() {
        return ResponseEntity.ok(service.readAll());
    }

    @GetMapping("/readBy")
    public ResponseEntity<ChatResponseDTO> readChatByID(@Valid @RequestParam Long id) {
        return ResponseEntity.ok(service.readChatByID(id));
    }
}
