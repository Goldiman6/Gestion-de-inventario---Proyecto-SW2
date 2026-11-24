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
