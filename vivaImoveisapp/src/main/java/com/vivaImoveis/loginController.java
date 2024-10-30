package com.vivaImoveis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.models.Usuario;
import com.repository.UsuarioRepository;

@Controller
public class loginController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @RequestMapping("/login")
    public String index() {
        return "imoveis/formLogin";
    }
    @RequestMapping("/imoveis/formUsuario")
    public String form() {
        return "imoveis/formLogin";
    }

    @PostMapping("/logar")
    public String logar(Model model, Usuario usuario) {
        Usuario user = this.usuarioRepository.login(usuario.getEmail(), usuario.getSenha());

        if (user != null) {
            return "redirect:/formImoveis";
        }

        model.addAttribute("erro", "Usuário ou senha inválido");
        return "imoveis/formLogin";
    }
}
