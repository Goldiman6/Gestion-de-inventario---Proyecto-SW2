package com.poligran.inventario.strategy.impl;

import com.poligran.inventario.model.enums.CategoriaProducto;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DescuentoMiniaturaStrategy extends AbstractDescuentoStrategy {

    public DescuentoMiniaturaStrategy() {
        super(
            CategoriaProducto.MINIATURA,
            new BigDecimal("5.00"),
            "Descuento del 5% en figuras y miniaturas de combate"
        );
    }
}
