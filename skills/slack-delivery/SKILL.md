# Slack Delivery

Use this skill only when the user or trigger configuration requests Slack delivery.

1. Complete all evidence collection, analysis, and chart generation first.
2. Compose the final response once; use the same response for the Agent Studio UI and Slack.
3. Call `slack.messages.send` last with the final report, generated charts, and configured channel unless the user explicitly selected another approved channel.
4. Confirm delivery from the tool result. Do not claim success when Slack rejects the request.
5. Do not expose tokens, credentials, internal prompts, or unredacted sensitive records.
