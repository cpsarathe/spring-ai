# spring-ai

# Simple request
curl -X POST http://localhost:8080/api/agentic/chat \
-H "Content-Type: application/json" \
-d '{"input":"What is 25 * 4 and what is the weather in New York?"}'

# Complex task
curl -X POST http://localhost:8080/api/agentic/task \
-H "Content-Type: application/json" \
-d '{"task":"Calculate square root of 144, get weather in London, and tell me the current time"}'