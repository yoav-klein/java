package spring.mvc;

import java.util.ArrayList;
import java.util.List;

public class ProductCategory {
    private Category category;
    private List<Product> products = new ArrayList<>();

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Product> getProducts() {
        return products;
    }
    
    public void setProducts(List<Product> products) {
        this.products = products;
    }

    
}
