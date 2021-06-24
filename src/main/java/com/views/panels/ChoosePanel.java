package com.views.panels;

import com.views.View;

import javax.swing.*;
import java.awt.*;


/**
 * Панель выбора корабля и ориентации перед стартом игры
 */
public class ChoosePanel extends JPanel {
    private View view;
    private JPanel shipsPanel; //Панель выбора кораблей
    private JRadioButton oneDeck; //Радиокнопка - Однопалубный
    private JRadioButton twoDeck; //Радиокнопка - Двухпалубный
    private JRadioButton threeDeck; //Радиокнопка - Трехпалубный
    private JRadioButton fourDeck; //Радиокнопка - Четырехпалубный
    private ButtonGroup shipsGroup; //Группировка радиокнопок для выбора кораблей

    private JPanel orientationPanel; //Панель выбора ориентации корабля
    private JRadioButton horizontal; //Радиокнопка - По горизонтали
    private JRadioButton vertical; //Радиокнопка - По вертикали
    private ButtonGroup orientationGroup; //Группировка радиокнопок для выбора ориентации кораблей

    private JButton clearShips; //Кнопка очистки поля от кораблей

    public ChoosePanel(View view) {
        this.view = view;
        setLayout(null); //Не будем использовать layout manager
        this.setPreferredSize(new Dimension(255, 400));
        shipsPanel = new JPanel();
        shipsPanel.setLayout(new BoxLayout(shipsPanel, BoxLayout.Y_AXIS)); //Ориентация сверху-вниз
        shipsPanel.setBounds(13, 190, 230, 130);
        shipsPanel.setBorder(BorderFactory.createTitledBorder("Выберите палубность корабля"));
        orientationPanel = new JPanel();
        orientationPanel.setLayout(new BoxLayout(orientationPanel, BoxLayout.Y_AXIS)); //Ориентация сверху-вниз
        orientationPanel.setBounds(13, 330, 230, 80);
        orientationPanel.setBorder(BorderFactory.createTitledBorder("Выберите ориентацию корабля"));
        clearShips = new JButton("Очистить поле");
        clearShips.setBounds(13, 410, 230, 30);
        clearShips.addActionListener(e -> view.initMyField()); //Слушатель на нажатие кнопки 'Очистить поле'
        oneDeck = new JRadioButton();
        setNameOneDeck(4);
        twoDeck = new JRadioButton();
        setNameTwoDeck(3);
        threeDeck = new JRadioButton();
        setNameThreeDeck(2);
        fourDeck = new JRadioButton();
        setNameFourDeck(1);
        shipsPanel.add(oneDeck);
        shipsPanel.add(twoDeck);
        shipsPanel.add(threeDeck);
        shipsPanel.add(fourDeck);
        vertical = new JRadioButton("Вертикальная");
        horizontal = new JRadioButton("Горизонтальная");
        orientationPanel.add(vertical);
        orientationPanel.add(horizontal);
        add(shipsPanel);
        add(orientationPanel);
        add(clearShips);
        shipsGroup = new ButtonGroup();
        shipsGroup.add(oneDeck);
        shipsGroup.add(twoDeck);
        shipsGroup.add(threeDeck);
        shipsGroup.add(fourDeck);
        orientationGroup = new ButtonGroup();
        orientationGroup.add(vertical);
        orientationGroup.add(horizontal);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(new ImageIcon("src/main/resources/img/info.png").getImage(), 2, 2, this);
    }

    public void setNameOneDeck(int count) {
        oneDeck.setText("Однопалубный, осталось - " + count);
    }

    public void setNameTwoDeck(int count) {
        twoDeck.setText("Двухпалубный, осталось - " + count);
    }

    public void setNameThreeDeck(int count) {
        threeDeck.setText("Трехпалубный, осталось - " + count);
    }

    public void setNameFourDeck(int count) {
        fourDeck.setText("Четырехпалубный, осталось - " + count);
    }
}
