package com.cp.openai.agentic.controller;

import com.cp.openai.agentic.service.AgenticAIService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST Controller for Agentic AI endpoints
 */
@RestController
@RequestMapping("/api/agentic")
public class AgenticAIController {

    private final AgenticAIService agenticAIService;

    public AgenticAIController(AgenticAIService agenticAIService) {
        this.agenticAIService = agenticAIService;
    }

    /**
     * Simple agentic AI request endpoint
     * POST /api/agentic/chat
     */
    @PostMapping("/chat")
    public ResponseEntity<AgenticResponse> chat(@RequestBody AgenticRequest request) {
        String response = agenticAIService.processRequest(request.input());
        return ResponseEntity.ok(new AgenticResponse(response));
    }

    /**
     * Complex task endpoint with system instructions
     * POST /api/agentic/task
     */
    @PostMapping("/task")
    public ResponseEntity<AgenticResponse> processTask(@RequestBody ComplexTaskRequest request) {
        String response = agenticAIService.processComplexTask(request.task());
        return ResponseEntity.ok(new AgenticResponse(response));
    }

    /**
     * Custom agentic request with system instructions
     * POST /api/agentic/custom
     */
    @PostMapping("/custom")
    public ResponseEntity<AgenticResponse> customRequest(@RequestBody CustomAgenticRequest request) {
        String response = agenticAIService.processRequestWithInstructions(
            request.systemInstructions(), 
            request.userInput()
        );
        return ResponseEntity.ok(new AgenticResponse(response));
    }

    // Request/Response DTOs
    public record AgenticRequest(String input) {}
    
    public record ComplexTaskRequest(String task) {}
    
    public record CustomAgenticRequest(String systemInstructions, String userInput) {}
    
    public record AgenticResponse(String response) {}
}

