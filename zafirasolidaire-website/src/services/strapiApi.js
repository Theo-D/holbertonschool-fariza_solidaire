import axios from "axios";

const STRAPI_TOKEN = "b8a1ebd9ac87c792536172cb68f5f0b6606316298554a29c781e3d76832c9578946dc5c865f73f512a33ce3257fe75680dbe056ebb3adc9676f0c7e387870fba81e3f2f89bb8bd48d1f86d8e9265969120a4c5776bd6da72192a054ef25f94ab488fec152cefa22618e047717ee9c03360413df9796c520255ea02a4d05eecd5";

const api = axios.create({
  baseURL: "http://localhost:1337/api",
  headers: {
    Authorization: `Bearer ${STRAPI_TOKEN}`,
  },
  withCredentials: true,
});

export default api;
