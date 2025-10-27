import axios from "axios";

const authApi = axios.create({
  baseURL: "http://localhost:8080",
  withCredentials: true,
});

export const refreshRequest = async () => {
  const response = await authApi.post("/auth/refresh");
  return response.data; // { accessToken }
};

export const logoutRequest = async () => {
  await authApi.post("/auth/logout");
};
