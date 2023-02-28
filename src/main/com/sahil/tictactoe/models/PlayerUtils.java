package main.com.sahil.tictactoe.models;

public class PlayerUtils {

    public static Move addMove(Board board, int row, int col, Player player) {
        board = fillBoardCell(board, row, col, player);
        return new Move(player, board.getBoard().get(row).get(col));
    }

    public static Board fillBoardCell(Board board, int row, int col, Player player) {
        board.getBoard().get(row).get(col).setCellState(CellState.FILLED);
        board.getBoard().get(row).get(col).setPlayer(player);
        return board;
    }
}
