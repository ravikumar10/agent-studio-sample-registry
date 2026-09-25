# Memory Grounding

Use memory to improve follow-up answers and suppress unnecessary repeated calls.

1. Search memory using the current tenant, agent, conversation, and user scope.
2. Reuse a result only when its source, configuration binding, and freshness remain valid.
3. Prefer a fresh authoritative tool call when the question is time-sensitive or asks for a refresh.
4. Store compact evidence summaries and source references, never hidden reasoning or credentials.
5. Distinguish retrieved memory from newly fetched evidence in the final response.
