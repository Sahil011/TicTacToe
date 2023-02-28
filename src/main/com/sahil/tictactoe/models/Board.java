package main.com.sahil.tictactoe.models;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private List<List<Cell>> board;

    public Board(int dimention) {
        this.board = new ArrayList<>();
        for (int i = 0; i < dimention; i++) {
            this.board.add(new ArrayList<>());
            for (int j = 0; j < dimention; j++) {
                this.board.get(i).add(new Cell(i, j));
            }
        }
    }

    public List<List<Cell>> getBoard() {
        return board;
    }

    public void setBoard(List<List<Cell>> board) {
        this.board = board;
    }

    public void display() {
        for (int i = 0; i < board.size(); i++) {
            for (int j = 0; j < board.size(); j++) {
                if (board.get(i).get(j).getCellState().equals(CellState.EMPTY)) {
                    System.out.print("| _ |");
                } else if (board.get(i).get(j).getCellState().equals(CellState.FILLED)) {
                    System.out.print("| " + board.get(i).get(j).getPlayer().getSymbol() + " |");
                }
            }
            System.out.println("\n");
        }
    }
}
