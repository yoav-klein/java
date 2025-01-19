package spring.mvc;

import java.util.List;
import java.util.ArrayList;

public class ProductQuantityDto {
    private List<ProductQuantityCategory> productQuantityCategories = new ArrayList<>();

    public List<ProductQuantityCategory> getProductQuantityCategories() {
        return productQuantityCategories;
    }

    public void getProductQuantityCategories(List<ProductQuantityCategory> productQuantities) {
        this.productQuantityCategories = productQuantities;
    }

}
