# Capstone Proposal Collectors App: DOW KIPPER

# Problem Statement:
> There is no app catered towards Collectors to accurately track and log the value of their collection
> Furthermore, current marketplace are not geared towards the buying, trading, and selling of collectibles

## Overiview:
### 1. Store Collectible Items(Ex. Baseball Cards) from Users and log their values akin to a stock ticker
> Utilize an Ebay api to collect real time data to accurately reflect the value of the user's collectible
    > Use eBay's "findCompletedItems" operation in "Finding API"
    > Retrieve listings for completed auction/buy it now items  
> Implement a UI element to dynamically show the changing value of their collection
    > Highest Sold Price
    > Lowest Sold Price
    > Last Sold
    > Change the color of graph from green to red depending on an increase or decrease in value
    > Display the volume of trades for that collectible, demonstrates liquidity and volatily 

> Limit the scope of collectibles to Baseball Cards, and one other cateogry if time permits

### 2. Learning Goals:
> TypeScript 
> Tailwind(Ian, Tim)
> Stretch Goal: AWS deployment 

### Stretch Goal: 
> Implement a peer to peer market place allowing users to trade or sell from their collection 

# Technical Solution:
We will create an app to catalog card/collectible values based off of online sold prices to give users an instant snapshot of what their collectible portfolio is worth and allows users to track various details (i.e. sold price/volume/value over time)

# Glossary:
> - Create a collection (user, Admin)
> - Edit a collection (user, Admin)
> - Delete an item from collection (user,Admin)
> - View collections (anyone)
> - Make a trade/buy/sell (user, Admin)
> - Approve a transaction (Admin)
> - Approve a listing (Admin) 

# Scenario:
> Tom has a binder of baseball cards passed down from his grandfather. He is not a baseball card expert but wants to know what he has. Instead of manually finding the price of each item online or on a catalog, he can enter his cards into our app. This way, he is able to dynamically track the value of his card binder, add/remove cards as he builds his collection, and check back any time in the future to see how the value has changed over time
