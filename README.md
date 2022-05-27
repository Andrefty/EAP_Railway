# ProiectEAP
Proiectul încearcă să implemeneteze un sistem de gestiune pentru o companie feroviara, care pe lângă transportul de pasageri, oferă și un serviciu de curierat între stații.
Pentru Stage 1, diagrama reprezintă relativ înlănțuirea dintre entități.
![](Diagrama_stage_1.drawio.svg)
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
  
Pentru Stage 2, am adăugat interfața GenericCSVIO, care este template pentru clasele de IO în CSV ale fiecărei clase.

Tot în cadrul Stage 2, am adăugat clasa AuditService, care înregistrează modificările într-un fișier CSV.  
- AuditService
- Service
- GenericCSVIO
  - Job2CSV
  - Passenger2CSV
  - Sender2CSV
  - Station2CSV
  
## View
Conține clasa ce interfațează cu utilizatorul, oferind operațiile de adăugare/listare pentru fiecare entitate.
De asemenea, exista o optiune ce afiseaza pasagerii care calatoresc cu un anumit tren.
## Stage 3
Am înlocuit serviciile ce foloseau CSV la stage 2 cu altele ce folosesc o baza de date MySQL, ce implementeaza CRUD.
| :exclamation:  This is very important   |
|-----------------------------------------|
Când interacțiunea se face doar cu clasele ce au persistență în baza de date, pentru alegerea entităților se folosesc ID-urile din baza de date.
Altfel, când interacțiunile se fac între clasele care nu au persistență, sau între cele 2 tipuri de clase, se folosesc numerotările de pe marginea stângă ca ID-uri.