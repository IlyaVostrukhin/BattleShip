package com.battleship;

import com.views.View;

public class BattleShip {
    public static void main(String[] args) {
        View view = new View();
        Game game = new Game();
        view.setGame(game);
        view.init();
    }
}
