package com.vivaImoveis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.models.Usuario;
import com.vivaImoveis.service.UsuarioService;

import jakarta.servlet.http.HttpSession;

@Controller
public class usuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/cadastrarusuario")
    public String exibirFormularioCadastro() {
        return "imoveis/formUsuario";
    }

    @PostMapping("/cadastrarusuario")
    public String cadastrarUsuario(Usuario usuario) {
        usuarioService.cadastrarUsuario(usuario);
        return "redirect:/cadastrarusuario";
    }

    @GetMapping("/login")
    public String mostrarFormularioDeLogin() {
        return "imoveis/formLogin";
    }

    @PostMapping("/login")
    public String autenticarUsuario(@RequestParam("email") String email, 
                                    @RequestParam("senha") String senha, 
                                    HttpSession session) {
        Usuario usuario = usuarioService.autenticarUsuario(email, senha);

        if (usuario != null) {
            // Salva o usuário na sessão
            session.setAttribute("usuarioLogado", usuario);
            return "redirect:/cadastrarimoveis"; // Redireciona para a página de cadastrar imóveis
        }

        // Credenciais inválidas
        return "redirect:/login?erro=true";
    }
}
