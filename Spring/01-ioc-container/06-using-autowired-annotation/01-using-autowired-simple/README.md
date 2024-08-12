# Simple
---

In this example, we use @Autowired in the HTTP class. 

We demonstrate the following features:
1. Using `@Autowired` on a field
2. Using `@Autowired` on an array will wire all the beans with the same type to the array.
3. Using `@Autowired` on a constructor will wire the constructor arguments to managed beans
4. Using `@Autowired` on a setter method

Also, we demonstrate the use of `primary` to declare one TCP bean as primary, so there's no ambiguity in the HTTP class
to which bean to wire.