const AdminDock = ({ activeSection, setActiveSection }) => {
  return (
    <div className="dock dock-xl absolute inset-x-0 bottom-0 left-1/2 transform -translate-x-1/2 z-50 bg-gray-50 p-2  shadow-lg flex space-x-4">
      <button
        onClick={() => setActiveSection("users")}
        className={`flex flex-col items-center px-3 py-2 rounded hover:bg-gray-200 transition ${
          activeSection === "users" ? "bg-pink-200" : ""
        }`}
      >
        <span className="text-xl">👤</span>
        <span className="text-sm">Users</span>
      </button>

      <button
        onClick={() => setActiveSection("events")}
        className={`flex flex-col items-center px-3 py-2 rounded hover:bg-gray-200 transition ${
          activeSection === "events" ? "bg-yellow-200" : ""
        }`}
      >
        <span className="text-xl">📅</span>
        <span className="text-sm">Events</span>
      </button>

      <button
        onClick={() => setActiveSection("partners")}
        className={`flex flex-col items-center px-3 py-2 rounded hover:bg-gray-200 transition ${
          activeSection === "partners" ? "bg-cyan-200" : ""
        }`}
      >
        <span className="text-xl">🤝</span>
        <span className="text-sm">Partners</span>
      </button>

      <button
        onClick={() => setActiveSection("articles")}
        className={`flex flex-col items-center px-3 py-2 rounded hover:bg-gray-200 transition ${
          activeSection === "articles" ? "bg-gray-200" : ""
        }`}
      >
        <span className="text-xl">📝</span>
        <span className="text-sm">Articles</span>
      </button>
    </div>
  );
};

export default AdminDock;
