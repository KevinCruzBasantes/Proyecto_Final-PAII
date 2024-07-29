package services;
import models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.*;

import java.util.List;
@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;

    @Autowired
    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public Pedido guardarPedido(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    public List<Pedido> obtenerPedidos() {
        return pedidoRepository.findAll();
    }

    public Pedido obtenerPedidoPorId(Long id) {
        return pedidoRepository.findById(id).orElse(null);
    }

    public void eliminarPedido(Long id) {
        pedidoRepository.deleteById(id);
    }
}