# Logout
---

Spring Security automatically sets up the `/logout` endpoint. The `GET /logout` will return a confirmation page, and the `POST /logout` will actually do logout.

In this example we just include a form that will send `POST /logout`. Usually you won't want to use the default ugly confirmation page.