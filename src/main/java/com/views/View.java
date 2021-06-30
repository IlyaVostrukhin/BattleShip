package com.views;

import com.battleship.Cell;
import com.battleship.Game;
import com.battleship.Ship;
import com.controllers.Controller;
import com.views.panels.ChoosePanel;
import com.views.panels.ControlPanel;
import com.views.panels.PlayerField;

import javax.swing.*;
import java.awt.*;

/**
 * Основное окно
 */
public class View extends JFrame {

    private PlayerField playerField;
    private Controller controller;
    private ChoosePanel choosePanel;
    private ControlPanel controlPanel;
    private Game game;

    public View() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); //Выставить оформление окна как в ОС
        } catch (Exception e) {
            e.printStackTrace();
        }
        setTitle("Морской бой"); //Заголовок
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //При закрытии выходим из приложения
        setLocationRelativeTo(null); //Центрируем окно
        setResizable(false); //Запрет изменения размера окна
        setIconImage(new ImageIcon("src/main/resources/img/icon.png").getImage());
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public void setController(Controller controller) { this.controller = controller; }

    public PlayerField getPlayerField() { return playerField; }

    public ChoosePanel getChoosePanel() {
        return choosePanel;
    }

    /**
     * Инициализия UI
     */
    public void init() {
        controller.loadEmptyMyField();
        add(choosePanel = new ChoosePanel(this), BorderLayout.EAST);
        playerField = new PlayerField(this);
        add(playerField, BorderLayout.WEST);
        add(controlPanel = new ControlPanel(this), BorderLayout.SOUTH);
        pack(); //Сжимает компоненты так, чтобы все вместились на минимальном расстоянии и не накладывались друг на друга
        revalidate(); //Считает заново положение компонентов во фрейме
        setVisible(true);
    }

    /**
     * Инициализация поля игрока и установка имени радиокнопок
     */
    public void initMyField() {
        controller.loadEmptyMyField();
        playerField.repaint();
        choosePanel.setNameOneDeck(4);
        choosePanel.setNameTwoDeck(3);
        choosePanel.setNameThreeDeck(2);
        choosePanel.setNameFourDeck(1);
    }

    /**
     * Перерисовка поля игрока
     * @param g - по умолчанию
     */
    public void repaintPlayerField(Graphics g) {
        Cell[][] matrix = game.getPlayersField(); //получаем матрицу нашего поля
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                Cell cell = matrix[i][j]; //присваиваем ячейке значение элемента матрицы
                if (cell == null) continue;
                //подгружаем картинку на панель нашего игрового поля
                g.drawImage(new ImageIcon("src/main/resources/img/" + cell.getStatus().name() + ".png").getImage(),
                        i * 40 + 20, j * 40 + 20, playerField);
            }
        }
    }

    /**
     * Добавление корабля на поле
     * @param ship - добавляемый корабль
     */
    public void addShip(Ship ship) {
        controller.addShip(ship);
    }

    /**
     * Удаление корабля с поля
     * @param x - координата x
     * @param y - координата y
     * @return - удаленный корабль
     */
    public Ship removeShip(int x, int y) {
        return controller.removeShip(x, y);
    }

    /**
     * Изменение имени радиокнопок при удалении/добавлении кораблей по типу корабля (количеству палуб)
     * @param shipType - тип (количество палуб)
     */
    public void changeCountShipOnChoosePanel(int shipType) {
        switch (shipType) {
            case 1: {
                choosePanel.setNameOneDeck(4 - game.getShipsOneDeck().size());
                break;
            }
            case 2: {
                choosePanel.setNameTwoDeck(3 - game.getShipsTwoDeck().size());
                break;
            }
            case 3: {
                choosePanel.setNameThreeDeck(2 - game.getShipsThreeDeck().size());
                break;
            }
            case 4: {
                choosePanel.setNameFourDeck(1 - game.getShipsFourDeck().size());
                break;
            }
        }
        choosePanel.revalidate();
    }

    /**
     * Вызов информационного окна при ошибках
     * @param message - сообщение
     */
    public static void alert(String message) {
        JOptionPane.showMessageDialog(
                null, message,
                "Внимание!", JOptionPane.ERROR_MESSAGE
        );
    }

}
