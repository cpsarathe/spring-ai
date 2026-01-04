# Agentic AI Implementation

This package contains a complete Agentic AI implementation using Spring AI with function calling capabilities.

## Structure

```
agentic/
├── tool/              # Tool implementations (Calculator, Weather, Time)
├── config/            # Spring configuration for tools and ChatClient
├── service/            # AgenticAIService - orchestrates agent interactions
└── controller/         # REST endpoints for agentic AI
```

## Available Tools

1. **CalculatorTool** - Mathematical operations (add, subtract, multiply, divide, power, sqrt)
2. **WeatherTool** - Get weather information for cities
3. **TimeTool** - Get current time and date information

## API Endpoints

### 1. Simple Chat
```bash
POST /api/agentic/chat
Content-Type: application/json

{
  "input": "What's 25 * 4 and what's the weather in New York?"
}
```

### 2. Complex Task
```bash
POST /api/agentic/task
Content-Type: application/json

{
  "task": "Calculate the square root of 144, then tell me the weather in London and the current time"
}
```

### 3. Custom Request with System Instructions
```bash
POST /api/agentic/custom
Content-Type: application/json

{
  "systemInstructions": "You are a helpful assistant. Always use tools when needed.",
  "userInput": "What's 10 + 20?"
}
```

## How It Works

1. User sends a request to the agent
2. Agent analyzes the request and determines which tools are needed
3. Agent automatically calls the appropriate tools
4. Agent combines the results and responds to the user

## Example Usage

The AI agent can automatically:
- Perform calculations: "What's 15 * 23?"
- Get weather: "What's the weather in Tokyo?"
- Get time: "What time is it?"
- Combine operations: "Calculate 100 / 4 and tell me the weather in Paris"

