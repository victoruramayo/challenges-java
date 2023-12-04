package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class TennisGame implements Challenge {
    private static final Logger LOG = LoggerFactory
            .getLogger(TennisGame.class);

    private static class Game {
        private Score scoreFirtsPlayer;
        private Score scoreSecondPlayer;
        private static final Map<Score, Score> scores = Map.of(
                Score.ZERO, Score.ONE,
                Score.ONE, Score.TWO,
                Score.TWO, Score.THREE,
                Score.THREE, Score.AD,
                Score.AD, Score.WINNER,
                Score.WINNER, Score.WINNER
        );

        public Game(Score first, Score second) {
            this.scoreFirtsPlayer = first;
            this.scoreSecondPlayer = second;
        }

        public Score getScoreFirtsPlayer() {
            return scoreFirtsPlayer;
        }

        public Score getScoreSecondPlayer() {
            return scoreSecondPlayer;
        }

        public String markPlayerScore(Player player) {

            if (player == Player.P1) {
                if (this.scoreFirtsPlayer == Score.THREE && this.scoreSecondPlayer == Score.AD) {
                    this.scoreSecondPlayer = Score.THREE;
                    return "Deuce";
                } else {
                    this.scoreFirtsPlayer = scores.get(this.scoreFirtsPlayer);
                }
                if (this.scoreFirtsPlayer == Score.AD || this.scoreFirtsPlayer ==Score.WINNER){
                    return "%s %s".formatted(this.scoreFirtsPlayer.getValue(),player);
                }
            }
            if (player == Player.P2) {
                if (this.scoreSecondPlayer == Score.THREE && this.scoreFirtsPlayer == Score.AD) {
                    this.scoreFirtsPlayer = Score.THREE;
                } else {
                    this.scoreSecondPlayer = scores.get(this.scoreSecondPlayer);
                }
                if (this.scoreFirtsPlayer == Score.AD || this.scoreFirtsPlayer ==Score.WINNER){
                    return "%s %s".formatted(this.scoreSecondPlayer.getValue(),player);
                }
            }

            if( this.scoreFirtsPlayer == Score.THREE && this.scoreSecondPlayer == Score.THREE){
                return "Deuce";
            }

            return "%s %s".formatted(this.getScoreFirtsPlayer().getValue(), this.getScoreSecondPlayer().getValue());
        }
    }

    private enum Player {
        P1("P1"), P2("P2");
        private final String value;

        Player(String player) {
            this.value = player;
        }

        public String getValue() {
            return this.value;
        }
    }

    private enum Score {
        ZERO("LOVE"), ONE("15"), TWO("30"), THREE("40"), AD("Ventaja"), WINNER("Ha Ganado");
        private final String value;

        Score(String score) {
            this.value = score;
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

        var game = new Game(Score.ZERO, Score.ZERO);

        var points = List.of(Player.P1,
                Player.P1,
                Player.P2,
                Player.P2,
                Player.P1,
                Player.P2,
                Player.P1,
                Player.P2,
                Player.P1,
                Player.P1
        );

        System.out.println(game.getScoreSecondPlayer().getValue() + " " + game.getScoreFirtsPlayer().getValue());
        for (Player p : points) {
            if(game.getScoreSecondPlayer()==Score.WINNER || game.getScoreFirtsPlayer()==Score.WINNER){
                System.out.println("Partido finalizado");
                continue;
            }
            System.out.println(p.getValue());
            System.out.println(game.markPlayerScore(p));
        }

    }

}

