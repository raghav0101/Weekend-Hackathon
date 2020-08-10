package util;

import data.Block;

import java.util.ArrayList;

public class NeighbourGenerator{

    public static void setNeighbours(ArrayList<Block> blocks, int dimension) {
        addLeftNeighbours(blocks, dimension);
        addRightNeighbours(blocks, dimension);
        addTopNeighbours(blocks, dimension);
        addBottomNeighbours(blocks, dimension);
        addTopLeftCornerNeighbours(blocks, dimension);
        addTopRightCornerNeighbours(blocks, dimension);
        addBottomLeftCornerNeighbours(blocks, dimension);
        addBottomRightCornerNeighbours(blocks, dimension);
    }

    private static void addLeftNeighbours(ArrayList<Block> blocks, int dimension) {
        for (int row = 0; row < dimension; row++) {
            for (int column = 1; column < dimension; column++) {
                Block block = blocks.get(row * dimension + column);
                Block leftNeighbour = blocks.get(row * dimension + column - 1);

                block.addNeighbour(leftNeighbour);
            }
        }
    }

    private static void addRightNeighbours(ArrayList<Block> blocks, int dimension) {
        for (int row = 0; row < dimension; row++) {
            for (int column = 0; column < (dimension - 1); column++) {
                Block block = blocks.get(row * dimension + column);
                Block rightNeighbour = blocks.get(row * dimension + column + 1);

                block.addNeighbour(rightNeighbour);
            }
        }
    }

    private static void addTopNeighbours(ArrayList<Block> blocks, int dimension) {
        for (int row = 1; row < dimension; row++) {
            for (int column = 0; column < dimension; column++) {
                Block block = blocks.get(row * dimension + column);
                Block topNeighbour = blocks.get((row - 1) * dimension + column);

                block.addNeighbour(topNeighbour);
            }
        }
    }

    private static void addBottomNeighbours(ArrayList<Block> blocks, int dimension) {
        for (int row = 0; row < (dimension - 1); row++) {
            for (int column = 0; column < dimension; column++) {
                Block block = blocks.get(row * dimension + column);
                Block bottomNeighbour = blocks.get((row + 1) * dimension + column);

                block.addNeighbour(bottomNeighbour);
            }
        }
    }

    private static void addTopLeftCornerNeighbours(ArrayList<Block> blocks, int dimension) {
        for (int row = 1; row < dimension; row++) {
            for (int column = 1; column < dimension; column++) {
                Block block = blocks.get(row * dimension + column);
                Block bottomNeighbour = blocks.get((row - 1) * dimension + (column - 1));

                block.addNeighbour(bottomNeighbour);
            }
        }
    }

    private static void addTopRightCornerNeighbours(ArrayList<Block> blocks, int dimension) {
        for (int row = 1; row < dimension; row++) {
            for (int column = 0; column < (dimension - 1); column++) {
                Block block = blocks.get(row * dimension + column);
                Block bottomNeighbour = blocks.get((row - 1) * dimension + (column + 1));

                block.addNeighbour(bottomNeighbour);
            }
        }
    }

    private static void addBottomLeftCornerNeighbours(ArrayList<Block> blocks, int dimension) {
        for (int row = 0; row < (dimension - 1); row++) {
            for (int column = 1; column < dimension; column++) {
                Block block = blocks.get(row * dimension + column);
                Block bottomNeighbour = blocks.get((row + 1) * dimension + (column - 1));

                block.addNeighbour(bottomNeighbour);
            }
        }
    }

    private static void addBottomRightCornerNeighbours(ArrayList<Block> blocks, int dimension) {
        for (int row = 0; row < (dimension - 1); row++) {
            for (int column = 0; column < (dimension - 1); column++) {
                Block block = blocks.get(row * dimension + column);
                Block bottomNeighbour = blocks.get((row + 1) * dimension + (column + 1));

                block.addNeighbour(bottomNeighbour);
            }
        }
    }

}
