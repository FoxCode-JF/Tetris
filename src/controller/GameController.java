package controller;

import gui.GameBoardPanel;
import gui.MainFrame;
import model.Board;
import model.Tetromino;


import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameController implements ActionListener
{

    private int TIME_INTERVAL = 500;
    Board.Move move;

    private Tetromino tetromino = new Tetromino();
    private Board board = new Board();
    MainFrame mainFrame = new MainFrame();
    private GameBoardPanel gameBoard = new GameBoardPanel();
    BoardController boardController = new BoardController(tetromino, board, gameBoard);

    private static boolean stoppedFalling =  true;

    public GameController()
    {
        Timer timer = new Timer(TIME_INTERVAL, this);
        timer.setActionCommand("timer");
        timer.start();
        mainFrame.add(gameBoard);

        gameBoard.addKeyListener(new KeyListener()
        {
            @Override
            public void keyTyped(KeyEvent keyEvent)
            {

            }

            @Override
            public void keyPressed(KeyEvent keyEvent)
            {
                int keyCode = keyEvent.getKeyCode();
                if (!stoppedFalling)
                {
                    switch(keyCode)
                    {
                        case KeyEvent.VK_D:
                            board.moveTetromino(Board.Move.right);
                            System.out.println("right");
                            break;
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent keyEvent)
            {

            }
        });
    }

    private void run()
    {
        if (stoppedFalling)
        {
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
}
