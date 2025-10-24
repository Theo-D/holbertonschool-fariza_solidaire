import React, { useState, useEffect } from 'react';
import { format, parseISO } from "date-fns";
import { getEvents, deleteEventById } from './eventApi';
import DialogModal from '../../components/DialogModel';
import CreateEventModal from '../../components/CreateEventModal';
import { PLACEHOLDERS } from '../../components/imgPlaceholder';

const EventList = () => {
    const [events, setEvents] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const [selectedEvent, setSelectedEvent] = useState(null);

    async function loadEvents() {
        try {
            const res = await getEvents();
            setEvents(res.data);
        } catch (err) {
            setError("Failed to load events");
        } finally {
            setLoading(false);
        }
    }

    useEffect(() => {
        loadEvents();
    }, []);

    function DateDisplay({ dateString }) {
        const formatted = format(parseISO(dateString), "dd/MM/yyyy HH:mm");
        return <span>{formatted}</span>;
    }

    const handleDelete = async (id) => {
        try {
            await deleteEventById(id);
            setEvents(events.filter(e => e.id !== id));
        } catch (err) {
            console.error(err);
            alert('Failed to delete event');
        }
    };

    const openModal = (myEvent) => {
        setSelectedEvent(myEvent);
        document.getElementById('event_modal')?.showModal();
    };

    if (loading) return <p>Loading...</p>;

    return (
        <div className="p-4">
            {/* Add Event Button */}
            <div className="mb-4 flex justify-center">
                <CreateEventModal onEventCreated={loadEvents} />
            </div>

            {error && (
                <div className="text-red-600 bg-red-100 p-4 rounded shadow mb-4">
                    <strong>Error:</strong> {error}
                </div>
            )}

            {events.length === 0 ? (
                <div className="text-gray-700 bg-gray-100 p-4 rounded shadow text-center">
                    No existing events
                </div>
            ) : (
                <ul className="list-none flex flex-wrap gap-4 justify-center">
                    {events.map((myEvent) => (
                        <li
                            key={myEvent.id}
                            className="flex items-center justify-between bg-white rounded-lg p-4 shadow w-80 overflow-hidden cursor-pointer hover:shadow-lg"
                            onClick={() => openModal(myEvent)}
                        >
                            <div className="flex items-center space-x-4 flex-1 min-w-0">
                                <img
                                    className="w-12 h-12 rounded-full object-cover shrink-0"
                                    src={myEvent.photoUrl || PLACEHOLDERS.event}
                                    alt="event photo"
                                />
                                <div className="min-w-0">
                                    <h3 className="font-bold truncate">{myEvent.category}</h3>
                                    <p className="truncate">
                                        Date: <DateDisplay dateString={myEvent.date} />
                                    </p>
                                    <p className="truncate">Capacity: {myEvent.capacity}</p>
                                </div>
                            </div>

                            {/* Delete Button */}
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
                    ))}
                </ul>
            )}

            <DialogModal selectedEvent={selectedEvent} setSelectedEvent={setSelectedEvent} />
        </div>
    );
};

export default EventList;
