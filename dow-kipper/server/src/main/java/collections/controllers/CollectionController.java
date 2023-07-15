package collections.controllers;

import collections.domain.*;
import collections.models.Collection;
import collections.models.CollectionItem;
import collections.models.Item;
import collections.security.AppUser;
import collections.security.JwtConverter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/collection")

public class CollectionController {

    private final CollectionService collectionService;
    private final CollectionItemService collectionItemService;

    private final ItemService itemService;
    private final JwtConverter jwtConverter;

    public CollectionController(CollectionService collectionService, CollectionItemService collectionItemService, ItemService itemService, JwtConverter jwtConverter) {
        this.collectionService = collectionService;
        this.collectionItemService = collectionItemService;
        this.itemService = itemService;
        this.jwtConverter = jwtConverter;
    }

    //get collections by userId
    @GetMapping("/user/{userId}")
    public List<Collection> getCollectionsByUserId(@PathVariable int userId) {
        return collectionService.getCollectionsByUserId(userId);
    }

    //get total value of collections by user
    @GetMapping("/user/value/{userId}")
    public BigDecimal getPortfolioValueByUserId(@PathVariable int userId){
        return collectionService.getPortfolioValue(userId);
    }

    //get total value of collection
    @GetMapping("/value/{collectionId}")
    public BigDecimal getCollectionValue(@PathVariable int collectionId){
        return collectionService.getCollectionValue(collectionId);
    }

    //get collection by collection ID
    @GetMapping("/{collectionId}")
    public ResponseEntity<Collection> getCollectionById(@PathVariable int collectionId) {
        Collection collection = collectionService.getCollectionById(collectionId);
        if (collection == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(collection);
    }

    //add a new collection to system
    @PostMapping
    public ResponseEntity<Object> addCollection(@RequestBody Collection collection, @RequestHeader("Authorization") String token) {
        AppUser user = jwtConverter.getUserFromToken(token);
        collection.setUser(user);
        collection.setValue(BigDecimal.ZERO);
        Result<Collection> result = collectionService.addCollection(collection);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }
        return new ResponseEntity<> (result.getMessages(), HttpStatus.BAD_REQUEST);
    }

    //add item to collection
    @PostMapping("/item/{collectionId}/{itemId}")
    public ResponseEntity<Object> addItemToCollection(@PathVariable int collectionId,@PathVariable int itemId, @RequestBody CollectionItem collectionItem){
        Collection collection = collectionService.getCollectionById(collectionId);
        Item item = itemService.findById(itemId);
        collectionItem.setCollection(collection);
        collectionItem.setItem(item);

        if(collectionId != collectionItem.getCollection().getId() || itemId != collectionItem.getItem().getId()){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        Result<CollectionItem> result = collectionItemService.addItemToACollection(collectionItem);
        if(result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(),HttpStatus.CREATED);
        }
        return new ResponseEntity<>(result.getMessages(), HttpStatus.BAD_REQUEST);
    }

    //update collection with provided collection ID
    @PutMapping("/{collectionId}")
    public ResponseEntity<Object> updateCollection(@PathVariable int collectionId, @RequestBody Collection collection) {
        if (collectionId != collection.getId()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        Result<Collection> result = collectionService.updateCollection(collection);
        if (result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else if(result.getResultType() == ResultType.NOT_FOUND) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(result.getMessages(), HttpStatus.BAD_REQUEST);
    }

    //update item in collection
    @PutMapping("/item/{collectionId}/{itemId}")
    public ResponseEntity<Object> updateItemInCollection(@PathVariable int collectionId,@PathVariable int itemId, @RequestBody CollectionItem collectionItem){
        if(collectionId != collectionItem.getCollection().getId() || itemId != collectionItem.getItem().getId()){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        Result<CollectionItem> result = collectionItemService.updateItemInCollection(collectionItem);
        if(result.isSuccess()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else if(result.getResultType() == ResultType.NOT_FOUND){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(result.getMessages(), HttpStatus.BAD_REQUEST);
    }


    //delete collection
    @DeleteMapping("/{collectionId}")
    public ResponseEntity<Void> deleteCollection(@PathVariable int collectionId) {
        if (collectionService.deleteCollection(collectionId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //delete item from collection
    @DeleteMapping("/item/{collectionId}/{itemId}")
    public ResponseEntity<Void> deleteItemFromCollection(@PathVariable int collectionId, @PathVariable int itemId) {
        if(collectionItemService.deleteItemFromCollection(collectionId,itemId)){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
