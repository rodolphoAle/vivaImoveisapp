package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.models.Imoveis;

@Repository
public interface ImoveisRepository extends JpaRepository<Imoveis, Long> {
    List<Imoveis> findTop6ByOrderByIdDesc();

    

    
}
