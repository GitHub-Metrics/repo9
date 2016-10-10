package org.webonise.springboot.services;


import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.webonise.springboot.TicTacToe.MinmaxAlgorithm;
import org.webonise.springboot.TicTacToe.TicTacToePlayer;
import org.webonise.springboot.entities.BestMove;
import org.webonise.springboot.entities.Board;

import java.util.Arrays;
import java.util.List;

import static org.webonise.springboot.TicTacToe.MinmaxAlgorithm.OPPONENT;
import static org.webonise.springboot.TicTacToe.MinmaxAlgorithm.PLAYER;

//singleton class
@Service
public class TicTacToeCalculator {

    private Board board;
    private static TicTacToeCalculator ticTacToeCalculator = new TicTacToeCalculator();

    private TicTacToeCalculator() {

        board = new Board(3, 3);
        fillBoardByDash();
    }

    @Bean("getTicTacToeCalculator")
    public static TicTacToeCalculator getTicTacToeCalculator() {
        return ticTacToeCalculator;
    }


    public String getResponse(String messageContent) {
        TicTacToePlayer ticTacToePlayer = new MinmaxAlgorithm();

        List<String> moveValue = Arrays.asList(messageContent.split(","));
        int row = Integer.parseInt(moveValue.get(0));
        int column = Integer.parseInt(moveValue.get(1));

        if (board.getSpecificBoardValue(row, column) != '_') {
            return row + "," + column + " is aleardy occupied";
        }

        board.setSpecificBoardValue(OPPONENT, row, column);

        if (!ticTacToePlayer.isMovesLeft(board)) {
            fillBoardByDash();
            return "No move left, game is Draw";
        }

        BestMove bestMove = ticTacToePlayer.getBestMove(board);

        int sendRow = bestMove.getRow();
        int sendColumn = bestMove.getColumn();
        board.setSpecificBoardValue(PLAYER, sendRow, sendColumn);
        displayBoard(board);

        int evaluateValue = ticTacToePlayer.evaluate(board);
        if (evaluateValue == -10) {
            fillBoardByDash();
            return "you won";
        } else if (evaluateValue == 10) {
            fillBoardByDash();
            return "Conputer won";
        }
        return sendRow + "," + sendColumn;
    }

    public void fillBoardByDash() {
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 3; column++) {
                this.board.setSpecificBoardValue('_', row, column);
            }
        }
    }

    private void displayBoard(Board board) {
        int noOfRows = board.getNumberOfRow();
        int noOfColumns = board.getNumberOfColumn();

        for (int row = 0; row < noOfRows; row++) {
            for (int column = 0; column < noOfColumns; column++) {

                System.out.print(board.getSpecificBoardValue(row, column) + " ");
            }
            System.out.println();
        }
    }

}
