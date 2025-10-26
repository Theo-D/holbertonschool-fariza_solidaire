import api from "../springApi";

export const getClothingWeight=async function() {
  return await api.get("/clothing");
}
