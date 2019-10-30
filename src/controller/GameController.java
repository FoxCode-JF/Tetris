package controller;

import gui.MainFrame;
import model.Board;
import model.Tetromino;


import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameController implements ActionListener
{

    private int TIME_INTERVAL = 300;
    boolean stop =  false;
    Tetromino tetromino = new Tetromino();
    Board board = new Board();
    BoardController boardController = new BoardController(tetromino,board);
    MainFrame mainFrame = new MainFrame();

    public GameController()
    {
        Timer timer = new Timer(TIME_INTERVAL, this);
        timer.setActionCommand("timer");
        timer.start();
    }

    private void run()
    {
        while (!stop)
        {
            System.out.println("I'm here !!");
            boardController.drawBoard();
            stop = board.moveTetromino(Board.Move.down);
        }
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
