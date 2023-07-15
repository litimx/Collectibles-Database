# DOW KIPPER - Capstone Checklist

Developers: Ian Ouyoung, Xi-Ping Chen, Tim Li

## Learning Goal: TYPESCRIPT

## Tasks

* [ ] Build a full stack java project, including backend, sql data, front end configuration (react & fetch API). 
On a high level, this project catalogs a user's hobby items based off of eBay completed item prices and calculates the "hobby portfolio" value. 
  * [x] Create a detailed proposal checklist
* [x] Review the requirements 
* [x] Identify any research to be done
* [ ] Complete backend and implemnt by Tuesday
* [ ] TypeScript has a conversion command


### Part 1: Project Setup, Database Design, and PriceCharting API (Est 8 Hours)

* [X] Research pricecharting.com APIs and understand how they can be utilized (3 Hours)
  * [X] Use the "fetchItemId", "fetchItemKeyword", and "fetchItemSoldHistory" operations (1 Hour)
  * [X] Pass the id of item(s) of interest to the id parameter of the fetchitemid/fetchitemsoldhistory operations to filter detailed look for each item (0.5 Hour)
  * [X] Display sold history results to display pricing over time for item(s) of interest. (0.5 Hour)
  * [X] Calculate the average sold price by dividing the total sum of prices by the count of items  (1 Hour)
* [X] Initialize Java project (backend) (1 Hour)
* [X] Identify database tables and their relationships (1 Hour)

### Part 2: Database Connection & Model Creation (Est 5 Hours)

* [x] Setup a SQL database and connect to java app (2.5 Hours)
  * [x] Create data models in Java according to database (2.5 Hours)

### Part 3: PriceTracking API Integration (Est 3 Hours)

* [x] Write the logic to fetch data from API (3 Hours)

### Part 4: Implement CRUD (Est 25 Hours Total)
* [x] Revised our Data Schema to account for a many to many relationship between Collections and Items
   * [x] Required implementation of a model, CRUD, service and controller

* [ ] Implement CRUD operations (Users, Collections, Items, CollectionsItems)
  * [x] Models (3 Hours)
     * [x] User 
        * [x] Int id
        * [x] String name
        * [x] String email
        * [x] getters & setters
     * [x] Collection
        * [x] Int id
        * [x] String name
        * [x] private User user
        * [x] private List<Item> items
        * [x] getters & setters
     * [x] Item 
        * [x] Int id
        * [x] String name
        * [x] BigDecimal price
        * [x] private Collection collection (possibly change to collectionId)
        * [x] getters & setters
  * [x] Repositories (1 Hour)
     * [x] UserRepository - NEEDS TESTING
     * [x] CollectionRepository - NEEDS TESTING
     * [x] ItemRepository - NEEDS TESTING
   * [ ] Service Classes (8 Hours - 2 Hours per Service Class)
     * [x] CollectionService - NEEDS TESTING
        * [x] List<Collection> getCollections()
        * [x] Collection addCollection(Collection collection)
        * [x] Collection updateCollection(Collection collection)
        * [x] deleteCollection(Long id)
     * [x] ItemService - NEEDS TESTING
        * [x] getItem
        * [x] addItem
        * [x] updateItem
        * [x] deleteItem
     * [x] FetchService - NEEDS TESTING    
     * [x] Result<T> (0.5 Hours)
        * [x] private ResultType resultType;
        * [x] private String message;
        * [x] private T data;
        * [x] public Result(ResultType resultType, String message)
        * [x] public Result(ResultType resultType, String message, T data)
        * [x] getters and setters
     * [x] ResultType
  * [x] Controllers (10 Hours - 2 Hours per Controller Class)
     * [x] CollectionController
        * [x] private CollectionService collectionService
        * [x] public List<Collection> getCollections()
        * [x] public Collection addCollection()
        * [x] public Collection updateCollection()
        * [x] public void deleteCollection()
     * [x] ItemController
        * [x] private ItemService itemService
        * [x] public List<Item> getItems()
        * [x] public Item addItem() 
        * [x] public Item updateItem()
        * [x] public void deleteItem()
     * [x] AuthController
        * [x] private UserService userService
        * [x] public User register()
        * [x] public User login()
     * [ ] Security
      * [x] Implement user and admin
      * [x] Data Layer
      * [x] Domain Layer
      * [ ] UI (Controller) Layer, Security Config
     * [x] GlobalExceptionHandler

