package com.poligran.inventario.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Gestion de Inventario - Tienda de Coleccionables y Juegos de Rol")
                        .version("1.0.0")
                        .description("Servicio RESTful con arquitectura en capas para administracion del catalogo y control de stock de productos de rol y coleccionables.")
                        .contact(new Contact()
                                .name("Equipo de Desarrollo Politecnico Grancolombiano")
                                .email("Jsaperez@poligran.edu.co"))
                        .license(new License().name("Apache 2.0").url("https://springdoc.org")));
    }
}
