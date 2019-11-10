package gui;

import model.Board;
import model.Cell;
import model.Tetromino;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameBoardPanel extends JPanel implements ActionListener
{
    private Board board;
    private JPanel[][] fallingBoardView;
    //private BoardView[][] resultTable = new BoardView[board.getBOARD_SIZE_Y()][board.getBOARD_SIZE_Y()];
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
        boolean[][] tempFallingTable = board.getFallingTable();
        boolean[][] tempResultTable = board.getResultTable();

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


        //boardView[0][5].setBackground(Color.GREEN);
//        for (int i = 0; i < board.getBOARD_SIZE_Y(); i++)
//        {
//            for (int j = 0; j < board.getBOARD_SIZE_X(); j++)
//            {
//            }
//                resultBoardView[i][j].setBackground(Color.BLACK);
//                //resultTable[i][j].setPanelPlaced(tempResultTable[i][j]);
//
//                if (tempFallingTable[i][j] || tempResultTable[i][j])
//                {
//                    switch (tetromino.getCurrentShape())
//                    {
//                        case shapeI:
//                            resultBoardView[i][j].setBackground(Color.CYAN);
//                            break;
//                        case shapeJ:
//                            resultBoardView[i][j].setBackground(Color.BLUE);
//                            break;
//                        case shapeL:
//                            resultBoardView[i][j].setBackground(Color.ORANGE);
//
//                            break;
//                        case shapeO:
//                            resultBoardView[i][j].setBackground(Color.YELLOW);
//                            break;
//                        case shapeS:
//                            resultBoardView[i][j].setBackground(Color.GREEN);
//                            break;
//                        case shapeZ:
//                            resultBoardView[i][j].setBackground(Color.RED);
//                            break;
//                        case shapeT:
//                            resultBoardView[i][j].setBackground(new Color(148, 0, 211));
//                            break;
//                    }
                    /*switch (tetromino.getCurrentShape())
                    {
                        case shapeI:
                            fallingBoardView[i][j].setBackground(Color.CYAN);
                        case shapeJ:
                            fallingBoardView[i][j].setBackground(Color.BLUE);
                            break;
                        case shapeL:
                            fallingBoardView[i][j].setBackground(Color.ORANGE);
                            break;
                        case shapeO:
                            fallingBoardView[i][j].setBackground(Color.YELLOW);
                            break;
                        case shapeS:
                            fallingBoardView[i][j].setBackground(Color.GREEN);
                            break;
                        case shapeZ:
                            fallingBoardView[i][j].setBackground(Color.RED);
                        case shapeT:
                            fallingBoardView[i][j].setBackground(new Color(148,0,211));
                            //resultBoardView[i][j].setBackground(fallingBoardView[i][j].getBackground());

                    }*/
                //}

            //}
        //}
    }

    public void updateResultBoard(Tetromino tetromino)
    {
        Cell[] tetrominoOnBoard = board.getTetromino();
        for(Cell cell : tetrominoOnBoard)
        {
            int x = cell.getCoordX();
            int y = cell.getCoordY();

                switch (tetromino.getCurrentShape())
                {
                    case shapeI:
                        resultBoardView[y][x].setBackground(Color.CYAN);
                        break;
                    case shapeJ:
                        resultBoardView[y][x].setBackground(Color.BLUE);
                        break;
                    case shapeL:
                        resultBoardView[y][x].setBackground(Color.ORANGE);

                        break;
                    case shapeO:
                        resultBoardView[y][x].setBackground(Color.YELLOW);
                        break;
                    case shapeS:
                        resultBoardView[y][x].setBackground(Color.GREEN);
                        break;
                    case shapeZ:
                        resultBoardView[y][x].setBackground(Color.RED);
                        break;
                    case shapeT:
                        resultBoardView[y][x].setBackground(new Color(148, 0, 211));
                        break;
                }
            }
        }
    }