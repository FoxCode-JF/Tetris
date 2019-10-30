package gui;

import javax.swing.*;
import java.awt.*;

public final class MainFrame extends JFrame
{
    GameBoardPanel gameBoard;
    JPanel menu;

    public MainFrame()
    {
        super("Tetris by Fox");
        setLayout(new BorderLayout());
        gameBoard = new GameBoardPanel();
        menu = new JPanel();

        add(gameBoard, BorderLayout.EAST);

        setMinimumSize(new Dimension(500, 400));
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    void layoutComponents()
    {

    }
}
