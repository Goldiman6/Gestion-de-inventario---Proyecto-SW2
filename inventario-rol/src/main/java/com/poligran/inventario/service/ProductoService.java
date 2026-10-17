package com.poligran.inventario.service;

import com.poligran.inventario.dto.request.ProductoRequestDTO;
import com.poligran.inventario.dto.response.ProductoResponseDTO;
import com.poligran.inventario.model.enums.CategoriaProducto;
import java.util.List;

public interface ProductoService {

    List<ProductoResponseDTO> listarTodos();

    ProductoResponseDTO obtenerPorId(Long id);

    ProductoResponseDTO obtenerPorSku(String sku);

    List<ProductoResponseDTO> listarPorCategoria(CategoriaProducto categoria);

    ProductoResponseDTO crear(ProductoRequestDTO request);

    ProductoResponseDTO actualizar(Long id, ProductoRequestDTO request);

    void eliminar(Long id);
}
