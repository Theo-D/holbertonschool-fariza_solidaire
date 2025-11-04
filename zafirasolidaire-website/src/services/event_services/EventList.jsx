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

    const sortedEvents = [...events].sort((a, b) => new Date(a.date) - new Date(b.date));

    async function loadEvents() {
        try {
            const res = await getEvents();
            console.log("LOADED EVENTS FROM GETEVENTS", res.data)
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
        if (!window.confirm("Êtes-vous sûr(e) de vouloir supprimer cet évènement?")) return;
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
            {error && (
            <div className="text-red-600 bg-red-100 p-4 rounded shadow mb-4">
                <strong>Error:</strong> {error}
            </div>
            )}

            {events.length === 0 ? (
            <div className="text-gray-700 bg-gray-100 p-4 rounded shadow text-center">
                Pas encore d'évènements
            </div>
            ) : (
            <ul className="list-none flex flex-wrap gap-4 justify-center">
                {sortedEvents.map((myEvent) => {
                    const isPast = new Date(myEvent.date) < new Date();

                    return (
                    <li
                        key={myEvent.id}
                        className={` rounded-lg p-4 shadow w-80 border border-gray-300 overflow-hidden cursor-pointer hover:shadow-lg transition
                        ${isPast ? "bg-gray-200" : "bg-white"}`}
                        onClick={() => openModal(myEvent)}
                    >
                        <div className="flex items-center space-x-4 mb-3">
                        <img
                            className="w-12 h-12 rounded-full object-cover shrink-0 border border-gray-200"
                            src={myEvent.photoUrl || PLACEHOLDERS.event}
                            alt={`${myEvent.category} event`}
                        />
                        <div className="min-w-0">
                            <h3 className="font-bold truncate text-gray-800">{myEvent.category}</h3>
                            <p className="text-sm text-gray-600 truncate">
                            <span className="font-medium">Date:</span>{" "}
                            <DateDisplay dateString={myEvent.date} />
                            </p>
                            <p className="text-sm text-gray-600 truncate">
                            <span className="font-medium">Capacité:</span> {myEvent.capacity}
                            </p>
                        </div>
                        </div>

                        {myEvent.description && (
                        <p className="text-sm text-gray-700 bg-gray-100 rounded p-2 mb-3 line-clamp-3">
                            {myEvent.description}
                        </p>
                        )}

                        <div className='columns-2 gap-2 sm:columns-2'>
                            {myEvent.url && (
                            <a
                                href={myEvent.url}
                                target="_blank"
                                rel="noreferrer"
                                onClick={(e) => e.stopPropagation()}
                                className="text-blue-500 text-sm font-medium hover:underline inline-flex items-center mb-3"
                            >
                                Voir l'évènement
                                <svg
                                className="w-4 h-4 ml-1"
                                xmlns="http://www.w3.org/2000/svg"
                                fill="none"
                                viewBox="0 0 14 10"
                                >
                                <path
                                    stroke="currentColor"
                                    strokeLinecap="round"
                                    strokeLinejoin="round"
                                    strokeWidth="2"
                                    d="M1 5h12m0 0L9 1m4 4L9 9"
                                />
                                </svg>
                            </a>
                            )}
                            {isPast && <p className="text-red-500">Évènement passé</p>}
                        </div>

                        <div className="flex gap-2 justify-start">
                        <button
                            className="flex-1 bg-blue-500 text-white px-3 py-1 rounded text-sm font-semibold hover:bg-blue-600 transition"
                            onClick={(e) => {
                            e.stopPropagation();
                            openModal(myEvent);
                            }}
                        >
                            Edit
                        </button>
                        <button
                            className="flex-1 bg-red-500 text-white px-3 py-1 rounded text-sm font-semibold hover:bg-red-600 transition"
                            onClick={(e) => {
                            e.stopPropagation();
                            handleDelete(myEvent.id);
                            }}
                            aria-label={`Delete event ${myEvent.id}`}
                        >
                            Supprimer
                        </button>
                        </div>
                    </li>
                    );
                })}
                </ul>

            )}

            {/* Dialog Modal */}
            <DialogModal selectedEvent={selectedEvent} setSelectedEvent={setSelectedEvent} />
            <div className="my-20 flex justify-center">
            <CreateEventModal onEventCreated={loadEvents} />
            </div>
        </div>
    );

};

export default EventList;
