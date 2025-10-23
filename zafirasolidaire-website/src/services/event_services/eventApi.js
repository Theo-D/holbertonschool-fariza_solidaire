import api from "../api"

export const getEvents = async function() {
  return await api.get("/events");
};

export const getEventCategories = async function() {
    return await api.get("/event_categories");
}

export const getEventbyId = async function(id) {
    return await api.get(`/events/${id}`);
};

export const getEventCategorybyId = async function(id) {
    return await api.get(`/event_categories/${id}`);
};

export const isEventExist = async function(id) {
    return await api.get(`/events/${id}/exists`);
};

export const countEvents = async function() {
    return await api.get(`/events/count`);
};

export const saveEvent = async function(event) {
    return await api.post(`/events`, event);
};

export const saveEventCategory = async function(eventCategory) {
    return await api.post(`/event_categories`, eventCategory);
};

export const saveEventPhoto = async function(photo, eventId) {
    return await api.post(`/events/${eventId}/photo`, photo);
};

export const updateEventById = async function(id, event){
    return await api.put(`/events/${id}`, event);
  }

export const deleteEventById = async function(id) {
    return await api.delete(`/events/${id}`);
}

export const deleteEventCategoryById = async function(id) {
    return await api.delete(`/events/${id}/category`);
}
