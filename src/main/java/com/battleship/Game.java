package com.battleship;

import java.util.Arrays;
import java.util.Random;

import static com.battleship.CellStatus.EMPTY;
import static com.battleship.CellStatus.SHIP;
import static com.battleship.CellStatus.HIT;
import static com.battleship.GameStage.*; // загружаем весь список для использоваия в этом классе
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
    private String stage;

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
    private boolean playerTurn;

    /**
     * Закончил ли игрок подготовку первым
     */
    boolean first;

    CellStatus[][] playersField = new CellStatus[10][10];
    CellStatus[][] opponentsField = new CellStatus[10][10];

    Ship[] playersShip = new Ship[10];
    Ship[] opponentsShip = new Ship[10];

    public Game(){
        player = new Player();
        player.setName(setPlayerName());

        addNewEmptyField(playersField);
        addNewEmptyField(opponentsField);

        setPlayerOpponent(); //По умолчанию игра против ПК отключена
        playerTurn = Boolean.FALSE;

        addShips(playersShip);
        addShips(opponentsShip);

        mainMenu();
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
     * Процесс этапа игры Главное меню
     */
    public void mainMenu(){
        // Актуализируем этап Игры
        stage = MAIN_MENU.getStage();

        // ToDo реализовать сервис работы в главном меню

        // Переходим к этапу расстановки кораблей
        registration();
    }

    /**
     * Процесс этапа игры Подготовка
     */
    public void registration(){
        // Актуализируем этап Игры
        stage = REGISTRATION.getStage();

        // ToDo создать и реализовать сервис расстановки кораблей

        // После расстановки переходим к ожиданию оппонента
        waiting();
    }

    /**
     * Процесс этапа игры Ожидание
     */
    public void waiting(){
        // Актуализируем этап Игры
        stage = WAITING.getStage();

        // Проверяем, если оппонент ждет - начинаем игру
        if (WAITING.getStage().equals(getOpponentStage())){
            // Запрашиваем результаты жребия и начинаем игру
            giveRouletteResult();
            battle();
        } else {
            // Иначе фиксируем, бросаем жребий и ждем, когда оппонент попросит результаты
            isOpponentRequestRouletteResult(roulette());
            // Предыдущий метод в цикле ждет оппонента и возвращает управление, когда оппонент готов к началу
            // Начинаем игру
            battle();
        }
    }

    /**
     * Метод запрашивает у соперника его текущий этап игры
     */
    public String getOpponentStage(){
        // ToDo Написать реализацию получения от соперника этапа игры
        return "заглушка"; // заглушка
    }

    /**
     * Метод запрашивает результаты жребия оппонента
     */
    public void giveRouletteResult(){

        // ToDo Написать реализацию метода получения результатов жребьевки

        playerTurn = false; // заглушка. Заменить реализацией (Важно, результаты нужно инвертировать. см. isOpponentRequestRouletteResult()
    }

    /**
     * Метод-ждун - ожидает от соперника запрос результатов жребия
     */
    public void isOpponentRequestRouletteResult(boolean rouletteResult){

            // ToDo Реализовать ожидание запроса оппонентов результатов жребия
            // Отправить результаты

    }

    /**
     * Передача результата выбора хода оппоненту
     */
    private void setPlayersTurn() {
        if(isComputerOpponent()) {
            setPlayerTurn(roulette());
            return;
        }

        if(isFirst()) {
            setPlayerTurn(roulette());
            sendPlayerTurn(playerTurn);
            return;
        }

        /*
         * Принять результат жеребьевки от соперника
        */
        // ToDo Cоздать метод и реализовать получение от оппонента результатов рулетки

        boolean opponentTurn = (roulette()); //заглушка, пока нет метода принимающего результат рулетки от соперника

        setPlayerTurn(!opponentTurn);
    }

    /**
     * Метод отправляющий сопернику результат жеребьевки
     * @param playerTurn - результат жеребьевки
     */
    public void sendPlayerTurn(boolean playerTurn){
        // ToDo отправить результат жеребьевки
    }

    /**
     * Проверка, закончил ли подготовку игрок раньше соперника
     */
    public boolean isFirst(){
        return first;
    }

    /**
     * Установка признака, что игрок первым закончил подготовку
     */
    public void setFirst(){
        first = Boolean.TRUE;
    }

    /**
     * Главный метод матча, вызывается для этапа Battle
     */
    public void battle(){
        // Актуализируем этап игры
        // даже с учетом того, что метод рекурсивный проверка будет дороже, поэтому будет каждый раз присвоение
        stage = BATTLE.getStage();

        // Проверяем не окончена ли игра
        if(isGameOver()){
            // Игра окончена - вызываем метод окончания игры
            end();
            return;
        }

        // Ждем выстрела игрока или других действий
        shoot(player);

        // Передаем ход
        turnUp();

        // Проверить не окончена ли игра
        if(isGameOver()){
            // Игра окончена - вызываем метод окончания игры
            end();
            return;
        }

        // Вызываем метод battle() пока игра не закончится
        battle();
    }

    /**
     * Процесс этапа игры Конец игры
     */
    public void end(){
        stage = END.getStage();
        // ToDo реализовать сервис окончания игры

        // Возвращаемся в главное меню
        mainMenu();
    }

    /**
     * Выбор игрока, начинающего игру
     */
    public boolean roulette(){
        // Так как данный класс будет у каждого игрока свой, нужно запускать у игрока,
        // который первый закончил этап подготовки,
        // результат передать другому игроку - метод setPlayersTurn()
        return new Random().nextBoolean();
    }

    /**
     * Переход хода
     * Метод выполняется после завершения всех действий предыдущего хода
     */
    public void turnUp(){
        playerTurn = !playerTurn;
    }


    /**
     * Выстрел игрока
     * @param player - игрок, осуществляющий ход
     */
    public void shoot(Player player){
        int x = 0;
        int y = 0;

        // ToDo создать метод и реализовать в нем запрос у игрока координат выстрела, записать их в x, y
        // данный метод должен вернуть окончательные координаты, т.е. метод не принимает от игрока недопустымый выстрел:
        // - не принимать повторные координаты (нужна проверка статуса ячейки / элемента поля)
        // Метод должен реагировать на нажатие доступных кнопок меню (Выход) и корректно обрабатывать их


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

    public String getStage() {
        return stage;
    }

    public boolean isPlayerTurn() {
        return playerTurn;
    }

    public void setPlayerTurnTRUE(){
        playerTurn = Boolean.TRUE;
    }

    public void setPlayerTurnFALSE(){
        playerTurn = Boolean.FALSE;
    }

    public void setPlayerTurn(boolean rulleteResult){
        playerTurn = playerTurn;
    }
}
