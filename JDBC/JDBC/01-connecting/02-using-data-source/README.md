# DataSource
---

Using the DataSource interface is the preferred way of connecting to a DBMS.
A `DataSource` object represents a Database.

## How it works
We create an object of type `MysqlDataSource` which implements the `DataSource` interface, providing all the necessary information
like URL, username and password. Our application then uses this object to create connections.