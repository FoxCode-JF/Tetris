package model;

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

    private boolean stoppedFalling = false;
    private boolean gameStarted = false;
    private boolean isPaused = false;

    private final Cell STARTING_CELL =  new Cell(BOARD_SIZE_X % 2 == 0 ? BOARD_SIZE_X / 2 : BOARD_SIZE_X / 2 - 1, 0);
    private boolean fallingTable[][] = new boolean[BOARD_SIZE_Y][BOARD_SIZE_X];
    private boolean resultTable[][] = new boolean[BOARD_SIZE_Y][BOARD_SIZE_X];

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
        Cell nextIndex = new Cell(x, y);
        stoppedFalling = false;

     /*   int nextIndexX = currentCoord.getCoordX();
        int nextIndexY= currentCoord.getCoordY() + 1;;

        if(moveType == Move.right)
        {
            nextIndexX++;
        } else if (moveType == Move.left)
        {
            nextIndexX--;
        }*/

        switch(move)
        {
            case left:
                nextIndex.setCoordX(x - 1);
                if (!collisionDetected(currentTetrominoCoords, nextIndex))
                {
                    //if left border reached return
                    currentRotationAxisPos.setCoordX(x - 1);
                }
                break;
            case right:
                nextIndex.setCoordX(x + 1);
                if (!collisionDetected(currentTetrominoCoords, nextIndex))
                {
                    //if right border reached return
                    currentRotationAxisPos.setCoordX(x + 1);
                }
                break;
            case down:
                nextIndex.setCoordY(y + 1);
                if (!collisionDetected(currentTetrominoCoords, nextIndex))
                {
                    currentRotationAxisPos.setCoordY(y + 1);
                } else
                {
                    stoppedFalling = true;
                }
                break;
        }
        return stoppedFalling;
    }

    private boolean collisionDetected(Cell[] currentTetrominoCoords, Cell nextIndex)
    {
        for (Cell cell : currentTetrominoCoords)
        {
            if (nextIndex.getCoordX() + cell.getCoordX() < 0 || nextIndex.getCoordX() + cell.getCoordX() >= BOARD_SIZE_X)
            {
                //right or left border was reached
                return true;
            }
            if (nextIndex.getCoordY() + cell.getCoordY() >= BOARD_SIZE_Y)
            {
                return true;
            }
            if (resultTable[nextIndex.getCoordY() + cell.getCoordY()][nextIndex.getCoordX() + cell.getCoordX()])
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
        stoppedFalling = false;
        currentTetrominoCoords = tetromino.getCurrentCells();
        setStartingPosition(tetromino);
    }

    public void fillTable()
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
            if(stoppedFalling)
            {
                resultTable[tempYpos][tempXPos] = true;
            }
        }

        for (int i = 0; i < BOARD_SIZE_Y; i++)
        {
            for (int j = 0; j < BOARD_SIZE_X; j++)
            {
                if(!fallingTable[i][j] && resultTable[i][j])
                    System.out.print("X ");
                else if (fallingTable[i][j])
                {
                    System.out.print("X ");
                } else
                {
                    System.out.print("o ");
                }

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

    public boolean isStoppedFalling()
    {
        return stoppedFalling;
    }

    public boolean[][] getResultTable()
    {
        return resultTable;
    }

    public Cell[] getTetromino()
    {
        if(currentTetrominoCoords == null)
            return new Cell[0];

        Cell[] tetromino = new Cell[currentTetrominoCoords.length];
        for (int i = 0; i < currentTetrominoCoords.length; i++)
        {
            int tempYpos = currentRotationAxisPos.getCoordY() + currentTetrominoCoords[i].getCoordY();
            int tempXPos = currentRotationAxisPos.getCoordX() + currentTetrominoCoords[i].getCoordX();
            tetromino[i] = new Cell(tempXPos, tempYpos);
        }

        return tetromino;
    }
}
