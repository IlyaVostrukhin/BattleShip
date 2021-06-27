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
     * Имя оппонента-человека по-умолчанию
     */
    public static final String PLAYER_2 = "Player2";

    /**
     * Имя оппонента-компьютера по-умолчанию
     */
    public static final String AI_NAME = "Computer";

    /**
     * Конструктор игрока
     */
    public Player() {
    }

    /**
     * Конструктор создания соперника
     * @param name - имя ПК
     */
    public Player(String name) {
        this.name = name;
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

    public void setName(String name) {
        this.name = name;
    }
}
