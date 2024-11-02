package com.example.restaurantemarcos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;

@Entity
@Table(name = "menu")
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_id")
    private Long menuId;

    @NotBlank(message = "La categoría es obligatoria")
    @Column(name = "categoria", nullable = false)
    private String categoria;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 100, message = "El nombre no puede tener más de 100 caracteres")
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @NotNull(message = "El precio es obligatorio")
    @DecimalMin(value = "0.0", inclusive = false, message = "El precio debe ser mayor que cero")
    @Column(name = "precio", nullable = false, precision = 10, scale = 2)
    private BigDecimal precio;

    @PositiveOrZero(message = "El stock debe ser un número positivo o cero")
    @Column(name = "stock", nullable = false)
    @ColumnDefault("0")
    private int stock;

    @Size(max = 500, message = "La descripción no puede tener más de 500 caracteres")
    @Column(name = "descripcion")
    private String descripcion;

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public @NotBlank(message = "La categoría es obligatoria") String getCategoria() {
        return categoria;
    }

    public void setCategoria(@NotBlank(message = "La categoría es obligatoria") String categoria) {
        this.categoria = categoria;
    }

    public @NotBlank(message = "El nombre es obligatorio") @Size(max = 100, message = "El nombre no puede tener más de 100 caracteres") String getNombre() {
        return nombre;
    }

    public void setNombre(@NotBlank(message = "El nombre es obligatorio") @Size(max = 100, message = "El nombre no puede tener más de 100 caracteres") String nombre) {
        this.nombre = nombre;
    }

    public @NotNull(message = "El precio es obligatorio") @DecimalMin(value = "0.0", inclusive = false, message = "El precio debe ser mayor que cero") BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(@NotNull(message = "El precio es obligatorio") @DecimalMin(value = "0.0", inclusive = false, message = "El precio debe ser mayor que cero") BigDecimal precio) {
        this.precio = precio;
    }

    @PositiveOrZero(message = "El stock debe ser un número positivo o cero")
    public int getStock() {
        return stock;
    }

    public void setStock(@PositiveOrZero(message = "El stock debe ser un número positivo o cero") int stock) {
        this.stock = stock;
    }

    public @Size(max = 500, message = "La descripción no puede tener más de 500 caracteres") String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(@Size(max = 500, message = "La descripción no puede tener más de 500 caracteres") String descripcion) {
        this.descripcion = descripcion;
    }
}
