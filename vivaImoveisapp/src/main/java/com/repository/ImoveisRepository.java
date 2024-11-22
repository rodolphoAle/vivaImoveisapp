package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.models.Imoveis;

@Repository
public interface ImoveisRepository extends JpaRepository<Imoveis, Long> {
    List<Imoveis> findTop6ByOrderByIdDesc();

    List<Imoveis> findByEstado(String estado);

   List<Imoveis> findByCidade(String cidade);

   List<Imoveis> findByCategoria(String categoria);

   List<Imoveis> findByEstadoAndCidade(String estado, String cidade);

   List<Imoveis> findByEstadoAndCategoria(String estado, String categoria);

   List<Imoveis> findByCidadeAndCategoria(String cidade, String categoria);

   List<Imoveis> findByEstadoAndCidadeAndCategoria(String estado, String cidade, String categoria);

    

    
}
