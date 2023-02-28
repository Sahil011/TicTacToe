package main.com.sahil.tictactoe.strategies.gamewinningStrategy;

import main.com.sahil.tictactoe.models.Board;
import main.com.sahil.tictactoe.models.Cell;
import main.com.sahil.tictactoe.models.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrderOneWinningStrategy implements GameWinningStrategy {

    private List<HashMap<Character, Integer>> rowSymbolCount = new ArrayList<>();
    private List<HashMap<Character, Integer>> colSymbolCount = new ArrayList<>();
    private HashMap<Character, Integer> topLeftDiagonalSymbolCount = new HashMap<>();
    private HashMap<Character, Integer> topRightDiagonalSymbolCount = new HashMap<>();

    public OrderOneWinningStrategy(int dimention) {
        for (int i = 0; i < dimention; i++) {
            rowSymbolCount.add(new HashMap<>());
            colSymbolCount.add(new HashMap<>());
        }
    }


    public boolean checkWinner(Board board, Player lastMovedPlayer, Cell cell) {
        Character symbol = cell.getPlayer().getSymbol();
        int row = cell.getRow();
        int col = cell.getColumn();
        int dimention = board.getBoard().size();

        addRow(row, symbol, rowSymbolCount);
        addColumn(col, symbol, colSymbolCount);
        addLeftDiagonal(row, col, symbol, topLeftDiagonalSymbolCount);
        addRightDiagonal(row, col, symbol, dimention, topRightDiagonalSymbolCount);

        if (completeRowWin(row, dimention, symbol, rowSymbolCount) || completeColumnWin(col, dimention, symbol, colSymbolCount))
            return true;
        if (isLeftDiagonal(row, col) && leftDiagonalWin(symbol, dimention, topLeftDiagonalSymbolCount)) return true;
        if (isRightDiagonal(row, col, dimention) && rightDiagonalWin(symbol, dimention, topRightDiagonalSymbolCount))
            return true;

        return false;
    }

    private boolean rightDiagonalWin(Character symbol, int dimention, HashMap<Character, Integer> topRightDiagonalSymbolCount) {
        return topRightDiagonalSymbolCount.get(symbol) == dimention;
    }

    private boolean leftDiagonalWin(Character symbol, int dimention, HashMap<Character, Integer> topLeftDiagonalSymbolCount) {
        return topLeftDiagonalSymbolCount.get(symbol) == dimention;
    }

    private boolean completeColumnWin(int col, int dimention, Character symbol, List<HashMap<Character, Integer>> colSymbolCount) {
        return colSymbolCount.get(col).get(symbol) == dimention;
    }

    private boolean completeRowWin(int row, int dimention, Character symbol, List<HashMap<Character, Integer>> rowSymbolCount) {
        return rowSymbolCount.get(row).get(symbol) == dimention;
    }

    private void addRightDiagonal(int row, int col, Character symbol, int dimention, HashMap<Character, Integer> topRightDiagonalSymbolCount) {
        if (isRightDiagonal(row, col, dimention))
            topRightDiagonalSymbolCount.put(symbol, topRightDiagonalSymbolCount.getOrDefault(symbol, 0) + 1);
    }

    private void addLeftDiagonal(int row, int col, Character symbol, HashMap<Character, Integer> topLeftDiagonalSymbolCount) {
        if (isLeftDiagonal(row, col))
            topLeftDiagonalSymbolCount.put(symbol, topLeftDiagonalSymbolCount.getOrDefault(symbol, 0) + 1);
    }

    private void addColumn(int col, Character symbol, List<HashMap<Character, Integer>> colSymbolCount) {
        colSymbolCount.get(col).put(symbol, colSymbolCount.get(col).getOrDefault(symbol, 0) + 1);
    }

    private void addRow(int row, Character symbol, List<HashMap<Character, Integer>> rowSymbolCount) {
        rowSymbolCount.get(row).put(symbol, rowSymbolCount.get(row).getOrDefault(symbol, 0) + 1);
    }


    private boolean isLeftDiagonal(int row, int col) {
        return row == col;
    }

    private boolean isRightDiagonal(int row, int col, int dimention) {
        return row + col == (dimention - 1);
    }


}
