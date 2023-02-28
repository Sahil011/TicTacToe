package main.com.sahil.tictactoe.strategies.botPlayingStrategy;

import main.com.sahil.tictactoe.models.Board;
import main.com.sahil.tictactoe.models.Move;
import main.com.sahil.tictactoe.models.Player;

public interface BotplayingStrategy {

    Move decideMove(Board board, Player player);

}
