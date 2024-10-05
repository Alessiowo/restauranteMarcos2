package com.example.restaurantemarcos.service;

import com.example.restaurantemarcos.model.Reserva;
import com.example.restaurantemarcos.repository.MenuRepository;
import com.example.restaurantemarcos.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    //Metodo para hacer un get a todas las reservas y ya
    public List<Reserva> getAllReservas() {
            return reservaRepository.findAll();
    }

    //"" por id
    public Reserva getReservaById(Long id) {
        return reservaRepository.findById(id).orElse(null);
    }

    //Metodo para editar Reservas
    public Reserva addReserva(Reserva reserva) {
            return reservaRepository.save(reserva);
    }

    //Metodo para cambiar reservas
    public Reserva updateReserva(Reserva reserva, Long id) {
        Reserva reserva1 = reservaRepository.findById(id).orElse(null);
        if (reserva1 != null) {
            reserva1.setFechaReserva(reserva.getFechaReserva());
            reserva1.setEmailReserva(reserva.getEmailReserva());
            reserva1.setHoraReserva(reserva.getHoraReserva());
            reserva1.setNombreReserva(reserva.getNombreReserva());
        }
        return null;
    }
    //Metodo para incinerar c:
    public void deleteReserva(Long id) {
        reservaRepository.deleteById(id);
    }
}