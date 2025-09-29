package com.example.auroraai.service.impl;

import com.example.auroraai.dto.gemini.Root;
import com.example.auroraai.dto.request.ChatRequestDTO;
import org.springframework.stereotype.Service;

@Service
public interface GeminiImpl {
    Root processChatRequest(ChatRequestDTO dto);

    Root getLatestUpdates();
}
