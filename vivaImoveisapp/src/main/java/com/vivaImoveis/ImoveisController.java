package com.vivaImoveis;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.models.Imoveis;
import com.models.Usuario;
import com.repository.ImoveisRepository;
import com.repository.UsuarioRepository;
@Controller
public class ImoveisController {

    @Autowired
    private ImoveisRepository ir;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public byte[] convertImageToByteArray(File file) throws IOException {
    return Files.readAllBytes(file.toPath());
}

    @RequestMapping(value = "/cadastrarimoveis", method = RequestMethod.GET)
    public String form() {
        return "imoveis/formImoveis";
    }
    
@RequestMapping(value = "/cadastrarimoveis", method = RequestMethod.POST)
public String form(@RequestParam("imagem") MultipartFile imagem,
                   @RequestParam("anunciante") String anunciante,
                   @RequestParam("categoria") String categoria,
                   @RequestParam("cidade") String cidade,
                   @RequestParam("endereco") String endereco,
                   @RequestParam("estado") String estado,
                   @RequestParam("observacoes") String observacoes,
                   @RequestParam("titulo") String titulo,
                   @RequestParam("valor") Double valor,
                   Principal principal) { // Obtém o usuário autenticado

    Imoveis imoveis = new Imoveis();

    try {
        // Converte a imagem em bytes
        byte[] imagemBytes = imagem.getBytes();
        imoveis.setImagem(imagemBytes);

        // Define os outros atributos
        imoveis.setAnunciante(anunciante);
        imoveis.setCategoria(categoria);
        imoveis.setCidade(cidade);
        imoveis.setEndereco(endereco);
        imoveis.setEstado(estado);
        imoveis.setObservacoes(observacoes);
        imoveis.setTitulo(titulo);
        imoveis.setValor(valor);

        // Busca o usuário autenticado pelo nome
        //Usuario usuario = usuarioRepository.findByNome(principal.getName());
        //imoveis.setUsuario(usuario); // Associa o imóvel ao usuário

        // Salva o objeto imoveis no banco de dados
        ir.save(imoveis);

    } catch (IOException e) {
        e.printStackTrace();
        return "Erro ao salvar a imagem"; // Mensagem de erro
    }

    return "redirect:/cadastrarimoveis";
}


    @GetMapping(value = "/imagem/{id}", produces = MediaType.IMAGE_JPEG_VALUE) // Ajuste o tipo MIME conforme necessário
    public ResponseEntity<byte[]> carregarImagem(@PathVariable Long id) {
        Imoveis imovel = ir.findById(id).orElse(null);
        if (imovel != null && imovel.getImagem() != null) {
            return ResponseEntity.ok()
                                 .contentType(MediaType.IMAGE_JPEG) // Ou o tipo correto
                                 .body(imovel.getImagem());
        }
        return ResponseEntity.notFound().build();
    }
}
