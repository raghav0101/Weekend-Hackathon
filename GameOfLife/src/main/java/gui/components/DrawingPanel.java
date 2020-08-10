package gui.components;

import data.Block;
import data.BlockArray;
import data.GraphicSection;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

public class DrawingPanel extends JPanel implements MouseMotionListener, MouseListener {


    private BlockArray blocks;
    private ArrayList<GraphicSection> sections;

    private int dimension;
    private int count;

    public DrawingPanel(int d, int count) {
        this.dimension = d;
        this.count = count;

        setPreferredSize(new Dimension(dimension, dimension));
        setBackground(Color.WHITE);

        this.blocks = new BlockArray(count);
        this.sections = new ArrayList<>();
        generateSections();

        addMouseListener(this);
        addMouseMotionListener(this);
    }
    private void generateSections() {
        int cellSize = dimension / count;
        for (int row = 0; row < count; row++) {
            for (int column = 0; column < count; column++) {
                sections.add(new GraphicSection(column * cellSize, row * cellSize, cellSize));
            }
        }
        for (int i = 0; i < sections.size(); i++) {
            GraphicSection s = sections.get(i);
            Block block = blocks.getCells().get(i);
            s.setCell(block);
        }
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawLines(g);
        fillCells(g);
    }
    private void drawLines(Graphics g) {
        int cellSize = dimension / count;
        g.setColor(Color.LIGHT_GRAY);

        for (int row = 0; row < count; row++) {
            int y = row * cellSize;
            g.drawLine(0, y, dimension, y);
        }

        for (int column = 0; column < count; column++) {
            int x = column * cellSize;
            g.drawLine(x, 0, x, dimension);
        }

        g.drawLine(0, dimension - 1, dimension, dimension - 1);
        g.drawLine(dimension - 1, 0, dimension - 1, dimension);
    }
    private void fillCells(Graphics g) {
        g.setColor(Color.BLACK);
        for (GraphicSection s : sections) {
            if (s.getCell().isActive())
                g.fillRect(s.getX() + 1, s.getY() + 1, s.getSize() - 1, s.getSize() - 1);
        }

    }
    public BlockArray getCells()
    {
        return blocks;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        for (GraphicSection s : sections) {
            int mouseX = e.getX();
            int mouseY = e.getY();
            if (mouseX > s.getX() && mouseY > s.getY() && mouseX < s.getX() + s.getSize() && mouseY < s.getY() + s.getSize()) {
                s.getCell().setActive(true);
                repaint();
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e)) {
            switchCellsState(e, true);
        } else if (SwingUtilities.isRightMouseButton(e)) {
            switchCellsState(e, false);
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
    private void switchCellsState(MouseEvent e, boolean newState) {
        for (GraphicSection s : sections) {
            int mouseX = e.getX();
            int mouseY = e.getY();
            if (mouseX > s.getX() && mouseY > s.getY() && mouseX < s.getX() + s.getSize() && mouseY < s.getY() + s.getSize()) {
                s.getCell().setActive(newState);
                repaint();
            }
        }
    }
}
