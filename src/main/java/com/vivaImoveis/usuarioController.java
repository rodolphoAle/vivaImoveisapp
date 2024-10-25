package com.vivaImoveis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.models.Usuario;
import com.repository.UsuarioRepository;

@Controller
public class usuarioController {

    @Autowired
    private UsuarioRepository ir;

    @RequestMapping(value="/cadastrarusuario",method =RequestMethod.GET)
    public String form(){

        return "imoveis/formUsuario";
    }
    @RequestMapping(value="/cadastrarusuario",method =RequestMethod.POST)
    public String form(Usuario usuario){

        ir.save(usuario);

        return "redirect:/cadastrarusuario";
    }

}
