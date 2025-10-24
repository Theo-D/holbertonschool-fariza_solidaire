import React from 'react';
import UserList from '../services/user_services/UserList';
import EventList from '../services/event_services/EventList';
import PartnersList from '../services/partner_services/PartnersList';

const Admin = () => {
  return (
    <div className = "flex flex-col justify-center items-center">
      <h1>Admin Dashboard</h1>
      <section >
        <h2>Utilisateurs</h2>
        <UserList />
      </section>
      <section>
        <h2>Évènements</h2>
        <EventList/>
      </section>
      <section>
        <h2>Partenaires</h2>
        <PartnersList/>
      </section>
    </div>
  );
};

export default Admin;
