package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.models.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByNome(String nome);
     
    
    Usuario findByEmail(String email);
    
    @Query(value = "SELECT * FROM usuario WHERE email = :email AND senha = :senha", nativeQuery = true)
    public Usuario login(@Param("email") String email, @Param("senha") String senha);

}
