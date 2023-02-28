package main.com.sahil.tictactoe.models;

import main.com.sahil.tictactoe.exceptions.InvalidGameConstructorException;
import main.com.sahil.tictactoe.strategies.gamewinningStrategy.GameWinningStrategy;
import main.com.sahil.tictactoe.strategies.gamewinningStrategy.OrderOneWinningStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;


public class Game {
    private Board board;
    private List<Move> moves;
    private List<Board> boardLog;
    private List<Player> players;
    private GameState state;
    private int nextplayer;
    private GameWinningStrategy gameWinningStrategy;
    private Player winner;

    public static GameBuilder getBuilder() {
        return new GameBuilder();
    }

    public void makeNextMove() {
        Player player = players.get(nextplayer);
        System.out.println("It is " + players.get(nextplayer).getName() + "'s turn.");
        Move move = player.decideMove(this.board);
        int row = move.getCell().getRow();
        int col = move.getCell().getColumn();
        System.out.println("Move happened at: " +
                row + ", " + col + ".");
        this.moves.add(move);
        if (gameWinningStrategy.checkWinner(board, player, move.getCell())) {
            this.state = GameState.ENDED;
            this.winner = player;
        }
        if (isBoardFull(board)) this.state = GameState.ENDED;
        logBoardState(board);
        nextplayer += 1;
        nextplayer %= players.size();
    }

    private Board logBoardState(Board board) {
        int boardSize = board.getBoard().size();
        Board boardstate = new Board(board.getBoard().size());
        for (int i = 0; i < boardSize; i++) {
            boardstate.getBoard().add(new ArrayList<>());
            for (int j = 0; j < boardSize; j++) {
                Cell cell = new Cell(i, j);
                cell.setCellState(board.getBoard().get(i).get(j).getCellState());
                cell.setPlayer(board.getBoard().get(i).get(j).getPlayer());
                boardstate.getBoard().get(i).add(cell);
            }
        }
        this.boardLog.add(boardstate);
        return boardstate;
    }

    private boolean isBoardFull(Board board) {
        for (int i = 0; i < board.getBoard().size(); i++) {
            for (int j = 0; j < board.getBoard().size(); j++) {
                if (board.getBoard().get(i).get(j).getCellState().equals(CellState.EMPTY)) return false;
            }
        }
        return true;
    }

    public GameWinningStrategy getGameWinningStrategy() {
        return gameWinningStrategy;
    }

    public void setGameWinningStrategy(GameWinningStrategy gameWinningStrategy) {
        this.gameWinningStrategy = gameWinningStrategy;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public GameState getState() {
        return state;
    }

    public void setState(GameState state) {
        this.state = state;
    }

    public int getNextplayer() {
        return nextplayer;
    }

    public void setNextplayer(int nextplayer) {
        this.nextplayer = nextplayer;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public void display() {
        this.board.display();
    }

    public void undo() {
        if (this.boardLog.size() > 1) {
            this.boardLog.remove(this.boardLog.size() - 1);
            nextplayer--;
        } else {
            System.out.println("There is no move to undo");
            return;
        }
        this.board = this.boardLog.get(this.boardLog.size() - 1);
    }

    public List<Board> getBoardLog() {
        return boardLog;
    }

    public void setBoardLog(List<Board> boardList) {
        this.boardLog = boardList;
    }

    public static class GameBuilder {
        private int dimention;
        private List<Player> players;
        private List<Character> playersSymbols = new ArrayList<>();

        public GameBuilder setDimention(int dimention) {
            this.dimention = dimention;
            return this;
        }

        public GameBuilder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        private boolean valid() throws InvalidGameConstructorException {
            if (this.dimention < 3) {
                throw new InvalidGameConstructorException("dimention can't be less than 3 .");
            }
            if (this.players.size() + 1 != this.dimention) {
                throw new InvalidGameConstructorException("Number of player should be Dimention-1 ");
            }

            AtomicBoolean containsSameSymbol = new AtomicBoolean(false);
            players.forEach(player -> {
                if (!playersSymbols.contains(player.getSymbol())) playersSymbols.add(player.getSymbol());
                else containsSameSymbol.set(true);
            });
            if (containsSameSymbol.get())
                throw new InvalidGameConstructorException("Same Symbol can't be assigned to Different Player");

            AtomicInteger botCount = new AtomicInteger(0);
            players.forEach(player -> {
                if (PlayerType.BOT.equals(player.getPlayerType())) botCount.getAndAdd(1);
            });
            if (botCount.get() > 1) throw new InvalidGameConstructorException("Number of Bot should be more than 1");
            return true;
        }

        public Game build() throws InvalidGameConstructorException {
            try {
                valid();
            } catch (Exception ex) {
                throw new InvalidGameConstructorException(ex.getMessage());
            }
            Game game = new Game();
            game.setState(GameState.IN_PROGRESS);
            game.setPlayers(players);
            game.setBoard(new Board(dimention));
            game.setMoves(new ArrayList<>());
            game.setNextplayer(0);
            game.setGameWinningStrategy(new OrderOneWinningStrategy(dimention));
            List<Board> boardState = new ArrayList<>();
            boardState.add(new Board(dimention));
            game.setBoardLog(boardState);
            return game;
        }
    }


}
