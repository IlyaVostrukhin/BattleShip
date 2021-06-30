package com.battleship;

import java.util.ArrayList;
import java.util.List;

public class Ship {

    /**
     * Количество палуб
     */
    private final int type;

    /**
     * Ориентация
     * по дефолту true - горизонтальная ориентация
     */
    private boolean horizontal;

    /**
     * Список ячеек определенного корабля
     */
    private List<Cell> coordinates;

    public Ship(int type) {
        this.type = type;
        horizontal = true;
        coordinates = new ArrayList<>(type);
    }

    public int getType() {
        return type;
    }

    /**
     * Установка ориентации корабля
     * @param isHorizontal - передает UI в зависимости от выбора игрока
     * true - горизонтальный
     */
    public void setHorizontal(boolean isHorizontal){
        horizontal = isHorizontal;
    }

    public boolean isHorizontal() {
        return horizontal;
    }

    public List<Cell> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<Cell> coordinates) {
        this.coordinates = coordinates;
    }

    /**
     * Создание корабля горизонтальной ориентации по заданным кординатам x и y
     * @param x - координата x
     * @param y - координата y
     */
    public void createHorizontalShip(int x, int y) {
        //для того чтобы корабль не отрисовался за пределами игрового поля, высчитываем максимально возможную координату по X для отрисовки с учетом количества палуб
        int pointLimitValueForPaint = (10 - type) * 40;
        for (int i = 0; i < type; i++) {
            Cell cell;
            //если Х больше максимально допустимой точки отрисовки, то координата по Х для ячейки = pointLimitValueForPaint + i * 40
            if (x > pointLimitValueForPaint) {
                cell = new Cell(pointLimitValueForPaint + i * 40, y, CellStatus.SHIP);
            } else {
                cell = new Cell((x + i * 40), y, CellStatus.SHIP);
            }
            coordinates.add(cell);
        }
    }

    /**
     * Создание корабля вертикальной ориентации по заданным кординатам x и y
     * @param x - координата x
     * @param y - координата y
     */
    public void createVerticalShip(int x, int y) {
        int pointStartPaint = (10 - type) * 40 + 20;
        for (int i = 0; i < type; i++) {
            Cell cell;
            if (pointStartPaint < y) {
                cell = new Cell(x, pointStartPaint + i * 40, CellStatus.SHIP);
            } else {
                cell = new Cell(x, (y + i * 40), CellStatus.SHIP);
            }
            coordinates.add(cell);
        }
    }
}
