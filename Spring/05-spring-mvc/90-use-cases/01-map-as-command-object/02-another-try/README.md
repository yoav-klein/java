# Using a List of Lists
---

In this project, instead of using a `Map<Category, List<Product>>`, we use an auxilary class - `ProductCategory` - which holds a Category and a `List<Product>`.
Our `ProductCategoryDto` then holds a list of these objects, each representing a category.

Then, we use another class as the form-backing bean (or: command object), which is `ProductQuantityDto`.

There is a slight workaround here. I would love having the `ProductQuantityDto` just hold a `List<ProductQuantity>`, but that's not possible
since in the template I iterate using 2 loops: one iterates over the `List<ProductCategory>`, and the inner iterates over the `List<Product>`, and 
I don't have a way to have one index that keeps of all the `Products`. (hope that's clear..). That's why I had to split the `List<ProductQuantity>` into a nest list also.