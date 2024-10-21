package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.models.Imoveis;

@Repository
public interface ImoveisRepository extends JpaRepository<Imoveis, Long> {

}
