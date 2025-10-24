import React, { useState, useEffect } from 'react';
import { getAllPartners } from './partnerApi';

const PartnersList = function () {
    const [partners, setPartners] = useState([]);
    const [selectedPartner, setSelectedPartner] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(function () {
        async function loadPartners() {
            try {
                const res = await getAllPartners();
                console.log("LOADED PARTNERS: ", res.data);
                setPartners(res.data);
            } catch (err) {
                setError("Failed to load partners");
            } finally {
                setLoading(false);
            }
        }
    loadPartners();
    }, []);

    if (loading) return <p>Loading...</p>;
    if (error) {
        return (
        <div className="text-red-600 bg-red-100 p-4 rounded shadow">
            <strong>Error:</strong> {error}
        </div>
        );
    };

    return(
        <ul className="list-none bg-base-100 rounded-box shadow-md border border-gray-800 flex flex-wrap gap-4 p-4 justify-center w-300">
            {partners.map((partner) => {
                return(
                    <li
                        className="flex items-center space-x-4 bg-white rounded-lg p-4 shadow w-88 border border-gray-800"
                        key={partner.id}
                    >
                    </li>
                );
            })};
        </ul>
    );
};

export default PartnersList;
