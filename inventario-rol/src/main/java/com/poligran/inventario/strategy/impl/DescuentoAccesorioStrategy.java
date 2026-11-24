package com.poligran.inventario.strategy.impl;

import com.poligran.inventario.model.enums.CategoriaProducto;
import com.poligran.inventario.strategy.DescuentoStrategy;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Estrategia concreta GoF: Aplica un 8% de descuento en tableros y accesorios ludicos.
 */
@Component
public class DescuentoAccesorioStrategy implements DescuentoStrategy {

    private static final BigDecimal PORCENTAJE = new BigDecimal("8.00");
    private static final BigDecimal FACTOR = new BigDecimal("0.92");

    @Override
    public CategoriaProducto getCategoriaSoportada() {
        return CategoriaProducto.TABLERO_ACCESORIO;
    }

    @Override
    public BigDecimal calcularPrecioFinal(BigDecimal precioBase) {
        if (precioBase == null) return BigDecimal.ZERO;
        return precioBase.multiply(FACTOR).setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public BigDecimal obtenerPorcentajeDescuento() {
        return PORCENTAJE;
    }

    @Override
    public String obtenerDescripcionRegla() {
        return "Descuento del 8% en tableros, pantallas de master y accesorios";
    }
}
