import axios from "axios";

export const refreshRequest = async () => {
  const response = await axios.post("/auth/refresh",{},{ withCredentials: true });
  return response.data; // { accessToken }
};

export const logoutRequest = async () => {
  await axios.post("/auth/logout",{},{ withCredentials: true });
};
