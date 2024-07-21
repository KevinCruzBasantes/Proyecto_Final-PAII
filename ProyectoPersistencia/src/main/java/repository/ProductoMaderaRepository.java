package repository;

import models.ProductoMadera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoMaderaRepository extends JpaRepository<ProductoMadera, Long> {
}
