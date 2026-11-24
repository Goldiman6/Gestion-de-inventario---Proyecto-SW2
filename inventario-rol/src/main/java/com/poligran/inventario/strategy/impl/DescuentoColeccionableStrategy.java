package com.poligran.inventario.strategy.impl;

import com.poligran.inventario.model.enums.CategoriaProducto;
import com.poligran.inventario.strategy.DescuentoStrategy;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Estrategia concreta GoF: Articulo de coleccion exclusivo sin descuento comercial.
 */
@Component
public class DescuentoColeccionableStrategy implements DescuentoStrategy {

    private static final BigDecimal PORCENTAJE = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);

    @Override
    public CategoriaProducto getCategoriaSoportada() {
        return CategoriaProducto.FIGURA_COLECCIONABLE;
    }

    @Override
    public BigDecimal calcularPrecioFinal(BigDecimal precioBase) {
        if (precioBase == null) return BigDecimal.ZERO;
        return precioBase.setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public BigDecimal obtenerPorcentajeDescuento() {
        return PORCENTAJE;
    }

    @Override
    public String obtenerDescripcionRegla() {
        return "Articulo exclusivo de coleccion sin descuento aplicable";
    }
}
