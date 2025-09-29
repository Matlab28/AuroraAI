package com.example.auroraai.dto.gemini;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PromptTokensDetail {
    public String modality;
    public int tokenCount;

    @Override
    public String toString() {
        return "PromptTokensDetail{" +
                "modality='" + modality + '\'' +
                ", tokenCount=" + tokenCount +
                '}';
    }
}
