package com.cp.openai.agentic.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration for Agentic AI tools/functions
 * 
 * Spring AI 2.0.0-M1 automatically discovers @Tool annotated methods
 * from Spring beans and makes them available for function calling.
 * 
 * The ChatClient will automatically use these tools when OpenAI
 * requests function calls.
 * 
 * FUNCTION CALLING FLOW:
 * 1. Tools with @Tool annotation are auto-discovered by Spring AI
 * 2. ChatClient automatically includes them in requests to OpenAI
 * 3. When OpenAI needs a function, it requests it via function_call
 * 4. Spring AI executes the function and returns result
 * 5. OpenAI generates final response with function results
 */
@Configuration
public class AgenticToolConfiguration {

    /**
     * Creates a ChatClient that will automatically use @Tool annotated methods.
     * 
     * Spring AI's autoconfiguration automatically discovers all @Tool methods
     * from Spring beans and makes them available for function calling.
     * No manual registration needed!
     */
    @Bean
    public ChatClient agenticChatClient(ChatClient.Builder chatClientBuilder) {
        // Spring AI automatically discovers @Tool annotated methods
        // from all Spring beans (CalculatorTool, WeatherTool, TimeTool)
        // and makes them available for function calling
        // The ChatClient will automatically use them when OpenAI requests function calls
        return chatClientBuilder.build();
    }
}

