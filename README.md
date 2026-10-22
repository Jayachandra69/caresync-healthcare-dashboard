# CareSync — Healthcare Backend

CareSync is the Spring Boot REST API for the CareSync healthcare dashboard. It provides backend services for authentication, role-based access, and healthcare data stored in MySQL.

## Related Projects

* **Frontend:** [CareSync Frontend](https://github.com/Jayachandra69/caresync-frontend)
* **Backend:** [CareSync Healthcare Dashboard](https://github.com/Jayachandra69/caresync-healthcare-dashboard)

## Technology Stack

* Java 21
* Spring Boot
* Spring Security
* Spring Data JPA / Hibernate
* MySQL
* Maven

## Features

* Session-based login and logout
* Current-user endpoint for retrieving the authenticated username and role
* Role-based authorization for protected operations
* MySQL persistence through Spring Data JPA
* REST API intended for use by the CareSync React frontend

## Prerequisites

* JDK 21
* MySQL Server
* Git
* Eclipse or Maven

## Configuration

Configure these environment variables for your local environment.

| Variable                  | Purpose                                                                     | Example                                |
| ------------------------- | --------------------------------------------------------------------------- | -------------------------------------- |
| `DB_URL`                  | JDBC URL for MySQL                                                          | `jdbc:mysql://localhost:3306/hospital` |
| `DB_PASSWORD`             | Password for the configured MySQL user                                      | Set locally; do not commit it          |
| `CARESYNC_ADMIN_USERNAME` | Initial administrator username, if enabled by the application configuration | `admin`                                |
| `CARESYNC_ADMIN_PASSWORD` | Initial administrator password, if enabled by the application configuration | Set securely in your local environment |

The local development database is named `hospital`. Create it in MySQL if it does not already exist:

```sql
CREATE DATABASE hospital;
```

Check `src/main/resources/application.properties` locally for the exact property names and defaults used by your configuration. Keep local configuration containing credentials out of Git.

**Never commit real passwords, database credentials, or other private values to this repository.** Do not put database credentials in frontend variables such as `VITE_*`.

## Run Locally

### Option 1: Eclipse

1. Import the repository into Eclipse as a Maven project.
2. Confirm that JDK 21 is selected.
3. Configure the required environment variables in **Run Configurations → Environment**.
4. Run `HospitalApplication.java` using **Run As → Java Application**.
5. Check the Eclipse console for successful startup.

### Option 2: Windows Command Prompt

From the project root, configure your local environment variables and run the Maven wrapper:

```cmd
set DB_URL=jdbc:mysql://localhost:3306/hospital
set DB_PASSWORD=your_local_mysql_password
set CARESYNC_ADMIN_USERNAME=admin
set CARESYNC_ADMIN_PASSWORD=your_local_admin_password
mvnw.cmd spring-boot:run
```

Replace the example values with your own local settings. Do not share or commit real passwords. Variables set with `set` apply only to that Command Prompt session.

The backend uses port **1222** in the current local setup. If your configuration differs, use the port specified by your application configuration.

## Authentication Endpoints

| Method | Endpoint       | Purpose                                                       |
| ------ | -------------- | ------------------------------------------------------------- |
| `POST` | `/auth/login`  | Authenticate with username and password and create a session  |
| `GET`  | `/auth/me`     | Return the current authenticated user's username and role     |
| `POST` | `/auth/logout` | Clear the security context and invalidate the current session |

For session-based authentication, clients should preserve and send the session cookie on subsequent requests. The React frontend uses credentialed requests during local development.

## Frontend Connection

The frontend's local API base URL is:

```env
VITE_API_URL=http://localhost:1222
```

This is a local backend URL, not a secret. For deployment, configure the frontend with the deployed backend URL and update backend CORS settings to allow only the intended frontend origin.

## Security Notes

* Keep `DB_PASSWORD` and `CARESYNC_ADMIN_PASSWORD` outside source control.
* Use strong, unique passwords.
* Ensure passwords are hashed with a suitable password encoder before storing them.
* Review role-based access rules before exposing the API publicly.
* For production, use HTTPS, secure session-cookie settings, appropriate CSRF protection, and narrowly scoped CORS.
* Remove or disable temporary account-creation or development-only initializer code before production, and change any temporary credentials.
* A successful local run does not prove that the application is production-ready.

## Troubleshooting

* **MySQL access denied:** Verify that `DB_PASSWORD` matches the MySQL account password and that the database user has the required permissions.
* **Database not found:** Confirm that `hospital` exists and `DB_URL` points to the correct host and port.
* **Port already in use:** Stop the other process using port `1222`, or change the configured server port.
* **Frontend cannot reach the API:** Confirm that the backend is running, check `VITE_API_URL`, and verify the backend CORS configuration.

## License

No open-source license has been specified for this project. All rights are reserved by default unless otherwise stated.

