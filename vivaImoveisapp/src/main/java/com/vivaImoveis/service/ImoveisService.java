package com.vivaImoveis.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.models.Imagem;
import com.models.Imoveis;
import com.repository.ImoveisRepository;

@Service
public class ImoveisService {

    @Autowired
    private ImoveisRepository imoveisRepository;

    // Salvar imóvel no banco de dados
    public void cadastrarImovel(Imoveis imovel, List<MultipartFile> imagens) throws IOException {
        List<Imagem> listaImagens = new ArrayList<>();

        for (MultipartFile arquivo : imagens) {
            Imagem imagem = new Imagem();
            imagem.setDados(arquivo.getBytes());
            imagem.setImovel(imovel);
            listaImagens.add(imagem);
        }

        imovel.setImagens(listaImagens);
        imoveisRepository.save(imovel);
    }

    // Buscar imóvel por ID
    public Imoveis buscarPorId(Long id) {
        return imoveisRepository.findById(id).orElse(null);
    }

    // Filtrar imóveis por critérios
    public List<Imoveis> filtrarImoveis(String estado, String cidade, String categoria) {
        List<Imoveis> imoveis = imoveisRepository.findAll();

        if (estado != null && !estado.isEmpty()) {
            imoveis = imoveisRepository.findByEstado(estado);
        }

        if (cidade != null && !cidade.isEmpty()) {
            imoveis = imoveisRepository.findByCidade(cidade);
        }

        if (categoria != null && !categoria.isEmpty()) {
            imoveis = imoveisRepository.findByCategoria(categoria);
        }

        return imoveis;
    }
}

