package com.example.restaurantemarcos.controller;

import com.example.restaurantemarcos.model.Reserva;
import com.example.restaurantemarcos.repository.ReservaRepository;
import com.example.restaurantemarcos.service.PedidoService;
import com.example.restaurantemarcos.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reserva")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;
    @Autowired
    private PedidoService pedidoService;

    //tener al pedido por su id -http
    @GetMapping
    public List<Reserva> getAllReservas(){
        return reservaService.getAllReservas();
    }

    //agarrar pedido por id en el http

    @GetMapping("/{id}")
    public ResponseEntity<Reserva> getReservaById(@PathVariable Long id){
        Reserva reserva = reservaService.getReservaById(id);
        if(reserva != null){
            return ResponseEntity.ok(reserva);
        }
        return ResponseEntity.notFound().build();
    }

    //Registrar esto... reserva en el http
    @PutMapping("/{id}")
    public ResponseEntity<Reserva> updateReserva(@PathVariable Long id, @RequestBody Reserva reserva){
        Reserva reservaUptate = reservaService.updateReserva(reserva, id);
        if(reserva != null){
            return ResponseEntity.ok(reservaUptate);
        }
        return ResponseEntity.notFound().build();
    }

    //instakill para los registros pipipi
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReserva(@PathVariable Long id){
        reservaService.deleteReserva(id);
        return ResponseEntity.noContent().build();
    }
}
