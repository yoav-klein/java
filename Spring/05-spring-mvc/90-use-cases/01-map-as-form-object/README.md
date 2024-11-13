# Map as Form-backing Bean
---

## Background
In the Grocery application I had a use case in which I needed a Map as a form-backing bean.

More specifically, I had a page which the user specifies how many items he wants from each product,
and then when he submits, all these numbers need to go to the backend.

The model attribute was `Map<ProductCategory, List<Item>>`.

But it's a problem with Thymeleaf to have a Map as a form backing bean. So the solution was to wrap the map with another wrapper class.

This project is a POC of this approach.