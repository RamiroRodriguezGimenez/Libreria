
package com.libreria.controlador;

import com.libreria.entidades.Autor;
import com.libreria.entidades.Editorial;
import com.libreria.entidades.Libro;
import com.libreria.servicios.LibroServicio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LibroControlador {
    
    @Autowired
    public LibroServicio libroServicio;
    
   
    
    
    @GetMapping("/CrearLibro")
    public String crearLibro(ModelMap modelo){
        List<Editorial> editoriales = libroServicio.listarEditorial();
        modelo.put("editoriales",editoriales);
        List<Autor> autores = libroServicio.listarAutor();
        modelo.put("autores",autores);
        return "CrearLibro.html";
    }
    
    @PostMapping ("/CrearLibro")
    public String crearLibro (ModelMap modelo, @RequestParam String titulo, @RequestParam Long isbn, @RequestParam Integer anio, @RequestParam Integer ejemplar, @RequestParam String idAutor, @RequestParam String idEditorial){
     try{
        libroServicio.crear(titulo, isbn, anio, ejemplar, idAutor, idEditorial);
       modelo.put("exito", "El libro '" +titulo+ "' se cargo correctamente");
        } catch (Exception e) {
            modelo.put("error", e.getMessage());

        }
        return "CrearLibro.html";
    }
    
    @GetMapping("/ListarLibro")
    public String listar(ModelMap modelo){
        List<Libro> libros= libroServicio.listar();
        modelo.put("libros", libros);
        return "ListarLibro.html";
    }
    
    @GetMapping("/EliminarLibro/{id}")
    public String elimnar(@PathVariable String id){
        libroServicio.eliminar(id);
        return "redirect:/ListarLibro";
    }
    
    @GetMapping("/AnularLibro/{id}")
    public String anular(@PathVariable String id){
        libroServicio.anular(id);
        return"redirect:/ListarLibro";
    }
    
    @GetMapping("/EditarLibro/{id}")
    public String anular(ModelMap modelo, @PathVariable String id){
        List<Editorial> editoriales = libroServicio.listarEditorial();
        modelo.put("editoriales",editoriales);
        List<Autor> autores = libroServicio.listarAutor();
        modelo.put("autores",autores);
        return"EditarLibro.html";
    }
    
    @PostMapping("/EditarLibro/{id}")
    public String editar(ModelMap modelo, @PathVariable String id, @RequestParam (required=false) String titulo, @RequestParam (required=false) Long isbn, @RequestParam (required=false) Integer anio, @RequestParam (required=false) Integer ejemplar, @RequestParam (required=false) String idAutor, @RequestParam (required=false)String idEditorial){
        try {
            System.out.println(id);
        libroServicio.editar(id, titulo, isbn, anio, ejemplar, idAutor, idEditorial);
       modelo.put("exito", "El libro '" +titulo+ "' se cargo correctamente");
        }catch (Exception e) {
            modelo.put("error", e.getMessage());

        }
        
        
        
        return ("redirect:/ListarLibro");
    }
}
