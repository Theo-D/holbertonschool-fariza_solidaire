import api from "../api"

export const getUsers = async function() {
  return await api.get("/users");
};

export const getUserById = async function(id) {
  return await api.get("/users/{id}");
};

export const countUsers = async function() {
  return await api.get("/users/count");
};

export const setUserPhoto = async function(id) {
  return await api.post(`/users/${id}/photo`);
};

export const deleteUserById = async function(id) {
  return await api.delete(`/users/${id}`);
};
