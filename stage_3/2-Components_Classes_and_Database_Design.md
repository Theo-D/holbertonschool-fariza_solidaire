## 2 - Defining Components, Classes, and Database Design
```mermaid
sequenceDiagram
    actor Admin
    participant API
    participant Dashboard
    participant Database

    Admin->>+Dashboard: Submit event creation form

    Dashboard->>+API: POST /api/event

    alt Authorization Fails
        API->>API: Verify Admin's permissions
        API-->>-Dashboard: Return HTTP 403 Forbidden
        Dashboard-->>Admin: Show "Permission Denied" error

    else Validation Fails
        API->>API: Validate incoming event data
        API-->>-Dashboard: Return HTTP 400 Bad Request
        Dashboard-->>Admin: Show validation errors

    else Success
        API->>API: Validate incoming event data
        API->>+Database: INSERT INTO Events (event attributes)
        Database-->>-API: Return newly created event with id
        API-->>-Dashboard: Return HTTP 201 Created (with jsonified event object)
        Dashboard-->>Admin: "Event created successfully!"
    end

    deactivate API
    deactivate Database
    deactivate Dashboard

```
