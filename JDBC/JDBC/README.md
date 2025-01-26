# JDBC
---

JDBC is an API that allows interacting with databases (mostly relational databases) in an interoperable way.\
You use the same interfaces to interact with any concrete database. The adaptation is done using _JDBC Drivers_, which are
provided by the DBMS vendors.


## Prerequisites
For the following demonstrations, we'll need a MySQL server running, with a database named `users`:

```
CREATE DATABASE users;
```

Inside, we need a table `users` with some data:
```
CREATE TABLE users(id INTEGER PRIMARY KEY, name VARCHAR(255));
insert into USERS values (1, 'Yoav');
insert into USERS values (2, 'Dikla');

```