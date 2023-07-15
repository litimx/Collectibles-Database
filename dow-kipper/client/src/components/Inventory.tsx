import React from "react";
import Navbar from "./Navbar";
import Footer from "./Footer";

const Inventory: React.FC = () => {
  return (
    <div>
      <div className="main-collections">
        <h1 className="ml-3 mt-8 text-4xl font-bold">View Your Inventory</h1>
        <div className="collections-container grid grid-cols-3 gap-4 mt-4">
          <div
            className="card p-4 flex flex-col items-center"
            data-aos="flip-right"
            data-aos-delay="400"
          >
            <h2 className="mb-2 text-lg font-bold text-red-500 tracking-wide capitalize">
              Charizard [1st Edition] #4
            </h2>
            <img
              className="object-cover h-80 w-full"
              src="https://i.ebayimg.com/images/g/iJAAAOSwkpxdSJoR/s-l1600.jpg"
              alt=""
            />
            <p className="font-bold">Grade 10: </p>
            <p className="font-bold">$38,603.99</p>
            <p>
              Lorem ipsum, dolor sit amet consectetur adipisicing elit.
              Voluptatem dolorem dolor voluptatum earum. Impedit doloribus natus
              commodi accusamus magni, nam explicabo numquam aperiam unde
              tenetur doloremque ipsum illum est? Obcaecati?
            </p>
          </div>
          <div
            className="card p-4 flex flex-col items-center"
            data-aos="flip-up"
            data-aos-delay="600"
          >
            <h2 className="mb-2 text-lg font-bold text-yellow-500 tracking-wide capitalize">
              Pikachu Illustrator
            </h2>
            <img
              className="object-cover h-80 w-full"
              src="https://i.ebayimg.com/images/g/ZMkAAOSw2gBftsUY/s-l1600.jpg"
              alt=""
            />
            <p className="font-bold">Grade 9: </p>
            <p className="font-bold">$5,000,000.00</p>
            <p>
              The Holy Grail of Pokemon Cards. It took Ash Ketchum 20 years to
              become Pokemon World Champion, and you'll be working into the
              afterlife to be able to afford me. You will most likely find me in
              a display case in some Pokemon Museum in Los Angeles, California.
              You can't afford me. Pika, Pika.
            </p>
          </div>
          <div
            className="card p-4 flex flex-col items-center"
            data-aos="flip-left"
            data-aos-delay="800"
          >
            <h2 className="mb-2 text-lg font-bold text-blue-500 tracking-wide capitalize">
              Blastoise #009/165R Commissioned
            </h2>
            <img
              className="object-cover h-80 w-full"
              src="https://dyn1.heritagestatic.com/lf?set=path%5B2%2F3%2F2%2F9%2F2%2F23292417%5D&call=url%5Bfile%3Aproduct.chain%5D"
              alt=""
            />
            <p className="font-bold">Grade 9: </p>
            <p className="font-bold">$360,000.00</p>
            <p>
              Lorem ipsum dolor sit amet consectetur adipisicing elit. Magnam,
              sit? Nihil repellendus maxime ad non temporibus, dolores aliquam?
              Quasi eaque tenetur voluptas alias dignissimos reiciendis omnis
              iusto odio? Quia, ratione
            </p>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Inventory;