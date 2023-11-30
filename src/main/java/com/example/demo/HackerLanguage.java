package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Scanner;

@Component
public class HackerLanguage implements Challenge {
    private static final Logger LOG = LoggerFactory
            .getLogger(HackerLanguage.class);
    private static final Map<String, String> CONVERTER_MAP = Map.ofEntries(
            Map.entry("A", "4"),
            Map.entry("B", "I3"),
            Map.entry("C", "["),
            Map.entry("D", ")"),
            Map.entry("E", "3"),
            Map.entry("F", "|="),
            Map.entry("G", "&"),
            Map.entry("H", "#"),
            Map.entry("I", "1"),
            Map.entry("J", ",_|"),
            Map.entry("K", ">|"),
            Map.entry("L", "1"),
            Map.entry("M", "/\\/\\"),
            Map.entry("N", " ^/"),
            Map.entry("O", "0"),
            Map.entry("P", " |*"),
            Map.entry("Q", "(_,)"),
            Map.entry("R", "I2"),
            Map.entry("S", "5"),
            Map.entry("T", "7"),
            Map.entry("U", "(_)"),
            Map.entry("V", "\\/"),
            Map.entry("W", "\\/\\/\\"),
            Map.entry("X", "><"),
            Map.entry("Y", "j"),
            Map.entry("Z", "2"),
            Map.entry("1", "L"),
            Map.entry("2", "R"),
            Map.entry("3", "E"),
            Map.entry("4", "A"),
            Map.entry("5", "S"),
            Map.entry("6", "b"),
            Map.entry("7", "T"),
            Map.entry("8", "B"),
            Map.entry("9", "g"),
            Map.entry("0", "o")
    );

    private static final String TEST_1 = "Leet";
    private static final String TEST_2 = "Aquí está un texto de prueba para ver si funciona el reto!";


    @Override
    public void execute() {
        var scan=new Scanner(System.in);
        LOG.info("""
                
                Seleccionaste:
                Escribe un programa que reciba un texto y transforme lenguaje natural a
                "lenguaje hacker" (conocido realmente como "leet" o "1337"). Este lenguaje
                se caracteriza por sustituir caracteres alfanuméricos.
                - Utiliza esta tabla (https://www.gamehouse.com/blog/leet-speak-cheat-sheet/)
                con el alfabeto y los números en "leet".
                (Usa la primera opción de cada transformación. Por ejemplo "4" para la "a")
                
                Escribe el texto a convertir o presiona 0 para salir.
                """);


        while (!scan.hasNextInt() || scan.nextInt() != 0) {
            var text = scan.hasNextLine() ? scan.nextLine() : "";
            if ( !text.isEmpty()) {
                LOG.info("""
                                        
                        Probando texto '{}': {} \n""", text, coverter(text.toUpperCase()));
                continue;
            }
            LOG.info("""
                                        
                        Probando texto '{}': {} \n""", TEST_1, coverter(TEST_1.toUpperCase()));
            LOG.info("""
                                        
                        Probando texto '{}': {} \n""", TEST_2, coverter(TEST_2.toUpperCase()));
        }


    }

    private String coverter(String value){
        var textConverter = new StringBuilder();
        value.chars().forEach(c->{
            if(CONVERTER_MAP.containsKey(String.valueOf((char)c))){
                textConverter.append(CONVERTER_MAP.get(String.valueOf((char)c)));
                return;
            }
            textConverter.append((char) c);
        });
        return textConverter.toString();
    }
}
