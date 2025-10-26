import React, { useState, useEffect } from 'react';
import { getAllPartners, deletePartnerById, savePartner, updatePartner } from './partnerApi';
import { PLACEHOLDERS } from '../../components/imgPlaceholder';

const PartnersList = () => {
  const [partners, setPartners] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  const [newPartnerName, setNewPartnerName] = useState('');
  const [newPartnerHomepage, setNewPartnerHomepage] = useState('');
  const [newPartnerLogo, setNewPartnerLogo] = useState('');

  const [selectedPartner, setSelectedPartner] = useState(null); // partner being edited
  const [isEditOpen, setIsEditOpen] = useState(false); // modal visibility

  const PLACEHOLDER_HOMEPAGE = "Pas de page d'accueil";

  useEffect(() => {
    loadPartners();
  }, []);

  const loadPartners = async () => {
    try {
      const res = await getAllPartners();
      setPartners(res.data);
    } catch (err) {
      setError('Failed to load partners');
    } finally {
      setLoading(false);
    }
  };

  const handleDelete = async (id) => {
    if (!window.confirm("Êtes-vous sûr(e) de vouloir supprimer ce partenaire?")) return;
    try {
      await deletePartnerById(id);
      setPartners(partners.filter(p => p.id !== id));
    } catch (err) {
      setError('Failed to delete partner');
    }
  };

  const handleAddPartner = async () => {
    if (!newPartnerName.trim()) return;

    const newPartner = {
      name: newPartnerName,
      homepageUrl: newPartnerHomepage || null,
      logoUrl: newPartnerLogo || null
    };

    try {
      await savePartner(newPartner);
      await loadPartners();
      setNewPartnerName('');
      setNewPartnerHomepage('');
      setNewPartnerLogo('');
    } catch (err) {
      setError('Failed to add partner');
    }
  };

  const openEditModal = (partner) => {
    setSelectedPartner({ ...partner });
    setIsEditOpen(true);
  };

  const closeEditModal = () => {
    setIsEditOpen(false);
    setSelectedPartner(null);
  };

  const handleUpdatePartner = async () => {
    try {
      await updatePartner(selectedPartner.id, selectedPartner);
      await loadPartners();
      closeEditModal();
    } catch (err) {
      setError('Failed to update partner');
    }
  };

  if (loading) return <p>Loading...</p>;

  return (
    <div className="p-4">
      {/* Add Partner Form */}
      <div className="mb-4 flex gap-2 flex-wrap">
        <input
          type="text"
          placeholder="Partner name"
          value={newPartnerName}
          onChange={(e) => setNewPartnerName(e.target.value)}
          className="border p-2 rounded"
        />
        <input
          type="text"
          placeholder="Homepage URL"
          value={newPartnerHomepage}
          onChange={(e) => setNewPartnerHomepage(e.target.value)}
          className="border p-2 rounded"
        />
        <input
          type="text"
          placeholder="Logo URL"
          value={newPartnerLogo}
          onChange={(e) => setNewPartnerLogo(e.target.value)}
          className="border p-2 rounded"
        />
        <button
          onClick={handleAddPartner}
          className="bg-blue-500 text-white px-4 rounded hover:bg-blue-600"
        >
          Add Partner
        </button>
      </div>

      {error && (
        <div className="text-red-600 bg-red-100 p-4 rounded shadow mb-4">
          <strong>Error:</strong> {error}
        </div>
      )}

      {/* Partner List */}
      {partners.length === 0 ? (
        <div className="text-gray-700 bg-gray-100 p-4 rounded shadow text-center">
          Pas encore de partenaire
        </div>
      ) : (
        <ul className="list-none flex flex-wrap gap-4 p-4 justify-center">
          {partners.map((partner) => (
            <li
              key={partner.id}
              onClick={() => openEditModal(partner)}
              className="flex items-center justify-between bg-white rounded-lg p-4 shadow w-80 border border-gray-300 overflow-hidden cursor-pointer hover:shadow-lg transition"
            >
              <div className="flex items-center space-x-4 flex-1 min-w-0">
                <img
                  src={partner.logoUrl || PLACEHOLDERS.partner}
                  alt={partner.name}
                  className="w-12 h-12 object-contain shrink-0"
                />
                <div className="min-w-0">
                  <h3 className="font-bold truncate">{partner.name}</h3>
                  {partner.homepageUrl ? (
                    <a
                      href={partner.homepageUrl}
                      className="text-blue-500"
                      target="_blank"
                      rel="noreferrer"
                      onClick={(e) => e.stopPropagation()} // prevent opening modal when clicking link
                    >
                      {partner.homepageUrl}
                    </a>
                  ) : (
                    <span className="italic text-gray-500 cursor-default">
                      {PLACEHOLDER_HOMEPAGE}
                    </span>
                  )}
                </div>
              </div>
              <button
                className="bg-red-500 text-white px-3 py-1 rounded hover:bg-red-600"
                onClick={(e) => {
                  e.stopPropagation(); // prevent modal
                  handleDelete(partner.id);
                }}
              >
                Delete
              </button>
            </li>
          ))}
        </ul>
      )}

      {/* Edit Partner Modal */}
      {isEditOpen && selectedPartner && (
        <div className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center z-50">
          <div className="bg-white rounded-lg shadow-lg p-6 w-96 relative">
            <h2 className="text-xl font-bold mb-4">Modifier le partenaire</h2>
            <button
              className="absolute top-2 right-3 text-gray-600 hover:text-black"
              onClick={closeEditModal}
            >
              ✕
            </button>

            <div className="flex flex-col gap-3">
              <input
                type="text"
                value={selectedPartner.name}
                onChange={(e) =>
                  setSelectedPartner({ ...selectedPartner, name: e.target.value })
                }
                placeholder="Nom du partenaire"
                className="border p-2 rounded"
              />
              <input
                type="text"
                value={selectedPartner.homepageUrl || ''}
                onChange={(e) =>
                  setSelectedPartner({ ...selectedPartner, homepageUrl: e.target.value })
                }
                placeholder="Homepage URL"
                className="border p-2 rounded"
              />
              <input
                type="text"
                value={selectedPartner.logoUrl || ''}
                onChange={(e) =>
                  setSelectedPartner({ ...selectedPartner, logoUrl: e.target.value })
                }
                placeholder="Logo URL"
                className="border p-2 rounded"
              />
            </div>

            <div className="flex justify-end gap-2 mt-4">
              <button
                onClick={closeEditModal}
                className="bg-gray-300 text-black px-4 py-2 rounded hover:bg-gray-400"
              >
                Annuler
              </button>
              <button
                onClick={handleUpdatePartner}
                className="bg-blue-600 text-white px-4 py-2 rounded hover:bg-blue-700"
              >
                Enregistrer
              </button>
            </div>
          </div>
        </div>
      )}
    </div>
  );
};

export default PartnersList;
