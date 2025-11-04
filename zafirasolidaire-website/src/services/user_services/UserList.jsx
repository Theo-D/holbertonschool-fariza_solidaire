import React, { useState, useEffect } from 'react';
import {getUsers, deleteServicedUserByUserId, deleteUserById, updateUser, createServicedUser} from "./userApi";

const UserList = () => {
  const [users, setUsers] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  // Load users and set initial state
  useEffect(() => {
    async function loadUsers() {
      try {
        const res = await getUsers();
        console.log("LOADED USERS: ", res.data);
        setUsers(res.data);
      } catch (err) {
        setError('Failed to load users');
      } finally {
        setLoading(false);
      }
    }

    loadUsers();
  }, []);

  // Toggle serviced status for a user
  const toggleServiced = async (userId) => {
    const userIndex = users.findIndex((u) => u.userId === userId);
    if (userIndex === -1) return;

    const currentUser = users[userIndex];
    console.log("CURRENT USER: ", currentUser)
    const newServicedState = !currentUser.isServiced;

    try {
      const updatedUser = { ...currentUser, isServiced: newServicedState };
      await updateUser(updatedUser.userId, updatedUser);

      if (newServicedState) {
        await createServicedUser(userId);
      } else {
        await deleteServicedUserByUserId(userId);
      }

      const updatedUsers = [...users];
      updatedUsers[userIndex] = updatedUser;
      setUsers(updatedUsers);

    } catch (error) {
      console.error('Error toggling serviced state:', error);
      alert('Could not update serviced state.');
    }
  };

  const handleDelete = async (id) => {
    if (!window.confirm("Êtes-vous sûr(e) de vouloir supprimer cet utilisateur?")) return;
    try {
      await deleteUserById(id);
      const updatedUsers = await getUsers();
      setUsers(updatedUsers.data);
    } catch (err) {
      console.error(err);
      alert('Failed to delete user');
    }
  };

  if (loading) return <p>Loading...</p>;
  if (error) {
    return (
      <div className="text-red-600 bg-red-100 p-4 rounded shadow">
        <strong>Error:</strong> {error}
      </div>
    );
  }

  return (
    <div className="p-4">
      {users.length === 0 ? (
        <div className="text-gray-700 bg-gray-100 p-4 rounded shadow text-center">
          Aucun utilisateur pour le moment
        </div>
      ) : (
        <ul className="list-none flex flex-wrap gap-4 p-4 justify-center">
          {users
            .filter((user) => !user.isAdmin)
            .map((user) => {
            const isServiced = user.isServiced;

            return (
              <li
                key={user.userId}
                className="bg-white rounded-lg p-4 shadow w-80 border border-gray-300 overflow-hidden cursor-pointer hover:shadow-lg transition"
              >
                <div className="flex items-center space-x-4 mb-3">
                  <img
                    className="w-12 h-12 rounded-full object-cover shrink-0 border border-gray-200"
                    src={user.photoUrl || '/default-user.png'}
                    alt={`${user.firstName} ${user.lastName}`}
                  />
                  <div className="min-w-0">
                    <h3 className="font-bold truncate text-gray-800">
                      {user.firstName} {user.lastName}
                    </h3>
                    <p className="text-sm text-gray-600 truncate">{user.emailaddress}</p>
                  </div>
                </div>

                {!user.isAdmin && (
                  <div className="flex gap-2 justify-start">
                    <button
                      className={`flex-1 px-3 py-1 rounded text-sm font-semibold transition ${
                        isServiced
                          ? 'bg-green-500 text-white hover:bg-green-600'
                          : 'bg-red-500 text-white hover:bg-red-600'
                      }`}
                      onClick={(e) => {
                        e.stopPropagation();
                        toggleServiced(user.userId);
                      }}
                    >
                      {isServiced ? 'Accompagné' : 'À accompagner'}
                    </button>

                    <button
                      className="flex-1 bg-gray-200 text-red-600 px-3 py-1 rounded hover:bg-red-100 text-sm font-semibold"
                      onClick={(e) => {
                        e.stopPropagation();
                        handleDelete(user.userId);
                      }}
                      aria-label={`Delete ${user.firstName} ${user.lastName}`}
                    >
                      Supprimer
                    </button>
                  </div>
                )}
              </li>

            );
          })}
        </ul>
      )}
    </div>
  );

};

export default UserList;
