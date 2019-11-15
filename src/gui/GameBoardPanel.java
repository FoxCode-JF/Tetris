package gui;

import model.Board;
import model.Cell;
import model.ShapeTab;
import model.Tetromino;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameBoardPanel extends JPanel implements ActionListener
{
    private Board board;
    private JPanel[][] fallingBoardView;
    private JPanel[][] resultBoardView;
    Dimension dim = new Dimension();

    public GameBoardPanel(Board board)
    {
        this.board = board;
        this.fallingBoardView = new JPanel[board.getBOARD_SIZE_Y()][board.getBOARD_SIZE_Y()];
        this.resultBoardView = new JPanel[board.getBOARD_SIZE_Y()][board.getBOARD_SIZE_Y()];
        setLayout(new GridLayout(board.getBOARD_SIZE_Y(), board.getBOARD_SIZE_X(), 2, 2));

        setBorder(BorderFactory.createLineBorder(Color.BLACK, 10));
        setPreferredSize(new Dimension(board.getBOARD_SIZE_X() * 30, board.getBOARD_SIZE_Y() * 30));
        setBackground(Color.BLACK);
        dim.setSize(30, 30);

        //draw tetris blocks
        for (int i = 0; i < board.getBOARD_SIZE_Y(); i++)
        {
            for (int j = 0; j < board.getBOARD_SIZE_X(); j++)
            {
                fallingBoardView[i][j] = new JPanel();
                resultBoardView[i][j] = new JPanel();

                fallingBoardView[i][j].setMaximumSize(dim);
                fallingBoardView[i][j].setBackground(Color.BLUE);
                resultBoardView[i][j].setBackground(Color.BLACK);

                add(fallingBoardView[i][j]);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent)
    {

    }

    public void updateBoardView(Board board, Tetromino tetromino)
    {
        Cell[] tetrominoOnBoard = board.getTetromino();

        for (int i = 0; i < board.getBOARD_SIZE_Y(); i++)
        {
            for (int j = 0; j < board.getBOARD_SIZE_X(); j++)
            {
                fallingBoardView[i][j].setBackground(resultBoardView[i][j].getBackground());
            }
        }

        for (Cell tetr : tetrominoOnBoard)
        {
            int x = tetr.getCoordX();
            int y = tetr.getCoordY();
            switch (tetromino.getCurrentShape())
            {
                case shapeI:
                    fallingBoardView[y][x].setBackground(Color.CYAN);
                    break;
                case shapeJ:
                    fallingBoardView[y][x].setBackground(Color.BLUE);
                    break;
                case shapeL:
                    fallingBoardView[y][x].setBackground(Color.ORANGE);
                    break;
                case shapeO:
                    fallingBoardView[y][x].setBackground(Color.YELLOW);
                    break;
                case shapeS:
                    fallingBoardView[y][x].setBackground(Color.GREEN);
                    break;
                case shapeZ:
                    fallingBoardView[y][x].setBackground(Color.RED);
                    break;
                case shapeT:
                    fallingBoardView[y][x].setBackground(new Color(148, 0, 211));
                    break;
            }

        }
    }

    public void updateResultBoard()
    {
        ShapeTab shapeTab = board.getShapeTab();

        for (int i = 0; i < board.getBOARD_SIZE_Y(); i++)
        {
            for (int j = 0; j < board.getBOARD_SIZE_X(); j++)
            {
                if (shapeTab.cellColored[i][j])
                {
                    switch (shapeTab.shape[i][j])
                    {
                        case shapeI:
                            resultBoardView[i][j].setBackground(Color.CYAN);
                            break;
                        case shapeJ:
                            resultBoardView[i][j].setBackground(Color.BLUE);
                            break;
                        case shapeL:
                            resultBoardView[i][j].setBackground(Color.ORANGE);
                            break;
                        case shapeO:
                            resultBoardView[i][j].setBackground(Color.YELLOW);
                            break;
                        case shapeS:
                            resultBoardView[i][j].setBackground(Color.GREEN);
                            break;
                        case shapeZ:
                            resultBoardView[i][j].setBackground(Color.RED);
                            break;
                        case shapeT:
                            resultBoardView[i][j].setBackground(new Color(148, 0, 211));
                            break;
                    }
                } else
                {
                    resultBoardView[i][j].setBackground(Color.BLACK);
                }

            }
        }
    }

    }