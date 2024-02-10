# Character streams
---

Java stores character values using the Unicode convention. Character streams help translate
this internal representation using some encoding system. FileWriter uses the default 
character set of the system it's running on. If you need to use a different encoding,
use `OutputStreamWriter`.

The default on Windows is usually `windows-1252`. (See the `DefaultCharset.java` program). This is why if you try to write a hebrew letter 
using FileWriter you'll get gibrish. This is because there is no representation for these characters in this
character set.

The `FileWriter` and `FileReader` classes use the default charset, so you can't use those for hebrew.

The `Hebrew.java` program writes a String in hebrew to a file. There is hebrew characters in the source code, 
so you need to compile it as such:
```
$ java -encoding UTF-8 Hebrew.java
```



