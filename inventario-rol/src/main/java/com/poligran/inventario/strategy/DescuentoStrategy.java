package com.poligran.inventario.strategy;

import com.poligran.inventario.model.enums.CategoriaProducto;
import java.math.BigDecimal;

/**
 * Interfaz que define el contrato del patron de diseno GoF Strategy.
 * Cada estrategia concreta encapsula la politica de calculo de descuento
 * aplicable a una categoria especifica de productos ludicos.
 */
public interface DescuentoStrategy {

    CategoriaProducto getCategoriaSoportada();

    BigDecimal calcularPrecioFinal(BigDecimal precioBase);

    BigDecimal obtenerPorcentajeDescuento();

    String obtenerDescripcionRegla();
}
