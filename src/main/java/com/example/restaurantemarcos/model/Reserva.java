package com.example.restaurantemarcos.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.Date;

@Entity
@Table(name = "reserva")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reservaId;

    @NotBlank(message = "El nombre del cliente es obligatorio")
    @Column(name = "nombreCliente", nullable = false)
    private String nombreCliente;

    @Positive(message = "La cantidad de personas debe ser positiva")
    @Column(name = "cantidadPersonas", nullable = false)
    private int cantidadPersonas;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "preordenId")
    private Pedido preorden;

    @NotNull(message = "La fecha de la reserva es obligatoria")
    @FutureOrPresent(message = "La fecha de la reserva debe ser en el presente o en el futuro")
    @Column(name = "fecha", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fecha;

    @NotBlank(message = "El estado de la reserva es obligatorio")
    @Column(name = "estadoReserva", nullable = false)
    private String estadoReserva;

    public Long getReservaId() {
        return reservaId;
    }

    public void setReservaId(Long reservaId) {
        this.reservaId = reservaId;
    }

    public @NotBlank(message = "El nombre del cliente es obligatorio") String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(@NotBlank(message = "El nombre del cliente es obligatorio") String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    @Positive(message = "La cantidad de personas debe ser positiva")
    public int getCantidadPersonas() {
        return cantidadPersonas;
    }

    public void setCantidadPersonas(@Positive(message = "La cantidad de personas debe ser positiva") int cantidadPersonas) {
        this.cantidadPersonas = cantidadPersonas;
    }

    public Pedido getPreorden() {
        return preorden;
    }

    public void setPreorden(Pedido preorden) {
        this.preorden = preorden;
    }

    public @NotNull(message = "La fecha de la reserva es obligatoria") @FutureOrPresent(message = "La fecha de la reserva debe ser en el presente o en el futuro") Date getFecha() {
        return fecha;
    }

    public void setFecha(@NotNull(message = "La fecha de la reserva es obligatoria") @FutureOrPresent(message = "La fecha de la reserva debe ser en el presente o en el futuro") Date fecha) {
        this.fecha = fecha;
    }

    public @NotBlank(message = "El estado de la reserva es obligatorio") String getEstadoReserva() {
        return estadoReserva;
    }

    public void setEstadoReserva(@NotBlank(message = "El estado de la reserva es obligatorio") String estadoReserva) {
        this.estadoReserva = estadoReserva;
    }
}
