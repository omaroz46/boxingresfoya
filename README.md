# boxingresfoya
Dies ist die Implementation für das ÜK Modul 295 Backend Für Applikationen Realisieren.
In diesem Repository wurde das Backend mit den folgenden Vorgaben erstellt:
* Datenbankanbindung
    * Definition der Entitäten
    * Integration JPA Repositories
* Implementierung REST
    * Controller-Eingabe-Validierung und korrekte Status-Rückgabe
    * Definition von min. 4 REST-Controllern mit korrekten HTTP-Methoden
    * Implementierung Services mit CRUD-Operationen
* Security
    * Setup und Anbindung OAuth2-Server (Keycloak)
    * Absicherung von Controller-Methoden mittels Tokens
    * Implementierung rollenbasierter Zugriff auf Controller-Methoden (min. 2 Rollen)
* Testing
    * Integration Swagger UI
    * JUnit Test für Rest Controller
    * JUnit Test für Datenbank (CRUD)

Schritte zur Installation mit den nötigen Konfigurationseinstellungen:
* Datenbankverbing
* Name DB: boxingresfoya
* User: postgres
* Passwort: Inthisbeetch.23
* Einstellung Keycloak
* Anmeldename: omar.ozan
* Passwort: Inthisbeetch.23
* Realmname: http://localhost:8080/admin/master/console/#/ILV
* Client ID: boxingresfoya
* Rollen:
* ROLE_read
* ROLE_update
* ROLE_admin
* User und Passwort und Entsprechende Rolle:
* admin, admin, (ROLE_read, ROLE_update, ROLE_admin)
* user, user, (ROLE_read, ROLE_update)
