# PreparedStatement
---

`PreparedStatement` is a subtype of `Statement` that adds the following advantages:
1. Unlike a Statement, it is given an SQL statement when it's created. In most cases, the statement will be sent to the DBMS right away
and be compiled there, so when it's executed, it's already compiled.
2. It allows you to parameterize your statements.
3. It protects you from SQL injections. The inserted arguments are never treated as SQL code, but only as values.

