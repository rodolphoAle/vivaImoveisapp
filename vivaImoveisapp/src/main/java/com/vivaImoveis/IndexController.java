package com.vivaImoveis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import com.models.Imoveis;
import com.repository.ImoveisRepository;

@Controller
public class IndexController {

    @Autowired
    private ImoveisRepository imoveisRepository;

    @RequestMapping("/")
    public String index(Model model) {
        List<Imoveis> novidades = imoveisRepository.findTop6ByOrderByIdDesc();
        model.addAttribute("novidades", novidades);
        return "index";
    }
    @GetMapping("/imagem/{id}")
    public ResponseEntity<byte[]> getImagem(@PathVariable("id") long id) {
        Imoveis imovel = imoveisRepository.findById(id).orElse(null);
        if (imovel != null && imovel.getImagem() != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(org.springframework.http.MediaType.IMAGE_JPEG);
            return new ResponseEntity<>(imovel.getImagem(), headers, HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }
}
