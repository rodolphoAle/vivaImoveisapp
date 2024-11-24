package com.models;

import jakarta.persistence.*;

@Entity
public class Imagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private byte[] dados;

    @ManyToOne
    @JoinColumn(name = "imovel_id")
    private Imoveis imovel;

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getDados() {
        return dados;
    }

    public void setDados(byte[] dados) {
        this.dados = dados;
    }

    public Imoveis getImovel() {
        return imovel;
    }

    public void setImovel(Imoveis imovel) {
        this.imovel = imovel;
    }
}
