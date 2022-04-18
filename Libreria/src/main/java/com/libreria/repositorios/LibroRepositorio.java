
package com.libreria.repositorios;

import com.libreria.entidades.Autor;
import com.libreria.entidades.Libro;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LibroRepositorio extends JpaRepository<Libro, String>{
  
   @Query ("SELECT l FROM Libro l WHERE l.autor LIKE :id ") 
   public List<Libro> buscarPorAutor(@Param("id") String idAutor);
       
    @Query ("SELECT l FROM Libro l WHERE l.editorial LIKE :id ") 
   public List<Libro> buscarPorEditorial(@Param("id") String idEditorial);
    
}
