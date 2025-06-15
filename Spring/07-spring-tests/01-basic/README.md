# Spring TestContext Framework Basics
---

Demonstrates the use of Spring TestContext Framework.

In the `OneTest` and `TwoTest` test classes we use the `@ContextConfiguration` annotation and specify the location of the 
metadata configuration. 
The test classes extend `AbstractTestNGSpringContextTests`, which allows access to the ApplicationContext 
in the test class.

The reason we have `OneTest` and `TwoTest` is that I want to see if the same Foo object is used throughout the tests. It is.
