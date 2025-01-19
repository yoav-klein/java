# Map as Form-backing Bean
---

## Background
In the grocery application, I had the following situation:  I wanted a page that displays all the products that the user defined, allowing the user
to set the quantity for each product, and then click `Save`. The products had to be ordered in the page according to category.

This means that I had to pass to the Thymeleaf template a `Map<Category, List<Product>>`. If you just pass a `List<Product>`, how would you 
display it sorted by category?

The following two projects demonstrate ways to handle this situation.