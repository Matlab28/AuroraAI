package com.example.auroraai.dto.gemini;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GenerateContentRequest {
    private List<Content> contents;
    private GenerationConfig generationConfig;
}
