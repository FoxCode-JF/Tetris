package com.Jaroslaw.Filipiuk.projekt.tetris;

import controller.GameController;

import javax.swing.*;

public class Tetris
{
    public static void main(String[] args)
    {
       SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                GameController gameController = new GameController();
            }
        });
    }
}
