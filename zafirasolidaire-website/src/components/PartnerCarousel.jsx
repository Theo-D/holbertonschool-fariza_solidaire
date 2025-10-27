import React from 'react';
import { Swiper, SwiperSlide } from 'swiper/react';
import 'swiper/css';
import 'swiper/css/navigation';
import PartnerCard from './PartnerCard';

const PartnerCarousel = ({ partners }) => {
  return (
    <Swiper
      spaceBetween={2}
      slidesPerView={8}
      navigation={true}
      parallax={true}
      freeMode={true}
      className="relative"
    >
      {partners.map((partner) => (
        <SwiperSlide key={partner.name}>
          <PartnerCard {...partner} />
        </SwiperSlide>
      ))}
    </Swiper>
  );
};



export default PartnerCarousel;
