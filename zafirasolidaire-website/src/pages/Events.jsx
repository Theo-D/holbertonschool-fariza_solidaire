import { useEffect, useState } from "react";
import PageLayout from "../components/PageLayout";
import {getEvents} from "../services/event_services/eventApi";
import { PLACEHOLDERS } from "../components/imgPlaceholder";

const Events = function () {
    const [events, setEvents] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        const fetchEvents = async () => {
            try {
                const response = await getEvents();
                const futureEvents = response.data.filter(event => new Date(event.date) > new Date());
                setEvents(futureEvents);
            } catch (err) {
                setError("Failed to load events.");
                console.error(err);
            } finally {
                setLoading(false);
            }
        };

        fetchEvents();
    }, []);

    const formatDate = (dateString) => {
        const date = new Date(dateString);
        return date.toLocaleString(undefined, {
            weekday: "short",
            year: "numeric",
            month: "short",
            day: "numeric",
            hour: "2-digit",
            minute: "2-digit",
        });
    };

    if (loading) return <p className="text-center mt-20">Loading events...</p>;
    if (error) return <p className="text-center mt-20 text-red-500">{error}</p>;

    return (
        <PageLayout>
            <div className="min-h-screen bg-linear-to-b from-cyan-700 to-cyan-950 py-12 px-4 sm:px-6 lg:px-8">
                <section className="pt-15 px-4 sm:px-6 lg:px-8"></section>
                <h1 className="text-3xl font-bold text-white mb-10 text-center">Évènements à venir</h1>
                <div className="grid gap-10 sm:grid-cols-2 lg:grid-cols-3 mx-20">
                    {events.map((event, index) => (
                        <div key={index} className="card bg-base-100 shadow-md hover:shadow-lg transition-shadow">
                            <figure>
                                <img
                                    src={event.photoUrl || PLACEHOLDERS.event}
                                    alt={event.category}
                                    className="h-48 w-full object-cover"
                                />
                            </figure>
                            <div className="card-body">
                                <h2 className="card-title">{event.category}</h2>
                                <p>{event.description}</p>
                                <div className="card-actions justify-between mt-4">
                                    <p className="font-semibold">{formatDate(event.date)}</p>
                                    <p className="font-semibold">{event.capacity} places</p>
                                </div>
                                {event.url && (
                                    <a
                                        href={event.url}
                                        className="mt-3 inline-block text-cyan-700 font-semibold hover:underline"
                                        target="_blank"
                                        rel="noopener noreferrer"
                                    >
                                        Plus d'informations
                                    </a>
                                )}
                            </div>
                        </div>
                    ))}
                </div>
            </div>
        </PageLayout>
    );
};

export default Events;
