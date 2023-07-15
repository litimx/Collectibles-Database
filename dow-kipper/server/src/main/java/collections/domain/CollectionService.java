package collections.domain;

import collections.data.CollectionItemRepository;
import collections.data.CollectionRepository;
import collections.models.Collection;
import collections.models.CollectionItem;
import collections.models.Item;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CollectionService {
    private final CollectionRepository collectionRepository;
    private final CollectionItemRepository collectionItemRepository;

    public CollectionService(CollectionRepository collectionRepository, CollectionItemRepository collectionItemRepository) {
        this.collectionRepository = collectionRepository;
        this.collectionItemRepository = collectionItemRepository;
    }

    //get all collections by user id
    public List<Collection> getCollectionsByUserId(int userId) {
        return collectionRepository.findAllByUserId(userId);
    }

    //get collection by id
    public Collection getCollectionById(int collectionId) {
        return collectionRepository.findById(collectionId);
    }

    //add new collection
    public Result<Collection> addCollection(Collection collection) {
        Result<Collection> result = validate(collection);
        if (!result.isSuccess()) {
            return result;
        }

        if (collection.getId() != 0) {
            result.addMessage("CollectionId cannot be set for `add` operation", ResultType.INVALID);
            return result;
        }

        collection = collectionRepository.addCollection(collection);
        result.setPayload(collection);
        return result;
    }

    //update existing collection
    public Result<Collection> updateCollection(Collection collection) {
        Result<Collection> result = validate(collection);
        if (!result.isSuccess()) {
            return result;
        }

        if (collection.getId() <= 0) {
            result.addMessage("CollectionId must be set for update", ResultType.INVALID);
            return result;
        }

        if (!collectionRepository.updateCollection(collection)) {
            String msg = String.format("CollectionId: %s, not found", collection.getId());
            result.addMessage(msg, ResultType.NOT_FOUND);
        }
        collection.setValue(getCollectionValue(collection.getId()));
        return result;
    }

    //delete a collection
    public boolean deleteCollection(int collectionId) {
        return collectionRepository.deleteCollection(collectionId);
    }

    //get the total value of a user's collection
    public BigDecimal getPortfolioValue(int userId) {
        List<Collection> collections = collectionRepository.findAllByUserId(userId);
        return collections.stream()
                .map(Collection::getValue)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    //get collection value
    public BigDecimal getCollectionValue(int collectionId){
        List<CollectionItem> collectionItems = collectionItemRepository.getCollectionItemByCollectionId(collectionId);
        return collectionItems.stream().map(CollectionItem::getItem)
                .map(Item::getValue)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    //validation
    private Result<Collection> validate(Collection collection) {
        Result<Collection> result = new Result<>();
        if (collection == null) {
            result.addMessage("Collection cannot be null", ResultType.INVALID);
            return result;
        }

        if (collection.getName() == null || collection.getName().trim().isEmpty()) {
            result.addMessage("Name is required", ResultType.INVALID);
        }

        return result;
    }
}
