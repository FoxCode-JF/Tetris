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
        setLayout(new GridBagLayout());

        setMinimumSize(new Dimension(600, 700));
        setSize(600, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    void layoutComponents()
    {

    }
}
