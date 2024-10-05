package com.example.restaurantemarcos.service;

import com.example.restaurantemarcos.model.Pedido;
import com.example.restaurantemarcos.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    //Metodo para obtener todos los pedidos
    public List<Pedido> getAllPedidos() {
        return pedidoRepository.findAll();
    }

    //Metodo para registrar pedido
    public Pedido addPedido(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    // "" por id
    public Pedido getPedidoById(Long id) {
        return pedidoRepository.findById(id).orElse(null);
    }

    //Metodo para editar Pedidos

    public Pedido updatePedido(Pedido pedido, Long id) {
        Pedido pedido1 = pedidoRepository.findById(id).orElse(null);
        if (pedido1 != null) {
            pedido1.setDescripcionPedido(pedido.getDescripcionPedido());
            pedido1.setFechaPedido(pedido.getFechaPedido());
            pedido1.setNombrePedido(pedido.getNombrePedido());
            pedido1.setPrecioPedido(pedido.getPrecioPedido());
        }
        return null;
    }
    //Metodo para borrar pedidoooooss.... :'v
    public void deletePedido(Long id) {
        pedidoRepository.deleteById(id);
    }
}
