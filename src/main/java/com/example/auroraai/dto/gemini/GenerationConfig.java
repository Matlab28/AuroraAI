package com.example.auroraai.dto.gemini;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenerationConfig {
    private double temperature;
    private int topK;
    private double topP;
    private int maxOutputTokens;
    private String responseMimeType;
}
