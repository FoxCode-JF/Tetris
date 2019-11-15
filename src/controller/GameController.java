package controller;

import gui.GameBoardPanel;
import gui.MainFrame;
import model.Board;
import model.Tetromino;

import java.awt.event.*;

public class GameController extends KeyAdapter implements ActionListener
{
    private static boolean stoppedFalling =  true;
    private boolean drop = false;
    private boolean isPaused = false;
    private Board board = new Board();
    private MainFrame mainFrame;
    private BoardController boardController;
    private TimeController timer;
    private long score;

    public GameController()
    {
        GameBoardPanel gameBoard = createBoard();
        mainFrame = new MainFrame(gameBoard);
        mainFrame.add(gameBoard);
        mainFrame.addKeyListener(this);
    }

    public void startGame(){
        timer = new TimeController(this);
        timer.start();
    }

    private GameBoardPanel createBoard(){
        GameBoardPanel gameBoard = new GameBoardPanel(board);
        boardController = new BoardController(board, gameBoard);
        return gameBoard;
    }

    private void run()
    {
        if (isPaused)
            return;

        score += board.countScore();
        mainFrame.setScoreLabel(String.valueOf(score));

        if (stoppedFalling)
        {
            boardController.updateResult();
            boardController.addNewTetromino();
            if(drop)
            {
                timer.setDefaultDelay();
                drop = false;
            }
            stoppedFalling = false;
        }
        else
        {
            stoppedFalling = board.moveTetromino(Board.Move.down);
        }

        boardController.drawBoard();

        if(boardController.gameOver())
        {
            timer.stop();
            return;
        }
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent)
    {
        run();
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        if(drop)
            return;

        int key = e.getKeyCode();

        if(!isPaused)
        {
            switch (key)
            {
                case KeyEvent.VK_S:
                    startGame();
                    break;
                case KeyEvent.VK_RIGHT:
                    board.moveTetromino(Board.Move.right);
                    break;
                case KeyEvent.VK_LEFT:
                    board.moveTetromino(Board.Move.left); // moves block left
                    break;
                case KeyEvent.VK_DOWN:
                    timer.shortenDelay(15);
                    break;
                case KeyEvent.VK_UP:
                    if(!(boardController.getTetromino().getCurrentShape() == Tetromino.Shape.shapeO))
                    {
                        board.moveTetromino(Board.Move.rotate);
                    }
                    break;
                case KeyEvent.VK_SPACE:
                    drop = true;
                    timer.shortenDelay(15);
                    break;
                case KeyEvent.VK_P:
                    isPaused = true;
                    break;
            }
        }
        else if (key == KeyEvent.VK_P){
            isPaused = false;
        }
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        int key = e.getKeyCode();

        switch (key)
        {
            case KeyEvent.VK_DOWN:
                timer.setDefaultDelay();
                break;
        }
    }
}
