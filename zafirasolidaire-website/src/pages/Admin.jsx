import React from 'react';
import UserList from '../services/user_services/UserList';
import EventList from '../services/event_services/EventList';

const Admin = () => {
  return (
    <div>
      <h1>Admin Dashboard</h1>
      <section>
        <h2>Utilisateurs</h2>
        <UserList />
      </section>
      <section>
        <h2>Évènements</h2>
        <EventList/>
      </section>
    </div>
  );
};

export default Admin;
