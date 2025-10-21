package TiendaTech.com.demo.repository;

/**
 *
 * @author kathe
 */
import TiendaTech.com.demo.domain.Producto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository {

    public interface ProductoRepository extends JpaRepository<Producto, Integer> {

        public List<Producto> findByActivoTrue();
    }
}
