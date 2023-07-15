import React, { useState } from "react";
import Navbar from "./Navbar";

const API_DATA =
  "https://www.sportscardspro.com/api/product?t=0ed6fa9c6ab7bb46cb3304b030b13255919e8c59&id=2530687%27";

// Form collection
// Add an item
// Parse the data
const Formsubmit: React.FC = () => {
  const [input, setInput] = useState("");
  const [inputData, setInputData] = useState("");

  const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();

    try {
      const response = await fetch(API_DATA, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          "Access-Control-Allow-Origin": "http://localhost:3000",
        },
        body: JSON.stringify({ input: input }),
      });

      console.log("Response:", response);

      if (response.ok) {
        const data = await response.json();
        console.log("Data:", data);
        setInputData(data); // If the POST method is good, set the state to the response object
      } else {
        console.error("THE API REQUEST FAILED. PLEASE TRY AGAIN.");
      }
    } catch (error) {
      console.error("AN ERROR OCCURRED", error);
    }
  };

  return (
    <div>
      <Navbar />
      <h1>API FORM SUBMIT</h1>
      <form className="flex" onSubmit={handleSubmit}>
        <div className="flex mb-4">
          <label htmlFor="inputForm">Enter Your Collection: </label>
          <input
            className="border border-gray-400 px-2 py-1"
            type="text"
            id="inputForm"
            name="inputForm"
            value={input}
            onChange={(e) => setInput(e.target.value)}
            placeholder="Enter a collection to query"
          />
        </div>
        <button
          type="submit"
          className=" ml-6 mb-4 bg-blue-500 hover:bg-blue-600 text-white py-1 px-4 rounded"
        >
          Submit
        </button>
      </form>
    </div>
  );
};

export default Formsubmit;
