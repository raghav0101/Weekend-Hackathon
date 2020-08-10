package data;

import java.util.ArrayList;

public class Block implements Element{
    private boolean active;
    private ArrayList<Block> neighbours;

    public Block() {
        this.active = false;
        this.neighbours = new ArrayList<>();
    }
    @Override
    public void addNeighbour(Block neighbour)
    {
        neighbours.add(neighbour);
    }
    @Override
    public int getActiveNeighbourCount() {
        int activeNeighbours = 0;
        for(Block c : neighbours) {
            if(c.isActive())
                activeNeighbours++;
        }

        return activeNeighbours;
    }
    @Override
    public boolean isActive() { return active; }

    @Override
    public void setActive(boolean active)
    {
        this.active = active;
    }
}