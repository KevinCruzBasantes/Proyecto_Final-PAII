package repository;
import models.ProductoProceso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ProductoProcesoRepository extends JpaRepository<ProductoProceso, Long> {
}
