package com.example.auroraai.client;

import com.example.auroraai.dto.gemini.GenerateContentRequest;
import com.example.auroraai.dto.gemini.Root;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "gemini", url = "https://generativelanguage.googleapis.com/v1beta")
public interface GeminiAPIClient {

    @PostMapping("/models/gemini-2.0-flash-exp:generateContent")
    Root generateContent(
            @RequestParam("key") String apiKey,
            @RequestBody GenerateContentRequest request
    );

//    @PostMapping("/models/gemini-2.0-flash-exp:generateContent")
//    Root generateContent(@RequestParam("key") String key, @RequestBody String content);
}
