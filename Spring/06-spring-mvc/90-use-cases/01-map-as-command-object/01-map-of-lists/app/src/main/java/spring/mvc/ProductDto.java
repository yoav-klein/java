package spring.mvc;

import java.util.Map;

import spring.mvc.Category;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

public class ProductDto {
    
    private Map<Category, List<Product>> list;

    public ProductDto() {
        list = new HashMap<>();
        
    }

    public void setList(Map<Category, List<Product>> list) {
        this.list = list;
    }

    public Map<Category, List<Product>> getList() {
        return this.list;
    }

    public void print() {
        for(Map.Entry<Category,List<Product>> entry : this.list.entrySet()) {
            System.out.println(entry.getKey());
            System.out.println("====");

            for(Product item : entry.getValue()) {
                System.out.println(item.getName());
            }
        }

    }
}
