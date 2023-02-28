package main.com.sahil.tictactoe.factories;

import main.com.sahil.tictactoe.models.BotLevel;
import main.com.sahil.tictactoe.strategies.botPlayingStrategy.BotplayingStrategy;
import main.com.sahil.tictactoe.strategies.botPlayingStrategy.RandomPlayingStrategy;

public class BotPlayingFactory {

    public static BotplayingStrategy getBotPlyingStrategy(BotLevel botLevel) {
        return new RandomPlayingStrategy();
    }
}
