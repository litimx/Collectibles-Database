package collections.domain;

import collections.data.CollectionItemRepository;
import collections.data.CollectionRepository;
import collections.data.ItemRepository;
import collections.models.CollectionItem;
import collections.models.Item;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CollectionItemServiceTest {

    @Mock
    CollectionItemRepository collectionItemRepository;

    @Mock
    CollectionRepository collectionRepository;

    @Mock
    ItemRepository itemRepository;

    @Test
    public void testGetCollectionValue(){
        Item item1 = new Item();
        item1.setValue(BigDecimal.valueOf(2000));
        Item item2 = new Item();
        item2.setValue(BigDecimal.valueOf(1500));
        CollectionItem collectionItem1 = new CollectionItem();
        collectionItem1.setItem(item1);
        CollectionItem collectionItem2 = new CollectionItem();
        collectionItem2.setItem(item2);
        List<CollectionItem> collectionItems = Arrays.asList(collectionItem1, collectionItem2);
        when(collectionItemRepository.getCollectionItemByCollectionId(anyInt())).thenReturn(collectionItems);

        CollectionItemService service = new CollectionItemService(collectionItemRepository, collectionRepository, itemRepository);
        BigDecimal result = service.getCollectionValue(1);
        assertEquals(BigDecimal.valueOf(3500), result);
    }

    @Test
    public void testGetCollectionItemsByCollectionId(){
        CollectionItem collectionItem1 = new CollectionItem();
        CollectionItem collectionItem2 = new CollectionItem();
        List<CollectionItem> collectionItems = Arrays.asList(collectionItem1, collectionItem2);
        when(collectionItemRepository.getCollectionItemByCollectionId(anyInt())).thenReturn(collectionItems);

        CollectionItemService service = new CollectionItemService(collectionItemRepository, collectionRepository, itemRepository);
        List<CollectionItem> result = service.getCollectionItemsByCollectionId(1);
        assertEquals(collectionItems, result);
    }
}
