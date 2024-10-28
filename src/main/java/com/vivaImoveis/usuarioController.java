package com.vivaImoveis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.models.Usuario;
import com.repository.UsuarioRepository;

import jakarta.servlet.http.HttpSession;

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
@GetMapping("/login")
public String mostrarFormularioDeLogin() {
    return "imoveis/formLogin"; // Nome do arquivo HTML para o formulário de login
}


@PostMapping(value = "/login")
public String form(@RequestParam("email") String email, @RequestParam("senha") String senha, HttpSession session) {
    Usuario usuario = ir.findByEmail(email);

    if (usuario != null && usuario.getSenha().equals(senha)) {
        // Salva o usuário na sessão
        session.setAttribute("usuarioLogado", usuario);
        return "redirect:/cadastrarimoveis"; // Redireciona para a página de cadastrar imóveis
    } else {
        // Credenciais inválidas
        return "redirect:/login?erro=true";
    }
}


}
