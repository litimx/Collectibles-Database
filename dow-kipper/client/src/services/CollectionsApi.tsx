import { Collection, CollectionItem, Init, Item } from "../models/models";

const COLLECTION_URL = 'http://localhost:8080/api/collection'
const ITEM_URL = 'http://localhost:8080/item'
const CI_URL = 'http://localhost:8080/collection/item'

export async function getCollectionByCollectionId(collectionId:number){
    const response = await fetch(`${COLLECTION_URL}/${collectionId}`);
  if (response.status === 200) {
    return response.json();
  } else {
    return Promise.reject(`Collection ${collectionId} could not be found.`);
  }
}

export async function getCollectionsByUserId(userId:number){
    const response = await fetch(`${COLLECTION_URL}/user/${userId}`);
  if (response.status === 200) {
    return response.json();
  } else {
    return Promise.reject(`User ${userId}'s collections were not found.`);
  }
}

export async function getCollectionValue(collectionId:number){
    const response = await fetch(`${COLLECTION_URL}/value/${collectionId}`);
  if (response.status === 200) {
    return response.json();
  } else {
    return Promise.reject(`Collection ${collectionId} could not be found.`);
  }
}

export async function getTotalValueOfCollectionsByUser(userId:number){
    const response = await fetch(`${COLLECTION_URL}/user/value/${userId}`);
  if (response.status === 200) {
    return response.json();
  } else {
    return Promise.reject(`User ${userId}'s collections were not found.`);
  }
}

export async function getItem(itemId:number){
    const response = await fetch(`${ITEM_URL}/${itemId}`);
  if (response.status === 200) {
    return response.json();
  } else {
    return Promise.reject(`Item ${itemId} was not found.`);
  }
}

export async function getItemsByCollectionId(collectionId:number){
    const response = await fetch(`${ITEM_URL}/collection/${collectionId}`);
  if (response.status === 200) {
    return response.json();
  } else {
    return Promise.reject(`Items for Collection ${collectionId} were not found.`);
  }
}

export async function getItemByCollectionIdAndItemId(itemId:number,collectionId:number){
    const response = await fetch(`${ITEM_URL}/${collectionId}/${itemId}`);
  if (response.status === 200) {
    return response.json();
  } else {
    return Promise.reject(`Item ${itemId} was not found in Collection ${collectionId}.`);
  }
}

export async function addCollection(collection: Collection) {

    const init= makeCollectionInit('POST', collection);
    const response = await fetch(COLLECTION_URL, init);

    if(response.status === 201){
        return response.json();
    } else if (response.status === 403){
        return Promise.reject('Unauthorized');
    } else {
        const errors = await response.json();
        return Promise.reject(errors);
    }
}

export async function addItem(item: Item) {

    const init = makeItemInit('POST', item);
    const response = await fetch(ITEM_URL, init);

    if(response.status === 201){
        return response.json();
    } else if (response.status === 403){
        return Promise.reject('Unauthorized');
    } else {
        const errors = await response.json();
        return Promise.reject(errors);
    }
}

export async function addItemToCollection(collectionItem: CollectionItem) {

    const init = makeCollectionItemInit('POST', collectionItem);
    const response = await fetch(`${CI_URL}/${collectionItem.collectionId}/${collectionItem.itemId}`, init);

    if(response.status === 201){
        return response.json();
    } else if (response.status === 403){
        return Promise.reject('Unauthorized');
    } else {
        const errors = await response.json();
        return Promise.reject(errors);
    }
}

export async function updateCollection(collection:Collection) {

    const init = makeCollectionInit('PUT', collection);
    const response = await fetch(`${COLLECTION_URL}/${collection.id}`, init);
  
    if (response.status === 404) {
      return Promise.reject(`Collection ${collection.id} was not found.`);
    } else if (response.status === 400) {
      const errors = await response.json();
      return Promise.reject(errors);
    } else if (response.status === 409) {
      return Promise.reject('Our data is conflicting');
    } else if (response.status === 403) {
      return Promise.reject('Unauthorized');
    }
}

