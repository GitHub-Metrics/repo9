package org.webonise.springboot.TicTacToe;


import org.webonise.springboot.entities.BestMove;
import org.webonise.springboot.entities.Board;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Integer.MIN_VALUE;
import static java.lang.Math.max;
import static java.lang.Math.min;

//here player is maximizer and opponent is minimizer
public class MinmaxAlgorithm implements TicTacToePlayer {

    private static final int INITIAL_BESTMOVE_ROW_VALUE = -1;
    private static final int INITIAL_BESTMOVE_COLUMN_VALUE = -1;
    private static final int DEPTH = 0;
    private static final boolean FLAG = false;
    public static final char PLAYER = 'x';
    public static final char OPPONENT = 'o';

    @Override
    public BestMove getBestMove(Board board) {
        int bestScore = MIN_VALUE;

        BestMove bestMove = new BestMove();
        bestMove.setRow(INITIAL_BESTMOVE_ROW_VALUE);
        bestMove.setColumn(INITIAL_BESTMOVE_COLUMN_VALUE);

        int noOfRows = board.getNumberOfRow();
        int noOfColumns = board.getNumberOfColumn();

        for (int row = 0; row < noOfRows; row++) {
            for (int column = 0; column < noOfColumns; column++) {
                if (board.getSpecificBoardValue(row, column) == '_') {
                    board.setSpecificBoardValue(PLAYER, row, column);

                    int moveScore = minmax(board, DEPTH, FLAG);

                    board.setSpecificBoardValue('_', row, column);

                    if (moveScore > bestScore) {
                        bestMove.setRow(row);
                        bestMove.setColumn(column);
                        bestScore = moveScore;
                    }
                }
            }
        }
        bestMove.setValue(bestScore);
        return bestMove;
    }

    private int minmax(Board board, int depth, boolean isPlayer) {

        int score = evaluate(board);

        // if maximizer has won
        if (score == +10) {
            return score - depth;
        }

        //if minimizer has won
        if (score == -10) {
            return score + depth;
        }

        //draw
        if (isMovesLeft(board) == false) {
            return 0;
        }

        if (isPlayer) {
            int best = MIN_VALUE;

            int noOfRows = board.getNumberOfRow();
            int noOfColumns = board.getNumberOfColumn();
            for (int row = 0; row < noOfRows; row++) {
                for (int column = 0; column < noOfColumns; column++) {
                    if (board.getSpecificBoardValue(row, column) == '_') {
                        board.setSpecificBoardValue(PLAYER, row, column);

                        best = max(best, minmax(board, depth + 1, !isPlayer));

                        board.setSpecificBoardValue('_', row, column);
                    }
                }
            }

            return best;
        } else {
            int best = MAX_VALUE;

            int noOfRows = board.getNumberOfRow();
            int noOfColumns = board.getNumberOfColumn();
            for (int row = 0; row < noOfRows; row++) {
                for (int column = 0; column < noOfColumns; column++) {
                    if (board.getSpecificBoardValue(row, column) == '_') {
                        board.setSpecificBoardValue(OPPONENT, row, column);

                        best = min(best, minmax(board, depth + 1, !isPlayer));

                        board.setSpecificBoardValue('_', row, column);
                    }
                }
            }

            return best;
        }

    }

    public boolean isMovesLeft(Board board) {
        int noOfRows = board.getNumberOfRow();
        int noOfColumns = board.getNumberOfRow();

        for (int row = 0; row < noOfRows; row++) {
            for (int column = 0; column < noOfColumns; column++) {
                if (board.getSpecificBoardValue(row, column) == '_') {
                    return true;
                }
            }
        }
        return false;
    }

    public int evaluate(Board board) {
        int noOfRows = board.getNumberOfRow();
        int noOfColumns = board.getNumberOfRow();
        char currentBoard[][] = board.getBoard();

        //check row
        for (int row = 0; row < noOfRows; row++) {
            if (currentBoard[row][0] == currentBoard[row][1] && currentBoard[row][1] == currentBoard[row][2]) {
                if (currentBoard[row][0] == PLAYER) {
                    return +10;
                } else if (currentBoard[row][0] == OPPONENT) {
                    return -10;
                }
            }
        }

        //check column
        for (int column = 0; column < noOfColumns; column++) {
            if (currentBoard[0][column] == currentBoard[1][column] && currentBoard[1][column] == currentBoard[2][column]) {
                if (currentBoard[0][column] == PLAYER) {
                    return +10;
                } else if (currentBoard[0][column] == OPPONENT) {
                    return -10;
                }
            }
        }


        //checkDiagonal
        if (currentBoard[0][0] == currentBoard[1][1] && currentBoard[1][1] == currentBoard[2][2]) {
            if (currentBoard[0][0] == PLAYER) {
                return +10;
            } else if (currentBoard[0][0] == OPPONENT) {
                return -10;
            }
        }

        if (currentBoard[0][2] == currentBoard[1][1] && currentBoard[1][1] == currentBoard[2][0]) {
            if (currentBoard[0][2] == PLAYER) {
                return +10;
            } else if (currentBoard[0][2] == OPPONENT) {
                return -10;
            }
        }

        //else no one win
        return 0;
    }
}

