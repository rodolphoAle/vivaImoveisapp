package com.vivaImoveis;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.models.Imoveis;
import com.vivaImoveis.service.ImoveisService;

@Controller
public class ImoveisController {

    @Autowired
    private ImoveisService imoveisService;

    // Exibir formulário para cadastro
    @RequestMapping(value = "/cadastrarimoveis", method = RequestMethod.GET)
    public String form() {
        return "imoveis/formImoveis";
    }

    // Salvar imóvel
    @RequestMapping(value = "/cadastrarimoveis", method = RequestMethod.POST)
    public String form(@RequestParam("imagem") MultipartFile imagem,
                       @RequestParam("anunciante") String anunciante,
                       @RequestParam("categoria") String categoria,
                       @RequestParam("cidade") String cidade,
                       @RequestParam("endereco") String endereco,
                       @RequestParam("estado") String estado,
                       @RequestParam("observacoes") String observacoes,
                       @RequestParam("titulo") String titulo,
                       @RequestParam("valor") Double valor) {

        Imoveis imovel = new Imoveis();
        imovel.setAnunciante(anunciante);
        imovel.setCategoria(categoria);
        imovel.setCidade(cidade);
        imovel.setEndereco(endereco);
        imovel.setEstado(estado);
        imovel.setObservacoes(observacoes);
        imovel.setTitulo(titulo);
        imovel.setValor(valor);

        try {
            imoveisService.salvarImovel(imovel, imagem);
            return "redirect:/cadastrarimoveis";
        } catch (IOException e) {
            e.printStackTrace();
            return "Erro ao salvar a imagem";
        }
    }

    // Carregar imagem do imóvel
    @GetMapping(value = "/imagem/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> carregarImagem(@PathVariable Long id) {
        Imoveis imovel = imoveisService.buscarPorId(id);
        if (imovel != null && imovel.getImagem() != null) {
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imovel.getImagem());
        }
        return ResponseEntity.notFound().build();
    }

    // Exibir detalhes de um imóvel
    @GetMapping("/imoveis/{id}")
    public String exibirDetalhesImovel(@PathVariable Long id, Model model) {
        Imoveis imovel = imoveisService.buscarPorId(id);
        if (imovel != null) {
            model.addAttribute("imovel", imovel);
            return "imoveis/detalhesImovel";
        }
        return "redirect:/";
    }

    // Filtrar imóveis
    @RequestMapping(value = "/imoveis/filtrar", method = RequestMethod.GET)
    public String filtrarImoveis(@RequestParam(required = false) String estado,
                                 @RequestParam(required = false) String cidade,
                                 @RequestParam(required = false) String categoria,
                                 Model model) {

        model.addAttribute("imoveis", imoveisService.filtrarImoveis(estado, cidade, categoria));
        model.addAttribute("estado", estado);
        model.addAttribute("cidade", cidade);
        model.addAttribute("categoria", categoria);

        return "imoveis/listaImoveis";
    }
}
