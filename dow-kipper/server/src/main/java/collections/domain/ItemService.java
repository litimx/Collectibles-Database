package collections.domain;

import collections.data.CollectionItemRepository;
import collections.data.ItemRepository;
import collections.models.CollectionItem;
import collections.models.Item;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemService {
    private final ItemRepository itemRepository;

    private final CollectionItemRepository collectionItemRepository;

    public ItemService(ItemRepository itemRepository, CollectionItemRepository collectionItemRepository) {
        this.itemRepository = itemRepository;
        this.collectionItemRepository = collectionItemRepository;
    }

    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    public Item findById(int itemId) {
        return itemRepository.findById(itemId);
    }

    public Result<Item> addItem(Item item) {
        Result<Item> result = validate(item);
        if (!result.isSuccess()) {
            return result;
        }

        if (item.getId() != 0) {
            result.addMessage("ItemId cannot be set for `add` operation", ResultType.INVALID);
            return result;
        }

        item = itemRepository.addItem(item);
        result.setPayload(item);
        return result;
    }

    public Result<Item> updateItem(Item item) {
        Result<Item> result = validate(item);
        if (!result.isSuccess()) {
            return result;
        }

        if (item.getId() <= 0) {
            result.addMessage("Item Id must be set for `update` operation", ResultType.INVALID);
            return result;
        }

        if (!itemRepository.updateItem(item)) {
            String msg = String.format("ItemId: %s, not found", item.getId());
            result.addMessage(msg, ResultType.NOT_FOUND);
        }

        return result;
    }

    public boolean deleteItem(int itemId) {
        return itemRepository.deleteItem(itemId);
    }

    public List<Item> getItemsByCollectionId(int collectionId){
        return collectionItemRepository.getCollectionItemByCollectionId(collectionId)
                .stream().map(CollectionItem::getItem).collect(Collectors.toList());
    }

    public Item getCollectionItem(int collectionId, int itemId){
        CollectionItem collectionItem = collectionItemRepository.getCollectionItem(collectionId,itemId);
        return collectionItem.getItem();
    }
    private Result<Item> validate(Item item) {
        Result<Item> result = new Result<>();
        if (item == null) {
            result.addMessage("Item cannot be null", ResultType.INVALID);
            return result;
        }

        if (item.getName() == null || item.getName().trim().isEmpty()) {
            result.addMessage("Name is required", ResultType.INVALID);
        }

        if (item.getValue().compareTo(BigDecimal.ZERO) < 0) {
            result.addMessage("Price cannot be negative", ResultType.INVALID);
        }

        return result;
    }
}
