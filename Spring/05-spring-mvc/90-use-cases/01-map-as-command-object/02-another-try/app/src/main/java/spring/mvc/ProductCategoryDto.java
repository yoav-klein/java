package spring.mvc;

import java.util.ArrayList;
import java.util.List;

import spring.mvc.ProductCategory;

public class ProductCategoryDto {
    private List<ProductCategory> productCategories = new ArrayList<>();

    public List<ProductCategory> getProductCategories() {
        return productCategories;
    }

    public void setProductCategories(List<ProductCategory> productCategories) {
        this.productCategories = productCategories;
    }


}
