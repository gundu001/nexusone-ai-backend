# NexusOne AI Phase 2 Backend - MySQL Edition

## Requirements
- Java 21
- Maven 3.9+
- MySQL Server 8.x

## 1. Create the database
Run in MySQL Workbench or the MySQL command line:

```sql
CREATE DATABASE IF NOT EXISTS nexusone_ai
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;
```

The application also includes `createDatabaseIfNotExist=true`, but the configured MySQL account must have permission to create a database for that option to work.

## 2. Configure environment variables in PowerShell

```powershell
$env:DB_URL="jdbc:mysql://localhost:3306/nexusone_ai?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC"
$env:DB_USERNAME="root"
$env:DB_PASSWORD="your-mysql-password"
$env:GITHUB_TOKEN="your-github-token"
```

Do not commit database passwords or GitHub tokens to Git.

## 3. Build and run

```powershell
mvn clean install
mvn spring-boot:run
```

## Application URLs
- Swagger UI: http://localhost:8080/swagger-ui.html
- API base: http://localhost:8080/api

## Main API flow
1. `GET /api/github/repos`
2. `GET /api/github/repos/{owner}/{repo}/branches`
3. `POST /api/apps`
4. `POST /api/apps/analyze`
5. `GET /api/dashboard`

Register an application:

```json
{
  "name": "Order-Service",
  "repositoryUrl": "https://github.com/YOUR_ID/Order-Service",
  "technology": "SPRING_BOOT",
  "branch": "main"
}
```

Analyze a repository:

```json
{
  "repositoryUrl": "https://github.com/YOUR_ID/Order-Service",
  "branch": "main"
}
```

## Verify MySQL tables

```sql
USE nexusone_ai;
SHOW TABLES;
SELECT * FROM applications;
SELECT * FROM deployments;
```

Hibernate creates or updates the `applications` and `deployments` tables when the application starts.
