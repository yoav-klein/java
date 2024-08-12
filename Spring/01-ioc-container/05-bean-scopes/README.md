# Bean Scopes
---

Demonstrates Bean Scopes. In a non-web application we have 2 scopes: `singleton` (which is the default) and `prototype`.

Each call to `getBean` on a singleton bean will get the same object. But each call to `getBean` on a prototype bean will get a new bean.

So we have a `House` bean, which is a singleton (because we have only one house..) and a `Meal` bean, which is a prototype, because 
a meal is a one-time thing.