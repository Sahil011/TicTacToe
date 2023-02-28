package main.com.sahil.tictactoe.models;

import main.com.sahil.tictactoe.factories.BotPlayingFactory;
import main.com.sahil.tictactoe.strategies.botPlayingStrategy.BotplayingStrategy;

public class Bot extends Player {

    private BotLevel botLevel;
    private BotplayingStrategy botplayingStrategy;

    public Bot(String name, char symbol, BotLevel botLevel) {
        super(name, symbol, PlayerType.BOT);
        this.botplayingStrategy = BotPlayingFactory.getBotPlyingStrategy(botLevel);
        this.botLevel = botLevel;
    }

    public BotLevel getBotLevel() {
        return botLevel;
    }

    public void setBotLevel(BotLevel botLevel) {
        this.botLevel = botLevel;
    }

    @Override
    public Move decideMove(Board board) {
        return botplayingStrategy.decideMove(board, this);
    }
}
