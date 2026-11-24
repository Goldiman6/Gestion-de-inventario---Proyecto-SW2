package com.poligran.inventario.strategy.impl;

import com.poligran.inventario.model.enums.CategoriaProducto;
import com.poligran.inventario.strategy.DescuentoStrategy;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Clase base abstracta que centraliza la logica comun del calculo
 * de descuentos, eliminando duplicacion de codigo segun lineamientos SonarQube.
 */
public abstract class AbstractDescuentoStrategy implements DescuentoStrategy {

    private final CategoriaProducto categoria;
    private final BigDecimal porcentaje;
    private final String descripcion;

    protected AbstractDescuentoStrategy(CategoriaProducto categoria, BigDecimal porcentaje, String descripcion) {
        this.categoria = categoria;
        this.porcentaje = porcentaje;
        this.descripcion = descripcion;
    }

    @Override
    public CategoriaProducto getCategoriaSoportada() {
        return categoria;
    }

    @Override
    public BigDecimal obtenerPorcentajeDescuento() {
        return porcentaje;
    }

    @Override
    public String obtenerDescripcionRegla() {
        return descripcion;
    }

    @Override
    public BigDecimal calcularPrecioFinal(BigDecimal precioBase) {
        if (precioBase == null) {
            return BigDecimal.ZERO;
        }
        if (porcentaje.compareTo(BigDecimal.ZERO) == 0) {
            return precioBase.setScale(2, RoundingMode.HALF_UP);
        }
        BigDecimal factor = BigDecimal.ONE.subtract(porcentaje.divide(new BigDecimal("100"), 4, RoundingMode.HALF_UP));
        return precioBase.multiply(factor).setScale(2, RoundingMode.HALF_UP);
    }
}
