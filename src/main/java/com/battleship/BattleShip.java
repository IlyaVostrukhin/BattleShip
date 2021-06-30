package com.battleship;

import com.controllers.Controller;
import com.views.View;

public class BattleShip {
    public static void main(String[] args) {
        View view = new View();
        Game game = new Game();
        Controller controller = new Controller(game);
        view.setGame(game);
        view.setController(controller);
        view.init();
    }
}
