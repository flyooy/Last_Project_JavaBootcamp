English Version

# TrashNoting

TrashNoting is a secondhand goods trading platform based on Spring Boot and a front-end framework, allowing users to post, buy, and manage secondhand items. The project includes features such as user authentication, product posting, shopping lists, and wishlists.

## Table of Contents

```plaintext
project-root/
├── backend/
└── frontend/
Tech Stack
Backend: Spring Boot, Spring Security, JPA, Hibernate, Postgresql
Frontend: Javascript React HTML CSS
Backend
The backend API primarily provides functionalities for user authentication, product management, and user data management.

Main Modules
AuthController
Handles user registration and login, supporting token generation.

Main Endpoints:
/api/v1/auth/signup: User registration.
/api/v1/auth/signin: User login and token retrieval.
BenutzerController
Manages user’s product information, including selling items and purchase history.

Main Endpoints:
/api/v1/benutzer/sellItem: Sell an item.
/api/v1/benutzer/soldItems/{id}: Get the sold items list of a specific user.
/api/v1/benutzer/allSoldItems: Get a list of all sold items.
BenutzerDetailController
Provides detailed information of a single user.

Main Endpoints:
/api/v1/benutzerDetail/{id}: Get user details by ID.
ProduktController
Manages the creation, update, and retrieval of products.

Main Endpoints:
/api/v1/product: Create a new product.
/api/v1/product/{id}: Update product details.
ProduktEinkaufenController
Handles items in the shopping list.

Main Endpoints:
/api/v1/produkte/addToShoppinglist: Add an item to the shopping list.
/api/v1/produkte/gekauft/{benutzerId}: Get a list of purchased items for a user.
/api/v1/produkte/{id}: Retrieve item details by ID.
/api/v1/produkte/searchByTitle: Search products by title.
/api/v1/produkte/searchByKategorie: Search products by category.
ProduktControllerWithImage
Supports posting products with images.

Main Endpoint:
/api/v1/product/withImage: Create a product with an image.
WunschSetController
Manages the user's wishlist.

Main Endpoints:
/api/v1/wishlist/add: Add an item to the wishlist.
/api/v1/wishlist/{id}: Get user’s wishlist.
Frontend
The frontend provides an interface for user interactions with the platform, including user authentication, product browsing, product search, shopping list management, and wishlist management.

Main Pages
Homepage: Displays recommended secondhand items and allows browsing by category.
Product Details: Shows detailed information about the item, including description, price, and seller info.
User Registration/Login: Allows users to create an account or log in for full functionality.
Shopping List: Shows items added by the user, with options to remove or mark as purchased.
Wishlist: Allows users to add favorite items for future reference.
Deployment
Backend Deployment
Ensure Docker and Docker Compose are installed.

Create a .env file in the project root and add the required database connection info.
Run the following commands to start the backend:

docker-compose up --build
Frontend Deployment
Ensure Node.js is installed.

API Documentation
The backend API documentation is accessible via Swagger or can be integrated into Postman. The API base path is http://localhost:8080/api/v1, such as:

POST /api/v1/auth/signin for user login.
POST /api/v1/produkte/addToShoppinglist for adding an item to the shopping list.
Contribution
If you’d like to contribute to this project, please clone the repository and submit a pull request.

License
This project is licensed under the MIT License.  MIT License

git@github.com:brokensea/trashNothing_29_10_2024.git

Deutsch:

Technologiestack
Backend: Spring Boot, Spring Security, JPA, Hibernate, Postgresql
Frontend: Frontend: Javascript React HTML CSS
Backend
Das Backend-API bietet hauptsächlich Funktionen zur Benutzer-Authentifizierung, Produktverwaltung und Benutzerdatenverwaltung.

Hauptmodule
AuthController
Verantwortlich für die Registrierung und Anmeldung von Benutzern, unterstützt die Token-Erstellung.

Hauptschnittstellen:
/api/v1/auth/signup: Benutzerregistrierung.
/api/v1/auth/signin: Benutzeranmeldung und Token-Erhalt.
BenutzerController
Verwaltet die Produktinformationen des Benutzers, einschließlich der Verkaufsliste und Kaufhistorie.

Hauptschnittstellen:
/api/v1/benutzer/sellItem: Artikel verkaufen.
/api/v1/benutzer/soldItems/{id}: Liste der verkauften Artikel eines bestimmten Benutzers abrufen.
/api/v1/benutzer/allSoldItems: Liste aller verkauften Artikel abrufen.
BenutzerDetailController
Bietet detaillierte Informationen eines einzelnen Benutzers.

Hauptschnittstellen:
/api/v1/benutzerDetail/{id}: Benutzerinformationen nach ID abrufen.
ProduktController
Verwaltet die Erstellung, Aktualisierung und den Abruf von Produkten.

Hauptschnittstellen:
/api/v1/product: Neues Produkt erstellen.
/api/v1/product/{id}: Produktdetails aktualisieren.
ProduktEinkaufenController
Verantwortlich für die Verwaltung von Produkten in der Einkaufsliste.

Hauptschnittstellen:
/api/v1/produkte/addToShoppinglist: Artikel zur Einkaufsliste hinzufügen.
/api/v1/produkte/gekauft/{benutzerId}: Liste der gekauften Artikel eines Benutzers abrufen.
/api/v1/produkte/{id}: Artikelinformationen nach ID abrufen.
/api/v1/produkte/searchByTitle: Produkte nach Titel suchen.
/api/v1/produkte/searchByKategorie: Produkte nach Kategorie suchen.
ProduktControllerMitBild
Unterstützt das Veröffentlichen von Produkten mit Bildern.

Hauptschnittstelle:
/api/v1/product/withImage: Produkt mit Bild erstellen.
WunschSetController
Verwaltet die Wunschliste des Benutzers.

Hauptschnittstellen:
/api/v1/wishlist/add: Artikel zur Wunschliste hinzufügen.
/api/v1/wishlist/{id}: Wunschliste des Benutzers abrufen.
Frontend
Das Frontend bietet eine Schnittstelle für die Benutzerinteraktion mit der Plattform, einschließlich Benutzerauthentifizierung, Produktdurchsuchen, Produktsuche, Einkaufslistenverwaltung und Wunschlistenverwaltung.

Hauptseiten
Startseite: Zeigt empfohlene Second-Hand-Artikel an und ermöglicht das Durchsuchen nach Kategorien.
Produktdetails: Zeigt detaillierte Informationen zum Artikel an, einschließlich Beschreibung, Preis und Verkäuferinformationen.
Benutzerregistrierung/Anmeldung: Ermöglicht es Benutzern, ein Konto zu erstellen oder sich anzumelden, um alle Funktionen zu nutzen.
Einkaufsliste: Zeigt vom Benutzer hinzugefügte Artikel an, mit Optionen zum Entfernen oder als gekauft markieren.
Wunschliste: Ermöglicht Benutzern, favorisierte Artikel für spätere Betrachtung hinzuzufügen.
Deployment
Backend-Deployment
Stellen Sie sicher, dass Docker und Docker Compose installiert sind.

Erstellen Sie eine .env-Datei im Projektstamm und fügen Sie die erforderlichen Datenbankverbindungsinformationen hinzu.
Führen Sie die folgenden Befehle aus, um das Backend zu starten:

cd backend
docker-compose up --build

Frontend-Deployment
Stellen Sie sicher, dass Node.js installiert ist.

Abhängigkeiten im Frontend-Ordner installieren:

npm install

Entwicklungsserver starten:

npm start


API-Dokumentation
Die Backend-API-Dokumentation ist über Swagger zugänglich oder kann in Postman integriert werden. Der API-Basispfad lautet http://localhost:8080/api/v1, wie zum Beispiel:

POST /api/v1/auth/signin für die Benutzeranmeldung.
POST /api/v1/produkte/addToShoppinglist für das Hinzufügen eines Artikels zur Einkaufsliste.

Lizenz
Dieses Projekt steht unter der MIT-Lizenz.

git@github.com:brokensea/trashNothing_29_10_2024.git
```
