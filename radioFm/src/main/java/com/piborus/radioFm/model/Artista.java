package com.piborus.radioFm.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "artista")
public class Artista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "nome_artista")
    String nome;
    @Column(name = "tipo_Artista")
    @Enumerated(EnumType.STRING)
    TipoArtista tipoArtista;
    @Column(name = "estilo_artista")
    @Enumerated(EnumType.STRING)
    EstiloArtista estiloArtista;

    @OneToMany(mappedBy = "artista", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    List<Musica> musicas = new ArrayList<>();


    public Artista() {}

    public Artista( String nome, TipoArtista tipoArtista, EstiloArtista estiloArtista) {
        this.nome = nome;
        this.tipoArtista = tipoArtista;
        this.estiloArtista = estiloArtista;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {this.nome = nome;}

    public TipoArtista getTipoArtista() {
        return tipoArtista;
    }

    public void setTipoArtista(TipoArtista tipoArtista) {
        this.tipoArtista = tipoArtista;
    }

    public EstiloArtista getEstiloArtista() {
        return estiloArtista;
    }

    public void setEstiloArtista(EstiloArtista estiloArtista) {
        this.estiloArtista = estiloArtista;
    }

    public List<Musica> getMusicas() {return musicas;}

    public void setMusicas(List<Musica> musicas) {this.musicas = musicas;}

    @Override
    public String toString() {
        return "\nArtista{" +
                ", nome='" + nome + '\'' +
                ", tipoArtista=" + tipoArtista +
                ", estiloArtista=" + estiloArtista +
                ", musicas=" + musicas +
                '}';
    }
}
