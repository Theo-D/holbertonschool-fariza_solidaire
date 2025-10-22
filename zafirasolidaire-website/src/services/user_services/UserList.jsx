import React, { useState, useEffect } from 'react';
import { deleteUserById, getUsers } from "./userApi";

const UserList = () => {
  const [users, setUsers] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    async function loadUsers() {
      try {
        const res = await getUsers();
        setUsers(res.data);
      } catch (err) {
        setError('Failed to load users');
      } finally {
        setLoading(false);
      }
    }

    loadUsers();
  }, []);

  const handleDelete = async (id) => {
    try {
        await deleteUserById(id);
        // After successful deletion, reload users from the backend
        const updatedUsers = await getUsers();
        setUsers(updatedUsers.data);
    } catch (err) {
        console.error(err);
        alert('Failed to delete user');
    }
};

  if (loading) return <p>Loading...</p>;
  if (error) return <p>{error}</p>;

  return (
    <ul className="list-none bg-base-100 rounded-box shadow-md grid grid-cols-3 gap-4 p-4">
        {users.map(user => (
            <li
            className="flex items-center space-x-4 bg-white rounded-lg p-4 shadow"
            key={user.userId}
            >
            <div>
                <img
                className="w-10 h-10 rounded-full object-cover"
                src={user.photoUrl}
                alt="user photo"
                />
            </div>
            <div className="grow">
                <div>{user.firstName} {user.lastName}</div>
                <div className="text-xs font-semibold opacity-60">{user.emailaddress}</div>
            </div>

            {!user.isAdmin && (
                <button
                className="btn btn-square btn-ghost"
                onClick={() => handleDelete(user.userId)}
                aria-label={`Delete ${user.firstName} ${user.lastName}`}
                >
                <svg
                    className="w-5 h-5 text-red-600"  // Tailwind class to make it red
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
            )}
            </li>
        ))}
    </ul>
  );
};

export default UserList;
