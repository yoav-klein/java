# Simple
---

We have the package `com.example.app` with the class `First`.

Compile:
```
$ javac com/example/app/First.java
```

This will create the `First.class` file in `com/example/app`

Now in order to run it, stand in this directory and run:
```
$ java com.example.app.First
```

The classpath is the current directory. The path to the class is constructed 
by adding the CLASSPATH and the package name. So the CLASSPATH is the current directory
(since the current directory is always part of the CLASSPATH), and the package is `com.example.app`,
so the class files will be searched in `CLASSPATH/com/example/app`

