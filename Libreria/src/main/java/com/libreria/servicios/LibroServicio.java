package com.libreria.servicios;

import com.libreria.entidades.Autor;
import com.libreria.entidades.Editorial;
import com.libreria.entidades.Libro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.libreria.repositorios.LibroRepositorio;
import java.util.List;
import java.util.Optional;

@Service
public class LibroServicio {

    @Autowired
    private LibroRepositorio libroRepositorio;
    @Autowired
    private AutorServicio autorServicio;
    @Autowired
    private EditorialServicio editorialServicio;

    @Transactional(rollbackFor = Exception.class) //se edita algo en la BD, Crea, elimina o modifica
    public Libro crear(String titulo, Long isbn, Integer anio, Integer ejemplar, String autorId, String editorialId) throws Exception {
        Libro libro = new Libro();
        libro.setTitulo(titulo);
        libro.setAutor(autorServicio.buscarPorId(autorId));
        libro.setEditorial(editorialServicio.buscarPorId(editorialId));
        libro.setAnio(anio);
        libro.setIsbn(isbn);
        libro.setAlta(true);
        libro.setEjemplar(ejemplar);

        return libroRepositorio.save(libro);
    }

    @Transactional(readOnly = true) //cuando no se modifica nada en la BD, solo se consulta
    public Libro buscarPorId(String id) throws Exception {
        Optional<Libro> respuesta = libroRepositorio.findById(id);

        if (respuesta.isPresent()) {
            Libro libro = respuesta.get();
            return libro;
        } else {
            throw new Exception("no existe ese libro");
        }
    }

    @Transactional(readOnly = true)
    private List<Libro> buscarPorAutor(String autor) throws Exception {

        List<Libro> respuesta = libroRepositorio.buscarPorAutor(autor);

        if (respuesta.isEmpty()) {
            return respuesta;

        } else {
            throw new Exception("Na hay libros con ese autor");
        }

    }

    @Transactional(rollbackFor = Exception.class)
    public void eliminarAutor(String idAutor) throws Exception {
        List<Libro> libros = buscarPorAutor(idAutor);
        for (Libro libro : libros) {
            libro.setAutor(null);
            libroRepositorio.save(libro);
        }

    }

    public List<Editorial> listarEditorial() {
        return editorialServicio.listar();
    }

    public List<Autor> listarAutor() {
        return autorServicio.listar();
    }

    @Transactional(readOnly = true)
    public List<Libro> listar() {
        return libroRepositorio.findAll();
    }

    @Transactional(rollbackFor = Exception.class)
    public void eliminar(String id) {
        libroRepositorio.deleteById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    public void anular(String id) {
        Optional<Libro> respuesta = libroRepositorio.findById(id);
        Libro libro = respuesta.get();
        libro.setAlta(!libro.isAlta());
        libroRepositorio.save(libro);
    }

    @Transactional(rollbackFor = Exception.class)
    public void editar(String id, String titulo, Long isbn, Integer anio, Integer ejemplar, String autorId, String editorialId) throws Exception {
        Optional<Libro> respuesta = libroRepositorio.findById(id);
       
        if (respuesta.isPresent()) {
            Libro  libro = respuesta.get();
            
            if (!titulo.isEmpty()){
            libro.setTitulo(titulo);
            }
              
            if (!autorId.isEmpty()){
            libro.setAutor(autorServicio.buscarPorId(autorId));
            }
                
            if (!editorialId.isEmpty()){
            libro.setEditorial(editorialServicio.buscarPorId(editorialId));
            }
            
            if (anio!=null){
            libro.setAnio(anio);
            }
            
            if (isbn!=null){
            libro.setIsbn(isbn);
            }
               
            if (ejemplar!=null){
            libro.setEjemplar(ejemplar);
            }
           
            libroRepositorio.save(libro);
              
        } else {
            throw new Exception("no existe ese libro");
        }

      
    }
}
