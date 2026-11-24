package com.poligran.inventario.strategy.impl;

import com.poligran.inventario.model.enums.CategoriaProducto;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DescuentoColeccionableStrategy extends AbstractDescuentoStrategy {

    public DescuentoColeccionableStrategy() {
        super(
            CategoriaProducto.FIGURA_COLECCIONABLE,
            BigDecimal.ZERO,
            "Articulo exclusivo de coleccion sin descuento aplicable"
        );
    }
}
