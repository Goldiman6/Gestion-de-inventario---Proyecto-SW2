package com.poligran.inventario.strategy.impl;

import com.poligran.inventario.model.enums.CategoriaProducto;
import com.poligran.inventario.strategy.DescuentoStrategy;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Estrategia concreta GoF: Aplica un 15% de descuento a paquetes y sets de dados.
 */
@Component
public class DescuentoDadosStrategy implements DescuentoStrategy {

    private static final BigDecimal PORCENTAJE = new BigDecimal("15.00");
    private static final BigDecimal FACTOR = new BigDecimal("0.85");

    @Override
    public CategoriaProducto getCategoriaSoportada() {
        return CategoriaProducto.SET_DADOS;
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
        return "Descuento del 15% por adquisicion de sets y paquetes de dados poliedricos";
    }
}
