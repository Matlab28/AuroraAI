package com.example.auroraai.dto.response;

import com.example.auroraai.constant.Language;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ChatResponseDTO {
    private Long id;
    private Language language;
    private String message;
}
