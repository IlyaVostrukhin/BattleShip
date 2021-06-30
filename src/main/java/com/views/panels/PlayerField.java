package com.views.panels;

import com.actions.ActionMouse;
import com.views.View;

import javax.swing.*;
import java.awt.*;

/**
 * Поле игрока
 */

public class PlayerField extends JPanel {

    private View view;

    public PlayerField(View view) {
        this.view = view;
        this.setPreferredSize(new Dimension(440, 440)); //Размер поля
        this.addMouseListener(new ActionMouse(view)); //Добавляем слушатель
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        view.repaintPlayerField(g);
    }
}
