package model;

public class ShapeCell
{
    public Tetromino.Shape[][] shape;
    public boolean[][] cellColored;

    public ShapeCell(Tetromino.Shape[][] shape, boolean[][] cellColored)
    {
        this.shape = shape;
        this.cellColored = cellColored;
    }
}
