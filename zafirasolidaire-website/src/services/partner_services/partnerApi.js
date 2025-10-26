import api from "../springApi";

export const savePartner = async function(partner) {
  return await api.post("/partners", partner);
}

export const updatePartner = async function(id, partner) {
  return await api.put(`/partners/${id}`, partner);
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
  return await api.delete(`/partners/${id}`)
}
