package com.battleship;

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
     * Координаты начала корабля
     * начало верх либо левая сторона
     */
    private Integer[][] coordinates;

    public Ship(int type) {
        this.type = type;
        horizontal = true;
        coordinates = new Integer[2][type];
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

    public Integer[][] getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Integer[][] coordinates) {
        this.coordinates = coordinates;
    }
}
