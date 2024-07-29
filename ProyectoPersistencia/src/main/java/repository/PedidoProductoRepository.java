package repository;
import models.PedidoProducto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface PedidoProductoRepository extends JpaRepository<PedidoProducto, Long> {
}