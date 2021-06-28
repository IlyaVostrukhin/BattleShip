package com.battleship;

public class Player {

    /**
     * Имя игрока
     */
    private String name;

    /**
     * Имя игрока по-умолчанию
     */
    public static final String PLAYER_1 = "Player1";

    /**
     * Ожидает начала игры
     */
    private boolean waiting;


    public Player(String name) {
        this.name = name;
        this.waiting = Boolean.FALSE;
    }

    /**
     * Выстрел, вызывается при переходе хода, после проверки на конец игры
     * Вызывается сущностью Game, ждет координаты
     * @param x - координата x
     * @param y - координата y
     * @return int[] - массив с координатами
     */
    public int[] shoot(int x, int y){
        return new int[] {x, y};
    }

    public String getName() {
        return name;
    }

    // Установить пользовательское имя игрока
    public void setName(String name) {
        this.name = name;
    }

    public boolean isWaiting() {
        return waiting;
    }

    public void setWaiting(boolean waiting) {
        this.waiting = waiting;
    }
}
