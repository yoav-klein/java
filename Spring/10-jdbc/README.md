# Spring JDBC
---

This folder includes examples of Spring JDBC support.

In order to run these examples, you need to have a MySQL database, and run the following queries there:

```
CREATE DATABASE test;
USE test;

CREATE TABLE users(id INTEGER PRIMARY KEY, name VARCHAR(100) NOT NULL);
INSERT INTO users VALUES(1, 'Yoav');
INSERT INTO users VALUES(2, 'Dikla');
INSERT INTO users VALUES(3, 'Tamar');
INSERT INTO users VALUES(4, 'Roni');

```
