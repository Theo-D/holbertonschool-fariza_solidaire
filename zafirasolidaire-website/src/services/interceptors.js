// api.js
import api from "./springApi";
import { refreshRequest, logoutRequest } from "../services/tokenService";
import { useNavigate } from "react-router-dom";

// Flag to indicate if a refresh is in progress
let isRefreshing = false;

// Queue of pending requests while refreshing
let refreshQueue = [];

// Helper to process queued requests after refresh
const processQueue = (error, token = null) => {
  refreshQueue.forEach(({ resolve, reject }) => {
    if (token) {
      resolve(token);
    } else {
      reject(error);
    }
  });
  refreshQueue = [];
};

api.interceptors.request.use((config) => {
  const token = localStorage.getItem("accessToken");
  if (token) {
    config.headers.Authorization = `Bearer ${token}`;
  }
  return config;
});

// Interceptor
api.interceptors.response.use(
  (response) => response,
  async (error) => {
    const originalRequest = error.config;

    // Only handle 401/403 errors and prevent retry loops
    if ([401, 403].includes(error.response?.status) && !originalRequest._retry) {
      originalRequest._retry = true;

      if (!isRefreshing) {
        isRefreshing = true;

        try {
          const { accessToken: newToken } = await refreshRequest();

          // Save new access token
          localStorage.setItem("accessToken", newToken);

          isRefreshing = false;

          // Resolve all queued requests
          processQueue(null, newToken);
        } catch (refreshError) {
          isRefreshing = false;

          // Reject all queued requests
          processQueue(refreshError, null);

          // Fire-and-forget logout, then redirect immediately
          logoutRequest().catch(() => {});

          //window.location.href = "/home";

          // Stop further interceptor processing
          return Promise.reject(refreshError);
        }
      }

      // Return a promise that waits for the token to be refreshed
      return new Promise((resolve, reject) => {
        refreshQueue.push({
          resolve: (token) => {
            // Retry original request with new token
            originalRequest.headers.Authorization = `Bearer ${token}`;
            resolve(api(originalRequest));
          },
          reject: (err) => reject(err),
        });
      });
    }

    // Non-auth errors, reject immediately
    return Promise.reject(error);
  }
);

export default api;
