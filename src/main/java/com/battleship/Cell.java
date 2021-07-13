package com.battleship;

/**
 * Ячейка игрового поля.
 */
public class Cell {
    private int x;
    private int y;
    private CellStatus status;

    public Cell(int x, int y, CellStatus status) {
        this.x = x;
        this.y = y;
        this.status = status;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public CellStatus getStatus() {
        return status;
    }

    public void setStatus(CellStatus status) {
        this.status = status;
    }
}
