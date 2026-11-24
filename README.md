# Inventario ROL — API REST para Gestion de Tienda de Juegos de Rol y Coleccionables
[![Java](https://img.shields.io/badge/Java-17-orange.svg)](https://openjdk.org/projects/jdk/17/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.5-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Maven](https://img.shields.io/badge/Maven-3.9.9-blue.svg)](https://maven.apache.org/)
[![SonarQube](https://img.shields.io/badge/SonarQube-Quality%20Gate%20Passed-success.svg)](http://localhost:9000)
[![License](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)
Proyecto academico desarrollado para Ingenieria de Software 2 — Politecnico Grancolombiano.
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
