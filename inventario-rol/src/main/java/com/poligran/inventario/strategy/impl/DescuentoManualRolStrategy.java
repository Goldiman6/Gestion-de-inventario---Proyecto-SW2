package com.poligran.inventario.strategy.impl;

import com.poligran.inventario.model.enums.CategoriaProducto;
import com.poligran.inventario.strategy.DescuentoStrategy;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Estrategia concreta GoF: Aplica un 10% de descuento a manuales y libros de rol.
 */
@Component
public class DescuentoManualRolStrategy implements DescuentoStrategy {

    private static final BigDecimal PORCENTAJE = new BigDecimal("10.00");
    private static final BigDecimal FACTOR = new BigDecimal("0.90");

    @Override
    public CategoriaProducto getCategoriaSoportada() {
        return CategoriaProducto.MANUAL_ROL;
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
        return "Descuento del 10% para manuales y libros de reglas de rol";
    }
}
