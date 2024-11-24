package com.vivaImoveis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import com.models.Imoveis;
import com.repository.ImagemRepository;
import com.repository.ImoveisRepository;
import com.models.Imagem;

@Controller
public class IndexController {

    @Autowired
    private ImoveisRepository imoveisRepository;
    @Autowired
    private ImagemRepository imagemRepository;
    

    @RequestMapping("/")
    public String index(Model model) {
        // Buscar os imóveis mais recentes
        List<Imoveis> novidades = imoveisRepository.findTop6ByOrderByIdDesc();
        model.addAttribute("novidades", novidades);
        return "index";  // Retorna a página HTML que exibe os imóveis
    }

    // Método para buscar a imagem do imóvel por ID
    @GetMapping("/imagem/{id}")
public ResponseEntity<byte[]> getImagem(@PathVariable("id") long id) {
    Imagem imagem = imagemRepository.findById(id).orElse(null);  // Garanta que está acessando a imagem, não o imóvel
    if (imagem != null && imagem.getDados() != null) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG); // Ou o tipo correto, dependendo do formato da imagem
        return new ResponseEntity<>(imagem.getDados(), headers, HttpStatus.OK);
    }
    return ResponseEntity.notFound().build();
}
}
