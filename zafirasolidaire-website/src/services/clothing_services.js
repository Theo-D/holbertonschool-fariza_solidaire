import api from "./api";

export const getClothingWeight=async function() {
    return await api.get("/clothing");
}
