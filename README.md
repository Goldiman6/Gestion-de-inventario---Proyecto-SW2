# API de Gestión de Inventario - Tienda de Coleccionables y Juegos de Rol


---

## 🛠️ Tecnologías Utilizadas
- **Lenguaje:** Java 17 LTS
- **Framework:** Spring Boot 3.2.x (Spring Web, Spring Data JPA, Spring Validation)
- **Gestor de Construcción:** Apache Maven 3.9+
- **Persistencia y Base de Datos:** Hibernate ORM + PostgreSQL 16 (vía Docker)
- **Documentación:** Springdoc OpenAPI 3 (Swagger UI)
- **Pruebas de API:** Postman (Colección incluida en `/postman`)
- **Calidad de Código:** SonarQube Community LTS
- **Contenedores:** Docker & Docker Compose
- **Control de Versiones:** Git & GitHub

---

## 🏛️ Arquitectura del Software (3 Capas)
1. **Capa de Controladores (`controller`):** Exposición de endpoints RESTful, validación con Jakarta Validation y generación de Swagger.
2. **Capa de Servicio (`service`):** Lógica del negocio, validaciones de reglas comerciales y mapeo de datos DTOs.
3. **Capa de Repositorio (`repository`):** Persistencia y consultas automáticas con Spring Data JPA sobre PostgreSQL.

---

## 🚀 Guía de Inicio y Ejecución en Eclipse

### 1. Clonar el Repositorio
```bash
git clone https://github.com/JostinPerez/proyecto-sw2-inventario-rol.git
cd proyecto-sw2-inventario-rol
```

### 2. Iniciar Base de Datos con Docker (PostgreSQL)
Desde la terminal en la raíz del proyecto:
```bash
docker compose -f docker/docker-compose.yml up -d
```
Esto levantará:
- **PostgreSQL:** `localhost:5432` (Base de datos: `inventario_db`, Usuario: `postgres`, Contraseña: `postgres123`)

### 3. Importar y Ejecutar en Eclipse IDE
1. Abrir **Eclipse IDE** (o Spring Tool Suite STS).
2. Ir a: `File` -> `Import...` -> `Maven` -> `Existing Maven Projects`.
3. Seleccionar la carpeta raíz del proyecto y hacer clic en **Finish**.
4. Esperar a que Eclipse descargue las dependencias de Maven.
5. Hacer clic derecho sobre el proyecto -> `Run As` -> `Spring Boot App` (o abrir `InventarioApplication.java` y hacer clic en `Run As` -> `Java Application`).
6. La aplicación iniciará en el puerto `8080`.

---


## 📖 Documentacion Interactiva (Swagger UI)
Una vez iniciada la aplicacion, accede desde el navegador web a:
- Swagger UI: http://localhost:8080/swagger-ui.html
- Contrato OpenAPI (JSON): http://localhost:8080/v3/api-docs
