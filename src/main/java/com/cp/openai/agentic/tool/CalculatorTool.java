package com.cp.openai.agentic.tool;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

/**
 * Calculator tool that the AI agent can use to perform mathematical calculations
 */
@Component
public class CalculatorTool {

    @Tool(description = "Add two numbers together")
    public double add(
            @ToolParam(description = "First number") double a,
            @ToolParam(description = "Second number") double b) {
        return a + b;
    }

    @Tool(description = "Subtract second number from first number")
    public double subtract(
            @ToolParam(description = "First number") double a,
            @ToolParam(description = "Second number") double b) {
        return a - b;
    }

    @Tool(description = "Multiply two numbers")
    public double multiply(
            @ToolParam(description = "First number") double a,
            @ToolParam(description = "Second number") double b) {
        return a * b;
    }

    @Tool(description = "Divide first number by second number")
    public double divide(
            @ToolParam(description = "Dividend") double a,
            @ToolParam(description = "Divisor") double b) {
        if (b == 0) {
            throw new IllegalArgumentException("Cannot divide by zero");
        }
        return a / b;
    }

    @Tool(description = "Raise base to the power of exponent")
    public double power(
            @ToolParam(description = "Base number") double base,
            @ToolParam(description = "Exponent") double exponent) {
        return Math.pow(base, exponent);
    }

    @Tool(description = "Calculate square root of a number")
    public double sqrt(@ToolParam(description = "Number to calculate square root of") double value) {
        if (value < 0) {
            throw new IllegalArgumentException("Cannot calculate square root of negative number");
        }
        return Math.sqrt(value);
    }
}

