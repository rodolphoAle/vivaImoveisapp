package com.vivaImoveis.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.models.Imoveis;
import com.repository.ImoveisRepository;

@Service
public class ImoveisService {

    @Autowired
    private ImoveisRepository imoveisRepository;

    // Salvar imóvel no banco de dados
    public void salvarImovel(Imoveis imovel, MultipartFile imagem) throws IOException {
        if (imagem != null && !imagem.isEmpty()) {
            imovel.setImagem(imagem.getBytes());
        }
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

