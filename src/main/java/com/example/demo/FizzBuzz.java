package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class FizzBuzz implements Challenge {
    private static final Logger LOG = LoggerFactory
            .getLogger(FizzBuzz.class);

    @Override
    public void execute() {
        LOG.info("""
                Seleccionaste:
                Escribe un programa que muestre por consola (con un print) los
                 números de 1 a 100 (ambos incluidos y con un salto de línea entre
                 cada impresión), sustituyendo los siguientes:
                 - Múltiplos de 3 por la palabra "fizz".
                 - Múltiplos de 5 por la palabra "buzz".
                 - Múltiplos de 3 y de 5 a la vez por la palabra "fizzbuzz".""");

        for (int i = 1; i <= 100; i++) {
            String fizzBuzz = "";
            if (i % 3 == 0) {
                fizzBuzz += "fizz";
            }
            if (i % 5 == 0) {
                fizzBuzz += "buzz";
            }
            LOG.info("El numero es: " + i);
            if (!fizzBuzz.isEmpty()) {
                LOG.info(fizzBuzz);
            }

        }
    }
}
