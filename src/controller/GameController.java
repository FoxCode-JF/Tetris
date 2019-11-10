package controller;

import gui.GameBoardPanel;
import gui.MainFrame;
import model.Board;
import model.Tetromino;


import javax.swing.Timer;
import java.awt.event.*;

public class GameController extends KeyAdapter implements ActionListener
{

    private int TIME_INTERVAL = 300;

    private Tetromino tetromino = new Tetromino();
    private Board board = new Board();
    MainFrame mainFrame = new MainFrame();
    private GameBoardPanel gameBoard = new GameBoardPanel(board);
    BoardController boardController = new BoardController(tetromino, board, gameBoard);
    private static boolean stoppedFalling =  true;

    public GameController()
    {
        Timer timer = new Timer(TIME_INTERVAL, this);
        timer.setActionCommand("timer");
        timer.start();
        //Timer fastMode = new Timer(TIME_INTERVAL / 5, this);
        //fastMode.start();
        //timer.setActionCommand("fastMode");

        mainFrame.add(gameBoard);
        mainFrame.addKeyListener(this);
    }

    private void run()
    {
        if (stoppedFalling)
        {
            boardController.updateResult();
            boardController.addNewTetromino();
            stoppedFalling = false;
        }
        else
        {
            stoppedFalling = board.moveTetromino(Board.Move.down);
        }
        boardController.drawBoard();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent)
    {
        switch(actionEvent.getActionCommand())
        {
            case "timer":
                run();
                break;
        }
    }

    @Override
    public void keyPressed(KeyEvent e)
    {

        int key = e.getKeyCode();

        switch (key)
        {
            case KeyEvent.VK_RIGHT:
                board.moveTetromino(Board.Move.right);
                break;
            case KeyEvent.VK_LEFT:
                board.moveTetromino(Board.Move.left); // moves block left
                break;
            case KeyEvent.VK_DOWN:
                //rotate block
                break;
            case KeyEvent.VK_P:
                //pause game
        }
    }
}
