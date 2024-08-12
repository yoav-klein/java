# Internationalization
---

In this directory we demonstrate internationalization.

## How it Works
---
We have a default `LocaleResolver` - which is the `AcceptHeaderLocaleResolver`. This resolves locale based on the `Accept-Language` request header.
In order to support multiple languages, we create _Messages_ files, one for each language. In order to get these messages files, we create a Bean
that returns a `MessageSource` instance. In this case, we use the `ResourceBundleMessageSource` implementation, which gets messages from the `resources` directory.

## Usage
---

If you run:
```
$ curl <host>:8080/app/hello
Hello
```

You'll get the default language.

Now run:
```
$ curl -H "Accept-Language: he" <host>:8080/app/hello
Shalom
```