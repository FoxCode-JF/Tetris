package controller;

import gui.GameBoardPanel;
import gui.MainFrame;
import model.Board;
import model.ScoringModel;
import model.Tetromino;

import java.awt.event.*;

/**
 * Class representing the main controller of the game
 */
public class GameController extends KeyAdapter implements ActionListener
{
    private static boolean stoppedFalling =  true;
    private boolean drop = false;
    private boolean isPaused = false;
    private boolean isStarted = false;
    private Board board = new Board();
    private MainFrame mainFrame;
    private BoardController boardController;
    private TimeController timer;
    private  ScoringModel scoringModel = new ScoringModel();
    private long score;

    /**
     * Initializes game controller by creating a game board and setting the main window
     */
    public GameController()
    {
        GameBoardPanel gameBoard = createBoard();
        timer = new TimeController(this);
        mainFrame = new MainFrame(gameBoard);
        mainFrame.addKeyListener(new KeyListener());
        mainFrame.addStartListener(new StartListener());
        mainFrame.requestFocus();
    }

    private void startGame(){
        isStarted = true;
        board.clearBoard();
        score = 0;
        stoppedFalling = true;
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

        score += scoringModel.countScore(board.getScoredRowsNumber());
        board.resetScoredRowsNumber();
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
            mainFrame.gameOver();
        }
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent)
    {
        run();
    }

    class StartListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            mainFrame.disableStartButton();
            mainFrame.gameOverPanel.setVisible(false);
            mainFrame.requestFocus();
            startGame();
        }
    }

    class KeyListener extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e)
        {
            if(drop || !isStarted)
                return;

            int key = e.getKeyCode();

            if(!isPaused)
            {
                switch (key)
                {
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
                        timer.shortenDelay(18);
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
            if(!isStarted)
                return;

            int key = e.getKeyCode();

            if (key == KeyEvent.VK_DOWN) {
                timer.setDefaultDelay();
            }
        }
    }
}
