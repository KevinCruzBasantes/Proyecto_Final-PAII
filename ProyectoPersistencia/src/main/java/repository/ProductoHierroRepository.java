package repository;

import models.ProductoHierro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoHierroRepository extends JpaRepository<ProductoHierro, Long> {
}
