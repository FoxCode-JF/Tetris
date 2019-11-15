package com.Jarosław.Filipiuk.projekt.tetris;

import controller.BoardController;
import controller.GameController;
import model.Board;
import model.Tetromino;

import javax.swing.*;
import java.awt.event.*;

public class Tetris
{
    public static void main(String[] args)
    {
       SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    ex.printStackTrace();
                }
                GameController gameController = new GameController();
            }
        });
    }
}
