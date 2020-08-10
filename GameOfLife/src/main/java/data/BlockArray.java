package data;

import util.NeighbourGenerator;

import java.util.ArrayList;

public class BlockArray {

    private ArrayList<Block> blocks;
    private int dimension;

    public BlockArray(int dimension) {
        this.blocks = new ArrayList<>();
        this.dimension = dimension;

        initializeCells();
        resetCells();
    }

    private void initializeCells() {
        for (int i = 0; i < dimension * dimension; i++)
            blocks.add(new Block());

        setNeighbours();
    }

    private void setNeighbours()
    {
        NeighbourGenerator.setNeighbours(blocks, dimension);
    }

    public void resetCells() {
        for (Block c : blocks)
            c.setActive(false);
    }
    public ArrayList<Block> getCells()
    {
        return blocks;
    }

    public void nextGeneration() {
        ArrayList<Block> cellsToUpdate = new ArrayList<>();
        for (Block c : blocks) {
            if (c.getActiveNeighbourCount() == 3)
                cellsToUpdate.add(c);
        }

        checkIfCellsShouldDie();

        for (Block c : cellsToUpdate)
            c.setActive(true);
    }

    public void checkIfCellsShouldDie() {
        ArrayList<Block> cellsToUpdate = new ArrayList<>();

        for (Block c : blocks) {
            if (c.getActiveNeighbourCount() >= 4 || c.getActiveNeighbourCount() <= 1)
                cellsToUpdate.add(c);
        }

        for (Block c : cellsToUpdate)
            c.setActive(false);
    }


}
