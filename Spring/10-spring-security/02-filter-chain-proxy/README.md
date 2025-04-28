# FilterChainProxy
---

This project builds on the previous.

FilterChainProxy is the delegate of `DelegatingFilterProxy` (i.e. DelegatingFilterProxy delegates to FilterChainProxy).
FilterChainProxy holds a list of `SecurityFilterChain`s. Each such `SecurityFilterChain` can receive a HttpServletRequest and
decide whether or not it needs to filter this request. Additionally, it holds a chain of Filters that will be applied if
it decides that the request matches this SecurityFilterChain.

In this example, the `targetBeanName` that we pass to `DelegatingFilterProxy` is the bean of `FilterChainProxy`.
Then, we configure `FilterChainProxy` with a `SecurityFilterChain` (`security:filter-chain`). 

The SecurityFilterChain is configured to intercept all requests by the uri-pattern, and apply 2 Filters: `firstFilter` and `secondFilter`. These
are simple filters that just add some text to the response.

## Important note
---
This is not the way we'll work with `FilterChainProxy` in the everyday. In the next example, we'll see how `FilterChainProxy` is automatically
created as a bean and configured with `SecurityFilterChain` when including the appropriate elements in the `app-context.xml` file.


