# Proyecto Escondida

PROYECTO EN DESARROLLO
Fase 1:
API para el registro de pagos de nomina y horas extras.

Este es un proyecto backend desarrollado con **Java 21** y **Spring Boot 3.4.0**. El proyecto implementa una API REST segura utilizando autenticación basada en **JWT (JSON Web Tokens)** y se conecta a una base de datos **PostgreSQL**.

## 🛠️ Tecnologías y Dependencias Principales

*   **Lenguaje:** Java 21
*   **Framework Principal:** Spring Boot 3.4.0
*   **Seguridad:** Spring Security y JJWT (0.11.5) para el manejo y validación de tokens JWT.
*   **Persistencia:** Spring Data JPA (Hibernate)
*   **Base de Datos:**
    *   PostgreSQL (Producción / Principal)
    *   H2 Database (Para entorno de pruebas o ejecución en memoria)
*   **Herramientas Utilitarias:**
    *   **Lombok:** Para reducir el código repetitivo (getters, setters, constructores).
    *   **ModelMapper:** Para el mapeo y transformación entre Entidades y DTOs.
    *   **Spring Dotenv:** Para la carga de variables de entorno desde el archivo `.env`.
*   **Contenedores:** Docker y Docker Compose para orquestación de servicios locales.
*   **CI/CD:** Configurado con GitHub Actions.

## 🚀 Infraestructura y Configuración (Docker Compose)

El proyecto cuenta con un archivo `docker-compose.yml` que levanta los siguientes servicios:

1.  **PostgreSQL (`postgres`)**:
    *   Imagen: `postgres:15`
    *   Puerto expuesto localmente: `5433` (mapeado al `5432` del contenedor)
    *   Volumen persistente: `./data_db` (los datos se guardan de forma local en esta carpeta)
2.  **Aplicación Spring Boot (`app`)**:
    *   Construido mediante el `Dockerfile` del proyecto.
    *   Puerto expuesto: `8081`

## ⚙️ Variables de Entorno (`.env`)

El sistema utiliza un archivo `.env` en la raíz del proyecto para centralizar configuraciones sensibles:

*   `DATABASE_USE` / `DATABASE_PASSWORD` / `DATABASE_NAME`: Credenciales para la conexión a PostgreSQL.
*   `APP_USER` / `APP_PASSWORD`: Credenciales de inicialización para el administrador/usuario por defecto del sistema.
*   `APP_TOKEN`: Clave secreta (Secret Key) utilizada por el servicio JWT para firmar y verificar los tokens.

## 🏃‍♂️ Cómo ejecutar el proyecto

### Usando Docker Compose (Recomendado)
Para levantar tanto la base de datos como la aplicación backend de una sola vez:
```bash
docker-compose up -d --build
```

### Ejecución Local de Desarrollo (Maven)
Si deseas levantar la base de datos en Docker y correr la aplicación localmente en tu IDE:
1. Levanta solo la base de datos:
   ```bash
   docker-compose up -d postgres
   ```
2. Ejecuta la aplicación mediante Maven:
   ```bash
   ./mvnw spring-boot:run
   ```

## 🔒 Seguridad
El sistema está configurado con filtros y proveedores de seguridad de Spring. Las peticiones a rutas protegidas requieren enviar un token JWT válido en los Headers de la petición:
`Authorization: Bearer <tu_token>`
