package my.spring;

public class DevDataSource implements DataSource {
    @Override
    public void printDataSource() {
        System.out.println("I am Dev data source");
    }
}
    
