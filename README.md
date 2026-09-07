# NexusOne AI Backend

Spring Boot 3 / Java 21 backend compatible with the supplied NexusOne frontend.

## Run

```powershell
mvn clean test
mvn spring-boot:run
```

Swagger: `http://localhost:8080/swagger-ui/index.html`

## Main APIs

- `GET /api/apps`
- `POST /api/apps/analyze`
- `GET /api/deployments`
- `POST /api/deployments/simulate`
- `POST /api/deployments`
- `POST /api/deployments/{id}/rollback`
- `GET /api/dashboard`
- `GET /api/future/capabilities`

## Included engineering features

- Layered controller/service/repository design
- JPA and persistent H2 file database
- Bean validation and centralized error handling
- CORS configured for Vite on port 5173
- Swagger/OpenAPI and Actuator
- Seed application data
- MVC smoke tests
- Dockerfile and Docker Compose

## Future-ready extension points

The `/api/future/capabilities` endpoint records planned modules for AI RCA, self-healing, Git integration, Kubernetes execution, multi-cloud, and FinOps. These are extension placeholders, not claims of implemented integrations.
