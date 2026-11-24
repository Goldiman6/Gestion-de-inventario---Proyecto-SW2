package com.poligran.inventario.strategy.impl;

import com.poligran.inventario.model.enums.CategoriaProducto;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DescuentoManualRolStrategy extends AbstractDescuentoStrategy {

    public DescuentoManualRolStrategy() {
        super(
            CategoriaProducto.MANUAL_ROL,
            new BigDecimal("10.00"),
            "Descuento del 10% para manuales y libros de reglas de rol"
        );
    }
}
