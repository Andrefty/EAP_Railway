# ProiectEAP
Proiectul încearcă să implemeneteze un sistem de gestiune pentru o companie feroviara, care pe lângă transportul de pasageri, oferă și un serviciu de curierat între stații.
Pentru Stage 1, diagrama reprezintă relativ înlănțuirea dintre entități.

## Domain
Entitățile folosite sunt:
- Department
- Job
  - JobLocationType: Reprezintă tipurile de job (Remote,Onsite,Hybrid)
- Parcel: Colet ce poate fi livrat între stații
- Person: Reprezintă o persoană generică
  - Employee: O persoană ce este angajat
  - Sender: O persoană ce este expeditor/destinatar pentru un colet
  - Passenger: O persoană ce călătorește în trenurile companiei
    - PassengerType: Reprezintă tipurile de pasager
- Station: Stație tren
- Ticket
- Train
## Persistance/Collections
Conține clasele ce stochează datele folosite în aplicație:
- GenericRepository: Interfața folosită de fiecare Repository, conține prototipurile pentru operații de bază
  - DepartmentRepository
  - EmployeeRepository
  - JobRepository
  - ParcelRepository
  - PassengerRepository
  - SenderRepository
  - StationRepository
  - TicketRepository
  - TrainRepository
## Services
  Clasa Service conține deocamdată operațiile de adăugare a diverselor entități și de afișare a lor.
## View
Conține clasa ce interfațează cu utilizatorul, oferind operațiile de adăugare/listare pentru fiecare entitate. 
