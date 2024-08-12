# View Resolvers
---

Spring MVC uses _View Resolvers_ in order to map between logical view names returned by `@RequestMapping` methods (as String)
to actual View objects.

The default one used in Spring MVC is `InternalResourceViewResolver`. In this example, we configure this resolver
in the MVC Config (MyWebConfig.java) to add a prefix and suffix so that when a method returns a name (e.g. `hellopage`), 
a prefix and suffix will be added to this String and this will be the actual resource that the request will be forwarded to.