# Transcation Managmenet
---

When extending the `AbstractTransactionalTestNGSpringContextTests`, you need to have a `DataSource` and a `PlatformTransactionManager` beans in the application context.
Then, in your test class, you have access to a `jdbcTemplate` object. Additionally, JDBC operations are executed in a transaction that gets rolled back.

## Prerequisites
---
You need to have the `users` table in the `test` database.