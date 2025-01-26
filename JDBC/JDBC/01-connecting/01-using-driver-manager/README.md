# DriverManager
---

In this project we demonstrate connecting to the DBMS using `DriverManager`. The DriverManager class automatically detects
JDBC drivers that are on the classpath and use them.

## How it works
in our `build.gradle` file, we use the JDBC driver for MySQL by including it in our dependencies:
```
implementation group: 'mysql', name: 'mysql-connector-java', version: '8.0.33'
```

When the DriverManager identifies the driver in the classpath, it automatically loads it.

More explanation in the code inside.