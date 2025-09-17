## 1. System Architecture

    * Style: 
        Client–Server, REST API backend, web frontend.
    
    * Components:
        - Frontend: Web app for job seekers, donators, partners, admins.
        - Backend: REST API handling authentication.
        - Database: Stores users, sessions, availability, and notes.
    
    * External Services: Email/SMS notification service (weezevent).

 📊 Deliverable: High-level diagram showing:

        Clients (browser/mobile) → Backend API → Database
        Backend → External notification service