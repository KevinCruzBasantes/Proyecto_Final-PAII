package controller;
import models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import services.*;

import java.util.List;

@RestController
@RequestMapping("/procesos")
public class ProcesoController {

    private final ProcesoService procesoService;

    @Autowired
    public ProcesoController(ProcesoService procesoService) {
        this.procesoService = procesoService;
    }

    @PostMapping
    public Proceso crearProceso(@RequestBody Proceso proceso) {
        return procesoService.guardarProceso(proceso);
    }

    @GetMapping
    public List<Proceso> listarProcesos() {
        return procesoService.obtenerProcesos();
    }

    @GetMapping("/{id}")
    public Proceso obtenerProcesoPorId(@PathVariable Long id) {
        return procesoService.obtenerProcesoPorId(id);
    }

    @DeleteMapping("/{id}")
    public void eliminarProceso(@PathVariable Long id) {
        procesoService.eliminarProceso(id);
    }
}