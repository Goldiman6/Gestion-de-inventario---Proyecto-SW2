package com.poligran.inventario.dto.request;

import com.poligran.inventario.model.enums.CategoriaProducto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

@Schema(description = "Datos requeridos para crear o actualizar un producto")
public class ProductoRequestDTO {

    @NotBlank(message = "El codigo SKU es obligatorio")
    @Size(max = 50, message = "El codigo SKU no puede superar 50 caracteres")
    @Schema(description = "Codigo SKU unico de inventario", example = "ROL-DD5-001")
    private String codigoSku;

    @NotBlank(message = "El nombre del producto es obligatorio")
    @Size(max = 150, message = "El nombre no puede superar 150 caracteres")
    @Schema(description = "Nombre comercial del producto", example = "Manual del Jugador D&D 5ta Edicion")
    private String nombre;

    @Size(max = 500, message = "La descripcion no puede superar 500 caracteres")
    @Schema(description = "Descripcion detallada del articulo", example = "Libro de reglas basicas para jugadores de rol en tapa dura")
    private String descripcion;

    @NotNull(message = "La categoria del producto es obligatoria")
    @Schema(description = "Categoria ludica del producto", example = "MANUAL_ROL")
    private CategoriaProducto categoria;

    @NotNull(message = "El precio es obligatorio")
    @Positive(message = "El precio debe ser mayor a cero")
    @Schema(description = "Precio unitario en moneda local", example = "180000.00")
    private BigDecimal precio;

    @NotNull(message = "El stock inicial es obligatorio")
    @PositiveOrZero(message = "El stock no puede ser negativo")
    @Schema(description = "Cantidad de unidades disponibles en almacen", example = "25")
    private Integer stock;

    public ProductoRequestDTO() {
    }

    public ProductoRequestDTO(String codigoSku, String nombre, String descripcion,
                              CategoriaProducto categoria, BigDecimal precio, Integer stock) {
        this.codigoSku = codigoSku;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.precio = precio;
        this.stock = stock;
    }

    // Getters y Setters
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
}
