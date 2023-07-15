package collections.domain;


import collections.data.CollectionItemRepository;
import collections.data.CollectionRepository;
import collections.models.Collection;
import collections.models.CollectionItem;
import collections.models.Item;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CollectionServiceTest {

    //create mock repositories
    @Mock
    CollectionRepository mockCollectionRepository;

    @Mock
    CollectionItemRepository mockCollectionItemRepository;


    @Test
    public void testGetCollectionsByUserId() {
        //test collection
        Collection testCollection = new Collection();

        //what should be returned
        when(mockCollectionRepository.findAllByUserId(anyInt())).thenReturn(Collections.singletonList(testCollection));

        //instance of service to be tested
        CollectionService serviceUnderTest = new CollectionService(mockCollectionRepository, mockCollectionItemRepository);

        //call the method to be tested
        List<Collection> result = serviceUnderTest.getCollectionsByUserId(1);

        //check result
        assertEquals(Collections.singletonList(testCollection), result);
    }


    @Test
    public void testGetCollectionById() {

        Collection testCollection = new Collection();

        when(mockCollectionRepository.findById(anyInt())).thenReturn(testCollection);

        CollectionService serviceUnderTest = new CollectionService(mockCollectionRepository, mockCollectionItemRepository);

        Collection result = serviceUnderTest.getCollectionById(1);

        assertEquals(testCollection, result);
    }


    @Test
    public void testGetPortfolioValue() {

        Collection testCollection = new Collection();
        testCollection.setValue(BigDecimal.valueOf(3000));

        when(mockCollectionRepository.findAllByUserId(anyInt())).thenReturn(Collections.singletonList(testCollection));

        CollectionService serviceUnderTest = new CollectionService(mockCollectionRepository, mockCollectionItemRepository);

        BigDecimal result = serviceUnderTest.getPortfolioValue(1);

        assertEquals(BigDecimal.valueOf(3000), result);
    }


    @Test
    public void testGetCollectionValue() {

        Item testItem1 = new Item();
        testItem1.setValue(BigDecimal.valueOf(2000));
        Item testItem2 = new Item();
        testItem2.setValue(BigDecimal.valueOf(1500));
        CollectionItem testCollectionItem1 = new CollectionItem();
        testCollectionItem1.setItem(testItem1);
        CollectionItem testCollectionItem2 = new CollectionItem();
        testCollectionItem2.setItem(testItem2);
        List<CollectionItem> testCollectionItems = Arrays.asList(testCollectionItem1, testCollectionItem2);

        when(mockCollectionItemRepository.getCollectionItemByCollectionId(anyInt())).thenReturn(testCollectionItems);

        CollectionService serviceUnderTest = new CollectionService(mockCollectionRepository, mockCollectionItemRepository);

        BigDecimal result = serviceUnderTest.getCollectionValue(1);

        assertEquals(BigDecimal.valueOf(3500), result);
    }
}