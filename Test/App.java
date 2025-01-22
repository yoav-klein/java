

import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Arrays;

class App {

    static public void main(String[] args) {

        Map<String, List<Product>> map = new HashMap<>();

        List<ProductCategory> productCategoryList = new ArrayList<>();

        map.put("VEGETABLES", new ArrayList<Product>(Arrays.asList(
            new Product("Cucmber", "VEGETABLES"),
            new Product("Tomato", "VEGETABLES"),
            new Product("Lattus", "VEGETABLES")
        )));

        map.put("VEGETABLES", new ArrayList<Product>(Arrays.asList(
            new Product("Ornage", "FRUIT"),
            new Product("Strawberry", "FRUIT"),
            new Product("Apple", "FRUIT")
        )));

        map.put("PROTEIN", new ArrayList<Product>(Arrays.asList(
            new Product("Eggs", "PROTEIN"),
            new Product("Chicken", "PROTEIN"),
            new Product("Meat", "PROTEIN")
        )));
        
        map.forEach((k, v) -> {
            productCategoryList.add(new ProductCategory(k, v));
        });

        System.out.println(productCategoryList.get(0).getCategory());
    }
}