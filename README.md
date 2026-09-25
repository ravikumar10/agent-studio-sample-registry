# Agent Studio sample registry

This repository demonstrates the portable Agent Studio registry format. `catalog.json` is the only discovery entry point; every artifact is versioned and reviewed before a client pulls it.

## Layout

- `agents/`: declarative agents binding logical capabilities and model profiles.
- `mcp/`: MCP server manifests. Deployment URLs and secrets are supplied by the client.
- `skills/`: reusable operating instructions loaded with progressive disclosure.

The database tools are read-only by design. Web fetching requires an allow-list and blocks private/link-local addresses. No manifest contains credentials, raw provider endpoints, or client-specific URLs.
