
package com.libreria.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class PortalControlador {
    
    @GetMapping("/AdministrarAutor")
    public String AdministrarAutor(){
    return"AdministrarAutor.html";
            }
    
   
}
