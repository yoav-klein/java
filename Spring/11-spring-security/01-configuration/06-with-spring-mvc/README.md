# Custom AuthorizationManager
---

If you want a more fine-grained access control, you can implement a `AuthorizationManager` and register it
for specific URLs.

In this case, we register a `AuthorizationManager` for the `/monkey` path which simply rejects all requests.