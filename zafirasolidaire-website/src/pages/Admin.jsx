import { useState } from "react";
import AdminDock from "../components/AdminDock";
import UserList from "../services/user_services/UserList"
import EventList from "../services/event_services/EventList"
import PartnersList from "../services/partner_services/PartnersList";
import BlogList from "../services/blog_services/BlogList";
import NavBar from "../components/NavBar";

const Admin = () => {
  const [activeSection, setActiveSection] = useState("users");

  const sectionIndex = {
    users: 0,
    events: 1,
    partners: 2,
    articles: 3,
  };

  return (

    <div>
      <NavBar/>
        <div className="flex flex-col items-center w-full h-screen overflow-hidden bg-gray-100">
          <h1 className="text-3xl font-bold my-4">Admin Dashboard</h1>

          <div className="relative flex w-full flex-1 overflow-hidden">
            {/* Sliding panels container */}
            <div
              className="flex transition-transform duration-500 ease-in-out w-full h-full"
              style={{
                transform: `translateX(-${sectionIndex[activeSection] * 100}%)`,
              }}
            >
              {/* Users Panel */}
              <div className="shrink-0 w-full h-full p-6 bg-white overflow-auto">
                <h2 className="text-2xl font-semibold mb-4">Utilisateurs</h2>
                <UserList />
              </div>

              {/* Events Panel */}
              <div className="shrink-0 w-full h-full p-6 bg-white overflow-auto">
                <h2 className="text-2xl font-semibold mb-4">Évènements</h2>
                <EventList />
              </div>

              {/* Partners Panel */}
              <div className="shrink-0 w-full h-full p-6 bg-white overflow-auto">
                <h2 className="text-2xl font-semibold mb-4">Partenaires</h2>
                <PartnersList />
              </div>

              {/* Articles Panel */}
              <div className="shrink-0 w-full h-full p-6 bg-white overflow-auto">
                <h2 className="text-2xl font-semibold mb-4">Articles</h2>
                <BlogList />
              </div>
            </div>
          </div>

          {/* Dock */}
          <AdminDock activeSection={activeSection} setActiveSection={setActiveSection} />
        </div>
    </div>

  );
};

export default Admin;
