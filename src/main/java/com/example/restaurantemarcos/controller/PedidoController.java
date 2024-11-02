package com.example.restaurantemarcos.controller;

import com.example.restaurantemarcos.exception.ResourceNotFoundException;
import com.example.restaurantemarcos.model.Pedido;
import com.example.restaurantemarcos.repository.PedidoRepository;
import com.example.restaurantemarcos.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedido")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    // Get al Pedido por su id -http
    @GetMapping
    public List <Pedido> getAllPedidos(){
        return pedidoService.getAllPedidos();
    }

    //obtener Pedido por id -http
    @GetMapping ("/{id}")
    public ResponseEntity<Pedido> getPedidoById(@PathVariable(value = "id") Long id){
        Pedido pedido = pedidoService.getPedidoById(id);
        if (pedido != null) {
            return ResponseEntity.ok(pedido);
        }
        return ResponseEntity.notFound().build();
    }

    //Registrar pedido por -http

    @PostMapping
    public ResponseEntity<Pedido> addPedido(@RequestBody Pedido pedido, @RequestParam Long menuId) {
        try {
            Pedido nuevoPedido = pedidoService.addPedido(pedido, menuId);
            return ResponseEntity.ok(nuevoPedido);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.badRequest().body(null);}
        }

    //actualizamos el pedidio con su id /http
    @PutMapping("/{id}")
    public  ResponseEntity<Pedido> updatePedido(@RequestBody Pedido pedido, @PathVariable(value ="id") Long id, @RequestParam Long menuId){
        Pedido pedidoUpdate = pedidoService.updatePedido(pedido, id, menuId);
        if (pedidoUpdate != null) {
            return ResponseEntity.ok(pedidoUpdate);
        }
        return ResponseEntity.notFound().build();
    }

    //matar pedidos ;-;

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePedido(@PathVariable(value = "id") Long id){
        pedidoService.deletePedido(id);
        return ResponseEntity.noContent().build();
    }
}
