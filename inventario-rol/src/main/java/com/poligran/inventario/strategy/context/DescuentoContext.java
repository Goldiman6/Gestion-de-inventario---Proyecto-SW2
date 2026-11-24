package com.poligran.inventario.strategy.context;

import com.poligran.inventario.model.enums.CategoriaProducto;
import com.poligran.inventario.strategy.DescuentoStrategy;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Contexto del patron GoF Strategy.
 * Administra las estrategias de descuento disponibles y selecciona
 * la adecuada dinamicamente segun la categoria del producto.
 */
@Component
public class DescuentoContext {

    private final Map<CategoriaProducto, DescuentoStrategy> mapaEstrategias;

    public DescuentoContext(List<DescuentoStrategy> listaEstrategias) {
        this.mapaEstrategias = listaEstrategias.stream()
                .collect(Collectors.toMap(DescuentoStrategy::getCategoriaSoportada, Function.identity()));
    }

    public DescuentoStrategy obtenerEstrategia(CategoriaProducto categoria) {
        DescuentoStrategy estrategia = mapaEstrategias.get(categoria);
        if (estrategia == null) {
            return new DescuentoStrategy() {
                @Override
                public CategoriaProducto getCategoriaSoportada() {
                    return categoria;
                }

                @Override
                public BigDecimal calcularPrecioFinal(BigDecimal precioBase) {
                    return precioBase != null ? precioBase : BigDecimal.ZERO;
                }

                @Override
                public BigDecimal obtenerPorcentajeDescuento() {
                    return BigDecimal.ZERO;
                }

                @Override
                public String obtenerDescripcionRegla() {
                    return "Precio estandar sin descuento comercial";
                }
            };
        }
        return estrategia;
    }

    public BigDecimal calcularPrecioFinal(CategoriaProducto categoria, BigDecimal precioBase) {
        return obtenerEstrategia(categoria).calcularPrecioFinal(precioBase);
    }

    public BigDecimal obtenerPorcentajeDescuento(CategoriaProducto categoria) {
        return obtenerEstrategia(categoria).obtenerPorcentajeDescuento();
    }

    public String obtenerDescripcionRegla(CategoriaProducto categoria) {
        return obtenerEstrategia(categoria).obtenerDescripcionRegla();
    }
}
