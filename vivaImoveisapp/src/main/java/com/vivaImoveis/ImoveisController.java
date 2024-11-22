package com.vivaImoveis;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.Principal;
import java.util.List;

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
import com.models.Usuario;
import com.repository.ImoveisRepository;
import com.repository.UsuarioRepository;
@Controller
public class ImoveisController {
   @Autowired
   private ImoveisRepository ir;
   @Autowired
   private UsuarioRepository usuarioRepository;

   public ImoveisController() {
   }

   public byte[] convertImageToByteArray(File file) throws IOException {
      return Files.readAllBytes(file.toPath());
   }

   @RequestMapping(
      value = {"/cadastrarimoveis"},
      method = {RequestMethod.GET}
   )
   public String form() {
      return "imoveis/formImoveis";
   }

   @RequestMapping(
      value = {"/cadastrarimoveis"},
      method = {RequestMethod.POST}
   )
   public String form(@RequestParam("imagem") MultipartFile imagem, @RequestParam("anunciante") String anunciante, @RequestParam("categoria") String categoria, @RequestParam("cidade") String cidade, @RequestParam("endereco") String endereco, @RequestParam("estado") String estado, @RequestParam("observacoes") String observacoes, @RequestParam("titulo") String titulo, @RequestParam("valor") Double valor, Principal principal) {
      Imoveis imoveis = new Imoveis();

      try {
         byte[] imagemBytes = imagem.getBytes();
         imoveis.setImagem(imagemBytes);
         imoveis.setAnunciante(anunciante);
         imoveis.setCategoria(categoria);
         imoveis.setCidade(cidade);
         imoveis.setEndereco(endereco);
         imoveis.setEstado(estado);
         imoveis.setObservacoes(observacoes);
         imoveis.setTitulo(titulo);
         imoveis.setValor(valor);
         this.ir.save(imoveis);
         return "redirect:/cadastrarimoveis";
      } catch (IOException var13) {
         var13.printStackTrace();
         return "Erro ao salvar a imagem";
      }
   }

   @GetMapping(
      value = {"/imagem/{id}"},
      produces = {"image/jpeg"}
   )
   public ResponseEntity<byte[]> carregarImagem(@PathVariable Long id) {
      Imoveis imovel = (Imoveis)this.ir.findById(id).orElse((Imoveis)null);
      return imovel != null && imovel.getImagem() != null ? ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imovel.getImagem()) : ResponseEntity.notFound().build();
   }

   @GetMapping({"/imoveis/{id}"})
   public String exibirDetalhesImovel(@PathVariable Long id, Model model) {
      Imoveis imovel = (Imoveis)this.ir.findById(id).orElse((Imoveis)null);
      if (imovel != null) {
         model.addAttribute("imovel", imovel);
         return "imoveis/detalhesImovel";
      } else {
         return "redirect:/";
      }
   }

    // Método para exibir imóveis filtrados
    @RequestMapping(value = "/imoveis/filtrar", method = RequestMethod.GET)
    public String filtrarImoveis(@RequestParam(required = false) String estado,
                                 @RequestParam(required = false) String cidade,
                                 @RequestParam(required = false) String categoria,
                                 Model model) {

        // Se não houver filtro algum, retorna todos os imóveis
        List<Imoveis> imoveisFiltrados = ir.findAll();

        // Verifica se o estado foi selecionado
        if (estado != null && !estado.isEmpty()) {
            imoveisFiltrados = ir.findByEstado(estado);
        }

        // Verifica se a cidade foi preenchida
        if (cidade != null && !cidade.isEmpty()) {
            imoveisFiltrados = ir.findByCidade(cidade);
        }

        // Verifica se a categoria foi selecionada
        if (categoria != null && !categoria.isEmpty()) {
            imoveisFiltrados = ir.findByCategoria(categoria);
        }

        // Passa os dados de filtros para o modelo
        model.addAttribute("imoveis", imoveisFiltrados);
        model.addAttribute("estado", estado);
        model.addAttribute("cidade", cidade);
        model.addAttribute("categoria", categoria);

        return "imoveis/listaImoveis"; // Página com a lista de imóveis filtrados
    }
}