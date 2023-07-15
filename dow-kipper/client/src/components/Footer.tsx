import React, { useState } from "react";
import AOS from "aos";
import "aos/dist/aos.css";

AOS.init();

const Footer: React.FC = () => {
  const [email, setEmail] = useState("");

  const handleEmailChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setEmail(e.target.value);
  };

  const handleSubscribe = (e: React.FormEvent) => {
    e.preventDefault();
    console.log(`Subscribed with email: ${email}`);
    setEmail("");
  };

  const handleContact = () => {
    console.log("Contact form submitted");
  };

  return (
    <div>
      <footer className="my-8" data-aos="zoom-in-left" data-aos-delay="800">
        <div className="cards grid grid-cols-3 gap-4">
          <div
            className="card p-4 rounded border border-black ml-4 h-5/6"
          >
            <h3 className="font-semibold text-xl">About:</h3>
            <p className="">
              Introducing Dow Kipper, your platform for trading cards and
              collectibles. Explore a vast range of items, trade with fellow
              enthusiasts, and stay informed with real-time data. Unleash your
              passion for collecting and elevate your collecting experience with
              Dow Kipper.
            </p>
          </div>
          <div
            className="card p-4 rounded border border-black h-5/6"
          >
            <h3 className="font-semibold text-xl">Join Our Community:</h3>
            <p>
              Discover the thrilling world of collectibles and trading cards! Be
              the first to receive exclusive updates, exciting product launches,
              and insider news from Dow Kipper. Join our vibrant community today
              and unlock a world of endless possibilities.
            </p>
            <form onSubmit={handleSubscribe} className="mt-1">
              <input
                type="email"
                placeholder="Your email address"
                value={email}
                onChange={handleEmailChange}
                className="border-2 border-gray-400 p-1 rounded text-sm"
              />
              <button
                type="submit"
                className="bg-blue-500 text-white px-2 py-1 rounded ml-2 text-sm"
              >
                Subscribe
              </button>
            </form>
          </div>
          <div
            className="p-4 rounded border border-black mr-4 h-5/6"
          >
            <h3 className="font-semibold text-xl">Partner with Us:</h3>
            <p className="">
              Are you interested in partnering with us? We'd love to hear from
              you! Please fill out the form below to get in touch with our team.
            </p>
            <form onSubmit={handleContact} className="mt-1">
              <div className="flex mb-2">
                <input
                  type="text"
                  placeholder="Your Name"
                  className="border-2 border-gray-400 p-1 rounded w-1/2 mr-2 text-sm"
                />
                <input
                  type="email"
                  placeholder="Your Email Address"
                  className="border-2 border-gray-400 p-1 rounded w-1/2 text-sm"
                />
              </div>
              <div className="flex mb-2">
                <textarea
                  placeholder="Your Message"
                  className="border-2 border-gray-400 p-1 rounded w-full mb-8 text-sm"
                  rows={1}
                ></textarea>
                <button
                  type="submit"
                  className="bg-blue-500 text-white ml-2 px-2 py-1 rounded self-start text-sm"
                >
                  Submit
                </button>
              </div>
            </form>
          </div>
        </div>
      </footer>
    </div>
  );
};

export default Footer;