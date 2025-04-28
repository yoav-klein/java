package spring.mvc;

public class Product {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Product() {}

    public Product(String name) {
        this.name =  name;
    }
    
}
