# @RequestBody and @ReseponseBody
---

Using `@RequestBody` and `@ReseponseBody` utilizes a `HttpMessageConverter` to convert between Java objects
and some other representation. In our case, we'll use `MappingJackson2HttpMessageConverter`

NOTE: We needed to add Jackson dependencies in our `build.gradle` for using `MappingJackson2HttpMessageConverter`
We need to configure this in the MVC Config (`MyWebConfig`).

## @RequestBody

The `@RequestBody` annotation is used to map a request body into a Java object.

In this case, we have the `User` class, and our `createUser` method gets a `@RequestBody` annotated argument.
This is translated into a `User` object.

## @ResponseBody

The `@ResponseBody` annotation is a method-level annotation, and it's used to cause that the return value of the method
will be translated using a `HttpMessageConverter`.

## Usage
```
# use the @RequestBody
$ curl -X POST http://localhost:8080/app/api/users/create -H "Content-Type: application/json" -d '{"name": "John Doe", "age": 30}'

# use the @ResponseBody
$ curl http://localhost:8080/app/api/users/john
```