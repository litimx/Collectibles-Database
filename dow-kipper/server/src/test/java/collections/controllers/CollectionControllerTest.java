package collections.controllers;

import collections.domain.*;
import collections.models.Collection;
import collections.security.AppUser;
import collections.security.JwtConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CollectionControllerTest {

    @Mock
    private CollectionController collectionController;
    private CollectionService collectionService;
    private CollectionItemService collectionItemService;

    private ItemService itemService;
    private JwtConverter jwtConverter;
    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        collectionController = new CollectionController(collectionService, collectionItemService, itemService, jwtConverter);
    }

    @Test
    void testGetCollectionsByUserId() {
        //given a mock collection...
        Collection collection = new Collection();
        //when getCollectionByUserId is called, return mock collection
        when(collectionService.getCollectionsByUserId(anyInt())).thenReturn(Arrays.asList(collection));

        //call method under test
        List<Collection> collections = collectionController.getCollectionsByUserId(1);

        //assert we get back expected collection...
        assertEquals(1, collections.size());
        assertEquals(collection, collections.get(0));
    }

    @Test
    void testGetCollectionById() {
        Collection collection = new Collection();
        when(collectionService.getCollectionById(anyInt())).thenReturn(collection);

        ResponseEntity<Collection> response = collectionController.getCollectionById(1);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(collection, response.getBody());
    }

    @Test
    void testGetCollectionByIdNotFound() {
        when(collectionService.getCollectionById(anyInt())).thenReturn(null);

        ResponseEntity<Collection> response = collectionController.getCollectionById(1);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testAddCollection() {
        Collection collection = new Collection();
        AppUser user = new AppUser(1,"test","@1password",true, Collections.singletonList("USER"));
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJjb2xsZWN0aW9ucyIsInN1YiI6ImlhbkB0ZXN0LmNvbSIsImFwcF91c2VyX2lkIjoxLCJhdXRob3JpdGllcyI6IlVTRVIiLCJleHAiOjE2ODg1NzAwOTd9.YnBKyhM33FOPN43Q1CNpmqd8IaciOcyZ3PDoZcAdhig";
        Result<Collection> result = new Result<>();
        result.setPayload(collection);
        when(collectionService.addCollection(any(Collection.class))).thenReturn(result);

        ResponseEntity<Object> response = collectionController.addCollection(collection,token);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(collection, response.getBody());
    }

    @Test
    void testAddCollectionBadRequest() {
        Collection collection = new Collection();
        Result<Collection> result = new Result<>();
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJjb2xsZWN0aW9ucyIsInN1YiI6ImlhbkB0ZXN0LmNvbSIsImFwcF91c2VyX2lkIjoxLCJhdXRob3JpdGllcyI6IlVTRVIiLCJleHAiOjE2ODg1NzAwOTd9.YnBKyhM33FOPN43Q1CNpmqd8IaciOcyZ3PDoZcAdhig";
        result.addMessage("Test error message");
        when(collectionService.addCollection(any(Collection.class))).thenReturn(result);

        ResponseEntity<Object> response = collectionController.addCollection(collection,token);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(Collections.singletonList("Test error message"), response.getBody());
    }

    @Test
    void testUpdateCollection() {
        Collection collection = new Collection();
        collection.setId(1);
        Result<Collection> result = new Result<>();
        when(collectionService.updateCollection(any(Collection.class))).thenReturn(result);

        ResponseEntity<Object> response = collectionController.updateCollection(1, collection);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void testUpdateCollectionConflict() {
        Collection collection = new Collection();
        collection.setId(2);

        ResponseEntity<Object> response = collectionController.updateCollection(1, collection);
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }

    @Test
    void testUpdateCollectionNotFound() {
        Collection collection = new Collection();
        collection.setId(1);
        Result<Collection> result = new Result<>();
        result.addMessage("Not found", ResultType.NOT_FOUND);
        when(collectionService.updateCollection(any(Collection.class))).thenReturn(result);

        ResponseEntity<Object> response = collectionController.updateCollection(12, collection);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testUpdateCollectionBadRequest() {
        Collection collection = new Collection();
        collection.setId(1);
        Result<Collection> result = new Result<>();
        result.addMessage("Test error message");
        when(collectionService.updateCollection(any(Collection.class))).thenReturn(result);

        ResponseEntity<Object> response = collectionController.updateCollection(13, collection);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(Collections.singletonList("Test error message"), response.getBody());
    }


    @Test
    void testDeleteCollection() {
        when(collectionService.deleteCollection(anyInt())).thenReturn(true);

        ResponseEntity<Void> response = collectionController.deleteCollection(1);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void testDeleteCollectionNotFound() {
        when(collectionService.deleteCollection(anyInt())).thenReturn(false);

        ResponseEntity<Void> response = collectionController.deleteCollection(12);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
