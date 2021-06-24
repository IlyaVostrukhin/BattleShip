package com.battleship;

import java.util.Arrays;
import java.util.Random;

import static com.battleship.CellStatus.EMPTY;
import static com.battleship.CellStatus.SHIP;
import static com.battleship.CellStatus.HIT;
import static com.battleship.GameStage.MAIN_MENU;

public class Game {

    /**
     * Этап игры
     */
    String stage;

    /**
     * Режим игры (с ПК - true, c человеком - false)
     */
    boolean AI;

    /**
     * Чей ход (true - игрока, false - соперника)
     */
    boolean playerTurn;

    CellStatus[][] playersField = new CellStatus[10][10];
    CellStatus[][] opponentsField = new CellStatus[10][10];

    public Game(){
        stage = MAIN_MENU.getStage();
        addNewEmptyField(playersField);
        addNewEmptyField(opponentsField);
        AI = Boolean.FALSE;
        playerTurn = Boolean.FALSE;
    }

    /**
     * Добавляет пустое поле
     */
    private static void addNewEmptyField(CellStatus[][] field){
        for (CellStatus[] strings : field) {
            Arrays.fill(strings, EMPTY);
        }
    }

    public CellStatus[][] getPlayersField() {
        return playersField;
    }

    /**
     * Установка режима игры с ПК
     */
    public void setAIgameMode(){
        AI = Boolean.TRUE;
    }

    /**
     * Установка режима игры с человеком
     */
    public void setPlayersGameMode(){
        AI = Boolean.FALSE;
    }

    /**
     * Выбор игрока, начинающего игру
     */
    public void roulette(){
        // Так как данный класс будет у каждого игрока свой, нужно запускать у игрока,
        // который первый закончил этап подготовки,
        // результат передать другому игроку - метод setPlayersTurn()
        playerTurn = new Random().nextBoolean();
    }

    /**
     * Переход хода
     * Метод выполняется после завершения всех действий предыдущего хода
     */
    public void turnUp(){
        playerTurn = !playerTurn;
        shoot();
    }

    /**
     * Принять результат выбора хода от соперника
     * только если игрок последним закончил
     * этап Подготовки
     */
    public void setPlayerTurn(boolean opponentsTurn){
        playerTurn = !opponentsTurn;
    }

    /**
     * Выстрел игрока
     */
    public void shoot(){
        //ToDo: реализовать метод выстрела
    }

    /**
     * проверка окончания игры
     * true - если ни на одном поле нет ячеек ship || hit
     */
    public boolean isGameOver(){
        for (CellStatus[] cellStatuses1 : playersField) {
            for (CellStatus cellStatuses2 : cellStatuses1) {
                return !(cellStatuses2 == SHIP || cellStatuses2 == HIT);
            }
        }
        return Boolean.TRUE;
    }
}
