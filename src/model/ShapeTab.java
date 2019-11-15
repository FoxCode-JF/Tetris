package model;

public class ShapeTab
{
    public Tetromino.Shape[][] shape;
    public boolean[][] cellColored;

    public ShapeTab()
    {
        //this.shape = shape;
        //this.cellColored = cellColored;
        shape = new Tetromino.Shape[Board.BOARD_SIZE_Y][Board.BOARD_SIZE_X];

        for (int i = 0; i < Board.BOARD_SIZE_Y; i++)
        {
            for (int j = 0; j < Board.BOARD_SIZE_X; j++)
            {
                this.shape[i][j] = Tetromino.Shape.empty;
            }
        }
        this.cellColored = new boolean[Board.BOARD_SIZE_Y][Board.BOARD_SIZE_X];
    }
}
