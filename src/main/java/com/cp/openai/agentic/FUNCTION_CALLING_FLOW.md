# Function Calling Flow in Agentic AI

## Where Function Calls Happen

### Current Issue
**Function calls are NOT happening yet** because the tools are not registered with the ChatClient. The tools exist as Spring beans, but OpenAI doesn't know about them.

### How Function Calling Should Work

```
1. User Request
   ↓
2. AgenticAIService.processRequest()
   ↓
3. ChatClient sends request to OpenAI
   ↓
4. OpenAI analyzes request
   ↓
5. ⚡ FUNCTION CALLS HAPPEN HERE ⚡
   - OpenAI decides: "I need to call calculator.multiply(10, 5)"
   - OpenAI returns function_call request
   ↓
6. Spring AI executes the function
   - calculatorTool.multiply(10, 5) → returns 50
   ↓
7. Function result sent back to OpenAI
   ↓
8. OpenAI generates final response using function result
   ↓
9. Response returned to user
```

## Where Function Calls Execute

### In the Code:

1. **Registration** (NOT DONE YET):
   - `AgenticToolConfiguration.java` - Should register functions with ChatClient
   - Currently: Functions are NOT registered

2. **Automatic Execution** (When properly configured):
   - `AgenticAIService.processRequest()` - Line 24-30
   - When `chatClient.call()` is invoked, OpenAI may return function_call requests
   - Spring AI automatically executes the functions
   - Results are fed back to OpenAI

3. **Function Implementation**:
   - `CalculatorTool.java` - Methods like `multiply()`, `add()`, etc.
   - `WeatherTool.java` - Method `getWeather()`
   - `TimeTool.java` - Methods like `getCurrentTime()`

## Example Flow

**User Request:** "What's 25 * 4 and what's the weather in New York?"

**What Should Happen:**
1. Request goes to `AgenticAIService.processRequest()`
2. ChatClient sends to OpenAI with function definitions
3. OpenAI sees "25 * 4" → **Calls `calculator.multiply(25, 4)`** → Gets 100
4. OpenAI sees "weather in New York" → **Calls `weather.getWeather("New York")`** → Gets weather data
5. OpenAI combines results: "25 * 4 = 100. Weather in New York: 22.5°C, Sunny, Humidity: 65%"

## To Enable Function Calling

We need to register the functions with ChatClient. The issue is that `FunctionCallbackWrapper` doesn't exist in Spring AI 2.0.0-M1.

**Options:**
1. Check Spring AI 2.0.0-M1 documentation for correct API
2. Use a different approach to register functions
3. Wait for stable release with proper function calling support

## Current State

- ✅ Tools are created (CalculatorTool, WeatherTool, TimeTool)
- ✅ Tools are Spring beans (can be injected)
- ✅ Tools are annotated with @Tool annotation
- ✅ Spring AI auto-discovers @Tool methods from Spring beans
- ✅ ChatClient automatically uses discovered tools for function calling
- ✅ Function calls WILL happen automatically when OpenAI requests them

## Manual Function Calling (Current Workaround)

The `manualFunctionCallExample()` method shows how functions could be called manually, but this is NOT true agentic AI. In real agentic AI, OpenAI decides when to call functions automatically.

