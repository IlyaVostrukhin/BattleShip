package com.battleship;

/**
 * Ячейка игрового поля.
 * ToDo: пока используется CellStatus (тупо для отрисовки поля). Надо обсудить этот момент.
 */
public class Cell {
    private int x;
    private int y;
    private CellStatus cellStatus;

    public Cell(int x, int y, CellStatus cellStatus) {
        this.x = x;
        this.y = y;
        this.cellStatus = cellStatus;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
