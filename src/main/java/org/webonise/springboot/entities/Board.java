package org.webonise.springboot.entities;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component()
public class Board {
    private final int numberOfRow;
    private final int numberOfColumn;
    private char[][] board;

    public Board(final int numberOfRow, final int numberOfColumn) {
        this.numberOfRow = numberOfRow;
        this.numberOfColumn = numberOfColumn;
        board = new char[numberOfRow][numberOfColumn];
    }

    public char[][] getBoard() {
        return board;
    }

    public int getNumberOfRow() {
        return numberOfRow;
    }

    public int getNumberOfColumn() {
        return numberOfColumn;
    }

    public void setSpecificBoardValue(char value, int row, int column) {
        board[row][column] = value;
    }

    public char getSpecificBoardValue(int row, int column) {
        return board[row][column];
    }
}
