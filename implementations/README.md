# MCP reference implementations

These implementations make the sample registry independently reviewable and buildable. They expose Agent Studio's HTTP tool adapter contract at `/tools/{capability}` and health/readiness endpoints through Spring Boot Actuator or `/health` for the Playwright worker.

No image contains credentials. Agent Studio injects resolved, organization-scoped credentials at invocation time. The examples enforce bounded inputs and keep side-effect declarations in the corresponding registry manifests.

| Implementation | Capabilities |
|---|---|
| `catalog-reader-mcp` | Public web fetch/extraction and read-only PostgreSQL queries |
| `headless-browser-mcp` | Navigation, extraction, screenshots, and governed actions |
| `chart-mcp` | Portable multi-chart response documents |
| `redis-memory-mcp` | Tenant-scoped hot memory operations |
| `slack-mcp` | Formatted Slack messages and chart file delivery |
