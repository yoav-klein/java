# JDBC
---

This small application demonstrates the use of JDBC.

JDBC is an API that allows interacting with databases (mostly relational databases) in an interoperable way.\
You use the same interfaces to interact with any concrete database. The adaptation is done using _JDBC Drivers_, which are
provided by the DBMS vendors.

## Prerequisites
To run this application, we need a MySQL server with a database named `users`, with a table `users` inside.\
Basically, just run these commands:

```
CREATE TABLE users(id INTEGER PRIMARY KEY, name VARCHAR(255));
insert into USERS values (1, 'Yoav');
insert into USERS values (2, 'Dikla');

```

## How it works
in our `build.gradle` file, we use the JDBC driver for MySQL by including it in our dependencies:
```
implementation group: 'mysql', name: 'mysql-connector-java', version: '8.0.33'
```

When the DriverManager identifies the driver in the classpath, it automatically loads it.

More explanation in the code inside.