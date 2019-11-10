package gui;

import model.Board;
import model.Tetromino;

import javax.swing.*;
import java.awt.*;

public class BoardView
{
    public boolean panelPlaced;
    public Tetromino.Shape panelColor;
    //public JPanel panelColor;

    public boolean isPanelPlaced()
    {
        return panelPlaced;
    }

    public void setPanelPlaced(boolean panelPlaced)
    {
        this.panelPlaced = panelPlaced;
    }

    public Tetromino.Shape getPanelColor()
    {
        return panelColor;
    }

    public void setPanelColor(Tetromino.Shape panelColor)
    {
        this.panelColor = panelColor;
    }
}
