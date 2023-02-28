package main.com.sahil.tictactoe.strategies.botPlayingStrategy;

import main.com.sahil.tictactoe.models.*;

public class RandomPlayingStrategy implements BotplayingStrategy {

    @Override
    public Move decideMove(Board board, Player player) {

        for (int i = 0; i < board.getBoard().size(); ++i) {
            for (int j = 0; j < board.getBoard().size(); ++j) {
                if (CellState.EMPTY.equals(board.getBoard().get(i).get(j).getCellState())) {
                    return PlayerUtils.addMove(board, i, j, player);
                }
            }

        }

        return null;
    }
}
