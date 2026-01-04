package com.cp.openai.agentic.tool;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Time tool that the AI agent can use to get current time information
 */
@Component
public class TimeTool {

    @Tool(description = "Get the current date and time")
    public String getCurrentTime() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    @Tool(description = "Get the current date")
    public String getCurrentDate() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    @Tool(description = "Get current time in a specific timezone")
    public String getCurrentTimeInTimezone(@ToolParam(description = "Timezone name (e.g., UTC, EST, PST)") String timezone) {
        // Simplified - in real scenario, use proper timezone handling
        LocalDateTime now = LocalDateTime.now();
        return String.format("Current time in %s: %s", 
            timezone, 
            now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }
}

