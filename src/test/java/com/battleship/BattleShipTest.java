package com.battleship;

import org.junit.Assert;
import org.junit.Test;

public class BattleShipTest {

    /* Пока в комментарий, мешает сборке
    @Test
    public void testBattleShip() {
        Assert.assertEquals(true, false);
    }
     */

    // Тест создания пустых игровых полей
    @Test
    public void testAddEmptyFields(){
        Game game = new Game();
        Assert.assertEquals(game.playersField[0][9], CellStatus.EMPTY);
        Assert.assertEquals(game.opponentsField[9][0], CellStatus.EMPTY);
    }
}
