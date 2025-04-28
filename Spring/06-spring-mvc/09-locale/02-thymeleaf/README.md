# Locale with Thymeleaf
---

To support internationalization in Thymeleaf, we just create the `MessageSource` bean in the Spring configuration, and 
create the messages files in the `resources` directory. Thymeleaf Template Engine uses the LocaleResolver bean
to resolve the locale.
