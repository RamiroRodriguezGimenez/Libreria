
package com.libreria.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Libro {
    
    @Id
    @GeneratedValue(generator="uuid")
    @GenericGenerator(name="uuid", strategy="uuid2")
    private String id;
    private Long isbn;
    private String titulo;
    private Integer anio;
    private Integer ejemplar;
    private Integer ejemplaresPrestado;
    private Integer ejemplareRestante;
    private boolean alta;
    @ManyToOne
    private Editorial editorial;
    @ManyToOne
    private Autor autor;

    public String getId() {
        return id;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getIsbn() {
        return isbn;
    }

    public void setIsbn(Long isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public Integer getEjemplar() {
        return ejemplar;
    }

    public void setEjemplar(Integer ejemplar) {
        this.ejemplar = ejemplar;
    }

    public Integer getEjemplaresPrestado() {
        return ejemplaresPrestado;
    }

    public void setEjemplaresPrestado(Integer ejemplaresPrestado) {
        this.ejemplaresPrestado = ejemplaresPrestado;
    }

    public Integer getEjemplareRestante() {
        return ejemplareRestante;
    }

    public void setEjemplareRestante(Integer ejemplareRestante) {
        this.ejemplareRestante = ejemplareRestante;
    }

   
   

    public boolean isAlta() {
        return alta;
    }

    public void setAlta(boolean alta) {
        this.alta = alta;
    }

    public Editorial getEditorial() {
        return editorial;
    }

    public void setEditorial(Editorial editorial) {
        this.editorial = editorial;
    }
    
}
