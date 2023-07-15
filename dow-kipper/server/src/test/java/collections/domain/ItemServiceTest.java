package collections.domain;


import collections.data.CollectionItemRepository;
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
class ItemServiceTest {

    //Create mocked repositories
    @Mock
    ItemRepository mockItemRepository;

    @Mock
    CollectionItemRepository mockCollectionItemRepository;

    //test findAll
    @Test
    public void testFindAll() {

        //create test items
        Item testItem1 = new Item();
        Item testItem2 = new Item();

        //what should be returned when findAll is called
        when(mockItemRepository.findAll()).thenReturn(Arrays.asList(testItem1, testItem2));

        //create an instance of the service
        ItemService serviceUnderTest = new ItemService(mockItemRepository, mockCollectionItemRepository);

        List<Item> result = serviceUnderTest.findAll();

        //check result
        assertEquals(Arrays.asList(testItem1, testItem2), result);
    }

    @Test
    public void testFindById() {


        Item testItem = new Item();
        when(mockItemRepository.findById(anyInt())).thenReturn(testItem);
        ItemService serviceUnderTest = new ItemService(mockItemRepository, mockCollectionItemRepository);
        Item result = serviceUnderTest.findById(1);
        assertEquals(testItem, result);
    }

    @Test
    public void testGetItemsByCollectionId() {

        //Create test items and collection items
        CollectionItem collectionItem1 = new CollectionItem();
        CollectionItem collectionItem2 = new CollectionItem();
        Item item1 = new Item();
        item1.setValue(BigDecimal.valueOf(2000));
        Item item2 = new Item();
        item2.setValue(BigDecimal.valueOf(1500));
        collectionItem1.setItem(item1);
        collectionItem2.setItem(item2);

        when(mockCollectionItemRepository.getCollectionItemByCollectionId(anyInt())).thenReturn(Arrays.asList(collectionItem1, collectionItem2));

        //instance of service to be tested
        ItemService serviceUnderTest = new ItemService(mockItemRepository, mockCollectionItemRepository);

        List<Item> result = serviceUnderTest.getItemsByCollectionId(1);

        assertEquals(Arrays.asList(item1, item2), result);
    }

    @Test
    public void testGetCollectionItem() {

        CollectionItem testCollectionItem = new CollectionItem();
        Item testItem = new Item();
        testItem.setValue(BigDecimal.valueOf(2000));
        testCollectionItem.setItem(testItem);

        when(mockCollectionItemRepository.getCollectionItem(anyInt(), anyInt())).thenReturn(testCollectionItem);

        ItemService serviceUnderTest = new ItemService(mockItemRepository, mockCollectionItemRepository);

        Item result = serviceUnderTest.getCollectionItem(1, 1);

        assertEquals(testItem, result);
    }
}
