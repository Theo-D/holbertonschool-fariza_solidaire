import React, { useState, useEffect } from "react";
import { saveEvent, getEventCategories, saveEventCategory, deleteEventCategoryById, getEvents, getEventCategorybyId} from "../services/event_services/eventApi";
import BinSvg from "./svg_components/Bin";

function CreateEventModal(props) {
  const [open, setOpen] = useState(false);
  const [dateTime, setDateTime] = useState("");
  const [capacity, setCapacity] = useState("");
  const [categories, setCategories] = useState([]);
  const [category, setCategory] = useState([]);
  const [loading, setLoading] = useState(true);
  const [events, setEvents] = useState([]);
  const [selectedCategory, setSelectedCategory] = useState("");
  const [newCategoryInput, setNewCategoryInput] = useState("");
  const [addingCategory, setAddingCategory] = useState(false);

  const toggleModal = () => setOpen(!open);

  async function loadCategories() {
      try {
          const res = await getEventCategories();
          console.log("LOADED CATEGORIES: ", res.data);
          setCategories(res.data);
      } catch (err) {
          setError("Failed to load categories");
      } finally {
          setLoading(false);
      }
    }

  useEffect( function (){
    loadCategories();
  },[]);

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (!dateTime) return alert("Please select date & time");
    if (!capacity || capacity < 1 || capacity > 99)
      return alert("Capacity must be between 1 and 99");
    if (!selectedCategory) return alert("Please select a category");

    try {
      const res = await getEventCategorybyId(selectedCategory);
      const categoryData = res.data;
      const event = {
        category: categoryData.name,
        capacity: capacity,
        date: dateTime,
      };
      await handleCreateEvent(event);
    } catch (err) {
      console.error("Failed to create event", err);
      alert("Failed to create event");
    }
  };


  const handleAddCategory = async () => {
    if (newCategoryInput.trim() === "") return;
    try {
      await saveEventCategory({ name: newCategoryInput.trim() });
      const res = await getEventCategories();
      setCategories(res.data);
      const addedCategory = res.data.find(cat => cat.name === newCategoryInput.trim());
    if (addedCategory) {
      setSelectedCategory(addedCategory);
    }
      setNewCategoryInput("");
      setAddingCategory(false);
    } catch (error) {
      alert("Failed to add category");
      console.error(error);
    }
  };

  const handleDelete = async (id) => {
      try {
        await deleteEventCategoryById(id);
        const updatedCat = await getEventCategories();
        setCategories(updatedCat.data);
      } catch (err) {
        console.error(err);
        alert('Failed to delete event category');
      }
    };

    const handleCreateEvent = async (event) => {
      try {
        await saveEvent(event)
        const updatedEvents = await getEvents();
        setEvents(updatedEvents.data)
        toggleModal();
        if (props.onEventCreated) {
          props.onEventCreated();
        }
      } catch (err) {
        alert("Failed to save Event");
      }
    };

  return (
    <>
      <button
        className="btn btn-primary"
        onClick={toggleModal}
        aria-haspopup="dialog"
        aria-expanded={open}
      >
        Create Event
      </button>

      {open && (
        <div
          className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center z-50"
          onClick={toggleModal}
        >
          <div
            className="bg-white rounded-lg p-6 w-full max-w-md"
            onClick={(e) => e.stopPropagation()}
            role="dialog"
            aria-modal="true"
            aria-labelledby="modal-title"
          >
            <h2 id="modal-title" className="text-xl font-bold mb-4">
              Create New Event
            </h2>

            <form onSubmit={handleSubmit} className="space-y-4">
              {/* Date & time */}
              <div>
                <label
                  htmlFor="datetime"
                  className="block text-sm font-medium mb-1"
                >
                  Date & Time
                </label>
                <input
                  id="datetime"
                  type="datetime-local"
                  value={dateTime}
                  onChange={(e) => setDateTime(e.target.value)}
                  className="input input-bordered w-full"
                  required
                />
              </div>

              {/* Capacity */}
              <div>
                <label
                  htmlFor="capacity"
                  className="block text-sm font-medium mb-1"
                >
                  Capacity (1-99)
                </label>
                <input
                  id="capacity"
                  type="number"
                  min="1"
                  max="99"
                  value={capacity}
                  onChange={(e) => setCapacity(e.target.value)}
                  className="input input-bordered w-full"
                  required
                />
              </div>

              {/* Category dropdown */}
              <div>
                <label
                  htmlFor="category"
                  className="block text-sm font-medium mb-1"
                >
                  Category
                </label>
                {!addingCategory ? (
                  <div className="flex items-center space-x-2">
                  <select
                    id="category"
                    value={selectedCategory}
                    onChange={(e) => {
                      if (e.target.value === "__add_new__") {
                        setAddingCategory(true);
                      } else {
                        setSelectedCategory(e.target.value);
                      }
                    }}
                    className="select select-bordered grow"
                    required
                  >
                    <option value="" disabled>
                      Select category
                    </option>
                    {categories.map((category) => (
                      <option key={category.id} value={category.id}>
                        {category.name}
                      </option>
                    ))}
                    <option value="__add_new__">+ Add new category</option>
                  </select>

                  <button
                    type="button"
                    onClick={() => {
                      //console.log(selectedCategory)
                      handleDelete(selectedCategory)
                    }}
                    aria-label="Delete category"
                    className="btn btn-ghost p-1"
                  >
                    <BinSvg className="w-6 h-6 text-red-600" />
                  </button>
                </div>
                ) : (
                    <div className="flex space-x-2">
                      <input
                        type="text"
                        placeholder="New category name"
                        className="input input-bordered grow"
                        value={newCategoryInput}
                        onChange={(e) => setNewCategoryInput(e.target.value)}
                      />
                      <button
                        type="button"
                        onClick={handleAddCategory}
                        className="btn btn-primary"
                      >
                        Add
                      </button>
                      <button
                        type="button"
                        onClick={() => {
                          setAddingCategory(false);
                          setNewCategoryInput("");
                        }}
                        className="btn btn-ghost"
                      >
                        Cancel
                      </button>
                    </div>
                  )}
                </div>

              {/* Buttons */}
              <div className="flex justify-end space-x-2 pt-4">
                <button
                  type="button"
                  onClick={toggleModal}
                  className="btn btn-ghost"
                >
                  Cancel
                </button>
                <button type="submit" className="btn btn-primary">
                  Create
                </button>
              </div>
            </form>
          </div>
        </div>
      )}
    </>
  );
}

export default CreateEventModal
