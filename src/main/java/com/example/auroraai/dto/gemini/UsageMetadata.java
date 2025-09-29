package com.example.auroraai.dto.gemini;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Setter
@Getter
public class UsageMetadata {
    public int promptTokenCount;
    public int candidatesTokenCount;
    public int totalTokenCount;
    public ArrayList<PromptTokensDetail> promptTokensDetails;
    public ArrayList<CandidatesTokensDetail> candidatesTokensDetails;

    @Override
    public String toString() {
        return "UsageMetadata{" +
                "candidatesTokenCount=" + candidatesTokenCount +
                ", promptTokenCount=" + promptTokenCount +
                ", totalTokenCount=" + totalTokenCount +
                ", promptTokensDetails=" + promptTokensDetails +
                ", candidatesTokensDetails=" + candidatesTokensDetails +
                '}';
    }

    //    private Integer promptTokenCount;
//    private Integer candidatesTokenCount;
//    private Integer totalTokenCount;
//
//    @Override
//    public String toString() {
//        return "UsageMetadata{" +
//                "promptTokenCount=" + promptTokenCount +
//                ", candidatesTokenCount=" + candidatesTokenCount +
//                ", totalTokenCount=" + totalTokenCount +
//                '}';
//    }
}
