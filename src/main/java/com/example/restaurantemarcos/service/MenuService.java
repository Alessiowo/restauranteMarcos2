package com.example.restaurantemarcos.service;

import com.example.restaurantemarcos.model.Menu;
import com.example.restaurantemarcos.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {
    @Autowired
    private MenuRepository menuRepository;

    //Metodo para obtener todos los menus
    public List<Menu> getAllMenus() {
        return menuRepository.findAll();
    }

    // "" por id
    public Menu getMenuById(Long id) {
        return menuRepository.findById(id).orElse(null);
    }

    //Metodo para registrar menus
    public Menu addMenu(Menu menu) {
        return menuRepository.save(menu);
    }

    //Metodo para editar menus

    public Menu updateMenu(Long id, Menu menu) {
        Menu menu1 = menuRepository.findById(id).orElse(null);
        if (menu1 != null) {
            menu1.setDescripcionMenu(menu.getDescripcionMenu());
            menu1.setNombreMenu(menu.getNombreMenu());

        }
        return null;
    }

    //Metodo para deletear el menu :c

    public void deleteMenu(Long id) {
        menuRepository.deleteById(id);
    }
}
