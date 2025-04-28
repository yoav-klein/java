package my.spring;

public class ProdDataSource implements DataSource {
    @Override
    public void printDataSource() {
        System.out.println("I am Prod data source");
    }
}
