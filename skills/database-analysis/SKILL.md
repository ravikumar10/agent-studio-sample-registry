# Database Analysis

Use this skill for read-only analysis of an approved relational database.

1. Call `database.describe-schema` for only the schemas needed by the question.
2. Form a single parameterized `SELECT`; request the minimum columns and rows required.
3. Call `database.query-readonly`. Do not concatenate user input into SQL.
4. Explain the result and include table/column provenance and relevant filters.
5. Treat empty results as evidence, not as an error, and state important data-quality limits.

Never request credentials, bypass row limits, or attempt DDL/DML, stored procedures, or multiple statements.
