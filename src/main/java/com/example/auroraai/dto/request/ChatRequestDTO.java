package com.example.auroraai.dto.request;

import com.example.auroraai.constant.Language;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ChatRequestDTO {
    private Language language;
    private String message;
}
