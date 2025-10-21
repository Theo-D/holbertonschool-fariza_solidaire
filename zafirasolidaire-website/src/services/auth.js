import api from './api'

export const loginRequest = async (email, password) => {
  const response = await api.post('/auth/login', { email, password });
  return response.data; // { accessToken, ... }
};

export const refreshRequest = async () => {
  const response = await api.post('/auth/refresh', {}, { withCredentials: true });
  return response.data; // { accessToken }
};

export const logoutRequest = async () => {
  await api.post('/auth/logout');
};

export const getCurrentUser = async () => {
  const response = await api.get('/auth/me');
  return response.data; // user object
};
