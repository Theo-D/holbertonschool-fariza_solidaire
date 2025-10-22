import api from "./api";
import { refreshRequest, logoutRequest } from "./tokenService";

let isRefreshing = false;
let refreshUsers = [];

function onRefreshed(newToken) {
  refreshUsers.forEach(callback => callback(newToken));
  refreshUsers = [];
}

api.interceptors.request.use((config) => {
  const accessToken = localStorage.getItem("accessToken");
  console.log("ACCESS TOKEN FROM INTERCEPTORS: ", accessToken)
  if (accessToken) {
    config.headers.Authorization = `Bearer ${accessToken}`;
  }
  return config;
});

api.interceptors.response.use(
  (response) => response,
  async (error) => {
    const originalRequest = error.config;
    if (error.response?.status === 401 && !originalRequest._retry) {
      originalRequest._retry = true;

      if (!isRefreshing) {
        isRefreshing = true;
        try {
          const { accessToken: newToken } = await refreshRequest();
          localStorage.setItem("accessToken", newToken);
          isRefreshing = false;
          onRefreshed(newToken);
        } catch (err) {
          isRefreshing = false;
          try {
            await logoutRequest();
          } finally {
            window.location.replace(window.location.origin + "/");
          }
          return Promise.reject(err);
        }
      }

      return new Promise((resolve) => {
        refreshUsers.push((token) => {
          originalRequest.headers.Authorization = `Bearer ${token}`;
          resolve(api(originalRequest));
        });
      });
    }

    return Promise.reject(error);
  }
);
