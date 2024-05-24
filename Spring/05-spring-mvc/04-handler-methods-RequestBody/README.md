# @RequestBody
---

The `@RequestBody` annotation is used to map a request body into a Java object.

In this case, we have the `User` class, and our `createUser` method gets a `@RequestBody` annotated argument.
This is translated into a `User` object. How? Using the `MappingJackson2HttpMessageConverter` which we've configured in our MVC Config (`MyWebConfig`)

NOTE: We needed to add Jackson dependencies in our `build.gradle` for using `MappingJackson2HttpMessageConverter`
