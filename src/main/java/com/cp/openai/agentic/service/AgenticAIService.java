package com.cp.openai.agentic.service;

import com.cp.openai.agentic.tool.CalculatorTool;
import com.cp.openai.agentic.tool.TimeTool;
import com.cp.openai.agentic.tool.WeatherTool;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.stereotype.Service;

/**
 * Agentic AI Service that orchestrates AI agent interactions
 * with tool/function calling capabilities
 * 
 * FUNCTION CALLING FLOW:
 * 1. User sends request → processRequest()
 * 2. ChatClient sends request to OpenAI with function definitions
 * 3. OpenAI decides which functions to call → FUNCTION CALLS HAPPEN HERE
 * 4. Function results are returned to OpenAI
 * 5. OpenAI generates final response with function results
 */
@Service
public class AgenticAIService {

    private final ChatClient agenticChatClient;
    private final CalculatorTool calculatorTool;
    private final WeatherTool weatherTool;
    private final TimeTool timeTool;

    public AgenticAIService(
            ChatClient agenticChatClient,
            CalculatorTool calculatorTool,
            WeatherTool weatherTool,
            TimeTool timeTool) {
        this.agenticChatClient = agenticChatClient;
        this.calculatorTool = calculatorTool;
        this.weatherTool = weatherTool;
        this.timeTool = timeTool;
    }

    /**
     * Process a user request using the agentic AI with tool calling
     * 
     * FUNCTION CALLS HAPPEN HERE:
     * - When OpenAI detects a need for a tool (e.g., "calculate 5*3"),
     *   it returns a function_call request
     * - Spring AI executes the function and returns the result
     * - OpenAI uses the result to generate the final response
     */
    public String processRequest(String userInput) {
        // This is where function calling happens automatically
        // OpenAI will detect if tools are needed and call them
        ChatResponse response = agenticChatClient
                .prompt(userInput)
                .call()
                .chatResponse();

        return response.getResult().getOutput().getText();
    }

    /**
     * Process a request with system instructions for the agent
     * 
     * FUNCTION CALLS: Same as above, but with custom system instructions
     */
    public String processRequestWithInstructions(String systemInstructions, String userInput) {
        ChatResponse response = agenticChatClient
                .prompt()
                .system(systemInstructions)
                .user(userInput)
                .call()
                .chatResponse();

        return response.getResult().getOutput().getText();
    }

    /**
     * Example: Multi-step agentic task
     * 
     * FUNCTION CALLS: Multiple function calls can happen in sequence
     * Example: "Calculate 10*5, get weather in NYC, and tell me the time"
     * → Function call 1: calculator.multiply(10, 5) → returns 50
     * → Function call 2: weather.getWeather("New York") → returns weather data
     * → Function call 3: time.getCurrentTime() → returns current time
     * → OpenAI combines all results into final response
     */
    public String processComplexTask(String task) {
        String systemPrompt = """
            You are a helpful AI assistant with access to various tools.
            When a user asks a question that requires calculations, weather information, or time,
            use the appropriate tools to get accurate information before responding.
            Always explain what tools you used and why.
            """;

        return processRequestWithInstructions(systemPrompt, task);
    }

    /**
     * Manual function calling example (for demonstration)
     * This shows how functions are called, but in real agentic AI,
     * OpenAI decides when to call functions automatically
     */
    public String manualFunctionCallExample(String request) {
        // This is NOT how agentic AI works - this is manual
        // In real agentic AI, OpenAI decides when to call functions
        
        if (request.contains("calculate") || request.contains("math")) {
            // Manual call - in real agentic AI, OpenAI would call this automatically
            double result = calculatorTool.multiply(10, 5);
            return "Calculation result: " + result;
        }
        
        if (request.contains("weather")) {
            // Manual call - in real agentic AI, OpenAI would call this automatically
            var weather = weatherTool.getWeather("New York");
            return weather.toString();
        }
        
        return "I don't know how to handle that request";
    }
}

