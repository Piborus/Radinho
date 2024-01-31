package com.piborus.radioFm.model;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Table(name = "musica")
public class Musica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "nome_musica")
    String nomeMusica;

    @ManyToOne
    Artista artista;

    public Musica(){}

    public Musica(String nomeMusica) {
        this.nomeMusica = nomeMusica;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeMusica() {
        return nomeMusica;
    }

    public void setNomeMusica(String nomeMusica) {
        this.nomeMusica = nomeMusica;
    }

    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }

    @Override
    public String toString() {
        return "Musica{" +
                ", Nome='" + nomeMusica + '\'' +
                ", artista=" + artista.getNome() +
                '}';
    }
}
