import React, { useState } from "react";
import Navbar from "./Navbar";

const API_DATA =
  "https://www.sportscardspro.com/api/product?t=0ed6fa9c6ab7bb46cb3304b030b13255919e8c59&id=2530687%27";

interface Item {
  id: number;
  name: string;
  value: number;
  grade: string;
}

const Itemsubmit: React.FC = () => {
  const [input, setInput] = useState("");
  const [results, setResults] = useState<Item[]>([]);

  const handleSubmit = async (e: React.FormEvent) => {
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
        setResults(data); // If the POST method is good, set the state to the response object
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
      <h1>Itemsubmit</h1>
      <form onSubmit={handleSubmit}>
        <input
          type="text"
          value={input}
          onChange={(e) => setInput(e.target.value)}
          placeholder="Enter an item to query"
        />
        <button
          type="submit"
          className=" ml-6 mb-4 bg-blue-500 hover:bg-blue-600 text-white py-1 px-4 rounded"
        >
          Submit
        </button>
        {results.length > 0 && (
          <div>
            <h2>Results:</h2>
            <ul>
              {results.map((item) => (
                <>
                  key={item.id}
                  <li>{item.name}</li>
                  <li>{item.value}</li>
                  <li>{item.grade}</li>
                </>
              ))}
            </ul>
          </div>
        )}
      </form>
    </div>
  );
};

export default Itemsubmit;
