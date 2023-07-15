package collections.data;

import collections.models.Item;

import java.util.List;

public interface
ItemRepository {
    List<Item> findAll();
    public Item findById(int itemId);
    public Item addItem(Item item);
    public boolean updateItem(Item item);
    public boolean deleteItem(int itemId);
}
