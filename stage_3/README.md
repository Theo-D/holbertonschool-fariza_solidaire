# ZAFIRA SOLIDAIRE - Portfolio - Stage 3: Technical Documentation

## In this stage of the project, the team will create a comprehensive blueprint for building the MVP. By planning technical aspects, source control, and quality assurance in advance, the team reduces risks, improves clarity, and enhances the development process

## 0 - Defining users stories

### Must-Have

    - As a Beneficiary, I want to be able to create an account so I can have access to the website's services.

    - As a Beneficiary, I want to be able consult events so I can take part in them given the availability.

    - As an Admin, I want to be able to manage events so that the association has a support to fulfill its missions.

    - As an Admin, I want to be able to manage events so that the association has a support to fulfill its missions.

    - As a Donator, I want to be able to take an apointment at a given location and a given date so I can donate clothes.

### Should-Have

    - As a Job Seeker, I want to upload a picture of my outfit so I can get remote feedback.

    - As a Volunteer, I want to leave notes after a session to track advice given.

### Could-have

    - As a Job Seeker, I want automated reminders via email/SMS so I don’t miss my appointment.

### Won't-have

    - As a job Seeker, I want to be able to bet my RSA on rooster fights.

---

## Mock-up

There is the intended look of the website, with first steps of involvment in graphical chart.

![Global view of the website](./img/mockup/Mockup-Zafira-Solidaire.png)

---

The front page is the first one seen when a user will connect to the website. It displays a summary of the site content, and some Zafira-Solidaire activities.

![Front-page](./img/mockup/Zafira-Solidaire_front_page.png)

---

The blogs page will display recent interviews of local representatives, or articles concerning work market.

![Blogs page](./img/mockup/Zafira-Solidaire_blog_page.png)

---

The event page will show the different workshops created by or involving Zafira-Solidaire and which a user can attend.

![Event page](./img/mockup/Zafira-Solidaire_event_page.png)

---

The admin dashboard aims to give a simple toolbox to an administrator willing to quickly change the website content, divided by pages.

![Admin dashboard](./img/mockup/Zafira-Solidaire_admin_dashboard.png)

---

The user page is a reminder of all the ways to contact the association, and a summary on the association philosophy.

![User page](./img/mockup/Zafira-Solidaire_user_page.png)

---

## 1 - Design System Architecture

![Design Architecture Diagram](./img/Zafira_Solidaire_Architecture_Diag.drawio.png)

---

## 2 - Defining Components, Classes, and Database Design

### Class Diagram

![Class Diagram](./img/Zafira_Solidaire_Class_Diagram.drawio.png)

---

### Entity Relationship Diagram

![ER Diagram](./img/Zafira_Solidaire_ER_Diag.drawio.png)

---

## 3 - Creating High-Level Sequence Diagrams

### User Account Creation Sequence Diagram

```mermaid

sequenceDiagram
    actor User
    participant UI
    participant API
    participant Database

    User->>+UI: Submit User creation form
    UI->>+API: POST /api/user

    alt Validation Fails
        API->>API: Validate email address format and fields
        API-->>UI: Return HTTP 400 Bad Request
        UI-->>User: Show validation errors

    else Success
        API->>API: Validate incoming User data and hashes password
        API->>+Database: INSERT INTO Users (user attributes)
        Database-->>API: Return newly created User with id
        API-->>UI: Return HTTP 201 Created (with jsonified user object)
        UI-->>User: "Account created successfully"
        UI-->>UI: Redirects User to login page
    end

    deactivate UI
    deactivate Database
    deactivate API

```

### User Login Sequence Diagram

```mermaid

sequenceDiagram
    actor User
    participant UI
    participant API
    participant Database

    User->>+UI: Submit login form
    UI->>+API: POST /api/login

    API->>+Database: Find user matching credentials
    Database-->>API: Return user data or null

    alt Credentials are valid
        API-->>UI: Return HTTP 200 OK (with JSON Web Token)
        UI-->>User: "Login successful!"
        UI->>UI: Redirect to main page

    else Credentials are invalid
        API-->>UI: Return HTTP 401 Unauthorized
        UI-->>User: Show "Invalid credentials" error
    end

    deactivate API
    deactivate UI

```

### Admin Event Creation Sequence Diagram

```mermaid

sequenceDiagram
    actor Admin
    participant Dashboard
    participant API
    participant Database

    Admin->>+Dashboard: Submit event creation form
    Dashboard->>+API: POST /api/event

    alt Authorization Fails
        API->>API: Verify Admin's permissions
        API-->>Dashboard: Return HTTP 403 Forbidden
        Dashboard-->>Admin: Show "Permission Denied" error

    else Validation Fails
        API->>API: Validate incoming event data
        API-->>Dashboard: Return HTTP 400 Bad Request
        Dashboard-->>Admin: Show validation errors

    else Success
        API->>API: Validate incoming event data
        API->>+Database: INSERT INTO Events (event attributes)
        Database-->>API: Return newly created event with id
        API-->>Dashboard: Return HTTP 201 Created (with jsonified event object)
        Dashboard-->>Admin: "Event created successfully!"
    end

    deactivate Dashboard
    deactivate Database
    deactivate API

```

## 4 - Planning SCM and QA Strategies

### Source Control Management Tools and Strategy

Our team will use **Git** as the version control system, with a **feature-based branching strategy**. This approach helps isolate development tasks and supports parallel teamwork.

### Branching Strategy

Each feature or task will be developed in its own branch. A "feature" typically represents the creation or update of a file, module, or functionality.

### Example Branch Names

- Creating a new model:
  `feature/create-user-model`
- Updating an existing model:
  `feature/update-user-model`

Once the feature is complete and tested, it will be **merged into the `development` branch** via a **Pull Request (PR)**, and the feature branch will be closed.

The branch flow will go as follow: feature/* → development → release → main

### SCM Best Practices

- Follow adopted **branch naming conventions**.
- Submit **Pull Requests** for all merges to `development` and `release`.
- **peer code reviews** before merging.

---

### Quality Assurance Tools and Strategy

### Backend Testing

We will use **Spring Boot’s testing mechanisms**, including:

- **Unit Testing** — using Mockito
- **Integration Testing** — testing interactions between services and components

### Frontend Testing

Frontend testing will be handled using:

- **React Testing Library**
- **Jest**

---

## SCM and QA workflow diagram

Below is a workflow diagram summarizing the above strategies.

![SCM and QA workflow diagram](./img/Zafira_solidaire_scm_and_qa_flow_diag.drawio.png)
