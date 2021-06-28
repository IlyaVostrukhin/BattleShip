package com.battleship;

public enum GameStage {

    MAIN_MENU("MainMenu"),
    REGISTRATION("Registration"),
    WAITING("Waiting"),
    BATTLE("Battle"),
    END("End");

    private String stage;

    GameStage(String stage){
        this.stage = stage;
    }

    public String getStage(){ return stage;}
}
