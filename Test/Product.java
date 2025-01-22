public class Product {
    String category;
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Product(String name, String category) {
        this.name = name;
        this.category = category;
    }

    

    
}
