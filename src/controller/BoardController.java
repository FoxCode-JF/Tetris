package controller;

import gui.GameBoardPanel;
import model.Board;
import model.Tetromino;

/**
 * Class representing controller of the board
 */
public class BoardController
{
    private Tetromino tetromino = new Tetromino();
    private Board board;
    private GameBoardPanel gameBoard;

    BoardController(Board board, GameBoardPanel gameBoard)
    {
        this.board = board;
        this.gameBoard = gameBoard;
    }

    void addNewTetromino()
    {
        tetromino.generateRandomShape();
        board.placeTetromino(tetromino);
    }

    void drawBoard()
    {
        board.fillTable(tetromino);
        gameBoard.updateBoardView(tetromino);
    }

    void updateResult(){
        gameBoard.updateResultBoard();
    }

    Tetromino getTetromino()
    {
        return tetromino;
    }

    boolean gameOver()
    {
        return board.isGameOver(tetromino);
    }

}
