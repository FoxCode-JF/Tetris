package model;

public class Cell
{
    private int coordY;
    private int coordX;

    public Cell(int x, int y)
    {
        coordX = x;
        coordY = y;
    }


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




}
