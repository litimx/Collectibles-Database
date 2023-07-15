package collections.controllers;

import collections.domain.ItemService;
import collections.domain.Result;
import collections.domain.ResultType;
import collections.models.Item;
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

class ItemControllerTest {

    @Mock
    private ItemService itemService;

    private ItemController itemController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        itemController = new ItemController(itemService);
    }

    @Test
    void testFindAllByCollectionId() {
        //mock item
        Item item = new Item();
        //when called, return mock
        when(itemService.getItemsByCollectionId(anyInt())).thenReturn(Arrays.asList(item));

        //call method under test
        List<Item> items = itemController.findAllByCollectionId(1);

        //assert we get back expected item
        assertEquals(1, items.size());
        assertEquals(item, items.get(0));
    }

    @Test
    void testFindByCollectionAndItemId() {
        Item item = new Item();
        when(itemService.getCollectionItem(anyInt(), anyInt())).thenReturn(item);

        ResponseEntity<Item> response = itemController.findByCollectionAndItemId(1, 1);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(item, response.getBody());
    }

    @Test
    void testFindById() {
        Item item = new Item();
        when(itemService.findById(anyInt())).thenReturn(item);

        ResponseEntity<Item> response = itemController.findById(1);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(item, response.getBody());
    }

    @Test
    void testFindByIdNotFound() {
        when(itemService.findById(anyInt())).thenReturn(null);

        ResponseEntity<Item> response = itemController.findById(1);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testAddItem() {
        Item item = new Item();
        Result<Item> result = new Result<>();
        result.setPayload(item);
        when(itemService.addItem(any(Item.class))).thenReturn(result);

        ResponseEntity<Object> response = itemController.addItem(item);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(item, response.getBody());
    }

    @Test
    void testAddItemBadRequest() {
        Item item = new Item();
        Result<Item> result = new Result<>();
        result.addMessage("Test error message");
        when(itemService.addItem(any(Item.class))).thenReturn(result);

        ResponseEntity<Object> response = itemController.addItem(item);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(Collections.singletonList("Test error message"), response.getBody());
    }

    @Test
    void testUpdateItem() {
        Item item = new Item();
        item.setId(1);
        Result<Item> result = new Result<>();
        when(itemService.updateItem(any(Item.class))).thenReturn(result);

        ResponseEntity<Object> response = itemController.updateItem(1, item);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void testUpdateItemConflict() {
        Item item = new Item();
        item.setId(2);

        ResponseEntity<Object> response = itemController.updateItem(1, item);
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }

    @Test
    void testUpdateItemNotFound() {
        Item item = new Item();
        item.setId(1);
        Result<Item> result = new Result<>();
        result.addMessage("Not found", ResultType.NOT_FOUND);
        when(itemService.updateItem(any(Item.class))).thenReturn(result);

        ResponseEntity<Object> response = itemController.updateItem(1, item);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testUpdateItemBadRequest() {
        Item item = new Item();
        item.setId(1);
        Result<Item> result = new Result<>();
        result.addMessage("Test error message");
        when(itemService.updateItem(any(Item.class))).thenReturn(result);

        ResponseEntity<Object> response = itemController.updateItem(1, item);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(Collections.singletonList("Test error message"), response.getBody());
    }

    @Test
    void testDeleteItem() {
        when(itemService.deleteItem(anyInt())).thenReturn(true);

        ResponseEntity<Void> response = itemController.deleteItem(1);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void testDeleteItemNotFound() {
        when(itemService.deleteItem(anyInt())).thenReturn(false);

        ResponseEntity<Void> response = itemController.deleteItem(1);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
