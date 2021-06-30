package com.controllers;

import com.battleship.Cell;
import com.battleship.CellStatus;
import com.battleship.Game;
import com.battleship.Ship;
import com.views.View;

import java.util.List;

public class Controller {

    private Game game;

    public Controller(Game game) {
        this.game = game;
    }

    /**
     * Загрузка пустого игровое поля игрока
     */
    public void loadEmptyMyField() {
        //очистка списков всех типов кораблей
        game.getShipsOneDeck().clear();
        game.getShipsTwoDeck().clear();
        game.getShipsThreeDeck().clear();
        game.getShipsFourDeck().clear();
        game.setPlayersField(new Cell[10][10]);
        //присваение элементам матрицы нашего игрового поля значений
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                game.addCellInField(game.getPlayersField(), new Cell(40 * i, 40 * j, CellStatus.SEA));
            }
        }
    }

    /**
     * Добавление корабля, предварительно проверяя - не пересекается ли новый корабль с уже добавленным кораблем
     * @param ship - добавляемый корабль
     */
    public void addShip(Ship ship) {
        List<Cell> cellsOfShip = ship.getCoordinates();
        for (Cell cellShip : cellsOfShip) {
            if (checkAround(cellShip, cellsOfShip)) {
                cellsOfShip.clear();
                return;
            }
        }
        if (cellsOfShip.size() != 0) {
            game.addShip(ship);
        }
    }

    /**
     * Удаление корабля по заданным координатам
     * @param x - координата x
     * @param y - координата y
     * @return - удаленный корабль
     */
    public Ship removeShip(int x, int y) {
        List<Ship> allShips = game.getAllShips(); //получаем список всех добавленных кораблей
        for (Ship ship : allShips) {
            for (Cell cell : ship.getCoordinates()) {
                if (x == cell.getX() && y == cell.getY()) { //перебираем корабли, затем их ячейки,
                    // если координаты ячейки совпадают с заданными - удаляем корабль
                    game.removeShip(ship);
                    return ship;
                }
            }
        }
        return null;
    }

    /**
     * Проверка на пересечение с другими короблями
     * @param cell - ячейка проверяемого корабля
     * @param cellsOfShip - список ячеек всеъ кораблей
     * @return true - если корабль или его границы пересекаются с другими кораблями
     */
    private boolean checkAround(Cell cell, List<Cell> cellsOfShip) {
        int myX = cell.getX();
        int myY = cell.getY();
        for (int i = myX - 40; i <= myX + 40; i += 40) {
            for (int j = myY - 40; j <= myY + 40; j += 40) {
                Cell cellFromField = game.getCell(game.getPlayersField(), i, j);
                if (cellFromField != null && cellFromField.getStatus().equals(CellStatus.SHIP) && !cellsOfShip.contains(cellFromField)) {
                    View.alert("Сюда нельзя добавлять корабль - пересечение с другим");
                    cellsOfShip.clear();
                    return true;
                }
            }
        }
        return false;
    }
}
