package com.example.restaurantemarcos.controller;

import com.example.restaurantemarcos.model.Usuario;
import com.example.restaurantemarcos.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
@RequestMapping("/api/usuario")
@PreAuthorize("hasRole('ADMIN')")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    // Registrar un nuevo usuario - HTTP POST
    @PostMapping("/api/register")
    public ResponseEntity<?> registroUsuario(@RequestBody Usuario user) {
        try {
            Usuario newUser = usuarioService.addUsuario(user);
            return ResponseEntity.ok(newUser);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Obtener todos los usuarios - HTTP GET
    @GetMapping
    public ResponseEntity<List<Usuario>> getAllUsers() {
        return ResponseEntity.ok(usuarioService.getAllUsuarios());
    }

    // Obtener usuario por ID - HTTP GET
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(usuarioService.getUsuarioById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Actualizar un usuario existente - HTTP PUT
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody Usuario userDetails) {
        try {
            Usuario updatedUser = usuarioService.updateUsuario(id, userDetails);
            return ResponseEntity.ok(updatedUser);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
