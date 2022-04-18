package com.libreria.controlador;

import com.libreria.entidades.Editorial;
import com.libreria.servicios.EditorialServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EditorialCotrolador {

    @Autowired
    public EditorialServicio editorialServicio;

    @GetMapping("/CrearEditorial")
    public String crearEditorial() {

        return "CrearEditorial.html";
    }

    @PostMapping("/CrearEditorial")
    public String crearEditorial(ModelMap modelo, @RequestParam String nombre) {

        try {
            editorialServicio.crear(nombre);
            modelo.put("exito", "La editorial '" + nombre + "' se cargo correctamente");
        } catch (Exception e) {
            modelo.put("error", e.getMessage());
        }
        return "CrearEditorial.html";
    }

    @GetMapping("/ListarEditorial")
    public String listarEditorial(ModelMap modelo) {
        List<Editorial> editoriales = editorialServicio.listar();

        modelo.put("editoriales", editoriales);

        return ("ListarEditorial.html");
    }

    @GetMapping("/EditarEditorial/{id}")
    public String editarEditorial(@PathVariable String id) {
        System.out.println("hola");
        return "EditarEditorial.html";
    }
    
    @PostMapping("/EditarEditorial/{id}")
    public String editarEditorial(@PathVariable String id, @RequestParam String nombre){
         System.out.println(nombre+id);
            editorialServicio.editar(id, nombre);
          
               
        return "redirect:/ListarEditorial";
    }
    
    @GetMapping("/AnularEditorial/{id}")
    public String anularEditorial(@PathVariable String id){
        
            editorialServicio.anular(id);
        return "redirect:/ListarEditorial";
    }
    
    @GetMapping ("/EliminarEditorial/{id}")
    public String eliminarEditorial(@PathVariable String id){
        editorialServicio.eliminar(id);
        
        return "redirect:/ListarEditorial";
    }

}
