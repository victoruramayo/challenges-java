package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TennisGame implements Challenge {
    private static final Logger LOG = LoggerFactory
            .getLogger(TennisGame.class);

    private record Point<T, P>(T first, P second) {}

    private enum Players {
        P1("P1"), P2("P2");
        private final String value;

        Players(String player) {
            this.value = player;
        }

        public String getValue() {
            return this.value;
        }
    }

    @Override
    public void execute() {
        LOG.info("""
                                
                * Escribe un programa que muestre cómo transcurre un juego de tenis y quién lo ha ganado.
                 * El programa recibirá una secuencia formada por "P1" (Player 1) o "P2" (Player 2), según quien
                 * gane cada punto del juego.
                 *\s
                 * - Las puntuaciones de un juego son "Love" (cero), 15, 30, 40, "Deuce" (empate), ventaja.
                 * - Ante la secuencia [P1, P1, P2, P2, P1, P2, P1, P1], el programa mostraría lo siguiente:
                 *   15 - Love
                 *   30 - Love
                 *   30 - 15
                 *   30 - 30
                 *   40 - 30
                 *   Deuce
                 *   Ventaja P1
                 *   Ha ganado el P1
                 * - Si quieres, puedes controlar errores en la entrada de datos.  \s
                 * - Consulta las reglas del juego si tienes dudas sobre el sistema de puntos.  \s
                """);

        var points = new Point<>("Love", "Love");

        var game = List.of(Players.P1,
                Players.P1,
                Players.P2,
                Players.P2,
                Players.P1,
                Players.P2,
                Players.P1,
                Players.P2,
                Players.P1,
                Players.P1
        );

        System.out.println(points.first()+ " " + points.second());
        for (Players p : game) {
            System.out.println(p.getValue());
            points=rulesPoints(p,points);
            System.out.println(points.first()+ " " + points.second());
        }


    }

    private Point<String, String> rulesPoints(Players matcher, Point<String, String> points) {
        if (matcher == Players.P1) {
            return switch (points.first()) {
                case "Love" -> new Point<>("15", points.second());
                case "15" -> new Point<>("30", points.second());
                case "30" -> new Point<>("40", points.second());
                case "40" -> points.second().equals("Ventaja P2") ? new Point<>("40", "40") : new Point<>("Ventaja P1", points.second());
                case "Ventaja P1" -> new Point<>("Winner", "Loser");
                default -> points;
            };
        }
        return switch (points.second()) {
            case "Love" -> new Point<>(points.first(), "15");
            case "15" -> new Point<>(points.first(), "30");
            case "30" -> new Point<>(points.first(), "40");
            case "40" -> points.first().equals("Ventaja P1") ? new Point<>("40", "40") : new Point<>(points.first(), "Ventaja P2");
            case "Ventaja P2" -> new Point<>("Loser", "Winner");
            default -> points;
        };
    }
}

