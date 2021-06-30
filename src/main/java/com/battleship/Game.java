package com.battleship;

import com.views.View;

import java.util.ArrayList;
import java.util.List;

import static com.battleship.CellStatus.HIT;
import static com.battleship.CellStatus.SHIP;
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
    Cell[][] playersField = new Cell[10][10];
    Cell[][] opponentsField = new Cell[10][10];

    /**
     * Массив кораблей
     */
    private List<Ship> shipsOneDeck = new ArrayList<>(); //список всех наших однопалубных кораблей
    private List<Ship> shipsTwoDeck = new ArrayList<>(); //список всех наших двухпалубных кораблей
    private List<Ship> shipsThreeDeck = new ArrayList<>(); //список всех наших трехпалубных кораблей
    private List<Ship> shipsFourDeck = new ArrayList<>(); //список всех наших четырехпалубных кораблей

    public Game(){
        // Добавляем в игру игроков
        player = new Player(PLAYER_1);

        // Игрок, который раньше расставит корабли будет первым ходить
        playerTurn = Boolean.FALSE;

        // Запускаем Главное меню
//        mainMenu();
    }

    public Cell[][] getPlayersField() {
        return playersField;
    }

    public void setPlayersField(Cell[][] playersField) {
        this.playersField = playersField;
    }

    public Cell[][] getOpponentsField() {
        return opponentsField;
    }

    public void setOpponentsField(Cell[][] opponentsField) {
        this.opponentsField = opponentsField;
    }

    public List<Ship> getShipsOneDeck() {
        return shipsOneDeck;
    }

    public void setShipsOneDeck(List<Ship> shipsOneDeck) {
        this.shipsOneDeck = shipsOneDeck;
    }

    public List<Ship> getShipsTwoDeck() {
        return shipsTwoDeck;
    }

    public void setShipsTwoDeck(List<Ship> shipsTwoDeck) {
        this.shipsTwoDeck = shipsTwoDeck;
    }

    public List<Ship> getShipsThreeDeck() {
        return shipsThreeDeck;
    }

    public void setShipsThreeDeck(List<Ship> shipsThreeDeck) {
        this.shipsThreeDeck = shipsThreeDeck;
    }

    public List<Ship> getShipsFourDeck() {
        return shipsFourDeck;
    }

    public void setShipsFourDeck(List<Ship> shipsFourDeck) {
        this.shipsFourDeck = shipsFourDeck;
    }

    /**
     * Получение всех наших кораблей
     * @return список ВСЕХ наших кораблей
     */
    public List<Ship> getAllShips() {
        List<Ship> allCellsOfShips = new ArrayList<>();
        allCellsOfShips.addAll(shipsFourDeck);
        allCellsOfShips.addAll(shipsThreeDeck);
        allCellsOfShips.addAll(shipsTwoDeck);
        allCellsOfShips.addAll(shipsOneDeck);
        return allCellsOfShips;
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
     * Добавляет заданный корабль в соответствующий список кораблей в зависимости от количества палуб
     */
    public void addShip(Ship ship) {
        int shipType = ship.getType();
        switch (shipType) {
            case 1: {
                //проверка - если в списке уже есть максимальное кол-во кораблей данного типа (кол-во палуб),
                //то вызывается соответствующее информационное окно, иначе добавляем корабль в нужный список
                if (shipsOneDeck.size() < 4) {
                    shipsOneDeck.add(ship);
                    for (Cell cell : ship.getCoordinates()) {
                        addCellInField(playersField, cell);
                    }
                } else View.alert("Больше однопалубных нельзя. Максимально возможно - 4.");
                break;
            }
            case 2: {
                if (shipsTwoDeck.size() < 3) {
                    shipsTwoDeck.add(ship);
                    for (Cell cell : ship.getCoordinates()) {
                        addCellInField(playersField, cell);
                    }
                } else View.alert("Больше двухпалубных нельзя. Максимально возможно - 3.");
                break;
            }
            case 3: {
                if (shipsThreeDeck.size() < 2) {
                    shipsThreeDeck.add(ship);
                    for (Cell cell : ship.getCoordinates()) {
                        addCellInField(playersField, cell);
                    }
                } else View.alert("Больше трехпалубных нельзя. Максимально возможно - 2.");
                break;
            }
            case 4: {
                if (shipsFourDeck.size() < 1) {
                    shipsFourDeck.add(ship);
                    for (Cell cell : ship.getCoordinates()) {
                        addCellInField(playersField, cell);
                    }
                } else View.alert("Четырехпалубный уже добавлен. Максимально возможно - 1.");
                break;
            }
        }
    }

    /**
     * Удаление корабля из соответствующего списка - используется в процессе расстановки и удалении кораблей на своем игровом поле
     * @param ship - удаляемый корабль
     */
    public void removeShip(Ship ship) {
        //если корабль содержится в одном из списков, то перебираем все ячейки списка,
        //меняем значение их картинки на EMPTY и добавляем в матрицу нашего игрового поля эту ячейку
        if (shipsOneDeck.contains(ship)) {
            for (Cell cell : ship.getCoordinates()) {
                cell.setStatus(CellStatus.SEA);
                addCellInField(playersField, cell);
                shipsOneDeck.remove(ship);
            }
        } else if (shipsTwoDeck.contains(ship)) {
            for (Cell cell : ship.getCoordinates()) {
                cell.setStatus(CellStatus.SEA);
                addCellInField(playersField, cell);
                shipsTwoDeck.remove(ship);
            }
        } else if (shipsThreeDeck.contains(ship)) {
            for (Cell cell : ship.getCoordinates()) {
                cell.setStatus(CellStatus.SEA);
                addCellInField(playersField, cell);
                shipsThreeDeck.remove(ship);
            }
        } else if (shipsFourDeck.contains(ship)) {
            for (Cell cell : ship.getCoordinates()) {
                cell.setStatus(CellStatus.SEA);
                addCellInField(playersField, cell);
                shipsFourDeck.remove(ship);
            }
        }
    }

    /**
     * метод, устанавливающий значение указанной ячейки в указанную матрицу (игровое поле)
     * @param field поле игрока или оппонента
     * @param cell ячейка
     */
    public void addCellInField(Cell[][] field, Cell cell) {
        //по координатам ячейки вычисляем индексы соответствующего места в матрице (игровом поле)
        int i = cell.getX() / 40;
        int j = cell.getY() / 40;
        field[i][j] = cell;
    }

    /**
     * Метод, возвращающий ячейку из указанного игрового поля по координатам панели игрового поля
     * @param field поле игрока или оппонента
     * @param x координата по x
     * @param y координата по y
     * @return найденная ячейка поля или null
     */
    public Cell getCell(Cell[][] field, int x, int y) {
        int i = x / 40;
        int j = y / 40;
        int length = field.length - 1;
        //если координаты указывают на элемент, индекс которого больше размерности матрицы, то возвращаем null
        if (!(i > length || j > length)) {
            return field[i][j];
        }
        return null;
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
     * @return true - если ни одна ячейка не имеет статус ship || hit
     */
    private boolean isGameOver(){
        for (Cell[] cells1 : playersField) {
            for (Cell cells2 : cells1) {
                return !(cells2.getStatus() == SHIP || cells2.getStatus() == HIT);
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
