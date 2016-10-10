package org.webonise.springboot.entities;

import org.springframework.stereotype.Component;

@Component
public class BestMove {
    private int row;
    private int column;
    private long value;

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getColumn() {
        return column;
    }
}
