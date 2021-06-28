package com.battleship;

import java.util.Arrays;
import java.util.Random;

import static com.battleship.CellStatus.EMPTY;
import static com.battleship.CellStatus.SHIP;
import static com.battleship.CellStatus.HIT;
import static com.battleship.Player.PLAYER_1;

public class Game {

    /**
     * Игрок
     */
    Player player;

    /**
     * Чей ход (true - игрока, false - соперника)
     */
    private boolean playerTurn;

    CellStatus[][] playersField = new CellStatus[10][10];
    CellStatus[][] opponentsField = new CellStatus[10][10];

    Ship[] playersShip = new Ship[10];
    Ship[] opponentsShip = new Ship[10];

    public Game(){
        // Добавляем в игру игроков
        player = new Player(PLAYER_1);

        // Чей ход будет определено жребием позже
        playerTurn = Boolean.FALSE;

        // Добавляем в игру поля
        addNewEmptyField(playersField);
        addNewEmptyField(opponentsField);

        // Добавляем корабли
        addShips(playersShip);
        addShips(opponentsShip);

        // Запускаем Главное меню
        mainMenu();
    }

    /**
     * Процесс этапа игры Главное меню
     */
    public void mainMenu(){
        // Очистка игровых данных, нужно при сбросе игры или окончании игры
        clear();

        // ToDo создать метод и реализовать запрос у игрока его

        // ToDo реализовать сервис работы в главном меню

        // Переходим к этапу расстановки кораблей
        registration();
    }

    /**
     * Процесс этапа игры Подготовка
     */
    public void registration(){

        // ToDo создать и реализовать сервис расстановки кораблей

        // После расстановки переходим к ожиданию оппонента
        waiting();
    }

    /**
     * Процесс этапа игры Ожидание
     */
    public void waiting(){
        // Актуализируем этап Игры
        player.setWaiting(Boolean.TRUE);

        // Проверяем, если оппонент ждет - начинаем игру
        if (Boolean.TRUE.equals(opponentIsWaiting())){
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
     * Главный метод матча, вызывается для этапа Battle
     */
    public void battle(){
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
        // ToDo реализовать сервис окончания игры

        // Возвращаемся в главное меню
        mainMenu();
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

    /**
     * Добавляет пустое игровое поле
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
     * Метод спрашивает у оппонента, не ждет ли он начала игры
     */
    private boolean opponentIsWaiting(){
        // ToDo Написать реализацию получения от соперника этапа игры

        return Boolean.FALSE; // заглушка, поменять на статус оппонента
    }

    /**
     * Метод запрашивает результаты жребия оппонента
     */
    private void giveRouletteResult(){

        // ToDo Написать реализацию метода получения результатов жребьевки

        playerTurn = false; // заглушка. Заменить реализацией (Важно, результаты нужно инвертировать. см. isOpponentRequestRouletteResult()
    }

    /**
     * Метод-ждун - ожидает от соперника запрос результатов жребия
     */
    private void isOpponentRequestRouletteResult(boolean rouletteResult){

        // ToDo Реализовать ожидание запроса оппонентов результатов жребия

        // Отправляем результаты жеребьевки
        sendPlayerTurn(rouletteResult);
    }

    /**
     * Метод отправляющий сопернику результат жеребьевки
     * @param playerTurn - результат жеребьевки
     */
    public void sendPlayerTurn(boolean playerTurn){
        // ToDo отправить результат жеребьевки
    }

    /**
     * Жребий - выбор игрока, начинающего игру
     */
    public boolean roulette(){
        return playerTurn = new Random().nextBoolean();
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
     * Очистка игровых данных
     */
    private void clear(){
        player.setWaiting(Boolean.FALSE);

        // ToDo Очистка игровых данных, нужно при сбросе игры или окончании игры

    }
}
