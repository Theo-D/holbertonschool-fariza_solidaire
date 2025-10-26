import api from "../springApi";

export const loginRequest = async (emailAddress, password) => {
  const response = await api.post('/auth/login', { emailAddress, password }, {withCredentials: true});
  return response.data.accessToken;
};

export const getCurrentUser = async () => {
  const token = localStorage.getItem('accessToken');
  const response = await api.get('/auth/me', {withCredentials: true, headers: {Authorization: `Bearer ${token}`}});
  return response.data;
};

export const registerUser = async (user) => {
  return await api.post("/auth", user);
}
