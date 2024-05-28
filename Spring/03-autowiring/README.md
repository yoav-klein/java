# Autowiring
---

Autowiring allows you to wire dependencies to a bean without having to specify explicitly its dependencies in the configuration metadata.

Autowiring can be either done by:
1. byName - if a bean has a property name that matches a bean name, they're autowired
2. byType - if a bean has a property of a certain type and there's only one bean of that type, they're autowired
3. constructor - like 2, but is passed to the constructor instead of a setter method.

`Server` demonstrates 3, `HTTPServer` demonstrates 2.

