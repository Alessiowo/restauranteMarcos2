package com.example.restaurantemarcos.model;

import jakarta.persistence.*;
@Entity
public class Reserva {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)

    private Long idReserva;
    private String emailReserva;
    private String nombreReserva;
    private String fechaReserva;
    private String horaReserva;


    public Long getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(Long idReserva) {
        this.idReserva = idReserva;
    }

    public String getEmailReserva() {
        return emailReserva;
    }

    public void setEmailReserva(String emailReserva) {
        this.emailReserva = emailReserva;
    }

    public String getNombreReserva() {
        return nombreReserva;
    }

    public void setNombreReserva(String nombreReserva) {
        this.nombreReserva = nombreReserva;
    }

    public String getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(String fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public String getHoraReserva() {
        return horaReserva;
    }

    public void setHoraReserva(String horaReserva) {
        this.horaReserva = horaReserva;
    }
}
