package spring.mvc;

import java.util.List;
import java.util.ArrayList;
import org.springframework.stereotype.Service;

import spring.mvc.Category;

@Service
public class ProductService {
    List<Product> allProducts;

    ProductService() {
        allProducts = new ArrayList<>();

        addProduct(new Product("Banana", Category.FRUITS));
        addProduct(new Product("Chicken Wings", Category.PROTEIN));
        addProduct(new Product("Milk", Category.PROTEIN));
        addProduct(new Product("Spunch", Category.CLEANING_SUPPLIES));
        addProduct(new Product("Onion", Category.VEGETABLES));
    }

    List<Product> getAllProducts() {
        return allProducts;
    }

    void addProduct(Product p) {
        allProducts.add(p);
    }

    Product getProductById(int id) throws Exception {
        for(Product p : allProducts) {
            if(p.getId() == id) {
                return p;
            }
        }
        throw new Exception("no such product");
    }
    
}
