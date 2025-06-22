# HiddenHttpMethodFilter
---

Browsers only support GET and POST methods in forms and links. So, how can you use a DELETE endpoint in your backend?

For this we have the `HiddenHttpMethodFilter`. The trick is that we include a hidden input
in the form with the name `_method`. The value can then be `delete`, `patch`, or other HTTP methods.
The filter catches this and transforms the HTTP method to the specified value.