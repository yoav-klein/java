# Internationalization
---

This project demonstrates solution to the following scenario: We know how to work with `MessageSource` for externalizing messages when working with Thymeleaf (or some other view technology). But let's say that we need externalizing messages for our JavaScript code. Say that your JavaScript code needs to render translated text.
Now, you don't want to duplicate the Messages that you already have defined for your Server-Side Rendering (SSR).

So the idea is to have a `/messages` endpoint that returns a JSON object with all the translations. This way you can use the saem `Messages.properties` files (resource bundles) for both SSR and frontend code.