# Fine-tuning @Autowired with @Primary and @Qualifier
---

Basically, @Autowire is a type match thing. It matches your fields with other beans by the type.
If there's more than one bean of the same type, we need to use either @Primary or @Qualifier to 
sort thing out.

The following example demonstrates the declaration of 4 beans of the same type, each representing a family member. 
We  use primary and qualifiers to match Resident beans to Resident fields in the House bean.

