package TiendaTech.com.demo.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;

@Data
@Entity
@Table(name = "categoria")
public class Categoria implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria")
    private Long idCategoria;

    @Column(unique = true, nullable = false, length = 50)
    private String description;

    @Column(length = 1024)
    private String rutaImagen;

    @Column(name = "activo")
    private Boolean activo;

    //Relación de uno a muchos con la clase Producto
    @OneToMany(mappedBy = "categoria")
    private List<Producto> productos;
}
