import axios from "axios"

//create interceptor
const api = axios.create({
  baseURL: "http://localhost:8080",
  withCredentials: true,
});

export default api;
