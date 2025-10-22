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
      // 1. Update user object with new isServiced value
      const updatedUser = { ...currentUser, isServiced: newServicedState };
      console.log("UPDATED USER: ", updatedUser);
      await updateUser(updatedUser.userId, updatedUser); // <-- save updated user to backend

      // 2. Create or delete serviced user
      if (newServicedState) {
        await createServicedUser(userId);
      } else {
        await deleteServicedUserByUserId(userId);
      }

      // 3. Update local users state
      const updatedUsers = [...users];
      updatedUsers[userIndex] = updatedUser;
      setUsers(updatedUsers);

    } catch (error) {
      console.error('Error toggling serviced state:', error);
      alert('Could not update serviced state.');
    }
  };

  const handleDelete = async (id) => {
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
    <ul className="list-none bg-base-100 rounded-box shadow-md grid grid-cols-3 gap-4 p-4">
      {users.map((user) => {
        const isServiced = user.isServiced;

        const buttonClass = `btn btn-active w-36 ${
          isServiced ? 'bg-green-600 text-gray-900' : 'bg-red-700 text-gray-300'
        }`;

        return (
          <li
            className="flex items-center space-x-4 bg-white rounded-lg p-4 shadow"
            key={user.userId}
          >
            <img
              className="w-10 h-10 rounded-full object-cover"
              src={user.photoUrl || '/default-user.png'}
              alt="user photo"
            />
            <div className="grow">
              <div>{user.firstName} {user.lastName}</div>
              <div className="text-xs font-semibold opacity-60">{user.emailaddress}</div>
            </div>

            {!user.isAdmin && (
              <>
                <button
                  className={buttonClass}
                  onClick={() => toggleServiced(user.userId)}
                >
                  {isServiced ? 'Serviced' : 'Mark as Serviced'}
                </button>

                <button
                  className="btn btn-square btn-ghost"
                  onClick={() => handleDelete(user.userId)}
                  aria-label={`Delete ${user.firstName} ${user.lastName}`}
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
              </>
            )}
          </li>
        );
      })}
    </ul>
  );
};

export default UserList;
