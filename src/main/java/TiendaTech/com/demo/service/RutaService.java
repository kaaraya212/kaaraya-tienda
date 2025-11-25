package TiendaTech.com.demo.service;

import TiendaTech.com.demo.domain.Ruta;
import TiendaTech.com.demo.repository.RutaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RutaService {

    @Autowired
    private RutaRepository rutaRepository;

    public List<Ruta> getRutas() {
        return rutaRepository.findAllByOrderByRequiereRolAsc();
    }

}
