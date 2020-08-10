package data;

public class GraphicSection {
    private Block block;
    private int x;
    private int y;
    private int size;

    public GraphicSection(int x, int y, int size)
    {
        this.x = x;
        this.y = y;
        this.size = size;
    }

    public void setCell(Block block)
    {
        this.block = block;
    }

    public Block getCell()
    {
        return block;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public int getSize()
    {
        return size;
    }
}
