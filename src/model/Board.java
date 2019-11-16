package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing the Board on which Tetromino shapes are placed
 */
public class Board
{
    /**
     * Possible moves of the Tetromino
     */
    public enum Move
    {
        left,
        right,
        down,
        rotate
    }

    static int BOARD_SIZE_X = 12;
    static int BOARD_SIZE_Y = 22;
    private final Cell STARTING_CELL =  new Cell(BOARD_SIZE_X % 2 == 0 ? BOARD_SIZE_X / 2 : BOARD_SIZE_X / 2 - 1, 0);

    private Cell currentRotationAxisPos ;
    private Cell[] currentTetrominoCoords;

    private boolean stoppedFalling = false;
    private int scoredRowsNumber = 0;

    private ShapeTab shapeTab = new ShapeTab();

    /**
     * Initializes a Board and sets starting position for the falling Tetromino
     */
    public Board()
    {
        currentRotationAxisPos = new Cell(STARTING_CELL.getCoordX(), STARTING_CELL.getCoordY());
    }

    /**
     * Method to move tetromino left, right, down or rotate it and check if it already stopped falling
     * @param move The Move Tetromino should make
     * @return Flag that indicates if Tetromino stopped falling down and settled on the Board
     */
    public boolean moveTetromino(Move move)
    {
        int x = currentRotationAxisPos.getCoordX();
        int y = currentRotationAxisPos.getCoordY();
        Cell nextIndex = new Cell(x, y);
        stoppedFalling = false;

        switch(move)
        {
            case left:
                nextIndex.setCoordX(x - 1);
                if (!collisionDetected(currentTetrominoCoords, nextIndex))
                {
                    currentRotationAxisPos.setCoordX(x - 1);
                }
                break;
            case right:
                nextIndex.setCoordX(x + 1);
                if (!collisionDetected(currentTetrominoCoords, nextIndex))
                {
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
            case rotate:

                for (Cell cell : currentTetrominoCoords)
                {
                    rotateCell(cell);
                }

                while(collisionDetected(currentTetrominoCoords, currentRotationAxisPos))
                {
                    if(x < BOARD_SIZE_X/2)
                    {
                        x++;
                    } else if (x > BOARD_SIZE_X/2)
                    {
                        x--;
                    }
                    else
                    {
                        y++;
                    }
                    currentRotationAxisPos.setCoordX(x);
                    currentRotationAxisPos.setCoordY(y);
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
                // Right or left border was reached
                return true;
            }
            if (nextIndex.getCoordY() + cell.getCoordY() < 0 || nextIndex.getCoordY() + cell.getCoordY() >= BOARD_SIZE_Y)
            {
                // Top or bottom border was reached
                return true;
            }
            if (shapeTab.cellColored[nextIndex.getCoordY() + cell.getCoordY()][nextIndex.getCoordX() + cell.getCoordX()])
            {
                // Placed tetrominos reached
                return true;
            }
        }
        return false;
    }

    /**
     * Clears the result board
     */
    public void clearBoard()
    {
        shapeTab.clearTab();
    }

    /**
     * Method to place Tetromino at the top of the Board
     * @param tetromino Tetromino to be placed on Board
     */
    public void placeTetromino(Tetromino tetromino)
    {
        stoppedFalling = false;
        currentTetrominoCoords = tetromino.getCurrentCells();
        setStartingPosition(tetromino);
    }

    /**
     * Initialize empty game table with false values
     * write true values in taken cells
     */
    public void fillTable(Tetromino tetromino)
    {
        List<Integer> rowsToErase;
        for(Cell cell : currentTetrominoCoords)
        {
            int tempYPos = currentRotationAxisPos.getCoordY() + cell.getCoordY();
            int tempXPos = currentRotationAxisPos.getCoordX() + cell.getCoordX();

            if(stoppedFalling)
            {
                shapeTab.cellColored[tempYPos][tempXPos] = true;
                shapeTab.shape[tempYPos][tempXPos] = tetromino.getCurrentShape();
            }
        }

        if(stoppedFalling)
        {
            rowsToErase = scoredRows(shapeTab.cellColored);
            if(!rowsToErase.isEmpty())
            {
                updateResultTab(rowsToErase);
            }
        }
    }

    /**
     * Returns number of currently scored rows
     * @return Number of currently scored rows
     */
    public int getScoredRowsNumber(){
        return scoredRowsNumber;
    }

    /**
     * Resets number of currently scored rows to 0
     */
    public void resetScoredRowsNumber(){
        scoredRowsNumber = 0;
    }

    private List<Integer> scoredRows (boolean[][] resultTable)
    {
        int countTrueCells = 0;
        List<Integer> rowsToErase = new ArrayList<>();

        for (int i = BOARD_SIZE_Y - 1; i >= 0; i--)
        {
            for (int j = 0; j < BOARD_SIZE_X; j++)
            {
                if(resultTable[i][j])
                {
                    countTrueCells++;
                    if(countTrueCells == BOARD_SIZE_X)
                    {
                        scoredRowsNumber++;
                        rowsToErase.add(i);
                    }
                }else
                {
                    break;
                }
            }
                countTrueCells = 0;
        }
        return rowsToErase;
    }

    private void updateResultTab(List<Integer> scoredRows)
    {
        ShapeTab tmp = new ShapeTab();

        int k = 0,
            i = BOARD_SIZE_Y - 1,
            j = BOARD_SIZE_Y - 1;

        while (i >= 0 && j >= 0)
        {
            if(k < scoredRows.size() && i == scoredRows.get(k))
            {
                i--;
                k++;
            } else
            {
                tmp.shape[j] = shapeTab.shape[i];
                tmp.cellColored[j] = shapeTab.cellColored[i];
                i--;
                j--;
            }
        }
        shapeTab = tmp;
    }

    private void rotateCell(Cell cell)
    {
            int tmpXCoord = cell.getCoordX();
            int tmpYCoord = cell.getCoordY();

            cell.setCoordX(tmpYCoord);
            cell.setCoordY(tmpXCoord * (-1));
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

    /**
     * Returns board width
     * @return Board width
     */
    public int getBOARD_SIZE_X()
    {
        return BOARD_SIZE_X;
    }

    /**
     * Returns board height
     * @return Board height
     */
    public int getBOARD_SIZE_Y()
    {
        return BOARD_SIZE_Y;
    }

    /**
     * Returns current result board
     * @return Result board with shape information
     */
    public ShapeTab getShapeTab()
    {
        return shapeTab;
    }

    /**
     * Checks game over condition (if the Tetromino can be placed on board)
     * @param tetromino Tetromino to be placed on board
     * @return Flag if game is over
     */
    public boolean isGameOver(Tetromino tetromino)
    {
        if (collisionDetected(currentTetrominoCoords, currentRotationAxisPos))
        {
            if (tetromino.getCurrentShape() != Tetromino.Shape.shapeI && currentRotationAxisPos.getCoordY() == STARTING_CELL.getCoordY())
            {
                return true;
            }else return currentRotationAxisPos.getCoordY() == STARTING_CELL.getCoordY() + 1;
        } else
        {
            return false;
        }
    }

    /**
     * Returns Cells of the current Tetromino place on board
     * @return Array of Cells of the current Tetromino
     */
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
