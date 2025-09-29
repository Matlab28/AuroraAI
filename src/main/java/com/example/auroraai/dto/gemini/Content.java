package com.example.auroraai.dto.gemini;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class Content {
    private List<Part> parts;
    private String role;

    @Override
    public String toString() {
        return "Content{" +
                "parts=" + parts +
                ", role='" + role + '\'' +
                '}';
    }
}
