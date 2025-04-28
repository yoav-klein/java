# Exception Translation
---

The Spring framework, when using `JdbcTemplate`, automatically converts `SQLException`s to Spring's `DataAccessException`.

In this example, we use the overload of the `JdbcTemplate.execute` method: `execute(ConnectionCallback<T> action)`. The input parameter
is an implementation of the the `ConnectionCallback<T>` interface, which consists of one method `T withConnection(Connection conn)`, so basically
you pass in a lambda expression that takes a `Connection` object, in case you need access to the Connection object.

This can be useful if you want to work with Transactions without all the hastle of configuring Trasactions in Spring.

## Example
---
So in this example, we try to add the same user (with same ID) twice, which results in a SQLException, which is translated to:`org.springframework.dao.DuplicateKeyException`