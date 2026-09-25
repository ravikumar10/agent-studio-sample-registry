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

## Publishing lifecycle in Agent Studio

Repository content is not made executable merely because it appears in `catalog.json`. Studio uses an explicit lifecycle:

1. **Sync** reads `catalog.json` and stores matching artifact metadata as `DISCOVERED`.
2. **Pull** downloads only the selected versioned manifest. For MCP entries it also retrieves the referenced JSON configuration schema.
3. **Promote** materializes an agent draft, available skill, or logical MCP capabilities in the organization catalog.
4. A promoted MCP creates a schema-driven integration type, disabled provider profile, and disabled capability bindings.
5. An operator configures the named provider, supplies encrypted credentials, deploys the reviewed implementation, verifies health, and explicitly enables its bindings.

Re-pull an MCP artifact before promoting it when its manifest or configuration schema has changed. Running executions remain pinned to their immutable agent version.

## Adding an artifact

### MCP server

1. Add buildable source under `implementations/<server>/` with a Dockerfile and health endpoint.
2. Add `mcp/<server>/server.json` declaring logical tool names, descriptions, input/output schemas, transport, implementation source/image, side-effect and risk metadata, and `configurationSchema` when configuration is needed.
3. Add `configurations/<server>.schema.json`. Mark credentials with `"writeOnly": true`; never add credential defaults or examples containing real values.
4. Add the versioned MCP entry to `catalog.json`.
5. Build and test the implementation locally, validate all JSON, and verify that every declared operation exists.

### Skill

1. Add `skills/<skill>/SKILL.md` containing reusable instructions, prerequisites, capability expectations, safety constraints, and output expectations.
2. Add the versioned `SKILL` entry to `catalog.json`.
3. Keep skills provider-neutral and free of endpoints, credentials, and hidden reasoning requirements.

### Agent

1. Add `agents/<agent>/agent.json` with ID/version, interface, topology, trigger intent, logical model profile, ordered logical capabilities, and skills.
2. Ensure every referenced capability and skill is published in this or another approved registry.
3. Add the versioned `AGENT` entry to `catalog.json`.
4. Promotion imports the agent as a draft; review and activate it in Studio rather than declaring it live in the repository.

## Pull-request checklist

- [ ] IDs are stable, lowercase, and unique; versions are immutable.
- [ ] `catalog.json` and every referenced JSON document parse successfully.
- [ ] All paths are repository-relative and contain no traversal segments.
- [ ] Manifests bind logical capabilities, never deployment URLs or model endpoints.
- [ ] No token, password, connection string, private key, or customer data is committed.
- [ ] Side-effect tools declare risk accurately and document required approvals.
- [ ] Database operations are read-only unless a separately governed write capability is intentional.
- [ ] HTTP/browser implementations reject loopback, private, link-local, and metadata targets.
- [ ] Container images are scanned, signed, and pinned by digest before production activation.
- [ ] Source, schema, manifest, and README examples agree.

The platform must never dynamically load arbitrary repository JARs into the control plane or a shared runtime JVM. Custom implementations run in isolated containers behind the common invocation contract.
