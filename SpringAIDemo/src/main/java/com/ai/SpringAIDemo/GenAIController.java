package com.ai.SpringAIDemo;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.ai.image.ImageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class GenAIController {
    @Autowired
    ChatService chatService;
    @Autowired
    ImageService imageService;
    @GetMapping("/ask-ai")
    public String getResponse(@RequestParam String prompt) {
        return chatService.getResponse(prompt);
    }
    @GetMapping("/ask-ai-options")
    public String getResponseOptions(@RequestParam String prompt) {
        return chatService.getResponseOptions(prompt);
    }
    @GetMapping("/generate-images")
    public List<String> generateImages(HttpServletResponse response, @RequestParam String prompt) throws IOException {
        ImageResponse imageResponse = imageService.generateImage(prompt);
        List<String> imageUrls = imageResponse.getResults().stream()
                .map(result -> result.getOutput().getUrl())
                .toList();
        return imageUrls;
    }
}
