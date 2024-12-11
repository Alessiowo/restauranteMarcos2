package com.example.restaurantemarcos.controller;

import com.example.restaurantemarcos.model.Menu;
import com.example.restaurantemarcos.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menu")
public class MenuController {


    @Autowired
    private MenuService menuService;

    //Obtener Lista de menus / http
    @GetMapping
    public List<Menu> getAllMenus() {
        return menuService.getAllMenus();
    }

    //obtener menu por id /http
    @GetMapping("/{id}")
    public ResponseEntity<Menu> getMenuById(@PathVariable(value = "id") Long id) {
        Menu menu = menuService.getMenuById(id);
        if (menu != null) {
            return ResponseEntity.ok(menu);
        }
        return ResponseEntity.notFound().build();
    }

    //Registar un menu /http
    @PostMapping
    public Menu addMenu(@RequestBody Menu menu) {
        return menuService.addMenu(menu);
    }

    //actualizar un menu por ID /http
    @PutMapping("/{id}")
    public ResponseEntity<Menu> updateMenu(@PathVariable(value = "id") Long id, @RequestBody Menu menu) {
        Menu updatedMenu = menuService.updateMenu(id, menu);
        if (updatedMenu != null) {
            return ResponseEntity.ok(updatedMenu);
        }
        return ResponseEntity.notFound().build();
    }

    //Eliminar menu
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMenu(@PathVariable(value="id") Long id) {
        menuService.deleteMenu(id);
        return ResponseEntity.noContent().build();
    }
}
