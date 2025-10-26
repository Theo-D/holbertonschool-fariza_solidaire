import api from "../services/springApi";

export const refreshRequest = async () => {
  const response = await api.post("/auth/refresh",{},{ withCredentials: true });
  return response.data; // { accessToken }
};

export const logoutRequest = async () => {
  await api.post("/auth/logout",{},{ withCredentials: true });
};
