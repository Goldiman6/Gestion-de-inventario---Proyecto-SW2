package com.poligran.inventario.strategy.impl;

import com.poligran.inventario.model.enums.CategoriaProducto;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DescuentoDadosStrategy extends AbstractDescuentoStrategy {

    public DescuentoDadosStrategy() {
        super(
            CategoriaProducto.SET_DADOS,
            new BigDecimal("15.00"),
            "Descuento del 15% por adquisicion de sets y paquetes de dados poliedricos"
        );
    }
}
