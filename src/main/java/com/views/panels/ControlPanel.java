package com.views.panels;

import com.views.View;

import javax.swing.*;
import java.awt.*;

public class ControlPanel extends JPanel {
    private View view;
    private JTextField infoField;
    private JButton startGameButton;
    private JButton exitButton;
    private JButton restartGameButton;

    public ControlPanel(View view) {
        this.view = view;
        setLayout(null); //Не будем использовать layout manager
        setPreferredSize(new Dimension(400, 50));
        infoField = new JTextField();
        infoField.setText("Расстановка кораблей");
        infoField.setEnabled(false);
        infoField.setBounds(10, 15, 180, 20);
        startGameButton = new JButton("Начать игру");
        startGameButton.setBounds(210, 5, 150, 40);
//ToDO:        startGameButton.addActionListener(e -> view.startGame());
        exitButton = new JButton("Прервать и выйти");
        exitButton.setBounds(370, 5, 150, 40);
        exitButton.addActionListener(e -> {
//ToDO:            view.disconnectGame();
            exitButton.setEnabled(false);
            restartGameButton.setEnabled(true);
        });
        exitButton.setEnabled(false);
        restartGameButton = new JButton("Играть еще");
        restartGameButton.setBounds(530, 5, 150, 40);
        restartGameButton.setEnabled(false);
        restartGameButton.addActionListener(e -> view.init());
        add(infoField);
        add(startGameButton);
        add(exitButton);
        add(restartGameButton);
    }
}
