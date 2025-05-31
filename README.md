# EntrenaSync Fitness Services Microservice

## Descripción

Este microservicio forma parte del ecosistema **EntrenaSync** y se encarga de la gestión completa de servicios de fitness del sistema. Proporciona operaciones CRUD (Crear, Leer, Actualizar, Eliminar) para el manejo de servicios de fitness, incluyendo información de precios, descripciones, ubicaciones y tiempos de duración.

## Tecnologías Utilizadas

-   **Kotlin** - Lenguaje de programación principal
-   **Spring Boot 3.x** - Framework de aplicación
-   **Spring Web** - Para la creación de API REST
-   **Spring Data MongoDB** - Integración con base de datos MongoDB
-   **MongoDB** - Base de datos NoSQL para persistencia
-   **Jakarta Validation** - Validación de datos de entrada
-   **Spring Cache** - Sistema de caché para optimización
-   **Maven** - Gestión de dependencias y construcción del proyecto

## Características Principales

### Gestión de Servicios de Fitness

-   ✅ Creación de nuevos servicios de fitness
-   ✅ Consulta de servicios por ID y nombre
-   ✅ Listado paginado de servicios
-   ✅ Actualización de información de servicios
-   ✅ Eliminación de servicios
-   ✅ Validación completa de datos de entrada
-   ✅ Sistema de caché integrado
-   ✅ Manejo global de excepciones

### Modelos de Datos

-   **FitnessService**: Información completa del servicio (nombre, precio, descripción, ubicación, tiempo)

## Estructura del Proyecto

```
src/main/kotlin/entrenasync/dev/entrenasyncfitnessservicesmicroservice/
├── Controllers/
│   └── FitnessServiceController.kt      # Endpoints REST
├── Dto/
│   ├── FitnessServiceCreateRequest.kt   # DTO para creación
│   ├── FitnessServiceUpdateRequest.kt   # DTO para actualización
│   └── FitenessServiceResponse.kt       # DTO de respuesta
├── Models/
│   └── FitnessService.kt               # Entidad principal
├── Services/
│   ├── IFitnessService.kt              # Interfaz del servicio
│   └── FitenssServiceImpl.kt           # Implementación del servicio
├── Repositories/
│   └── IFitnessServicesRepository.kt   # Repositorio de servicios
├── Mappers/
│   └── FitenessServicesMappers.kt      # Transformadores de datos
├── Exceptions/
│   └── FitnessServiceException.kt      # Excepciones personalizadas
└── ExceptionHandler/
    └── GlobalExceptionHandler.kt       # Manejo global de errores
```

## API Endpoints

### Fitness Services

| Método   | Endpoint                | Descripción                            |
| -------- | ----------------------- | -------------------------------------- |
| `GET`    | `/services`             | Obtener todos los servicios (paginado) |
| `GET`    | `/services/{id}`        | Obtener servicio por ID                |
| `GET`    | `/services/name/{name}` | Buscar servicio por nombre             |
| `POST`   | `/services`             | Crear nuevo servicio                   |
| `PUT`    | `/services/{id}`        | Actualizar servicio existente          |
| `DELETE` | `/services/{id}`        | Eliminar servicio                      |

## Configuración

### Variables de Entorno

#### Desarrollo (application-dev.properties)

-   `MONGO_HOST`: Host de MongoDB (default: fitness-services-microservice-mongo-db)
-   `MONGO_PORT`: Puerto de MongoDB (default: 27017)
-   `MONGO_DB`: Nombre de la base de datos (default: fitnessServices)
-   `DATABASE_USER`: Usuario de MongoDB (default: admin)
-   `DATABASE_PASSWORD`: Contraseña de MongoDB (default: adminPassword123)

#### Producción (application-prod.properties)

-   `MONGO_URI`: URI de conexión a MongoDB Atlas
-   `MONGO_DB`: Nombre de la base de datos (default: fitnessServices)

### Profiles de Spring

-   **dev**: Configuración para desarrollo local
-   **prod**: Configuración para producción

## Soporte

Para soporte técnico o preguntas, contacta con el equipo de desarrollo de EntrenaSync.
