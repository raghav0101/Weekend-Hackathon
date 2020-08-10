package data;

public interface Element {
    public void addNeighbour(Block neighbour);
    public int getActiveNeighbourCount();
    public boolean isActive();
    public void setActive(boolean active);
}
