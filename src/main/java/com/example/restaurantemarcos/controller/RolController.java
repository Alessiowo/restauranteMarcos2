package com.example.restaurantemarcos.controller;


import com.example.restaurantemarcos.model.Rol;
import com.example.restaurantemarcos.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
@PreAuthorize("hasRole('ADMIN')")
public class RolController {

    @Autowired
    private RolService rolService;

    @GetMapping
    public ResponseEntity<List<Rol>> getAllRoles() {
        return ResponseEntity.ok(rolService.getAllRoles());
    }

    @PostMapping
    public ResponseEntity<?> createRol(@RequestBody Rol rol) {
        try {
            Rol nuevoRol = rolService.createRol(rol);
            return ResponseEntity.ok(nuevoRol);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRol(@PathVariable Long id) {
        try {
            rolService.deleteRol(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}