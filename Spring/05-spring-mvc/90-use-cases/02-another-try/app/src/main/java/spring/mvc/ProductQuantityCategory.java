package spring.mvc;

import java.util.List;
import java.util.ArrayList;

public class ProductQuantityCategory {
    private List<ProductQuantity> productQuantities = new ArrayList<>();

    public List<ProductQuantity> getProductQuantities() {
        return productQuantities;
    }

    public void setProductQuantities(List<ProductQuantity> productQuantities) {
        this.productQuantities = productQuantities;
    }

}
