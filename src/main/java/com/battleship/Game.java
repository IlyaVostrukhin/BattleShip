package com.battleship;

import java.util.Arrays;

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

    /**
     *  Игровые поля
     */
    CellStatus[][] playersField = new CellStatus[10][10];
    CellStatus[][] opponentsField = new CellStatus[10][10];

    /**
     * Массив кораблей
     */
    Ship[] playersShip = new Ship[10];
    Ship[] opponentsShip = new Ship[10];

    public Game(){
        // Добавляем в игру игроков
        player = new Player(PLAYER_1);

        // Игрок, который раньше расставит корабли будет первым ходить
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
    private void mainMenu(){
        // Очистка игровых данных, нужно при сбросе игры или окончании игры
        clear();

        // ToDo создать метод и реализовать запрос у игрока его имени

        // ToDo реализовать сервис работы в главном меню

        // Переходим к этапу расстановки кораблей
        registration();
    }

    /**
     * Расстановка кораблей
     */
    private void registration(){

        // ToDo создать и реализовать сервис расстановки кораблей

        // После расстановки переходим к ожиданию оппонента
        waiting();
    }

    /**
     * Ожидание оппонента
     */
    private void waiting(){
        // Устанавливаем статус игрока - ожидает
        player.setWaiting(Boolean.TRUE);

        // Проверяем, если оппонент ждет, устанавливаем право хода за оппонентом и начинаем игру
        if (Boolean.TRUE.equals(opponentIsWaiting())){
            playerTurn = Boolean.FALSE;
            battle();
        } else {
            // Иначе фиксируем право хода игрока и ждем, когда оппонент закончит расстановку кораблей
            // ToDo Написать ждуна

            playerTurn = Boolean.TRUE;
            // Предыдущий метод в цикле ждет оппонента и возвращает управление, когда оппонент готов к началу
            // начинаем игру
            battle();
        }
    }

    /**
     * Главный метод матча, вызывается для этапа Battle
     */
    private void battle(){
        // Только в свой ход
        if(playerTurn){
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
        }

        // Вызываем метод battle() пока игра не закончится
        battle();
    }

    /**
     * Конец игры
     */
    private void end(){
        // ToDo реализовать сервис окончания игры

        // Возвращаемся в главное меню
        // mainMenu();
    }

    /**
     * Создание набора корабля
     */
    private void addShips(Ship[] ships){
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
        // ToDo Написать реализацию получения от соперника статуса ожидания

        return Boolean.FALSE; // заглушка, поменять на статус оппонента
    }

    /**
     * Метод отвечает оппоненту, не ждем ли мы начала игры
     */
    public boolean playerIsWaiting(){
        // ToDo Написать реализацию отправки статуса ожидания сопернику

        return Boolean.TRUE; // заглушка, поменять на статус оппонента
    }

    /**
     * Переход хода
     * Метод выполняется после завершения всех действий предыдущего хода
     */
    private void turnUp(){
        playerTurn = !playerTurn;
        // ToDo передать инф оппоненту о передаче хода
    }

    /**
     * Выстрел игрока
     * @param player - игрок, осуществляющий ход
     */
    private void shoot(Player player){
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
     * Проверка окончания игры
     * true - если ни на одном поле нет ячеек ship || hit
     */
    private boolean isGameOver(){
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
        // Обнуляем статус ожидания
        player.setWaiting(Boolean.FALSE);

        // Очищаем игровые поля
        addNewEmptyField(playersField);
        addNewEmptyField(opponentsField);

        // Обнуляем данные о праве хода
        playerTurn = Boolean.FALSE;

        // ToDo Очистка игровых данных, нужно при сбросе игры или окончании игры

    }
}
