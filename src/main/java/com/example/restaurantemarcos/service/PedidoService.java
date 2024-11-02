package com.example.restaurantemarcos.service;

import com.example.restaurantemarcos.model.Pedido;
import com.example.restaurantemarcos.model.Menu;
import com.example.restaurantemarcos.repository.PedidoRepository;
import com.example.restaurantemarcos.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.restaurantemarcos.exception.ResourceNotFoundException;

import java.util.List;
import java.time.LocalDate;

@Service
public class PedidoService {

    @Autowired
    private final PedidoRepository pedidoRepository;

    @Autowired
    private MenuRepository menuRepository;


    @Autowired
    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    //Metodo para obtener todos los pedidos
    public List<Pedido> getAllPedidos() {
        return pedidoRepository.findAll();
    }

    //Metodo para registrar pedido
    public Pedido addPedido(Pedido pedido, Long menuId) throws ResourceNotFoundException {
        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new ResourceNotFoundException("Menu no encontrado con id: " + menuId));
        pedido.setMenu(menu);
        return pedidoRepository.save(pedido);
    }

    // "" por id
    public Pedido getPedidoById(Long id) {
        return pedidoRepository.findById(id).orElse(null);
    }

    //Metodo para editar Pedidos

    public Pedido updatePedido(Pedido pedido, Long id, Long menuId) {
        Pedido pedido1 = pedidoRepository.findById(id).orElse(null);
        Menu menu = menuRepository.findById(menuId).orElse(null);
        if (pedido1 != null && menu != null) {
            pedido1.setMenu(menu);
            pedido1.setNombreCliente(pedido.getNombreCliente());
            pedido1.setFecha(pedido.getFecha());
            pedido1.setCantidadPersonas(pedido.getCantidadPersonas());
            pedido1.setEstadoOrden(pedido.getEstadoOrden());
            return pedidoRepository.save(pedido1);
        }
        return null;
    }
    //Metodo para borrar pedidos
    public void deletePedido(Long id) {
        pedidoRepository.deleteById(id);
    }
}
