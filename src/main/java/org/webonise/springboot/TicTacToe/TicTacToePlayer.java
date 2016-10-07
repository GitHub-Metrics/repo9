package org.webonise.springboot.TicTacToe;

import org.webonise.springboot.entities.BestMove;
import org.webonise.springboot.entities.Board;

public interface TicTacToePlayer {

    BestMove getBestMove(Board board);

    boolean isMovesLeft(Board board);

    int evaluate(Board board);
}
