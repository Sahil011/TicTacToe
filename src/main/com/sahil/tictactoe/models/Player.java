package main.com.sahil.tictactoe.models;

import java.util.Scanner;

public class Player {

    private Character symbol;
    private String name;
    private PlayerType playerType;

    public Player(String name, char symbol, PlayerType playerType) {
        this.symbol = symbol;
        this.name = name;
        this.playerType = playerType;
    }

    public Move decideMove(Board board) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the row number of Cell You wants to select : ");
        int row = sc.nextInt();
        System.out.println("Enter the column number of Cell You wants to select : ");
        int col = sc.nextInt();
        //validateMove(board);
        return PlayerUtils.addMove(board, row, col, this);
    }


    public Character getSymbol() {
        return symbol;
    }

    public void setSymbol(Character symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }


}
