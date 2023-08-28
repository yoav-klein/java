
# Object streams
---

This example deeps dive to Object streams.
In particular, we want to see what happens when you write the same object twice.

So basically when you write the same object twice to the same object stream, 
the object is written only once, and the reference is written twice.

So in `Reader.java`, we read the same object twice.

However, in `ReaderTwoStreams.java`, we read using 2 different streams, in this case we are creating 2 separate objects.