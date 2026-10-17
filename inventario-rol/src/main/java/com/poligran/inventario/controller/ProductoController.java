package com.poligran.inventario.controller;

import com.poligran.inventario.dto.request.ProductoRequestDTO;
import com.poligran.inventario.dto.response.ProductoResponseDTO;
import com.poligran.inventario.model.enums.CategoriaProducto;
import com.poligran.inventario.service.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/productos")
@Tag(name = "Productos", description = "Endpoints para la gestion del catalogo e inventario de productos de rol y coleccionables")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping
    @Operation(summary = "Listar todos los productos", description = "Retorna el listado completo de productos registrados en el inventario")
    @ApiResponse(responseCode = "200", description = "Lista recuperada exitosamente")
    public ResponseEntity<List<ProductoResponseDTO>> listarTodos() {
        return ResponseEntity.ok(productoService.listarTodos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener producto por ID", description = "Busca y retorna un producto a partir de su identificador numerico")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Producto encontrado"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    public ResponseEntity<ProductoResponseDTO> obtenerPorId(
            @Parameter(description = "ID del producto a consultar", example = "1")
            @PathVariable Long id) {
        return ResponseEntity.ok(productoService.obtenerPorId(id));
    }

    @GetMapping("/sku/{sku}")
    @Operation(summary = "Obtener producto por codigo SKU", description = "Busca y retorna un producto segun su codigo SKU unico")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Producto encontrado"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado con el SKU suministrado")
    })
    public ResponseEntity<ProductoResponseDTO> obtenerPorSku(
            @Parameter(description = "Codigo SKU del producto", example = "ROL-DD5-001")
            @PathVariable String sku) {
        return ResponseEntity.ok(productoService.obtenerPorSku(sku));
    }

    @GetMapping("/categoria/{categoria}")
    @Operation(summary = "Filtrar productos por categoria", description = "Retorna todos los productos pertenecientes a una categoria especifica")
    @ApiResponse(responseCode = "200", description = "Listado filtrado exitosamente")
    public ResponseEntity<List<ProductoResponseDTO>> listarPorCategoria(
            @Parameter(description = "Categoria ludica del producto")
            @PathVariable CategoriaProducto categoria) {
        return ResponseEntity.ok(productoService.listarPorCategoria(categoria));
    }

    @PostMapping
    @Operation(summary = "Crear nuevo producto", description = "Registra un nuevo producto en el catalogo de inventario")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Producto creado satisfactoriamente"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada invalidos o SKU duplicado")
    })
    public ResponseEntity<ProductoResponseDTO> crear(
            @Valid @RequestBody ProductoRequestDTO request) {
        ProductoResponseDTO creado = productoService.crear(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar producto existente", description = "Actualiza los datos de un producto a partir de su ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Producto actualizado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada invalidos o conflicto de SKU"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado para actualizar")
    })
    public ResponseEntity<ProductoResponseDTO> actualizar(
            @Parameter(description = "ID del producto a actualizar", example = "1")
            @PathVariable Long id,
            @Valid @RequestBody ProductoRequestDTO request) {
        return ResponseEntity.ok(productoService.actualizar(id, request));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar producto", description = "Remueve un producto del inventario a partir de su ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Producto eliminado correctamente"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado para eliminar")
    })
    public ResponseEntity<Void> eliminar(
            @Parameter(description = "ID del producto a eliminar", example = "1")
            @PathVariable Long id) {
        productoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
