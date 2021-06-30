package com.actions;

import com.battleship.Ship;
import com.views.View;
import com.views.panels.ChoosePanel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Слушатель на нажатие кнопки мыши
 */
public class ActionMouse extends MouseAdapter {

    private View view;
    private ChoosePanel choosePanel;

    public ActionMouse(View view) {
        this.view = view;
        this.choosePanel = view.getChoosePanel();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //получаем координаты и округляем
        int x = ((e.getX() - 20) / 40) * 40 + 20;
        int y = ((e.getY() - 20) / 40) * 40 + 20;
        //получаем кол-во палуб и ориентацию корабля
        int shipType = choosePanel.getShipType();
        int placement = choosePanel.getPlacement();
        Ship ship;
        Ship removedShip;
        //если была нажата ЛКМ то добавляем корабль
        if (e.getButton() == MouseEvent.BUTTON1 && (x >= 20 && x < 420 && y >= 20 && y < 420)) {
            if (shipType > 0 && shipType <= 4) {
                switch (placement) {
                    case 1: {
                        ship = new Ship(shipType);
                        ship.setHorizontal(false);
                        ship.createVerticalShip(x, y);
                        view.addShip(ship);
                        break;
                    }
                    case 2: {
                        ship = new Ship(shipType);
                        ship.setHorizontal(true);
                        ship.createHorizontalShip(x, y);
                        view.addShip(ship);
                        break;
                    }
                    default:
                        View.alert("Не выбрана ориентация размещения!");
                }
            } else {
                View.alert("Не выбрано количество палуб!");
                return;
            }
            //если ПКМ то удаляем орабль
        } else if (e.getButton() == MouseEvent.BUTTON3 && (x >= 20 && x < 420 && y >= 20 && y < 420) &&
                (removedShip = view.removeShip(x, y)) != null) {
            //меняем в имени радиоБаттона кол-во оставшихся кораблей в связи с удалением корабля
            view.changeCountShipOnChoosePanel(removedShip.getType());
        }
        view.getPlayerField().repaint(); //переотрисовываем игровое поле
        //меняем в радиокнопке кол-во оставшихся кораблей
        view.changeCountShipOnChoosePanel(shipType);
    }
}
