import React from 'react';
import UserList from '../services/user_services/UserList';

const Admin = () => {
  return (
    <div>
      <h1>Admin Dashboard</h1>
      <section>
        <h2>Users</h2>
        <UserList />
      </section>
    </div>
  );
};

export default Admin;
