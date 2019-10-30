package com.Jarosław.Filipiuk.projekt.tetris;

import controller.BoardController;
import controller.GameController;
import model.Board;
import model.Tetromino;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

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
