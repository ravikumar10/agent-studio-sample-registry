# Agent Studio sample registry

This repository demonstrates the portable Agent Studio registry format. `catalog.json` is the only discovery entry point; every artifact is versioned and reviewed before a client pulls it.

## Layout

- `agents/`: declarative agents binding logical capabilities and model profiles.
- `mcp/`: MCP server manifests. Deployment URLs and secrets are supplied by the client.
- `skills/`: reusable operating instructions loaded with progressive disclosure.
- `configurations/`: JSON Schemas used by Agent Studio to build organization-scoped integration forms.
- `implementations/`: buildable reference server source and Dockerfiles for every MCP manifest.

Build any server with `docker build -t <name>:local implementations/<name>`. Registry manifests carry both an immutable release-image reference and a reviewable source path. Production promotion should build, scan, sign, and pin the resulting image digest before approval.

## Included examples

- Public website reading and headless-browser extraction.
- Read-only relational database analysis.
- Redis-backed hot memory and semantic retrieval.
- Multi-chart report generation.
- Slack report delivery with image attachments.
- Composed research, scheduled reporting, and knowledge-assistant agents.

Definitions contain logical capabilities only. After synchronization, an organization creates named integration profiles, stores encrypted credential references, and binds a profile to each capability selected by an agent. Agent Studio resolves those bindings at runtime.

The database tools are read-only by design. Web fetching requires an allow-list and blocks private/link-local addresses. No manifest contains credentials, raw provider endpoints, or client-specific URLs.
