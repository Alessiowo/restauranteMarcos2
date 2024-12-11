package com.example.restaurantemarcos.service;

import com.example.restaurantemarcos.model.Usuario;
import com.example.restaurantemarcos.model.Rol;
import com.example.restaurantemarcos.repository.RolRepository;
import com.example.restaurantemarcos.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.HashSet;
import java.util.stream.Collectors;
import java.util.Set;

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
    public Usuario updateUsuario(Long id, Usuario userDetails) {
        Usuario usuario = getUsuarioById(id);

        // Actualizar nombre de usuario si es proporcionado y diferente
        if (userDetails.getNombreUsuario() != null && !userDetails.getNombreUsuario().equals(usuario.getNombreUsuario())) {
            if (usuarioRepository.findByNombreUsuario(userDetails.getNombreUsuario()).isPresent()) {
                throw new RuntimeException("El nombre de usuario ya existe");
            }
            usuario.setNombreUsuario(userDetails.getNombreUsuario());
        }

        // Actualizar contraseña si es proporcionada
        if (userDetails.getContrasena() != null && !userDetails.getContrasena().isEmpty()) {
            usuario.setContrasena(passwordEncoder.encode(userDetails.getContrasena()));
        }
        // Update roles if provided
        if (userDetails.getRoles() != null && !userDetails.getRoles().isEmpty()) {
            Set<Rol> newRoles = new HashSet<>();
            for (Rol rol : userDetails.getRoles()) {
                Rol existingRole = rolRepository.findByName(rol.getName())
                        .orElseThrow(() -> new RuntimeException("Rol no encontrado: " + rol.getName()));
                newRoles.add(existingRole);
            }
            usuario.setRoles(newRoles);
        }

        return usuarioRepository.save(usuario);
    }
//actualizar rol de usuario
    public Usuario updateUsuarioRol(Long usuarioId, Set<String> roleNames) {
        Usuario user = getUsuarioById(usuarioId);

        // Security validation: Don't allow removing the last ADMIN
        boolean isRemovingLastAdmin = user.getRoles().stream()
                .anyMatch(r -> r.getName().equals("ADMIN")) &&
                !roleNames.contains("ADMIN");

        if (isRemovingLastAdmin && rolRepository.countByName("ADMIN") <= 1) {
            throw new RuntimeException("Cannot remove the last admin user");
        }

        // Convert role names to Role entities
        Set<Rol> newRoles = roleNames.stream()
                .map(name -> rolRepository.findByName(name)
                        .orElseThrow(() -> new RuntimeException("Rol no encontrado: " + name)))
                .collect(Collectors.toSet());

        user.setRoles(newRoles);
        return usuarioRepository.save(user);
    }


}