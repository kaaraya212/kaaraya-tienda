package TiendaTech.com.demo.service;

/**
 *
 * @author kathe
 */
import TiendaTech.com.demo.domain.Producto;
import TiendaTech.com.demo.repository.ProductoRepository;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Transactional(readOnly = true)
    public List<Producto> getProductos(boolean activo) {
        if (activo) {
            return productoRepository.findByActivoTrue();
        }
        return productoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Producto> getProducto(Integer idProducto) {
        return productoRepository.findById(idProducto);
    }

    @Autowired
    private FirebaseStorageService firebaseStorageService;

    @Transactional
    public void save(Producto producto, MultipartFile imagenFile) {
        producto = productoRepository.save(producto);
        if (!imagenFile.isEmpty()) { //Si no está vacío... pasaron una imagen...            
            try {
                String rutaImagen = firebaseStorageService.uploadImage(
                        imagenFile, "producto",
                        producto.getIdProducto());
                producto.setRutaImagen(rutaImagen);
                productoRepository.save(producto);
            } catch (IOException e) {

            }
        }
    }

    @Transactional
    public void delete(Integer idProducto) {
        // Verifica si la categoría existe antes de intentar eliminarlo
        if (!productoRepository.existsById(idProducto)) {
            // Lanza una excepción para indicar que el usuario no fue encontrado
            throw new IllegalArgumentException("La categoría con ID " + idProducto + " no existe.");
        }
        try {
            productoRepository.deleteById(idProducto);
        } catch (DataIntegrityViolationException e) {
            // Lanza una nueva excepción para encapsular el problema de integridad de datos
            throw new IllegalStateException("No se puede eliminar la producto. Tiene datos asociados.", e);
        }
    }
}
