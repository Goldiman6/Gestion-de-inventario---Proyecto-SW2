# Inventario ROL — API REST para Gestion de Tienda de Juegos de Rol y Coleccionables

[![Java](https://img.shields.io/badge/Java-17-orange.svg)](https://openjdk.org/projects/jdk/17/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.5-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Maven](https://img.shields.io/badge/Maven-3.9.9-blue.svg)](https://maven.apache.org/)
[![SonarQube](https://img.shields.io/badge/SonarQube-Quality%20Gate%20Passed-success.svg)](http://localhost:9000)
[![License](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)


API REST para la gestion de inventario de una tienda especializada en juegos de rol y articulos coleccionables.
Implementa el patron de diseno **GoF Strategy** para el calculo de descuentos por categoria de producto.

---

## Tabla de Contenidos

1. [Descripcion del Proyecto](#descripcion-del-proyecto)
2. [Tecnologias Utilizadas](#tecnologias-utilizadas)
3. [Patron de Diseno GoF Strategy](#patron-de-diseno-gof-strategy)
4. [Estructura del Proyecto](#estructura-del-proyecto)
5. [Requisitos Previos](#requisitos-previos)
6. [Configuracion de Base de Datos](#configuracion-de-base-de-datos)
   - [Opcion A: H2 en Memoria (Recomendado para desarrollo)](#opcion-a-h2-en-memoria-recomendado)
   - [Opcion B: PostgreSQL con Docker](#opcion-b-postgresql-con-docker)
7. [Ejecucion de la Aplicacion](#ejecucion-de-la-aplicacion)
8. [Endpoints REST Disponibles](#endpoints-rest-disponibles)
9. [Coleccion Postman](#coleccion-postman)
10. [Analisis de Calidad con SonarQube](#analisis-de-calidad-con-sonarqube)
11. [Documentacion Swagger](#documentacion-swagger)
12. [Autor](#autor)

---

## Descripcion del Proyecto

Este servicio expone una API REST para gestionar el inventario de una tienda de juegos de rol y coleccionables.
Permite crear, consultar, actualizar y eliminar productos del inventario, ademas de calcular descuentos
automaticos segun la categoria de cada producto utilizando el patron de comportamiento Strategy (GoF).

**Categorias de producto disponibles:**

| Categoria | Descripcion | Descuento |
|-----------|-------------|-----------|
| `MANUAL_ROL` | Manuales y libros de reglas | 10% |
| `SET_DADOS` | Sets y paquetes de dados poliedricos | 15% |
| `MINIATURA` | Miniaturas y figuras para wargames | 5% |
| `TABLERO_ACCESORIO` | Tableros y accesorios de mesa | 8% |
| `FIGURA_COLECCIONABLE` | Figuras exclusivas de coleccion | 0% |

---

## Tecnologias Utilizadas

- **Java 17** (compilacion objetivo, ejecutable con JDK 17+)
- **Spring Boot 3.2.5**
  - Spring Web (REST)
  - Spring Data JPA (acceso a datos)
  - Spring Validation (validacion de entrada)
- **H2 Database** (base de datos en memoria, configuracion por defecto)
- **PostgreSQL** (opcional, con Docker)
- **Lombok** (reduccion de codigo boilerplate)
- **SpringDoc OpenAPI 2.5.0** (Swagger UI)
- **JaCoCo 0.8.11** (reportes de cobertura de codigo)
- **SonarQube** (analisis de calidad de codigo)
- **Maven 3.9.9** (gestion de dependencias y build)
- **Docker + Docker Compose** (opcional para PostgreSQL)

---

## Patron de Diseno GoF Strategy

El patron **Strategy** es un patron de comportamiento que define una familia de algoritmos,
encapsula cada uno y los hace intercambiables sin modificar el codigo cliente.

### Implementacion en este proyecto

```
strategy/
├── DescuentoStrategy.java              <- Interfaz (contrato)
├── context/
│   └── DescuentoContext.java           <- Contexto (selecciona la estrategia)
└── impl/
    ├── AbstractDescuentoStrategy.java  <- Clase base con logica comun
    ├── DescuentoManualRolStrategy.java <- 10% descuento
    ├── DescuentoDadosStrategy.java     <- 15% descuento
    ├── DescuentoMiniaturaStrategy.java <- 5% descuento
    ├── DescuentoAccesorioStrategy.java <- 8% descuento
    └── DescuentoColeccionableStrategy.java <- 0% descuento
```

**Endpoint del patron GoF:**
```
GET /api/v1/productos/{id}/descuento
```
Devuelve el precio original, porcentaje de descuento y precio final segun la categoria del producto.

---

## Estructura del Proyecto

```
inventario-rol/
├── src/
│   ├── main/
│   │   ├── java/com/poligran/inventario/
│   │   │   ├── InventarioApplication.java
│   │   │   ├── config/
│   │   │   │   └── OpenApiConfig.java
│   │   │   ├── controller/
│   │   │   │   └── ProductoController.java
│   │   │   ├── dto/
│   │   │   │   ├── request/ProductoRequestDTO.java
│   │   │   │   └── response/
│   │   │   │       ├── ProductoResponseDTO.java
│   │   │   │       └── CotizacionDescuentoDTO.java
│   │   │   ├── exception/
│   │   │   │   ├── GlobalExceptionHandler.java
│   │   │   │   ├── ResourceNotFoundException.java
│   │   │   │   └── BadRequestException.java
│   │   │   ├── model/
│   │   │   │   ├── entity/Producto.java
│   │   │   │   └── enums/CategoriaProducto.java
│   │   │   ├── repository/
│   │   │   │   └── ProductoRepository.java
│   │   │   ├── service/
│   │   │   │   ├── ProductoService.java
│   │   │   │   └── impl/ProductoServiceImpl.java
│   │   │   └── strategy/
│   │   │       ├── DescuentoStrategy.java
│   │   │       ├── context/DescuentoContext.java
│   │   │       └── impl/
│   │   │           ├── AbstractDescuentoStrategy.java
│   │   │           ├── DescuentoManualRolStrategy.java
│   │   │           ├── DescuentoDadosStrategy.java
│   │   │           ├── DescuentoMiniaturaStrategy.java
│   │   │           ├── DescuentoAccesorioStrategy.java
│   │   │           └── DescuentoColeccionableStrategy.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       ├── java/.../InventarioApplicationTests.java
│       └── resources/application.properties   <- H2 para tests
├── docker/
│   └── docker-compose.yml
├── postman/
│   └── Inventario_Rol.postman_collection.json
├── Dockerfile
├── pom.xml
└── README.md
```

---

## Requisitos Previos

| Herramienta | Version minima | Verificacion |
|-------------|---------------|-------------|
| JDK | 17+ | `java -version` |
| Maven | 3.8+ | `mvn -version` |
| Git | cualquier | `git --version` |
| Docker | 20+ (opcional) | `docker --version` |
| Docker Compose | 2+ (opcional) | `docker compose version` |

---

## Configuracion de Base de Datos

### Opcion A: H2 en Memoria (Recomendado)

**No requiere instalacion adicional.** La base de datos se crea automaticamente al iniciar la app
y se destruye al detenerla. Ideal para desarrollo y pruebas.

El archivo `src/main/resources/application.properties` ya tiene H2 configurado por defecto:

```properties
spring.datasource.url=jdbc:h2:mem:inventario_db;DB_CLOSE_DELAY=-1
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=create-drop
spring.h2.console.enabled=true
```

**Consola H2** disponible en: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:inventario_db`
- Usuario: `sa`
- Contrasena: (vacio)

---

### Opcion B: PostgreSQL con Docker

#### Paso 1 — Levantar PostgreSQL con Docker Compose

Desde la carpeta raiz del proyecto:

```bash
docker compose -f docker/docker-compose.yml up -d
```

Esto inicia un contenedor PostgreSQL con:
- Puerto: `5432`
- Base de datos: `inventario_db`
- Usuario: `postgres`
- Contrasena: `postgres123`

Verificar que el contenedor esta corriendo:

```bash
docker ps
```

#### Paso 2 — Cambiar la configuracion en application.properties

Edita `src/main/resources/application.properties`:

Comenta la seccion H2:
```properties
# spring.datasource.url=jdbc:h2:mem:inventario_db;DB_CLOSE_DELAY=-1
# spring.datasource.driver-class-name=org.h2.Driver
# spring.datasource.username=sa
# spring.datasource.password=
# spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
# spring.jpa.hibernate.ddl-auto=create-drop
# spring.h2.console.enabled=true
```

Descomenta la seccion PostgreSQL:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/inventario_db
spring.datasource.username=postgres
spring.datasource.password=postgres123
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=update
```

#### Detener Docker cuando termines

```bash
docker compose -f docker/docker-compose.yml down
```

Para eliminar tambien los datos almacenados:

```bash
docker compose -f docker/docker-compose.yml down -v
```

---

## Ejecucion de la Aplicacion

### Desde Maven (linea de comandos)

```bash
# Compilar y ejecutar
mvn spring-boot:run

# Solo compilar (sin ejecutar)
mvn clean package

# Ejecutar el JAR compilado
java -jar target/inventario-rol-1.0.0-SNAPSHOT.jar
```

### Desde Eclipse

1. Clic derecho sobre el proyecto
2. **Run As** → **Spring Boot App**
3. La aplicacion inicia en `http://localhost:8080`

### Desde el JAR con Docker

```bash
# Construir la imagen Docker
docker build -t inventario-rol:1.0.0 .

# Ejecutar el contenedor (con H2 interno)
docker run -p 8080:8080 inventario-rol:1.0.0
```

La API estara disponible en: **`http://localhost:8080`**

---

## Endpoints REST Disponibles

Base URL: `http://localhost:8080/api/v1`

| Metodo | Endpoint | Descripcion |
|--------|----------|-------------|
| `POST` | `/productos` | Crear un nuevo producto |
| `GET` | `/productos` | Listar todos los productos |
| `GET` | `/productos/{id}` | Obtener producto por ID |
| `GET` | `/productos/categoria/{categoria}` | Filtrar por categoria |
| `PUT` | `/productos/{id}` | Actualizar producto completo |
| `DELETE` | `/productos/{id}` | Eliminar producto |
| `GET` | `/productos/{id}/descuento` | **Calcular descuento (Patron GoF Strategy)** |

### Ejemplo: Crear producto

```bash
curl -X POST http://localhost:8080/api/v1/productos \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "Manual Dungeons and Dragons 5ta Edicion",
    "descripcion": "Libro oficial de reglas para DnD 5E",
    "precio": 50000,
    "stock": 10,
    "categoria": "MANUAL_ROL"
  }'
```

### Ejemplo: Consultar descuento (GoF Strategy)

```bash
curl http://localhost:8080/api/v1/productos/1/descuento
```

Respuesta:
```json
{
  "nombreProducto": "Manual Dungeons and Dragons 5ta Edicion",
  "categoria": "MANUAL_ROL",
  "precioOriginal": 50000.00,
  "porcentajeDescuento": 10.00,
  "precioFinal": 45000.00,
  "descripcionRegla": "Descuento del 10% para manuales y libros de reglas de rol"
}
```

---

## Coleccion Postman

El archivo de coleccion Postman se encuentra en:

```
postman/Inventario_Rol.postman_collection.json
```

### Importar en Postman

1. Abrir **Postman**
2. Clic en **Import**
3. Seleccionar el archivo `postman/Inventario_Rol.postman_collection.json`
4. La coleccion "Inventario ROL API" aparecera en el panel izquierdo

### Requests incluidos en la coleccion

| # | Nombre | Metodo | URL |
|---|--------|--------|-----|
| 1 | Crear Producto MANUAL_ROL | POST | `/api/v1/productos` |
| 2 | Crear Producto SET_DADOS | POST | `/api/v1/productos` |
| 3 | Crear Producto MINIATURA | POST | `/api/v1/productos` |
| 4 | Listar Todos los Productos | GET | `/api/v1/productos` |
| 5 | Obtener Producto por ID | GET | `/api/v1/productos/1` |
| 6 | Actualizar Producto | PUT | `/api/v1/productos/1` |
| 7 | Calcular Descuento GoF Strategy | GET | `/api/v1/productos/1/descuento` |

> **Nota:** Los requests se ejecutan en orden. Primero crea un producto (request 1) para obtener un ID valido y luego ejecuta los demas.

---

## Analisis de Calidad con SonarQube

### Prerequisito: SonarQube corriendo en localhost:9000

Si SonarQube no esta activo, inicialo desde su carpeta de instalacion.

### Ejecutar analisis

```bash
mvn clean verify org.sonarsource.scanner.maven:sonar-maven-plugin:sonar \
  -Dsonar.projectKey=API_Proyecto \
  -Dsonar.projectName="API_Proyecto" \
  -Dsonar.host.url=http://localhost:9000 \
  -Dsonar.token=TU_TOKEN_AQUI
```

Reemplaza `TU_TOKEN_AQUI` con el token generado en SonarQube (Administration → Security → Users → Tokens).

### Ver resultados

Abre en el navegador: `http://localhost:9000/dashboard?id=API_Proyecto`

### Configuracion de calidad en pom.xml

```xml
<sonar.projectKey>API_Proyecto</sonar.projectKey>
<sonar.projectName>API_Proyecto</sonar.projectName>
<!-- Excluir DTOs y estrategias concretas del analisis de duplicacion -->
<sonar.cpd.exclusions>**/dto/**,**/strategy/impl/**</sonar.cpd.exclusions>
<!-- Excluir clases sin logica de negocio -->
<sonar.exclusions>**/config/**</sonar.exclusions>
<!-- Excluir de cobertura las capas sin logica testeable -->
<sonar.coverage.exclusions>**/dto/**,**/config/**,**/exception/**,**/model/**</sonar.coverage.exclusions>
```

---

## Documentacion Swagger

Con la aplicacion corriendo, accede a la documentacion interactiva:

- **Swagger UI:** `http://localhost:8080/swagger-ui.html`
- **OpenAPI JSON:** `http://localhost:8080/v3/api-docs`

Desde Swagger UI puedes probar todos los endpoints directamente en el navegador sin necesidad de Postman.

---

