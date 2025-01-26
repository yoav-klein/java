package org.example;

import java.util.List;

public interface ItemDAO {
    public List<Item> getAllItems();
    public void addItem(Item item);
    public void deleteItem(Item item);
   /*  public Item getItemByName(String name); */
}
