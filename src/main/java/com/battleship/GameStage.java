package com.battleship;

public enum GameStage {

    MAIN_MENU("MainMenu"),
    REGISTRATION("Registration"),
    WAITING("Waiting"),
    BEGIN("Begin"),
    BATTLE("Battle"),
    END("End");

    private String stage;

    GameStage(String stage){
        this.stage = stage;
    }

    public String getStage(){ return stage;}

    public String nextStage(GameStage stage){
        switch (stage){
            case MAIN_MENU: return REGISTRATION.getStage();
            case REGISTRATION: return WAITING.getStage();
            case WAITING: return BEGIN.getStage();
            case BEGIN: return BATTLE.getStage();
            case BATTLE: return END.getStage();
            case END: return MAIN_MENU.getStage();
            default: return MAIN_MENU.getStage();
        }
    }
}
