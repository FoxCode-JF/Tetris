package model;

public class Cell
{

    public Cell(int x, int y)
    {
        coordX = x;
        coordY = y;
    }
    private int coordX;

    public int getCoordX()
    {
        return coordX;
    }

    public void setCoordX(int coordX)
    {
        this.coordX = coordX;
    }

    public int getCoordY()
    {
        return coordY;
    }

    public void setCoordY(int coordY)
    {
        this.coordY = coordY;
    }

    private int coordY;


}
