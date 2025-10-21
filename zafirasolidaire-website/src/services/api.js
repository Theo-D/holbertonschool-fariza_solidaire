import axios from "axios"
import { refreshRequest, logoutRequest } from "./auth";

//create interceptor
const api = axios.create({
  baseURL: "http://localhost:8080",
  withCredentials: true,
});

// Add a request interceptor
api.interceptors.request.use(function (config) {
    const accessToken = localStorage.getItem('accessToken');
    if (accessToken) {
      config.headers.Authorization = `Bearer ${accessToken}`;
    }
    return config;
  });

let isRefreshing = false;
let refreshUsers = [];

function onRefreshed(newToken) {
  refreshUsers.forEach(callback => callback(newToken));
  refreshUsers = [];
}

//response interceptor
api.interceptors.response.use(
  response => response,
  async error => {
    const originalRequest = error.config;
    if (error.response?.status === 401 && !originalRequest._retry) {
      originalRequest._retry = true;

      if (!isRefreshing) {
        isRefreshing = true;
        try {
          const { accessToken: newToken } = await refreshRequest();
          localStorage.setItem('accessToken', newToken);
          isRefreshing = false;
          onRefreshed(newToken);
        } catch (err) {
          isRefreshing = false;
        try {
          await logoutRequest();
        } finally {
          window.location.replace(window.location.origin + '/');
        }
          return Promise.reject(err);
        }
      }

      return new Promise(resolve => {
        refreshUsers.push(token => {
          originalRequest.headers.Authorization = `Bearer ${token}`;
          resolve(api(originalRequest));
        });
      });
    }

    return Promise.reject(error);
  }
);
