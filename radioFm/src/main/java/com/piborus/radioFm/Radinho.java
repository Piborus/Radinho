package com.piborus.radioFm;

import com.piborus.radioFm.model.Artista;
import com.piborus.radioFm.model.EstiloArtista;
import com.piborus.radioFm.model.Musica;
import com.piborus.radioFm.model.TipoArtista;
import com.piborus.radioFm.repository.ArtistaRepository;
import com.piborus.radioFm.service.ConsultaChatGPT;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Radinho {

    private Scanner leitura = new Scanner(System.in);

    private ArtistaRepository repository;


    public Radinho(ArtistaRepository artistaRepository) {
        this.repository = artistaRepository;
    }

    public void menuRadio() {

        var opcao = -1;

        while (opcao != 10) {
            var menu = """
                    **** Menu Radinho Piborus ****
                                        
                    1- Cadastra Artista
                    2- Cadastra Musica
                    3- Lista Musicas
                    4- Lista Artistas
                    5- Buscar Musicas por Artista
                    6- Pesquisar dados sobre um Artista
                                        
                    9- Sair
                    """;

            System.out.println(menu);
            opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao) {
                case 1:
                    cadastraArtista();
                    break;
                case 2:
                    cadastraMusica();
                    break;
                case 3:
                    listaDeMusicas();
                    break;
                case 4:
                    listaDeArtistasPeloEstilo();
                    break;
                case 5:
                    buscarMusicaPorArtista();
                    break;
                case 6:
                    pesquisarDadosDoArtista();
                    break;
                default:
                    System.out.println("Opção invalida");
            }
        }
    }

    private void cadastraArtista() {

        System.out.println("Qual o nome do Artista");
        var nome = leitura.nextLine();

        System.out.println("Informe o tipo do Artista( solo, dupla, banda)");
        var tipo = leitura.nextLine();
        TipoArtista tipoArtista = TipoArtista.valueOf(tipo.toUpperCase());

        System.out.println("Informe o estilo musical");
        var estilo = leitura.nextLine();
        EstiloArtista estiloArtista = EstiloArtista.valueOf(estilo.toUpperCase());

        Artista artista = new Artista(nome, tipoArtista, estiloArtista);
        repository.save(artista);

        System.out.println("Deseja continua (s/n)");
        String resposta = leitura.next();
        if (resposta.equalsIgnoreCase("s")) {
            leitura.nextLine();
            cadastraArtista();
        }

    }

    private void cadastraMusica() {

        System.out.println("Digite o nome do Artista");
        var cadastro = leitura.nextLine();
        Optional<Artista> artista = repository.findByNomeContainingIgnoreCase(cadastro);

        if (artista.isPresent()) {
            System.out.println("Qual o nome da musica");
            var nome = leitura.nextLine();

            Musica musica = new Musica(nome);
            musica.setArtista(artista.get());
            artista.get().getMusicas().add(musica);
            repository.save(artista.get());


        } else {
            System.out.println("Artista não encontrado");
        }

    }

    private void listaDeMusicas() {
        List<Artista> artistas = repository.findAll();
        artistas.forEach(artista -> artista.getMusicas().forEach(System.out::println));
    }

    private void listaDeArtistasPeloEstilo() {

        System.out.println("Digite o estilo de musica");
        EstiloArtista estiloArtista = EstiloArtista.valueOf(leitura.nextLine().toUpperCase());
        List<Artista> artistas = repository.findAll();
        artistas.stream()
                .filter(artista -> artista.getEstiloArtista() == estiloArtista)
                .forEach(System.out::println);

    }

    private void buscarMusicaPorArtista() {
        System.out.println("Buscar de qual artista?");
        var nome = leitura.nextLine();

        List<Musica> musicas = repository.buscaMusicaPorArtista(nome);
        musicas.forEach(System.out::println);
    }

    private void pesquisarDadosDoArtista() {
        System.out.println("Pesquisar dados sobre qual artista? ");
        var nome = leitura.nextLine();
        var resposta = ConsultaChatGPT.obterInformacao(nome);
        System.out.println(resposta.trim());
    }

}
