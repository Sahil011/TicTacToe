package main.com.sahil.tictactoe.controllers;

import com.sun.xml.internal.bind.v2.TODO;
import main.com.sahil.tictactoe.models.Game;
import main.com.sahil.tictactoe.models.GameState;
import main.com.sahil.tictactoe.models.Player;

import java.util.List;

public class GameController {

    public Game createGame(int dimention, List<Player> playerList) {
        try {
            return Game.getBuilder().setPlayers(playerList).setDimention(dimention).build();
        } catch (Exception ex) {
            System.out.println(" exception occued while creating game : " + ex.getMessage());
            return null;
        }
    }

    public void undo(Game game) {
        game.undo();
    }

    public void displayBoard(Game game) {
        game.display();
    }

    public GameState getGameStatus(Game game) {
        return game.getState();
    }

    public void executeNextMove(Game game) {
        game.makeNextMove();
    }

    public Player getWinner(Game game) {
        return game.getWinner();
    }


}
