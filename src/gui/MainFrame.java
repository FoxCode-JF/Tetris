package gui;

import controller.GameController;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public final class MainFrame extends JFrame
{
    private String scoreLabel = "0";
    JLabel score;

    public MainFrame(GameBoardPanel gameBoardPanel)
    {
        super("Tetris by Fox");
        //setLayout(new GridBagLayout());

        layoutComponents(gameBoardPanel);

        setMinimumSize(new Dimension(600, 700));
        setSize(600, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(Color.CYAN);
        setVisible(true);
    }

    protected JLabel makeTextLabel(String msg,
                              GridBagLayout gridbag,
                              GridBagConstraints c) {
        JLabel label = new JLabel(msg);
        gridbag.setConstraints(label, c);
        add(label);
        return label;
    }
    private void layoutComponents(GameBoardPanel gameBoardPanel)
    {
        GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();

        setLayout(gridbag);
        c.fill = GridBagConstraints.CENTER;
        c.weightx = 10;
        c.weighty = 10;

        add(gameBoardPanel);

        //Scoreboard

        setLayout(gridbag);
        c.fill = GridBagConstraints.EAST;
        c.weightx = 1;
        c.weighty = 4;
        score = makeTextLabel("Score: " + scoreLabel, gridbag, c);

    }

    public void setScoreLabel(String scoreLabel)
    {
        this.scoreLabel = scoreLabel;
        score.setText("Score: " + this.scoreLabel);
    }
}
