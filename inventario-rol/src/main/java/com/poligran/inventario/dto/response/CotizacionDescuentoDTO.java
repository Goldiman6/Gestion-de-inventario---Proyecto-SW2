package com.poligran.inventario.dto.response;

import com.poligran.inventario.model.enums.CategoriaProducto;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;

@Schema(description = "Detalle del calculo comercial de descuento ejecutado por el patron GoF Strategy")
public class CotizacionDescuentoDTO {

    @Schema(description = "ID del producto cotizado", example = "1")
    private Long productoId;

    @Schema(description = "Codigo SKU del producto", example = "ROL-DD5-001")
    private String codigoSku;

    @Schema(description = "Nombre comercial", example = "Manual del Jugador D&D 5ta Edicion")
    private String nombre;

    @Schema(description = "Categoria del producto evaluada", example = "MANUAL_ROL")
    private CategoriaProducto categoria;

    @Schema(description = "Precio base del inventario", example = "180000.00")
    private BigDecimal precioBase;

    @Schema(description = "Porcentaje de descuento aplicado por la estrategia", example = "10.00")
    private BigDecimal porcentajeDescuento;

    @Schema(description = "Valor total descontado en moneda local", example = "18000.00")
    private BigDecimal montoDescuento;

    @Schema(description = "Precio final neto a pagar", example = "162000.00")
    private BigDecimal precioFinal;

    @Schema(description = "Estrategia concreta GoF ejecutada", example = "DescuentoManualRolStrategy")
    private String nombreEstrategia;

    @Schema(description = "Descripcion de la politica comercial", example = "Descuento del 10% para manuales y libros de reglas de rol")
    private String descripcionPolitica;

    public CotizacionDescuentoDTO() {
    }

    public CotizacionDescuentoDTO(Long productoId, String codigoSku, String nombre,
                                  CategoriaProducto categoria, BigDecimal precioBase,
                                  BigDecimal porcentajeDescuento, BigDecimal montoDescuento,
                                  BigDecimal precioFinal, String nombreEstrategia,
                                  String descripcionPolitica) {
        this.productoId = productoId;
        this.codigoSku = codigoSku;
        this.nombre = nombre;
        this.categoria = categoria;
        this.precioBase = precioBase;
        this.porcentajeDescuento = porcentajeDescuento;
        this.montoDescuento = montoDescuento;
        this.precioFinal = precioFinal;
        this.nombreEstrategia = nombreEstrategia;
        this.descripcionPolitica = descripcionPolitica;
    }

    // Getters y Setters
    public Long getProductoId() {
        return productoId;
    }

    public void setProductoId(Long productoId) {
        this.productoId = productoId;
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

    public CategoriaProducto getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaProducto categoria) {
        this.categoria = categoria;
    }

    public BigDecimal getPrecioBase() {
        return precioBase;
    }

    public void setPrecioBase(BigDecimal precioBase) {
        this.precioBase = precioBase;
    }

    public BigDecimal getPorcentajeDescuento() {
        return porcentajeDescuento;
    }

    public void setPorcentajeDescuento(BigDecimal porcentajeDescuento) {
        this.porcentajeDescuento = porcentajeDescuento;
    }

    public BigDecimal getMontoDescuento() {
        return montoDescuento;
    }

    public void setMontoDescuento(BigDecimal montoDescuento) {
        this.montoDescuento = montoDescuento;
    }

    public BigDecimal getPrecioFinal() {
        return precioFinal;
    }

    public void setPrecioFinal(BigDecimal precioFinal) {
        this.precioFinal = precioFinal;
    }

    public String getNombreEstrategia() {
        return nombreEstrategia;
    }

    public void setNombreEstrategia(String nombreEstrategia) {
        this.nombreEstrategia = nombreEstrategia;
    }

    public String getDescripcionPolitica() {
        return descripcionPolitica;
    }

    public void setDescripcionPolitica(String descripcionPolitica) {
        this.descripcionPolitica = descripcionPolitica;
    }
}
