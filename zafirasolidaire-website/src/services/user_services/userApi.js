import api from "../api"

export const getUsers = async function () {
  return await api.get("/users");
};

export const getUserById = async function (id) {
  return await api.get(`/users/${id}`);
};

export const countServicedUsers = async function () {
  return await api.get("/serviced_users/count");
};

export const saveUser = async function (user) {
  return await api.post("/users", user)
}

export const updateUser = async function (id, user) {
  return await api.put(`/users/${id}`, user)
}

export const setUserPhoto = async function (id) {
  return await api.post(`/users/${id}/photo`);
};

export const deleteUserById = async function (id) {
  return await api.delete(`/users/${id}`);
};

export const createServicedUser = async function (userId) {
  return await api.post(`serviced_users`, { userId });
}

export async function deleteServicedUserByUserId(userId) {
  return api.delete(`/serviced_users/by-user-id/${userId}`);
}
