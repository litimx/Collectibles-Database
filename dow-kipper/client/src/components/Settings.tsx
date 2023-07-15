import React, { useState } from "react";
import Navbar from "./Navbar";

const Settings: React.FC = () => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  const handlePasswordChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setPassword(e.target.value);
  };

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    console.log("Username:", username);
    console.log("Password:", password);
    // Reset the form
    setUsername("");
    setPassword("");
  };

  return (
    <div>
      <Navbar />
      <div className="account-main flex ml-4 py-6">
        <div className="side-bar">
          <div className="settings-container">
            <h1 className="text-gray-400 text-xl mb-2">Settings</h1>
            <h3>Account</h3>
            <h3>Notifications</h3>
            <h3>Password</h3>
          </div>
        </div>
        <div className="account-controls ml-12">
          <div className="your-account-container border border-black rounded-lg p-4">
            <h1 className="font-bold text-xl mb-2">Your Account</h1>
            <div className="flex items-center">
              <div className="w-6 h-6 bg-gray-300 rounded-full mr-3"></div>
              <h3 className="mr-2">Your Username</h3>
            </div>
          </div>
          <div className="change-password-container border border-black rounded-lg p-4 mt-6">
            <h1 className="font-bold text-xl mb-2 mt-6">Change Password</h1>
            <form onSubmit={handleSubmit}>
              <div className="form-group">
                <label htmlFor="username" className="form-label">
                  Username
                </label>
                <input
                  className="p-3 my-2 bg-white-700 rounded form-input"
                  type="text"
                  placeholder="username"
                  id="username"
                  value={username}
                  onChange={(e) => setUsername(e.target.value)}
                  required
                />
              </div>
              <div className="form-group">
                <label htmlFor="password" className="form-label">
                  Password
                </label>
                <input
                  className="ml-1 p-3 my-2 bg-white-700 rounded form-input"
                  type="password"
                  placeholder="Password"
                  id="password"
                  value={password}
                  onChange={handlePasswordChange}
                  required
                />
              </div>
              <button
                className="bg-blue-600 py-3 px-6 my-6 mx-3 rounded font-bold text-white"
                type="submit"
              >
                Update Password
              </button>
            </form>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Settings;