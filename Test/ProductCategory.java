import java.util.List;

public class ProductCategory {
    private String category;
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    private List<Product> list;
    
    public List<Product> getList() {
        return list;
    }

    public void setList(List<Product> list) {
        this.list = list;
    }

    public ProductCategory(String category, List<Product> list) {
        this.category = category;
        this.list = list;
    }
}
