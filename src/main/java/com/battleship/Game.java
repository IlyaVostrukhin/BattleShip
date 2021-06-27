package com.battleship;

import java.util.Arrays;
import java.util.Random;

import static com.battleship.CellStatus.EMPTY;
import static com.battleship.CellStatus.SHIP;
import static com.battleship.CellStatus.HIT;
import static com.battleship.GameStage.MAIN_MENU;
import static com.battleship.Player.AI_NAME;
import static com.battleship.Player.PLAYER_1;
import static com.battleship.Player.PLAYER_2;

public class Game {

    /**
     * Игрок
     */
    Player player;

    /**
     * Этап игры
     */
    String stage;

    /**
     * Оппонент (true - Компьютер, false - Человек)
     * Для проверки значения метод isComputerOpponent()
     * Для утсановки AI в true - метод setComputerOpponent()
     * Для установки AI в false - метод setPlayerOpponent()
     */
    private boolean computerOpponent;

    /**
     * Чей ход (true - игрока, false - соперника)
     */
    boolean playerTurn;

    CellStatus[][] playersField = new CellStatus[10][10];
    CellStatus[][] opponentsField = new CellStatus[10][10];

    Ship[] playersShip = new Ship[10];
    Ship[] opponentsShip = new Ship[10];

    public Game(){
        player = new Player();
        player.setName(setPlayerName());

        stage = MAIN_MENU.getStage();
        addNewEmptyField(playersField);
        addNewEmptyField(opponentsField);

        setPlayerOpponent(); //По умолчанию игра против ПК отключена
        playerTurn = Boolean.FALSE;

        addShips(playersShip);
        addShips(opponentsShip);
    }

    /**
     * Запрашивает у игрока имя и сохраняет его в сущности Player
     *
     */
    public String setPlayerName(){
        // ToDo создать метод и реализовать запрос у игрока его имени, по умолчанию возвращать имя "player1"


        return PLAYER_1;
    }

    /**
     * Добавляет оппонента в игру
     * @return сущность Player
     */
    public Player addOpponent(){
        Player opponent = new Player(AI_NAME);

        if(!isComputerOpponent()){
            // ToDo создать метод и реализовать запрос имени оппонента у стороннего апи


            if(PLAYER_1.equals(opponent.getName())){
                opponent.setName(PLAYER_2);
            }
        }

        return opponent;
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
     * Метод проверки значения выбора оппонента
     * @return true - для Компьютер
     */
    public boolean isComputerOpponent() {
        return computerOpponent;
    }

    /**
     * Установка режима игры с ПК
     */
    public void setComputerOpponent(){
        computerOpponent = Boolean.TRUE;
    }

    /**
     * Установка режима игры с человеком
     */
    public void setPlayerOpponent(){
        computerOpponent = Boolean.FALSE;
    }

    /**
     * Главный метод матча, вызывается для этапа Battle
     */
    public void battle(){

        shoot(player);

        turnUp();
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
     * @param player - игрок, осуществляющий ход
     *
     */
    public void shoot(Player player){
        int x = 0;
        int y = 0;

        // ToDo создать метод и реализовать в нем запрос у игрока координат выстрела, записать их в x, y
        // данный метод должен вернуть окончательные координаты, т.е. метод не принимает от игрока недопустымый выстрел:
        // - не принимать повторные координаты (нужна проверка статуса ячейки / элемента поля)


        int[] cell = player.shoot(x, y);

        // ToDo создать метод и реализовать в нем обработку координат выстрела
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

    /**
     * Создание набора корабля
     */
    public void addShips(Ship[] ships){
        ships[0] = new Ship(1);
        ships[1] = new Ship(1);
        ships[2] = new Ship(1);
        ships[3] = new Ship(1);
        ships[4] = new Ship(2);
        ships[5] = new Ship(2);
        ships[6] = new Ship(2);
        ships[7] = new Ship(3);
        ships[8] = new Ship(3);
        ships[9] = new Ship(4);
    }
}
