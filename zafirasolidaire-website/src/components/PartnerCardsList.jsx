import React, { useEffect, useState } from "react";
import api from "../services/springApi";
import PartnerCarousel from "./PartnerCarousel";
import { getAllPartners } from "../services/partner_services/partnerApi";


const PartnerCardsList = () => {
  const [partners, setPartners] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    async function fetchPartners() {
      try {
        const response = await getAllPartners();
        console.log("LOADED PARTNERS: ", response)
        setPartners(response.data);
      } catch (err) {
        setError("Failed to load partners.");
        console.error(err);
      } finally {
        setLoading(false);
      }
    }

    fetchPartners();
  }, []);

  if (loading) return <p className="p-4 text-gray-500">Loading partners...</p>;
  if (error) return <p className="p-4 text-red-500">{error}</p>;

    return (
        <PartnerCarousel partners={partners}/>
    );
}
export default PartnerCardsList;
