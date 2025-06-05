# PropertySource
---

PropertySources are, as they sound, sources of properties.

The default property sources are environment variables and JVM properties.
You can also use the `@PropertySource` annotation to configure additional property sources

In this example, we demonstrate the reading of environment variables and JVM properties using the PropertySource abstraction.
Additionally, we demonstrate configuration of a properties file as a PropertySource.