### Part 5: Frontend (React) - React App Setup and Component Design (Est 20 Hours Total)

* [x] Setup REACT (1 Hour)
* [x] Setup REACT w/ TYPESCRIPT
* [x] Organize and identify the necessary components (1 Hour)
* [x] Design our components (10 Hours)
  * [x] Home
  * [ ] Category
  * [x] Collections
  * [x] Inventory
  * [ ] User
  * [x] Footer
  * [x] Settings
  * [x] Submit Form
  * [x] Item Submit
* [ ] Enable routing with React-Router-DOM (3 hours)
  * [ ] Home
  * [ ] Login
  * [ ] Register
  * [ ] Settings
  * [ ] Category
  * [ ] Collections
  * [ ] Inventory
  * [ ] User
* [x] Hero Component will have a carousel view of categories and items in cards (1 Hour) -      *Update Carousel*
* [x] Footer component will either have individual card information or just a banner with p tag text (1 Hour)
* [ ] Implement CRUD for the frontend (3 Hours)
* [x] Styling with Tailwind CSS
* [x] Convert React .JSX files to .TSX
* [ ] Implement Pokemon TCG API to display images

### Part 6: Implement Component Logic (Est 20 Hours Total)

* [ ] Implement logic in components
  * [ ] Fetch data from backend (10 Hours)
  * [ ] Handle user inputs (5 Hours)
  * [ ] Ensure components can correctly fetch and display backend data (5 Hour)

### Part 7: Routing and Navigation (Est 21 Hours Total)

* [ ] Implement routing and navigation (20 Hours)
* [ ] Test navigation (1 Hour)

## High-Level Requirements

* Test PriceCharting API
* Setup SQL database
* Integrate fetch from API
* Create backend/implement CRUD
* Setup front-end/react
* Implement component logic
* Setup Routing/Navigation
* Final Testing


### File Structure: 
#### Server

```
 src
        ├───main
        │   ├───java
        │   │   └───learn
        │   │       └───Collections
        │   │           │   App.java
        │   │           │
        │   │           ├───data 
        |   |           ├─────────────mappers 
        |   |           |              AppUserMapper.java
        |   |           |              CollectionItemMapper.java
        |   |           |              CollectionMapper.java
        |   |           |              ItemMapper.java
        │   │           │       AppUserRepository.java
        │   │           │       AppUserJdbcRepository.java
        │   │           │       CollectionRepository.java
        │   │           │       CollectionJdbcRepository.java
        |   |           |       ItemRepository.java
        │   │           │       ItemJdbcRepository.java
        │   │           │       CollectionItemJdbcRepository.java
        |   |           |       CollectionItemRepository.java
        │   │           │
        │   │           ├───domain
        |   |           |       CollectionItemService.java
        │   │           │       CollectionService.java
        │   │           │       ItemService.java
        │   │           │       UserService.java
        │   │           │       Result.java
        │   │           │       ResultType.java
        │   │           │
        │   │           ├───models
        |   |           |       CollectionItem.java
        │   │           │       Collection.java
        │   │           │       Item.java
        |   |           |       Grade.java (enum)
        │   │           │
        │   │           └───controllers
        │   │                   AuthController.java
        |   |                   CollectionController.java
        |   |                   ItemController.java
        │   │                   Global Exception Handler.java
        │   └───resources
        └───test
            └───java
                └───learn
                    └───Collections
                        ├───data
                        │       CollectionRepositoryTest.java
                        |       ItemRepositoryTest.java       
                        │       AppUserRepositoryTest.java
                        │      
                        └───domain
                                CollectionServiceTest.java
                                ItemServiceTest.java
