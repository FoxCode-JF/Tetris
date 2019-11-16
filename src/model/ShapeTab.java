package model;

/**
 * Class storing result information - filled cells and shape they belong to
 */
public class ShapeTab
{
    /**
     * Stores information about the tetromino shapes that cells belongs to (Shape.empty if empty cell)
     */
    public Tetromino.Shape[][] shape;

    /**
     * Stores information which cells are filled (false if empty cell)
     */
    public boolean[][] cellColored;

    ShapeTab()
    {
        shape = new Tetromino.Shape[Board.BOARD_SIZE_Y][Board.BOARD_SIZE_X];
        cellColored = new boolean[Board.BOARD_SIZE_Y][Board.BOARD_SIZE_X];
        clearTab();
    }

    void clearTab(){
        for (int i = 0; i < Board.BOARD_SIZE_Y; i++)
        {
            for (int j = 0; j < Board.BOARD_SIZE_X; j++)
            {
                this.shape[i][j] = Tetromino.Shape.empty;
                this.cellColored[i][j] = false;
            }
        }
    }
}
