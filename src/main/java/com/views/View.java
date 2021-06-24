package com.views;

import javax.swing.*;

/**
 * Основное окно
 */
public class View extends JFrame {

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
        setIconImage(new ImageIcon("../resources/img/icon.png").getImage());
    }

    //Инициализируем UI
    public void init() {
        pack(); //Сжимает компоненты так, чтобы все вместились на минимальном расстоянии и не накладывались друг на друга
        revalidate(); //Считает заново положение компонентов во фрейме
        setVisible(true);
    }
}
