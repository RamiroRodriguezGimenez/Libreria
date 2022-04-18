package com.libreria.servicios;

import com.libreria.entidades.Autor;
import com.libreria.entidades.Libro;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.libreria.repositorios.AutorRepositorio;
import com.libreria.repositorios.LibroRepositorio;
import java.util.List;

@Service
public class AutorServicio {

    @Autowired
    private AutorRepositorio autorRepositorio;

    @Autowired  
    private LibroRepositorio libroRepositorio;

    @Transactional(readOnly = true) //cuando no se modifica nada en la BD, solo se consulta
    public Autor buscarPorId(String id) throws Exception {
        Optional<Autor> respuesta = autorRepositorio.findById(id);

        if (respuesta.isPresent()) {
            Autor autor = respuesta.get();
            return autor;
        } else {
            throw new Exception("no existe ese autor");
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public Autor crear(String nombre, String apellido) throws Exception {
        validar(nombre, apellido);
        Autor autor =  new Autor();
        autor.setNombre(nombre);
        autor.setApellido(apellido);
        autor.setAlta(true);

        return autorRepositorio.save(autor);
    }

    private void validar(String nombre, String apellido) throws Exception {
        if (nombre == null || nombre.isEmpty() || apellido == null || apellido.isEmpty()) {
            throw new Exception("Debe ingresar algun nombre y apellido");
        }
    }

    @Transactional(readOnly = true)
    public List<Autor> listar() {
        return autorRepositorio.findAll();

    }

    @Transactional(rollbackFor = Exception.class)
    public void eliminar(String id) throws Exception {
        try{ eliminarAutor(id); }
        catch(Exception e) {}
        finally{
        autorRepositorio.deleteById(id);}
    }
    
     @Transactional (readOnly=true)
    private List<Libro> buscarPorAutor(String idAutor) throws Exception{
        
        List<Libro> respuesta = libroRepositorio.buscarPorAutor(idAutor);
        
        if (respuesta.isEmpty())
        {        return respuesta;
                
                } else {
            throw new Exception("Na hay libros con ese autor");
        }
        
    }
    
    @Transactional (rollbackFor=Exception.class)
    public void eliminarAutor(String idAutor) throws Exception{
        List <Libro> libros= buscarPorAutor(idAutor);
        for (Libro libro : libros) {
            libro.setAutor(null);
            libroRepositorio.save(libro);
        }
        
    }
    @Transactional (rollbackFor=Exception.class)
    public void editar(String idAutor, String nombre, String apellido) throws Exception{
        Optional<Autor> respuesta = autorRepositorio.findById(idAutor);
        if (respuesta.isPresent()) {
            Autor autor = respuesta.get();
                    if (!nombre.isEmpty()){
                        autor.setNombre(nombre);
                    }
                    if (!apellido.isEmpty()){
                        autor.setApellido(apellido);
                    }
            autorRepositorio.save(autor);
        } else {
            throw new Exception("no existe ese autor");
        }
        
       
    }
    
    @Transactional (rollbackFor=Exception.class)
    public void anularAutor(String idAutor) throws Exception{
       try{
          Optional<Autor> respuesta= autorRepositorio.findById(idAutor);
          if (respuesta.isPresent()){
              Autor autor=respuesta.get();
              autor.setAlta(!autor.isAlta());
          }}catch (Exception e) {}
     
        
    }
    
   
}
