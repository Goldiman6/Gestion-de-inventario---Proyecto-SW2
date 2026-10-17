package com.poligran.inventario.dto.response;

import com.poligran.inventario.model.enums.CategoriaProducto;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "Datos de respuesta con informacion del producto")
public class ProductoResponseDTO {

    @Schema(description = "Identificador unico generado por el sistema", example = "1")
    private Long id;

    @Schema(description = "Codigo SKU unico de inventario", example = "ROL-DD5-001")
    private String codigoSku;

    @Schema(description = "Nombre comercial del producto", example = "Manual del Jugador D&D 5ta Edicion")
    private String nombre;

    @Schema(description = "Descripcion detallada", example = "Libro de reglas basicas para jugadores de rol en tapa dura")
    private String descripcion;

    @Schema(description = "Categoria ludica del producto", example = "MANUAL_ROL")
    private CategoriaProducto categoria;

    @Schema(description = "Precio unitario", example = "180000.00")
    private BigDecimal precio;

    @Schema(description = "Cantidad de unidades disponibles", example = "25")
    private Integer stock;

    @Schema(description = "Fecha y hora de registro en el sistema")
    private LocalDateTime fechaCreacion;

    @Schema(description = "Fecha y hora de ultima modificacion")
    private LocalDateTime fechaActualizacion;

    public ProductoResponseDTO() {
    }

    public ProductoResponseDTO(Long id, String codigoSku, String nombre, String descripcion,
                               CategoriaProducto categoria, BigDecimal precio, Integer stock,
                               LocalDateTime fechaCreacion, LocalDateTime fechaActualizacion) {
        this.id = id;
        this.codigoSku = codigoSku;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.precio = precio;
        this.stock = stock;
        this.fechaCreacion = fechaCreacion;
        this.fechaActualizacion = fechaActualizacion;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigoSku() {
        return codigoSku;
    }

    public void setCodigoSku(String codigoSku) {
        this.codigoSku = codigoSku;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public CategoriaProducto getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaProducto categoria) {
        this.categoria = categoria;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDateTime getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(LocalDateTime fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }
}
