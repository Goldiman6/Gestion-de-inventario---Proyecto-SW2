# API de Gestion de Inventario - Tienda de Coleccionables y Juegos de Rol

Proyecto de aula para la asignatura **Ingenieria de Software 2** - Politécnico Grancolombiano.

---

## 🛠️ Tecnologias Utilizadas
- **Lenguaje:** Java 17 LTS
- **Framework:** Spring Boot 3.2.x (Spring Web, Spring Data JPA, Spring Validation)
- **Gestor de Construccion:** Apache Maven 3.9+
- **Persistencia y Base de Datos:** Hibernate ORM + PostgreSQL 16 (via Docker)
- **Documentacion:** Springdoc OpenAPI 3 (Swagger UI)
- **Patron de Diseno GoF:** Strategy Pattern (Calculo dinamico y polimorfico de politicas de descuento)
- **Contenedores:** Docker & Docker Compose
- **Control de Versiones:** Git & GitHub

---

## 🏛️ Arquitectura del Software (3 Capas + Patron GoF Strategy)
1. **Capa de Controladores (`controller`):** Exposicion de endpoints RESTful, validacion con Jakarta Validation y generacion de documentacion Swagger.
2. **Capa de Servicio y Patron Strategy (`service` y `strategy`):**
   - Interfaz `DescuentoStrategy` y estrategias concretas por categoria (`MANUAL_ROL` 10%, `SET_DADOS` 15%, `TABLERO_ACCESORIO` 8%, `MINIATURA` 5%, `FIGURA_COLECCIONABLE` 0%).
   - Contexto de inyeccion automatica `DescuentoContext` para resolver la politica comercial sin condicionales anidados.
3. **Capa de Repositorio (`repository`):** Persistencia y consultas automaticas con Spring Data JPA sobre PostgreSQL.

---

## 🚀 Guia de Inicio y Ejecucion en Eclipse

### 1. Clonar el Repositorio
```bash
git clone https://github.com/Goldiman6/Gestion-de-inventario---Proyecto-SW2.git
cd Gestion-de-inventario---Proyecto-SW2
```

### 2. Iniciar Base de Datos con Docker (PostgreSQL)
Desde la terminal en la raiz del proyecto:
```bash
docker compose -f docker/docker-compose.yml up -d
```
Esto levantara:
- **PostgreSQL:** `localhost:5432` (Base de datos: `inventario_db`, Usuario: `postgres`, Contrasena: `postgres123`)

*(Opcion sin Docker: en `application.properties` puedes descomentar las lineas de H2 para correr en memoria).*

### 3. Importar y Ejecutar en Eclipse IDE
1. Abrir **Eclipse IDE** (o Spring Tool Suite STS).
2. Ir a: `File` -> `Import...` -> `Maven` -> `Existing Maven Projects`.
3. Seleccionar la carpeta del proyecto y hacer clic en **Finish**.
4. Esperar a que Eclipse descargue las dependencias de Maven.
5. Hacer clic derecho sobre el proyecto -> `Run As` -> `Spring Boot App` (o abrir `InventarioApplication.java` y hacer clic en `Run As` -> `Java Application`).
6. La aplicacion iniciara en el puerto `8080`.

---

## 📖 Documentacion Interactiva (Swagger UI)
Una vez iniciada la aplicacion, accede desde el navegador web a:
- **Swagger UI:** [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
- **Contrato OpenAPI (JSON):** [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs)

### Endpoint destacado GoF Strategy:
- `GET /api/v1/productos/{id}/descuento`: Ejecuta en tiempo de ejecucion la estrategia comercial GoF correspondiente segun la categoria del producto y devuelve el desglose de cotizacion con descuento.
