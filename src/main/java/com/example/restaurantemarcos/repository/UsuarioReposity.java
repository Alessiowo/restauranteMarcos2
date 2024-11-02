package com.example.restaurantemarcos.repository;

import com.example.restaurantemarcos.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface UsuarioReposity extends JpaRepository<Usuario, Long>{
}
