package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameBoardPanel extends JPanel implements ActionListener
{

    public GameBoardPanel()
    {
        setLayout(new GridBagLayout());
        Dimension dim = getPreferredSize();
        dim.width = 400;
        setPreferredSize(dim);
        setBackground(Color.BLUE);
        setBorder(BorderFactory.createBevelBorder(3));
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent)
    {

    }
}
