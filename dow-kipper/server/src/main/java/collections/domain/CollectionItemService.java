package collections.domain;

import collections.data.CollectionItemRepository;
import collections.data.CollectionRepository;
import collections.data.ItemRepository;
import collections.models.CollectionItem;
import collections.models.Item;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CollectionItemService {
    private final CollectionItemRepository repository;
    private final CollectionRepository collectionRepository;
    private final ItemRepository itemRepository;

    public CollectionItemService(CollectionItemRepository repository, CollectionRepository collectionRepository, ItemRepository itemRepository) {
        this.repository = repository;
        this.collectionRepository = collectionRepository;
        this.itemRepository = itemRepository;
    }

    public BigDecimal getCollectionValue(int collectionId){
        List<CollectionItem> collectionItems = repository.getCollectionItemByCollectionId(collectionId);
        return collectionItems.stream().map(CollectionItem::getItem)
                .map(Item::getValue)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public List<CollectionItem> getCollectionItemsByCollectionId(int collectionId){
        return repository.getCollectionItemByCollectionId(collectionId);
    }

    public Result<CollectionItem> addItemToACollection(CollectionItem collectionItem){
        Result<CollectionItem> result = validate(collectionItem);
        boolean isValid;
        if(!result.isSuccess()){
            return result;
        }

        isValid = repository.add(collectionItem);
        if(isValid){
            result.setPayload(collectionItem);
        }
        return result;
    }

    public boolean deleteItemFromCollection(int collectionId, int itemId){
        return repository.delete(collectionId,itemId);
    }
    public Result<CollectionItem> updateItemInCollection(CollectionItem collectionItem){
        Result<CollectionItem> result = validate(collectionItem);
        if(!result.isSuccess()){
            return result;
        }
        if(collectionItem.getCollection().getId() <= 0 || collectionItem.getItem().getId() <= 0){
            result.addMessage("Collection Id and Item Id must be set for update",ResultType.INVALID);
        }
        if(!repository.update(collectionItem)){
            result.addMessage("Item not found in that collection",ResultType.NOT_FOUND);
        }
        return result;
    }

    private Result<CollectionItem> validate(CollectionItem collectionItem){
        Result<CollectionItem> result = new Result<>();
        if(collectionItem == null){
            result.addMessage("Collection Item can not be null", ResultType.INVALID);
            return result;
        }
        if(collectionItem.getCollection() == null){
            result.addMessage("The collection must exist", ResultType.INVALID);
        }
        if(collectionItem.getItem() == null){
            result.addMessage("The item must exist", ResultType.INVALID);
        }
        if(collectionItem.getListedPrice().compareTo(BigDecimal.ZERO) < 0){
            result.addMessage("Price must be 0.00 or higher", ResultType.INVALID);
        }

        return result;
    }
}
