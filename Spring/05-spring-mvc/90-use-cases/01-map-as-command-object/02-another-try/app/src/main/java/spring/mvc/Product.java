package spring.mvc;

public class Product {
    private String name;
    private int id;
    private Category category;
    
    private static int counter = 0;

    private static int generateId() {
        return counter++;
    }
    
    public Product(String name, Category category) {
        this.name = name;
        this.category = category;
        this.id = generateId();
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
