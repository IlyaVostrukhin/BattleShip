package com.views;

import com.battleship.CellStatus;
import com.battleship.Game;
import com.views.panels.ChoosePanel;
import com.views.panels.ControlPanel;
import com.views.panels.MyField;

import javax.swing.*;
import java.awt.*;

/**
 * Основное окно
 */
public class View extends JFrame {

    private MyField myField;
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

    //Инициализируем UI
    public void init() {
        add(choosePanel = new ChoosePanel(this), BorderLayout.EAST);
        add(myField = new MyField(this), BorderLayout.WEST);
        add(controlPanel = new ControlPanel(this), BorderLayout.SOUTH);
        myField.setChoosePanel(choosePanel);
        pack(); //Сжимает компоненты так, чтобы все вместились на минимальном расстоянии и не накладывались друг на друга
        revalidate(); //Считает заново положение компонентов во фрейме
        setVisible(true);
    }

    public void initMyField() {

        myField.repaint(); //переотрисовка нашего игрового поля
        //установка имени радиокнопок на панели выбора корабля
        choosePanel.setNameOneDeck(4);
        choosePanel.setNameTwoDeck(3);
        choosePanel.setNameThreeDeck(2);
        choosePanel.setNameFourDeck(1);
    }

    public void repaintMyField(Graphics g) {
        CellStatus[][] matrix = game.getPlayersField(); //получаем матрицу нашего поля
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                CellStatus cell = matrix[i][j]; //присваиваем боксу значение элемента матрицы
                if (cell == null) continue;
                //подгружаем картинку на панель нашего игрового поля
                g.drawImage(new ImageIcon("src/main/resources/img/sea.png").getImage(),
                        i * 40 + 20, j * 40 + 20, myField);
            }
        }
    }

}
