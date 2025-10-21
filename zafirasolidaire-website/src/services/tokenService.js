import axios from "axios";

export const refreshRequest = async () => {
  const response = await axios.post(
    "http://localhost:8080/auth/refresh",
    {},
    { withCredentials: true }
  );
  return response.data; // { accessToken }
};

export const logoutRequest = async () => {
  await axios.post(
    "http://localhost:8080/auth/logout",
    {},
    { withCredentials: true }
  );
};
