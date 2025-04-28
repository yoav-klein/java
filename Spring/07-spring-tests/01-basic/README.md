# Spring TestContext Framework Basics
---

Demonstrates the use of Spring TestContext Framework.

In the `AppTest` test class we use the `@ContextConfiguration` annotation and specify the location of the 
metadata configuration. 
The AppTest class extends `AbstractTestNGSpringContextTests`, which allows access to the ApplicationContext 
in the test class.
