package model;

/**
 * Class representing single cell on the Board
 */
public class Cell
{
    private int coordY;
    private int coordX;

    /**
     * Initialize a Cell object wit given coordinates
     * @param x Horizontal coordinate
     * @param y Vertical coordinate
     */
    Cell(int x, int y)
    {
        coordX = x;
        coordY = y;
    }

    /**
     * Returns horizontal coordinate of the Cell
     * @return Horizontal coordinate
     */
    public int getCoordX()
    {
        return coordX;
    }

    void setCoordX(int coordX)
    {
        this.coordX = coordX;
    }

    /**
     * Returns vertical coordinate of the Cell
     * @return Vertical coordinate
     */
    public int getCoordY()
    {
        return coordY;
    }

    void setCoordY(int coordY)
    {
        this.coordY = coordY;
    }
}
