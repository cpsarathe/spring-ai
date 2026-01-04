package com.cp.openai.service;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PoetryService {
    private final ChatModel chatModel;
    private static final PromptTemplate PROMPT_TEMPLATE = 
            new PromptTemplate("Write a {genre} haiku about {theme} following the traditional 5-7-5 syllable structure.");

    PoetryService(ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    public String generate(String genre, String theme) {
        Prompt prompt = PROMPT_TEMPLATE.create(Map.of("genre", genre, "theme", theme));
        return chatModel.call(prompt).getResult().getOutput().getText();
    }
}
