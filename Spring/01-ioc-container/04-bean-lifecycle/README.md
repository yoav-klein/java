# Lifecycle of Beans
---

You can have initialization and destruction methods in your beans, and have the IoC container call these upon creation and destruction.

There are several methods to do this, each class in this example demonstrates one of them:
* `Dog` - By implementing the `InitializingBean` and `DisposableBean` interfaces
* `Cat` - Using the `@PostConstruct` and `@PreDestroy`
* `Elephant` - Using the `init-method` and `destroy-method` in the XML configuration file
* `Elegator` - Having a default init and destroy method names that will apply to all beans


