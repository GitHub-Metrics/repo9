package org.webonise.springboot;


import java.util.Arrays;
import java.util.List;

import static org.webonise.springboot.MinmaxAlgorithm.PLAYER;

public class TicTacToeCalculator {
    private Board board;

    private TicTacToeCalculator ticTacToeCalculator = new TicTacToeCalculator();

    private TicTacToeCalculator() {
        fillBoardByDash();
    }

    public Board getBoard() {
        return ticTacToeCalculator.board;
    }


    public  String getResponse(String messageContent) {
        TicTacToePlayer ticTacToePlayer = new MinmaxAlgorithm();

        List<String> moveValue = Arrays.asList(messageContent.split(","));
        int row = Integer.parseInt(moveValue.get(0));
        int column = Integer.parseInt(moveValue.get(1));
        Board board1 = getBoard();

        board1.setSpecificBoardValue(PLAYER, row, column);
        BestMove bestMove = ticTacToePlayer.getBestMove(board1);
        int sendRow = bestMove.getRow();
        int sendColumn = bestMove.getColumn();
        return sendRow + "," + sendColumn;
    }

    private void fillBoardByDash() {
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 3; column++) {
                this.board.setSpecificBoardValue('_', row, column);
            }
        }
    }
}
