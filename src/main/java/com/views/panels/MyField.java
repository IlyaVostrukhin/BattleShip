package com.views.panels;

import com.actions.ActionMouse;
import com.views.View;

import javax.swing.*;
import java.awt.*;

/**
 * Поле игрока, размещение кораблей
 */

public class MyField extends JPanel {

    private View view;
    private ChoosePanel choosePanel;

    public MyField(View view) {
        this.view = view;
        this.setPreferredSize(new Dimension(440, 440)); //Размер поля
        this.addMouseListener(new ActionMouse()); //Добавляем слушатель
    }

    public void setChoosePanel(ChoosePanel choosePanel) {
        this.choosePanel = choosePanel;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        view.repaintMyField(g);
    }
}
