# Character streams
---

Java stores character values using the Unicode convention. Character streams help translate
this internal representation using some encoding system. By default, it's the default 
character set of the system it's running on, but this can be configured. (See the DefaultCharset program).

The default on Windows is usually `windows-1252`. This is why if you try to write, using a Character stream, 
a hebrew letter, you'll get gibrish. This is because there is no representation for these characters in this
character set.

The `FileWriter` and `FileReader` classes use the default charset, so you can't use those for hebrew.

The `Hebrew.java` program writes the letter "Vav" to a file. For this, we can't use the `FileWriter` class, 
but we need to use the `OutputStreamWriter` class that takes a charset as a constructor argument.




