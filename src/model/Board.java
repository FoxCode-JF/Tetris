package model;

import java.util.Timer;

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
    private final Cell STARTING_CELL = new Cell(BOARD_SIZE_X % 2 == 0 ? BOARD_SIZE_X / 2 : BOARD_SIZE_X / 2 - 1, 0);
    private Cell currentRotationAxisPos;
    private Cell[] currentTetrominoCoords;

    private boolean gameStarted = false;
    private boolean isPaused = false;

    private boolean temp[][] = new boolean[BOARD_SIZE_X][BOARD_SIZE_Y];
    private int resultTable[][] = new int[BOARD_SIZE_X][BOARD_SIZE_Y];
    Timer timer;

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
                    //addToResult
                    stop = true;

                }
                break;
        }
        return stop;
    }

    private boolean collisionDetected(Cell currentCoord, Cell[] currentTetrominoCoords)
    {
        int nextIndexX = currentCoord.getCoordX() + 1;
        int nextIndexY = currentCoord.getCoordY() + 1;
        for (Cell cell : currentTetrominoCoords)
        {
            if (nextIndexX + cell.getCoordX() == 0 || nextIndexX + cell.getCoordX() == BOARD_SIZE_X)
            {
                //right or left border was reached
                return false;
            }
            if (nextIndexY + cell.getCoordY() == BOARD_SIZE_Y)
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
    public void addTetromino(Tetromino tetromino)
    {
        currentTetrominoCoords = tetromino.getCurrentCells();
        currentRotationAxisPos = STARTING_CELL;
    }

    public void print()
    {
        for (int j = 0; j < BOARD_SIZE_Y; j++)
        {
            for (int i = 0; i < BOARD_SIZE_X; i++)
            {
                temp[i][j] = false;
            }
        }

        for(Cell cell : currentTetrominoCoords){
            temp[currentRotationAxisPos.getCoordX()+cell.getCoordX()][currentRotationAxisPos.getCoordY()+cell.getCoordY()] = true;
        }

        for (int j = 0; j < BOARD_SIZE_Y; j++)
        {
            for (int i = 0; i < BOARD_SIZE_X; i++)
            {
                if(temp[i][j])
                    System.out.print("X");
                else
                    System.out.print("o");
            }
            System.out.print('\n');
        }
        System.out.println();
        System.out.println();
    }

}
