package controller;

import model.Board;
import model.Tetromino;

public class BoardController
{
    private Tetromino tetromino;
    private Board board;

    public BoardController(Tetromino tetromino, Board board)
    {
        tetromino.generateRandomShape();
        this.tetromino = tetromino;
        this.board = board;
    }

    public void drawBoard()
    {
        board.addTetromino(tetromino);
        board.print();
    }

}
