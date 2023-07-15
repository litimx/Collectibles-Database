import React from "react";
import { BrowserRouter, Link } from "react-router-dom";
import logo from "../assets/logoName.png";
import {
  SearchIcon,
  ShoppingCartIcon,
  BellIcon,
} from "@heroicons/react/outline";

const Navbar: React.FC = () => {
  return (
    <div>
      <nav className="bg-gray-400 flex items-center">
        <Link to="/">
          <img className="w-16 h-16 mr-80" src={logo} alt="logo" />
        </Link>
        <ul className="flex items-center justify-center h-16">
          <li className="mr-6">Shop By Category</li>
        </ul>
        <div className="relative">
          <input
            type="text"
            placeholder="Search..."
            className="bg-gray-100 text-gray-800 rounded-full py-2 px-24 pl-10 focus:outline-none focus:ring focus:border-blue-300 mr-6"
          />
          <div className="absolute inset-y-0 left-0 pl-3 flex items-center pointer-events-none">
            <SearchIcon className="h-5 w-5 text-gray-400" />
          </div>
        </div>
        <ul className="flex items-center justify-center h-16">
          {/* Added Routing */}
          <li className="mr-6">
            <Link to="/">Home</Link>
          </li>
          <li className="mr-6">
            <Link to="/collections">Collections</Link>
          </li>
          <li className="mr-6">
            <Link to="/inventory">Inventory</Link>
          </li>
          <li className="mr-6">
            <Link to="/user">User</Link>
          </li>
          <li className="mr-6">
            <Link to="/settings">Settings</Link>
          </li>
          <li className="mr-6">
            <Link to="/signup">Sign-Up</Link>
          </li>
          <li className="mr-6">
            <Link to="/login">Login</Link>
          </li>
        </ul>
        <div className="flex space-x-4 ml-4">
          <ShoppingCartIcon className="h-6 w-6 text-black" />
          <BellIcon className="h-6 w-6 text-black" />
        </div>
      </nav>
    </div>
  );
};

export default Navbar;