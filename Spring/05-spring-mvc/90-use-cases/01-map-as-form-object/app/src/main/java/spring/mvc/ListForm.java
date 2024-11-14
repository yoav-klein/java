package spring.mvc;

import java.util.Map;

import spring.mvc.Category;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

public class ListForm {
    
    private Map<Category, List<String>> list;

    public ListForm() {
        list = new HashMap<>();

        List<String> vegetables = new ArrayList<>();
        vegetables.add("Tomato");
        vegetables.add("Cucumber");
        list.put(Category.VEGETABLES, vegetables);

        List<String> fruit = new ArrayList<>();
        fruit.add("Orange");
        fruit.add("Strawberry");
        list.put(Category.FRUITS, fruit);
    }

    public void setList(Map<Category, List<String>> list) {
        this.list = list;
    }

    public Map<Category, List<String>> getList() {
        return this.list;
    }

    public void print() {
        for(Map.Entry<Category,List<String>> entry : this.list.entrySet()) {
            System.out.println(entry.getKey());
            System.out.println("====");

            for(String item : entry.getValue()) {
                System.out.println(item);
            }
        }

    }
}
