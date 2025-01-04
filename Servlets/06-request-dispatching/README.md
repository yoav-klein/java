# Request Dispatching
---

This project demnostrates request dispatching using the `RequestDispatcher` interface.

Request dispatching is where you have one servlet dispatching the request to another "resource" which can be 
an HTML file, a JSP or just another servlet.

In this case we dispatch requests coming to `/foo` to `/bar`.

There are 2 types of dispatchs: `forward` and `include`. In `forward`, we just forward the request to another
resource, so we don't do anything. In `include`, we include the response of the other resource in our response.

So notice that when you run `curl localhost:8080/app/foo?type=forward` you'll only get the response from Bar, not from Foo.
But when you run `curl localhost:8080/app/foo?type=include` you'll get both responses.
