package controller;
import models.*;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import services.*;

import java.util.List;
@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    @Autowired
    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping
    public Pedido crearPedido(@RequestBody Pedido pedido) {
        return pedidoService.guardarPedido(pedido);
    }

    @GetMapping
    public List<Pedido> listarPedidos() {
        return pedidoService.obtenerPedidos();
    }

    @GetMapping("/{id}")
    public Pedido obtenerPedidoPorId(@PathVariable Long id) {
        return pedidoService.obtenerPedidoPorId(id);
    }

    @DeleteMapping("/{id}")
    public void eliminarPedido(@PathVariable Long id) {
        pedidoService.eliminarPedido(id);
    }
}