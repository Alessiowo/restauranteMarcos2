package com.example.restaurantemarcos.service;

import com.example.restaurantemarcos.model.Rol;
import com.example.restaurantemarcos.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolService {

    @Autowired
    private RolRepository rolRepository;

    public List<Rol> getAllRoles() {
        return rolRepository.findAll();
    }

    public Rol createRol(Rol rol) {
        if (rolRepository.findByName(rol.getName()).isPresent()) {
            throw new RuntimeException("Role already exists");
        }
        return rolRepository.save(rol);
    }

    public Rol getRolById(Long id) {
        return rolRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));
    }

    public void deleteRol(Long id) {
        Rol rol = getRolById(id);

        // Prevent deletion of ADMIN role
        if ("ADMIN".equals(rol.getName())) {
            throw new RuntimeException("Cannot delete ADMIN role");
        }

        rolRepository.deleteById(id);
    }
}