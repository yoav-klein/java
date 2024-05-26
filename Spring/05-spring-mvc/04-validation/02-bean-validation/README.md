# Validation using Java Bean Validation
---

Spring provides support in Jakarta _Java Bean Validation_ specification.
We use the constraints annotations like @Min, @Max, @Size, etc in the model classes themselves to impose constraints.

For this, we need to create a bean of type `LocalValidatorFactoryBean`. We then autowire it to our Controller class.
