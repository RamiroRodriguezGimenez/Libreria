package com.libreria.controlador;

import com.libreria.entidades.Autor;
import com.libreria.servicios.AutorServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AutorControlador {

    @Autowired
    public AutorServicio autorServicio;

    @PostMapping("/AdministrarAutor")
    public String administrarAutor(ModelMap modelo, @RequestParam String nombre, @RequestParam String apellido) {
     
        try {
            autorServicio.crear(nombre, apellido);
            modelo.put("exito", "El autor '" + nombre + " " + apellido + "' se cargo correctamente");
        } catch (Exception e) {
            modelo.put("error", e.getMessage());

        }
        return "AdministrarAutor.html";
    }

    @GetMapping("lista-autores")
    public String listarAutor(ModelMap modelo) {
        List<Autor> autores = autorServicio.listar();

        modelo.put("autores", autores);
        return "lista-autores.html";
    }

    @GetMapping("/Autor/Eliminar/{id}")
    public String eliminarAutor(@PathVariable String id, Model model) {

        try {
            autorServicio.eliminar(id);
        } catch (Exception e) {
        }

        return "redirect:/lista-autores";
    }

    @GetMapping("/Autor/EditarAutor/{id}")
    public String editarAutor(@PathVariable String id) {

        return "EditarAutor";
    }

    @PostMapping("/Autor/EditarAutor/{id}")
    public String editarAutor(ModelMap modelo, @PathVariable String id, @RequestParam(required = false) String nombre, @RequestParam(required = false) String apellido) {

        try {
            autorServicio.editar(id, nombre, apellido);
        } catch (Exception e) {

        }

        return "redirect:/lista-autores";
    }
    
    @GetMapping("/Autor/Anular/{id}")
    public String anularAutor(@PathVariable String id) {
      
        try {
            autorServicio.anularAutor(id);
        } catch (Exception e) {
        }

        return "redirect:/lista-autores";
    }
    
    
}
