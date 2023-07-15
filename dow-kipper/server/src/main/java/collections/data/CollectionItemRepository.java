package collections.data;

import collections.models.Collection;
import collections.models.CollectionItem;

import java.util.List;

public interface CollectionItemRepository {

    boolean add(CollectionItem collectionItem);

    boolean update(CollectionItem collectionItem);

    boolean delete(int collectionId, int itemId);

    CollectionItem getCollectionItem(int collectionId, int itemId);

    List<CollectionItem> getCollectionItemByCollectionId(int collectionId);
}
