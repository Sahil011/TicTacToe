package main.com.sahil.tictactoe.strategies.gamewinningStrategy;

import main.com.sahil.tictactoe.models.Board;
import main.com.sahil.tictactoe.models.Cell;
import main.com.sahil.tictactoe.models.Player;

public interface GameWinningStrategy {
    public boolean checkWinner(Board board, Player lastMovedPlayer, Cell cell);

}
