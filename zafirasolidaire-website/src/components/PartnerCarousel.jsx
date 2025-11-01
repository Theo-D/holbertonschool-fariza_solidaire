import React from 'react';
import { useState, useRef } from "react";
import { Swiper, SwiperSlide } from 'swiper/react';
import {Navigation} from 'swiper/modules';
import 'swiper/css';
import 'swiper/css/navigation';
import PartnerCard from './PartnerCard';

const PartnerCarousel = ({ partners }) => {
  const swiperRef = useRef(null);
  const [isBeginning, setIsBeginning] = useState(true);
  const [isEnd, setIsEnd] = useState(false);

  const handleSlideChange = (swiper) => {
    setIsBeginning(swiper.isBeginning);
    setIsEnd(swiper.isEnd);
  };

  return (
    <div className="grid grid-cols-[auto_1fr_auto] items-center gap-4">
      {/* Left Navigation */}
      <div className="flex justify-center">
        <svg
          viewBox="-51.2 -51.2 614.4 614.4"
          width="75"
          height="75"
          fill="#000000"
          stroke="#000000"
          strokeWidth={0.00512}
          transform="matrix(1 0 0 1 0 0)"
          className={`cursor-pointer transition-opacity ${isBeginning ? 'opacity-30 pointer-events-none' : 'opacity-100'}`}
          onClick={() => swiperRef.current?.slidePrev()}
        >
          <circle cx="256" cy="256" r="256" fill="#e878d9" />
          <path
            d="M256,0v512c141.385,0,256-114.615,256-256S397.385,0,256,0z"
            fill="#df58b4"
          />
          <polygon
            points="405.943,228.571 256,228.571 256,122.714 122.757,255.989 256,389.296 256,283.429 405.943,283.429"
            fill="#F2F2F4"
          />
          <rect x="256" y="228.571" width="149.943" height="54.857" fill="#DFDFE1" />
        </svg>

      </div>

      {/* Swiper Carousel */}
      <div className="overflow-x-hidden">
        <Swiper
          spaceBetween={15}
          slidesPerView="auto"
          slidesOffsetBefore={25}
          freeMode={true}
          onSwiper={(swiper) => (swiperRef.current = swiper)}
          onSlideChange={handleSlideChange}
          onReachEnd={() => setIsEnd(true)}
          onReachBeginning={() => setIsBeginning(true)}
          modules={[Navigation]}
        >
          {partners.map((partner) => (
            <SwiperSlide key={partner.name} style={{ width: '250px' }}>
              <PartnerCard {...partner} />
            </SwiperSlide>
          ))}
        </Swiper>
      </div>

      {/* Right Navigation */}
      <div className="flex justify-center">
        <svg
          viewBox="-51.2 -51.2 614.4 614.4"
          width="75"
          height="75"
          fill="#000000"
          stroke="#000000"
          strokeWidth={0.00512}
          transform="matrix(-1 0 0 1 0 0)"
          className={`cursor-pointer transition-opacity ${isEnd ? 'opacity-30 pointer-events-none' : 'opacity-100'}`}
          onClick={() => swiperRef.current?.slideNext()}
        >
          <circle cx="256" cy="256" r="256" fill="#e878d9" />
          <path
            d="M256,0v512c141.385,0,256-114.615,256-256S397.385,0,256,0z"
            fill="#df58b4"
          />
          <polygon
            points="405.943,228.571 256,228.571 256,122.714 122.757,255.989 256,389.296 256,283.429 405.943,283.429"
            fill="#F2F2F4"
          />
          <rect x="256" y="228.571" width="149.943" height="54.857" fill="#DFDFE1" />
        </svg>
      </div>
    </div>
  );
};



export default PartnerCarousel;
