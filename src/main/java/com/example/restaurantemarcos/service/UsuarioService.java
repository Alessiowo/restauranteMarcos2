package com.example.restaurantemarcos.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.restaurantemarcos.model.Usuario;
import com.example.restaurantemarcos.repository.RolRepository;
import com.example.restaurantemarcos.repository.UsuarioRepository;

@Service
public class UsuarioService {
    @Autowired
    private  UsuarioRepository usuarioRepository;
    @Autowired
    private  RolRepository rolRepository;
    @Autowired
    private  PasswordEncoder passwordEncoder;

    // Create
    public Usuario addUsuario(Usuario usuario) {
        if (usuarioRepository.findByNombreUsuario(usuario.getNombreUsuario()).isPresent()) {
            throw new RuntimeException("El nombre de usuario ya existe");
        }

        usuario.setContrasena(passwordEncoder.encode(usuario.getContrasena()));

        return usuarioRepository.save(usuario);
    }

    // Read
    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario getUsuarioById(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    // Update
    public Usuario updateUsuario(Long id, Usuario usuarioDetalles) {
        Usuario usuario = getUsuarioById(id);

        // Actualizar nombre de usuario si es proporcionado y diferente
        if (usuarioDetalles.getNombreUsuario() != null && !usuarioDetalles.getNombreUsuario().equals(usuario.getNombreUsuario())) {
            if (usuarioRepository.findByNombreUsuario(usuarioDetalles.getNombreUsuario()).isPresent()) {
                throw new RuntimeException("El nombre de usuario ya existe");
            }
            usuario.setNombreUsuario(usuarioDetalles.getNombreUsuario());
        }

        // Actualizar contraseña si es proporcionada
        if (usuarioDetalles.getContrasena() != null && !usuarioDetalles.getContrasena().isEmpty()) {
            usuario.setContrasena(passwordEncoder.encode(usuarioDetalles.getContrasena()));
        }

        return usuarioRepository.save(usuario);
    }

}