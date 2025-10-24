import api from "../api"

export const savePartner = async function(partner) {
  return await api.post("/partners", partner);
}

export const getPartnerById = async function(id) {
  return await api.get(`/partners/${id}`);
}

export const getAllPartners = async function() {
  return await api.get("/partners");
}

export const isPartnerExist = async function(id) {
  return await api.get(`/partners/${id}/exists`);
}

export const getPartnerCount = async function() {
  return await api.get("/partners/count");
}

export const deletePartnerById = async function(id) {
  return await api.delete(`/partenrs/${id}`)
}
