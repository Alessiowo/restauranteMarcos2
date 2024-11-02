package com.example.restaurantemarcos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

@Entity
@Table(name = "pedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pedidoId;

    @NotBlank(message = "El nombre del cliente es obligatorio")
    @Column(name = "nombreCliente", nullable = false)
    private String nombreCliente;

    @Positive(message = "La cantidad de personas debe ser positiva")
    @Column(name = "cantidadPersonas", nullable = false)
    @Max(100)
    private int cantidadPersonas;

    @NotNull(message = "El menú es obligatorio")
    @ManyToOne(fetch = FetchType.LAZY)    // Relación con otra entidad
    @JoinColumn(name = "menuId", nullable = false)
    private Menu menu;

    @NotBlank(message = "El estado de la orden es obligatorio")
    @Column(name = "estadoOrden", nullable = false)
    private String estadoOrden;

    @NotNull(message = "La fecha es obligatoria")
    @PastOrPresent(message = "La fecha no puede ser futura")
    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    public Long getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(Long pedidoId) {
        this.pedidoId = pedidoId;
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

    public @NotNull(message = "El menú es obligatorio") Menu getMenu() {
        return menu;
    }

    public void setMenu(@NotNull(message = "El menú es obligatorio") Menu menu) {
        this.menu = menu;
    }

    public @NotBlank(message = "El estado de la orden es obligatorio") String getEstadoOrden() {
        return estadoOrden;
    }

    public void setEstadoOrden(@NotBlank(message = "El estado de la orden es obligatorio") String estadoOrden) {
        this.estadoOrden = estadoOrden;
    }

    public @NotNull(message = "La fecha es obligatoria") @PastOrPresent(message = "La fecha no puede ser futura") LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(@NotNull(message = "La fecha es obligatoria") @PastOrPresent(message = "La fecha no puede ser futura") LocalDate fecha) {
        this.fecha = fecha;
    }
}
