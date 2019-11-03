package model;

import java.awt.event.KeyAdapter;

public class Board
{
    public enum Move
    {
        left,
        right,
        down
    }
    private final int BOARD_SIZE_X = 12;
    private final int BOARD_SIZE_Y = 22;

    private Cell currentRotationAxisPos ;
    private Cell[] currentTetrominoCoords;

    private boolean gameStarted = false;
    private boolean isPaused = false;

    private final Cell STARTING_CELL =  new Cell(BOARD_SIZE_X % 2 == 0 ? BOARD_SIZE_X / 2 : BOARD_SIZE_X / 2 - 1, 0);
    private boolean fallingTable[][] = new boolean[BOARD_SIZE_Y][BOARD_SIZE_X];
    private int resultTable[][] = new int[BOARD_SIZE_Y][BOARD_SIZE_X];

    public Board(){
        currentRotationAxisPos = new Cell(STARTING_CELL.getCoordX(), STARTING_CELL.getCoordY());
    }

    /***
     * Method to move tetromino left, right or down
     * @param move
     * @return
     */
    public boolean moveTetromino(Move move)
    {
        int x = currentRotationAxisPos.getCoordX();
        int y = currentRotationAxisPos.getCoordY();
        boolean stop = false;

        switch(move)
        {
            case left:
                if (!collisionDetected(this.currentRotationAxisPos, currentTetrominoCoords))
                {
                    //if left border reached return
                    currentRotationAxisPos.setCoordX(x - 1);
                }
                break;
            case right:
                if (!collisionDetected(this.currentRotationAxisPos, currentTetrominoCoords))
                {
                    //if right border reached return
                    currentRotationAxisPos.setCoordX(x + 1);
                }
                break;
            case down:
                if (!collisionDetected(this.currentRotationAxisPos, currentTetrominoCoords))
                {
                    currentRotationAxisPos.setCoordY(y + 1);
                }
                else
                {
                    stop = true;
                }
                break;
        }
        return stop;
    }

    private boolean collisionDetected(Cell currentCoord, Cell[] currentTetrominoCoords)
    {
        int nextIndexX = currentCoord.getCoordX() ;
        int nextIndexY = currentCoord.getCoordY() + 1;
        for (Cell cell : currentTetrominoCoords)
        {
//            if (nextIndexX + cell.getCoordX() <= 0 || nextIndexX + cell.getCoordX() >= BOARD_SIZE_X)
//            {
//                //right or left border was reached
//                return false;
//            }
            if (nextIndexY - cell.getCoordY() >= BOARD_SIZE_Y)
            {
                return true;
            }
        }
        return false;
    }

    /***
     * Method to place tetromino at the top of the board
     * @param tetromino
     */
    public void placeTetromino(Tetromino tetromino)
    {
        currentTetrominoCoords = tetromino.getCurrentCells();
        setStartingPosition(tetromino);
    }

    public void print()
    {
        for (int i = 0; i < BOARD_SIZE_Y; i++)
        {
            for (int j = 0; j < BOARD_SIZE_X; j++)
            {
                fallingTable[i][j] = false;
            }
        }

        for(Cell cell : currentTetrominoCoords)
        {
            int tempYpos = currentRotationAxisPos.getCoordY() + cell.getCoordY();
            int tempXPos = currentRotationAxisPos.getCoordX() + cell.getCoordX();
            fallingTable[tempYpos][tempXPos] = true;
        }

        for (int i = 0; i < BOARD_SIZE_Y; i++)
        {
            for (int j = 0; j < BOARD_SIZE_X; j++)
            {
                if(fallingTable[i][j])
                    System.out.print("X ");
                else
                    System.out.print("o ");
            }
            System.out.print('\n');
        }
        System.out.println();
        System.out.println();
    }

    private void setStartingPosition(Tetromino tetromino)
    {
        if (tetromino.getCurrentShape() != Tetromino.Shape.shapeI)
        {
            this.currentRotationAxisPos.setCoordX(STARTING_CELL.getCoordX());
            this.currentRotationAxisPos.setCoordY(STARTING_CELL.getCoordY() + 1);

        }else
        {
            this.currentRotationAxisPos.setCoordY(STARTING_CELL.getCoordY());
            this.currentRotationAxisPos.setCoordX(STARTING_CELL.getCoordX());
        }
    }

    public int getBOARD_SIZE_X()
    {
        return BOARD_SIZE_X;
    }

    public int getBOARD_SIZE_Y()
    {
        return BOARD_SIZE_Y;
    }

    public boolean[][] getFallingTable()
    {
        return fallingTable;
    }

    public void setCurrentRotationAxisPos(Cell currentRotationAxisPos)
    {
        this.currentRotationAxisPos = currentRotationAxisPos;
    }
}
