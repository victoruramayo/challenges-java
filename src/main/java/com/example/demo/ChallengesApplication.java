package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import java.util.Scanner;

@SpringBootApplication
public class ChallengesApplication implements CommandLineRunner {

    private static Logger LOG = LoggerFactory
            .getLogger(ChallengesApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ChallengesApplication.class, args);
    }

    private final FizzBuzz fizzBuzz;
    private final HackerLanguage hackerLanguage;

    @Autowired
    public ChallengesApplication(FizzBuzz fizzBuzz,HackerLanguage hackerLanguage){
        this.fizzBuzz=fizzBuzz;
        this.hackerLanguage=hackerLanguage;
    }

    @Override
    public void run(String... args) throws Exception {
        LOG.info("""
        
        Selecciona una opcion:
        0: Para salir
        Del 1 al 52: Seleccionar un reto semanal
        
        """);
        var scan = new Scanner(System.in);


        while (true){
            var option = scan.nextInt();
            switch (option) {
				case 0:
					return;
				case 1:
                    fizzBuzz.execute();
					break;
				case 2:
                    hackerLanguage.execute();
					break;
				default:
					LOG.info("Selecciona una opcion valida");
            }
        }
    }
}
