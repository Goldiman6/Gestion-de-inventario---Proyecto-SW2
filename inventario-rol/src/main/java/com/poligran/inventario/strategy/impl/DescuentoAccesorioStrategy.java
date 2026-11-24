package com.poligran.inventario.strategy.impl;

import com.poligran.inventario.model.enums.CategoriaProducto;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DescuentoAccesorioStrategy extends AbstractDescuentoStrategy {

    public DescuentoAccesorioStrategy() {
        super(
            CategoriaProducto.TABLERO_ACCESORIO,
            new BigDecimal("8.00"),
            "Descuento del 8% en tableros, pantallas de master y accesorios"
        );
    }
}
