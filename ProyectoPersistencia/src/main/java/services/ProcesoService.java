package services;
import models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.*;

import java.util.List;
@Service
public class ProcesoService {

    private final ProcesoRepository procesoRepository;

    @Autowired
    public ProcesoService(ProcesoRepository procesoRepository) {
        this.procesoRepository = procesoRepository;
    }

    public Proceso guardarProceso(Proceso proceso) {
        return procesoRepository.save(proceso);
    }

    public List<Proceso> obtenerProcesos() {
        return procesoRepository.findAll();
    }

    public Proceso obtenerProcesoPorId(Long id) {
        return procesoRepository.findById(id).orElse(null);
    }

    public void eliminarProceso(Long id) {
        procesoRepository.deleteById(id);
    }

}