export async function updateItem(item:Item) {

    const init = makeItemInit('PUT', item);
    const response = await fetch(`${ITEM_URL}/${item.id}`, init);
  
    if (response.status === 404) {
      return Promise.reject(`Item ${item.id} was not found.`);
    } else if (response.status === 400) {
      const errors = await response.json();
      return Promise.reject(errors);
    } else if (response.status === 409) {
      return Promise.reject('Our data is conflicting');
    } else if (response.status === 403) {
      return Promise.reject('Unauthorized');
    }
}

export async function updateItemInCollection(collectionItem:CollectionItem) {

    const init = makeCollectionItemInit('PUT', collectionItem);
    const response = await fetch(`${CI_URL}/${collectionItem.collectionId}/${collectionItem.itemId}`, init);
  
    if (response.status === 404) {
      return Promise.reject(`Item ${collectionItem.itemId} in Collection ${collectionItem.collectionId} was not found.`);
    } else if (response.status === 400) {
      const errors = await response.json();
      return Promise.reject(errors);
    } else if (response.status === 409) {
      return Promise.reject('Our data is conflicting');
    } else if (response.status === 403) {
      return Promise.reject('Unauthorized');
    }
}

export async function deleteCollectionById(collectionId:number) {
    const jwtToken = localStorage.getItem('jwt_token');
    const init = {
      method: 'DELETE',
      headers: {
        'Authorization': `Bearer ${jwtToken}`
      },
    }
    const response = await fetch(`${COLLECTION_URL}/${collectionId}`, init);
  
    if (response.status === 404) {
      return Promise.reject(`Collection ${collectionId} was not found.`);
    } else if (response.status === 403) {
      return Promise.reject('Unauthorized');
    }
}

export async function deleteItemById(itemId:number) {
    const jwtToken = localStorage.getItem('jwt_token');
    const init = {
      method: 'DELETE',
      headers: {
        'Authorization': `Bearer ${jwtToken}`
      },
    }
    const response = await fetch(`${COLLECTION_URL}/${itemId}`, init);
  
    if (response.status === 404) {
      return Promise.reject(`Collection ${itemId} was not found.`);
    } else if (response.status === 403) {
      return Promise.reject('Unauthorized');
    }
}

export async function deleteItemFromCollection(itemId:number,collectionId:number) {
    const jwtToken = localStorage.getItem('jwt_token');
    const init = {
      method: 'DELETE',
      headers: {
        'Authorization': `Bearer ${jwtToken}`
      },
    }
    const response = await fetch(`${CI_URL}/${collectionId}/${itemId}`, init);
  
    if (response.status === 404) {
      return Promise.reject(`Item ${itemId} in Collection ${collectionId} was not found.`);
    } else if (response.status === 403) {
      return Promise.reject('Unauthorized');
    }
}

function makeCollectionInit(method: string, Collection: Collection) {
    const jwtToken = localStorage.getItem('jwt_token');
    
    const init:Init = {
      method: method,
      headers: {
        'Content-Type': 'application/json',
        'Accept': 'application/json',
        'Authorization': `Bearer ${jwtToken}`
      },
      body: JSON.stringify(Collection)
    };
  
    return init;
}

function makeItemInit(method: string, Item: Item) {
    const jwtToken = localStorage.getItem('jwt_token');

    const init:Init = {
        method: method,
        headers: {
          'Content-Type': 'application/json',
          'Accept': 'application/json',
          'Authorization': `Bearer ${jwtToken}`
        },
        body: JSON.stringify(Item)
      };
    
      return init;
}

function makeCollectionItemInit(method: string, CollectionItem: CollectionItem){
    const jwtToken = localStorage.getItem('jwt_token');

    const init:Init = {
        method: method,
        headers: {
          'Content-Type': 'application/json',
          'Accept': 'application/json',
          'Authorization': `Bearer ${jwtToken}`
        },
        body: JSON.stringify(CollectionItem)
      };
    
      return init;
}