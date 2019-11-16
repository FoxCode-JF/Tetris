package model;

import java.util.Map;
import java.util.Random;

/**
 * Class representing single element placed on the game board. Instantiated only once, changes its shape.
 */
public class Tetromino
{
    /**
     * Possible shapes of the Tetromino
     */
    public enum Shape
    {
        shapeI,
        shapeO,
        shapeT,
        shapeS,
        shapeZ,
        shapeJ,
        shapeL,
        empty
    }

    /**
     * Randomly generates shape of the Tetromino element and sets its Cells coordinates
     */
    public void generateRandomShape()
    {
        Random r = new Random();
        int randInt = r.nextInt(7);

        setCurrentTetromino(AVAILABLE_SHAPES[randInt]);
    }

    Cell[] getCurrentCells()
    {
        return currentCells;
    }

    /**
     * Returns current shape of the Tetromino
     * @return Current Shape of the Tetromino
     */
    public Shape getCurrentShape()
    {
        return currentShape;
    }

    //fix orientation
    private static final Map<Shape, int[][]> SHAPE_COORD_MAP = Map.ofEntries(
            Map.entry(Shape.shapeI, new int[][]{{-1, 0}, {0, 0}, {1, 0}, {2, 0}}),
            Map.entry(Shape.shapeO, new int[][]{{0, -1}, {0, 0}, {1, -1}, {1, 0}}),
            Map.entry(Shape.shapeT, new int[][]{{-1, 0}, {0, 0}, {0, -1}, {1, 0}}),
            Map.entry(Shape.shapeS, new int[][]{{-1, 0}, {0, 0}, {0, -1}, {1, -1}}),
            Map.entry(Shape.shapeZ, new int[][]{{-1, -1}, {0, -1}, {0, 0}, {1, 0}}),
            Map.entry(Shape.shapeJ, new int[][]{{-1, -1}, {-1, 0}, {0, 0}, {1, 0}}),
            Map.entry(Shape.shapeL, new int[][]{{-1, 0}, {0, 0}, {1, 0}, {1, -1}})
    );
    private static final Shape[] AVAILABLE_SHAPES = Shape.values();
    private Shape currentShape;
    private Cell[] currentCells;

    private void setCurrentTetromino(Shape shape)
    {
        this.currentShape = shape;
        int[][] coords = SHAPE_COORD_MAP.get(shape);
        int cellNum = coords.length;
        currentCells = new Cell[cellNum];
        for (int i = 0; i < cellNum; i++)
        {
            currentCells[i] = new Cell(coords[i][0],coords[i][1]);
        }
    }
}
