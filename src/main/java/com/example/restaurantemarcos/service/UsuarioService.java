package com.example.restaurantemarcos.service;

import com.example.restaurantemarcos.model.Usuario;
import com.example.restaurantemarcos.repository.UsuarioReposity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UsuarioService {
    private final UsuarioReposity usuarioReposity;
    @Autowired
    public UsuarioService(UsuarioReposity usuarioReposity) {
        this.usuarioReposity = usuarioReposity;
    }

    public List<Usuario> getAllUsuarios() {
        return usuarioReposity.findAll();
    }

    public Usuario getUsuarioById(Long id) {
        return usuarioReposity.findById(id).orElse(null);
    }

    public Usuario addUsuario(Usuario usuario) {
        return usuarioReposity.save(usuario);
    }

    public Usuario updateUsuario(Usuario usuario, Long id) {
        Usuario usuario1 = usuarioReposity.findById(id).orElse(null);
        if (usuario1 != null) {
            usuario1.setRol(usuario.getRol());
            return usuarioReposity.save(usuario1);
        }
        return null;
    }
    //No debemos tener ni siquiera la opción o el metodo de eliminar usuarios en nuestro sistema

}
