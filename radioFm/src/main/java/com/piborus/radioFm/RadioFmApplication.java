package com.piborus.radioFm;

import com.piborus.radioFm.repository.ArtistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RadioFmApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(RadioFmApplication.class, args);
	}
	@Autowired
	private ArtistaRepository artistaRepository;

	@Override
	public void run(String... args) throws Exception {

		Radinho radinho = new Radinho(artistaRepository);
		radinho.menuRadio();

	}
}
