package com.example.restaurantemarcos.service;

import com.example.restaurantemarcos.model.Reserva;
import com.example.restaurantemarcos.model.Pedido;
import com.example.restaurantemarcos.repository.ReservaRepository;
import com.example.restaurantemarcos.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.time.LocalDate;

@Service
public class ReservaService {

    @Autowired
    private final ReservaRepository reservaRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    public ReservaService(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    //Metodo para hacer un get a todas las reservas y ya
    public List<Reserva> getAllReservas() {
            return reservaRepository.findAll();
    }

    //"" por id
    public Reserva getReservaById(Long id) {
        return reservaRepository.findById(id).orElse(null);
    }

    //Metodo para añadir Reservas
    public Reserva addReserva(Reserva reserva, Long pedidoId) {
        Pedido pedido= pedidoRepository.findById(reserva.getPreorden().getPedidoId()).orElse(null);
            if(pedido != null){
                reserva.setPreorden(pedido);
                reserva.setEstadoReserva("Pendiente");
                reserva.setCantidadPersonas(1);
                reserva.setNombreCliente("Anonimo");
                reserva.setFecha(Date.from(LocalDate.now().atStartOfDay().atZone(java.time.ZoneId.systemDefault()).toInstant()));
            }

        return reservaRepository.save(reserva);
    }

    //Metodo para editar reservas
    public Reserva updateReserva(Reserva reserva, Long id, Long pedidoId) {
        Reserva reserva1 = reservaRepository.findById(id).orElse(null);
        Pedido pedido = pedidoRepository.findById(pedidoId).orElse(null);
        if (reserva1 != null && pedido != null) {
            reserva1.setNombreCliente(reserva.getNombreCliente());
            reserva1.setFecha(reserva.getFecha());
            reserva1.setCantidadPersonas(reserva.getCantidadPersonas());
            reserva1.setEstadoReserva(reserva.getEstadoReserva());
            reserva1.setPreorden(reserva.getPreorden());
            return reservaRepository.save(reserva1);
        }
        return null;
    }
    //Metodo para incinerar
    public void deleteReserva(Long id) {
        reservaRepository.deleteById(id);
    }
}