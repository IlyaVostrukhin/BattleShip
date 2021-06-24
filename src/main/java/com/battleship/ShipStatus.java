package com.battleship;

/**
 * Статус корабля (под вопросом) status
 */
public enum ShipStatus {

    /**
     * Начальный статус. Корабль вне поля
     */
    DODCKED("docked"),

    /**
     * Установка корабля на поле
     */
    INSTALL("install"),

    /**
     * Корабль на поле
     * возможен переход в статус INSTALL
     */
    ACTIVE("active"),

    /**
     * Ранен
     */
     SHOOTING("shooted"),

    /**
     * Убит
     */
     DEAD("dead");

    private String status;

    ShipStatus(String status){
        this.status = status;
    }

    public String getStage(){ return status;}

}
