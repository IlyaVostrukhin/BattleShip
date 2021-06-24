package com.views;

import com.actions.ActionMouse;

import javax.swing.*;
import java.awt.*;

/**
 * Поле игрока, размещение кораблей
 */

public class MyField extends JPanel {

    private View view;

    public MyField(View view) {
        this.view = view;
        this.setPreferredSize(new Dimension(400, 400)); //Размер поля
        this.addMouseListener(new ActionMouse()); //Добавляем слушатель
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
