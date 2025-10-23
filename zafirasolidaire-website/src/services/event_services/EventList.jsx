import React, { useState, useEffect } from 'react';
import { format, parseISO } from "date-fns";
import { getEvents, deleteEventById } from './eventApi';
import DialogModal from '../../components/DialogModel';
import CreateEventModal from '../../components/CreateEventModal';

const EventList = () => {
    const [events, setEvents] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const [selectedEvent, setSelectedEvent] = useState(null);

    useEffect(function () {
    async function loadEvents() {
        try {
            const res = await getEvents();
            console.log("LOADED EVENTS: ", res.data);
            setEvents(res.data);
        } catch (err) {
            setError("Failed to load events");
        } finally {
            setLoading(false);
        }
    }
    loadEvents();
    }, []);

    function DateDisplay({ dateString }) {
    const formatted = format(parseISO(dateString), "dd/MM/yyyy HH:mm");
    return <span>{formatted}</span>;
    }

    const handleDelete = async (id) => {
      try {
        await deleteEventById(id);
        const updatedEvents = await getEvents();
        setEvents(updatedEvents.data);
      } catch (err) {
        console.error(err);
        alert('Failed to delete event');
      }
    };

    const openModal = (myEvent) => {
        setSelectedEvent(myEvent);
        document.getElementById('event_modal').showModal();
    };

    if (loading) return <p>Loading...</p>;
    if (error) {
        return (
        <div className="text-red-600 bg-red-100 p-4 rounded shadow">
            <strong>Error:</strong> {error}
        </div>
        );
    };

    return (
        <>
        <ul className="list-none bg-base-100 rounded-box shadow-md border border-gray-800 flex flex-wrap gap-4 p-4 justify-center">
            {events.map((myEvent) => {
                return (
                    <li
                        className="flex items-center space-x-4 bg-white rounded-lg p-4 shadow w-88 border border-gray-800"
                        key={myEvent.id}
                        onClick={() => openModal(myEvent)}
                    >
                        <img
                        className="w-10 h-10 rounded-full object-cover"
                        src={myEvent.photoUrl || '/default-event.png'}
                        alt="event photo"
                        />
                        <div className="grow">
                            <div>
                                <p>Type: {myEvent.category}</p>
                                <p>Date - <DateDisplay dateString={myEvent.date} /></p>
                                <p>Capacity: {myEvent.capacity}</p>
                            </div>
                        </div>
                        <button
                        className="btn btn-square btn-ghost"
                        onClick={(e) => {
                            e.stopPropagation();
                            handleDelete(myEvent.id);
                          }}
                        aria-label={`Delete ${myEvent.id}`}
                        >
                        <svg
                            className="w-5 h-5 text-red-600"
                            xmlns="http://www.w3.org/2000/svg"
                            fill="none"
                            viewBox="0 0 24 24"
                            stroke="currentColor"
                            strokeWidth={2}
                        >
                            <line x1="18" y1="6" x2="6" y2="18" strokeLinecap="round" strokeLinejoin="round" />
                            <line x1="6" y1="6" x2="18" y2="18" strokeLinecap="round" strokeLinejoin="round" />
                        </svg>
                        </button>
                    </li>
                );
            })}
            <li
                className="flex items-center space-x-4 bg-white rounded-lg p-4 shadow w-88 border border-gray-800"
            >
                <div className="grow text-center font-bold">
                    <div>
                        <CreateEventModal/>
                    </div>
                </div>
            </li>
        </ul>
        <DialogModal
        selectedEvent={selectedEvent}
        setSelectedEvent={setSelectedEvent}
    />
    </>
    );
}

export default EventList;
