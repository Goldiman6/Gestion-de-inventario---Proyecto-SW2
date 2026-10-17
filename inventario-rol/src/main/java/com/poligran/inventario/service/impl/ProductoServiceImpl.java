package com.poligran.inventario.service.impl;

import com.poligran.inventario.dto.request.ProductoRequestDTO;
import com.poligran.inventario.dto.response.ProductoResponseDTO;
import com.poligran.inventario.exception.BadRequestException;
import com.poligran.inventario.exception.ResourceNotFoundException;
import com.poligran.inventario.model.entity.Producto;
import com.poligran.inventario.model.enums.CategoriaProducto;
import com.poligran.inventario.repository.ProductoRepository;
import com.poligran.inventario.service.ProductoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoServiceImpl(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductoResponseDTO> listarTodos() {
        return productoRepository.findAll()
                .stream()
                .map(this::mapearADTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ProductoResponseDTO obtenerPorId(Long id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con ID: " + id));
        return mapearADTO(producto);
    }

    @Override
    @Transactional(readOnly = true)
    public ProductoResponseDTO obtenerPorSku(String sku) {
        Producto producto = productoRepository.findByCodigoSku(sku)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con SKU: " + sku));
        return mapearADTO(producto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductoResponseDTO> listarPorCategoria(CategoriaProducto categoria) {
        return productoRepository.findByCategoria(categoria)
                .stream()
                .map(this::mapearADTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ProductoResponseDTO crear(ProductoRequestDTO request) {
        if (productoRepository.existsByCodigoSku(request.getCodigoSku())) {
            throw new BadRequestException("Ya existe un producto registrado con el SKU: " + request.getCodigoSku());
        }

        Producto producto = new Producto();
        actualizarDatosEntidad(producto, request);

        Producto guardado = productoRepository.save(producto);
        return mapearADTO(guardado);
    }

    @Override
    @Transactional
    public ProductoResponseDTO actualizar(Long id, ProductoRequestDTO request) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se puede actualizar. Producto no encontrado con ID: " + id));

        if (productoRepository.existsByCodigoSkuAndIdNot(request.getCodigoSku(), id)) {
            throw new BadRequestException("El SKU " + request.getCodigoSku() + " ya esta asignado a otro producto");
        }

        actualizarDatosEntidad(producto, request);
        Producto actualizado = productoRepository.save(producto);
        return mapearADTO(actualizado);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        if (!productoRepository.existsById(id)) {
            throw new ResourceNotFoundException("No se puede eliminar. Producto no encontrado con ID: " + id);
        }
        productoRepository.deleteById(id);
    }

    private void actualizarDatosEntidad(Producto entidad, ProductoRequestDTO dto) {
        entidad.setCodigoSku(dto.getCodigoSku().trim().toUpperCase());
        entidad.setNombre(dto.getNombre().trim());
        entidad.setDescripcion(dto.getDescripcion());
        entidad.setCategoria(dto.getCategoria());
        entidad.setPrecio(dto.getPrecio());
        entidad.setStock(dto.getStock());
    }

    private ProductoResponseDTO mapearADTO(Producto entidad) {
        return new ProductoResponseDTO(
                entidad.getId(),
                entidad.getCodigoSku(),
                entidad.getNombre(),
                entidad.getDescripcion(),
                entidad.getCategoria(),
                entidad.getPrecio(),
                entidad.getStock(),
                entidad.getFechaCreacion(),
                entidad.getFechaActualizacion()
        );
    }
}
