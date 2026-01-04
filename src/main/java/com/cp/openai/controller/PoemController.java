package com.cp.openai.controller;

import com.cp.openai.service.PoetryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PoemController {

    @Autowired
    private PoetryService poetryService;

    @PostMapping(value = "/poems" , consumes = "application/json")
    public ResponseEntity<String> generate(@RequestBody PoemGenerationRequest request) {
        String response = poetryService.generate(request.genre, request.theme);
        return ResponseEntity.ok(response);
    }

    record PoemGenerationRequest(String genre, String theme) {}

}
