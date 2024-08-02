# Locale Interceptor
---

In this example, we use a `LocaleChangeInterceptor` to change the locale of the request based on the `lang` request parameter.
Additionally, we use the `SessionLocaleResolver`, which will resolve the locale based on the Session, and not based on the 
`Accept-Language` header as the default `AcceptHeaderLocaleResolver` does.

## Usage
Browse to: `<host>:8080/app/hello`. You'll get an english page. Now browse to `<host>:8080/app/hello?lang=he` - you'll get a "hebrew" page (not really hebrew).
Note that in the current browser session, this setting will be saved. You can browse to `<host>:8080/app/hello?lang=en` again to change to english again.

