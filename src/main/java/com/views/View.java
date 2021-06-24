package com.views;

import com.views.panels.ChoosePanel;
import com.views.panels.MyField;

import javax.swing.*;
import java.awt.*;

/**
 * Основное окно
 */
public class View extends JFrame {

    private MyField myField;
    private ChoosePanel choosePanel;

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

    //Инициализируем UI
    public void init() {
        add(choosePanel = new ChoosePanel(this), BorderLayout.EAST);
        add(myField = new MyField(this), BorderLayout.WEST);
        myField.setChoosePanel(choosePanel);
        pack(); //Сжимает компоненты так, чтобы все вместились на минимальном расстоянии и не накладывались друг на друга
        revalidate(); //Считает заново положение компонентов во фрейме
        setVisible(true);
    }

    public void initMyField() {
//        controller.loadEmptyMyField();
        myField.repaint(); //переотрисовка нашего игрового поля
        //установка имени радиокнопок на панели выбора корабля
        choosePanel.setNameOneDeck(4);
        choosePanel.setNameTwoDeck(3);
        choosePanel.setNameThreeDeck(2);
        choosePanel.setNameFourDeck(1);
    }
}
