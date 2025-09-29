package com.example.auroraai.dto.gemini;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Setter
@Getter
public class Candidate {
    public Content content;
    public String finishReason;
    public double avgLogprobs;

    @Override
    public String toString() {
        return "Candidate{" +
                "avgLogprobs=" + avgLogprobs +
                ", content=" + content +
                ", finishReason='" + finishReason + '\'' +
                '}';
    }

    //    private Content content;
//    private String finishReason;
//    private Integer index;
//    private ArrayList<SafetyRating> safetyRatings;
//
//    @Override
//    public String toString() {
//        return "Candidate{" +
//                "content=" + content +
//                ", finishReason='" + finishReason + '\'' +
//                ", index=" + index +
//                ", safetyRatings=" + safetyRatings +
//                '}';
//    }
}
