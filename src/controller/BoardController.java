package controller;

import gui.GameBoardPanel;
import model.Board;
import model.Tetromino;

public class BoardController
{
    private Tetromino tetromino;
    private Board board;
    private GameBoardPanel gameBoard;

    public BoardController(Tetromino tetromino, Board board, GameBoardPanel gameBoard)
    {
        this.tetromino = tetromino;
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
        board.print();
        gameBoard.updateBoardView(board, tetromino);
    }

}
