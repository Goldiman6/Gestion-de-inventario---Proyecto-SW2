package com.poligran.inventario.repository;

import com.poligran.inventario.model.entity.Producto;
import com.poligran.inventario.model.enums.CategoriaProducto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    Optional<Producto> findByCodigoSku(String codigoSku);

    boolean existsByCodigoSku(String codigoSku);

    boolean existsByCodigoSkuAndIdNot(String codigoSku, Long id);

    List<Producto> findByCategoria(CategoriaProducto categoria);
}
