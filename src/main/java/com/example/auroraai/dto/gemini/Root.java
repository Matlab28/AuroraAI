package com.example.auroraai.dto.gemini;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Setter
@Getter
public class Root {
    public ArrayList<Candidate> candidates;
    public UsageMetadata usageMetadata;
    public String modelVersion;
    public String responseId;

    @Override
    public String toString() {
        return "Root{" +
                "candidates=" + candidates +
                ", usageMetadata=" + usageMetadata +
                ", modelVersion='" + modelVersion + '\'' +
                ", responseId='" + responseId + '\'' +
                '}';
    }

    //    private ArrayList<Candidate> candidates;
//    private UsageMetadata usageMetadata;
//
//    @Override
//    public String toString() {
//        return "Root{" +
//                "candidates='" + candidates + '\'' +
//                ", usageMetadata='" + usageMetadata + '\'' +
//                '}';
//    }
}
