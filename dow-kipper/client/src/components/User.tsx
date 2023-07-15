import React from 'react'
import Navbar from './Navbar';
import Collections from './Collections';
import Inventory from './Inventory';

interface Collection {
  id: number;
  name: string;
}

interface InventoryItem {
  id: number;
  name: string;
}

interface UserProps {
  collections: Collection[];
  inventory: InventoryItem[];
}

// const collectionsData: Collection[] = [
//   { id: 1, name: "Yugioh Collection" },
//   { id: 2, name: "Baseball Collection" },
//   { id: 3, name: "Pokemon Collection" },
// ];

// const inventoryData: InventoryItem[] = [
//   { id: 1, name: "Charizard [1st Edition] #4" },
//   { id: 2, name: "Pikachu Illustrator" },
//   { id: 3, name: "Blastoise #009/165R Commissioned" },
// ];


const User: React.FC = () => {
  return (
    <div>
      <Collections />
      <Inventory />
    </div>
  );
}

export default User