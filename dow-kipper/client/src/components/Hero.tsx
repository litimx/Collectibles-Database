import React from "react";

const Hero: React.FC = () => {
  return (
    /* Implement Carousel of Pokemon Cards */
    // AntD library perhaps
    <div className="relative">
      <img
        src="https://images.unsplash.com/photo-1616196334218-caffdc9b2317?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=870&q=80"
        alt="Hero"
        className="object-cover w-full h-64 sm:h-96"
        data-aos="fade-right"
        data-aos-delay="600"
      />
      <div className="absolute inset-0 bg-black opacity-50"></div>
      <div className="absolute inset-0 flex justify-center">
        <h1 className="text-white mt-12 text-4xl font-bold" data-aos="fade up">
          Discover Trading Cards by Category
        </h1>
      </div>
    </div>
  );
};

export default Hero;