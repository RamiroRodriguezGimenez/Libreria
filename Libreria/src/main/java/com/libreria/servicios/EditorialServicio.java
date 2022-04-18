
package com.libreria.servicios;

import com.libreria.entidades.Editorial;
import com.libreria.entidades.Libro;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.libreria.repositorios.EditorialRepositorio;
import com.libreria.repositorios.LibroRepositorio;
import java.util.List;

@Service
public class EditorialServicio {
    
    @Autowired 
    private EditorialRepositorio editorialRepositorio;
    
    @Autowired
    private LibroRepositorio libroRepositorio;
    
    @Transactional(readOnly=true)
    public Editorial buscarPorId(String id) throws Exception{
        Optional<Editorial> respuesta = editorialRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Editorial editorial = respuesta.get();
            return editorial;
        } else {
            throw new Exception ("Esa editorial no existe");
        }
            
    }
    
    @Transactional (rollbackFor = Exception.class)
    public Editorial crear(String nombre) throws Exception{
        validar(nombre);
        Editorial editorial=new Editorial();
        editorial.setNombre(nombre);
        editorial.setAlta(true);
        editorialRepositorio.save(editorial);
        return editorial;
        
    }
    
    private void validar(String nombre) throws Exception {
        if (nombre == null || nombre.isEmpty()) {
            throw new Exception("Debe ingresar nombre de la editorial");
        }
    }
    
    @Transactional (readOnly=true)
    public List<Editorial> listar(){
        return editorialRepositorio.findAll();
    }
    
    @Transactional (rollbackFor = Exception.class)
    public void editar(String id, String nombre){
        Optional<Editorial> respuesta=editorialRepositorio.findById(id);
        if (respuesta.isPresent()){
            Editorial editorial= respuesta.get();
            editorial.setNombre(nombre); 
            editorialRepositorio.save(editorial);
        }
        
    }
    
    @Transactional (rollbackFor = Exception.class)
    public void anular(String id){
        Optional<Editorial> respuesta=editorialRepositorio.findById(id);
        if (respuesta.isPresent()){
            Editorial editorial= respuesta.get();
            editorial.setAlta(!editorial.isAlta());
            editorialRepositorio.save(editorial);
        }
    }
    
    @Transactional (rollbackFor = Exception.class)
    public void eliminar(String id){
        Optional<Editorial> respuesta=editorialRepositorio.findById(id);
        eliminarDeLibro(id);
        editorialRepositorio.deleteById(id);
    }
    
    @Transactional (rollbackFor = Exception.class)
    public void eliminarDeLibro(String id){
        try{
        List <Libro> libros= buscarPorEditorial(id);
        for (Libro libro : libros) {
            libro.setEditorial(null);
            libroRepositorio.save(libro);
        }} catch(Exception e){}
    }
    
    @Transactional (readOnly=true)
    private List<Libro> buscarPorEditorial(String idEditorial) throws Exception{
        
        List<Libro> respuesta = libroRepositorio.buscarPorEditorial(idEditorial);
        
               return respuesta;
                
        
        
    }
    
}
