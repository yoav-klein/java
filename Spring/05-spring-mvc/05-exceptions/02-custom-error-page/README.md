# Custom Error Page
---

By default, if an exception is not handled by your application and is thrown, the servlet container will display
a default error page. You can customize this error page by including `<error-page>` sections in the `web.xml` file.

You can set an error page for specific status codes (404, 500, etc.) or specific exceptions. You can also set
a default error page that will be used by any exception or status code.