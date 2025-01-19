# Basic
---

In this project, we tackle the problem as such: We have a `ProductDto` which holds a `Map<Category, List<Product>>`. 

The problem is, that in the `update` method, we must refer to the ModelAttribute that's created by the `fullList` model attribute method.
Why? because Spring MVC will not add items to the lists (`List<Product>`) nested in the map (`Map<Category, List<Product>`). 
And that's why we'll get an exception. So we must refer to the Map that's already been created by the `fullList` method, and update it.

In the next example we'll see a more robust solution.

