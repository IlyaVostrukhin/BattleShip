package com.battleship;

import java.util.Arrays;

import static com.battleship.CellStatus.EMPTY;

public class Game {

    CellStatus[][] playersField = new CellStatus[10][10];
    CellStatus[][] opponentsField = new CellStatus[10][10];

    public Game(){
        addNewEmptyField(playersField);
        addNewEmptyField(opponentsField);
    }

    private static void addNewEmptyField(CellStatus[][] field){
        for (CellStatus[] strings : field) {
            Arrays.fill(strings, EMPTY);
        }
    }
}
