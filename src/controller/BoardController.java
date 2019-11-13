package controller;

import gui.GameBoardPanel;
import model.Board;
import model.Tetromino;

public class BoardController
{
    private Tetromino tetromino = new Tetromino();
    private Board board;
    private GameBoardPanel gameBoard;

    public BoardController(Board board, GameBoardPanel gameBoard)
    {
        this.board = board;
        this.gameBoard = gameBoard;
    }

    public void addNewTetromino()
    {
        tetromino.generateRandomShape();
        board.placeTetromino(tetromino);
    }

    public void drawBoard()
    {
        board.fillTable();
        gameBoard.updateBoardView(board, tetromino);
    }

    public void updateResult(){
        gameBoard.updateResultBoard(tetromino);
    }

    public Tetromino getTetromino()
    {
        return tetromino;
    }
}
