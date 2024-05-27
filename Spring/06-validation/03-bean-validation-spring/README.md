# Bean Validation with Spring
---

See https://docs.spring.io/spring-framework/reference/core/validation/beanvalidation.html

In this example we demonstrate the support of Spring in Java Bean Validation.

Mainly, the support is expressed by having the `LocalValidatorFactoryBean` bean, which creates a Bean in our Spring context
that implements both `jakarta.validation.Validator` and `org.springframework.validation.Validator`

