package gui;

import model.Board;
import model.Tetromino;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameBoardPanel extends JPanel implements ActionListener
{
    private Board board = new Board();
    private JPanel[][] boardView = new JPanel[board.getBOARD_SIZE_Y()][board.getBOARD_SIZE_Y()];

    Dimension dim = new Dimension();
    public GameBoardPanel()
    {
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
                boardView[i][j] = new JPanel();

                boardView[i][j].setMaximumSize(dim);
                boardView[i][j].setBackground(Color.BLUE);

                add(boardView[i][j]);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent)
    {

    }
    public void updateBoardView(Board board, Tetromino tetromino)
    {
        boolean[][] temp = board.getFallingTable();

        //boardView[0][5].setBackground(Color.GREEN);
        for (int i = 0; i < board.getBOARD_SIZE_Y(); i++)
        {
            for (int j = 0; j < board.getBOARD_SIZE_X(); j++)
            {
                boardView[i][j].setBackground(Color.BLACK);
                if (temp[i][j])
                {
                    switch (tetromino.getCurrentShape())
                    {
                        case shapeI:
                            boardView[i][j].setBackground(Color.CYAN);
                            break;
                        case shapeJ:
                            boardView[i][j].setBackground(Color.BLUE);
                            break;
                        case shapeL:
                            boardView[i][j].setBackground(Color.ORANGE);
                            break;
                        case shapeO:
                            boardView[i][j].setBackground(Color.YELLOW);
                            break;
                        case shapeS:
                            boardView[i][j].setBackground(Color.GREEN);
                            break;
                        case shapeZ:
                            boardView[i][j].setBackground(Color.RED);
                            break;
                        case shapeT:
                            boardView[i][j].setBackground(new Color(148,0,211));

                    }
                }
            }
        }
    }
}