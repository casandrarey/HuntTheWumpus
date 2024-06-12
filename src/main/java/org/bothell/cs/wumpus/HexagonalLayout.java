import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class HexagonalLayout implements LayoutManager {
    private final int numCells;  // Number of hexagonal cells
    private final Map<Component, Rectangle> componentMap = new HashMap<>();

    public HexagonalLayout(int numCells) {
        this.numCells = numCells;
    }

    @Override
    public void addLayoutComponent(String name, Component comp) {
        // Not used
    }

    @Override
    public void removeLayoutComponent(Component comp) {
        componentMap.remove(comp);
    }

    @Override
    public Dimension preferredLayoutSize(Container parent) {
        return calculateSize(parent);
    }

    @Override
    public Dimension minimumLayoutSize(Container parent) {
        return calculateSize(parent);
    }

    @Override
    public void layoutContainer(Container parent) {
        synchronized (parent.getTreeLock()) {
            int n = parent.getComponentCount();
            if (n == 0) return;

            int parentWidth = parent.getWidth();
            int parentHeight = parent.getHeight();

            int columns = (int) Math.ceil(Math.sqrt(numCells));  // Rough estimate
            int rows = (int) Math.ceil((double) numCells / columns);

            // Calculate the size of each hexagon to fit the available space
            int hexHeight = parentHeight / rows;
            int hexWidth = (int) (hexHeight / Math.sqrt(3) * 2);
            int hexSize = Math.min(hexWidth, hexHeight);

            int yOffset = (int) (hexSize * Math.sqrt(3) / 2);

            for (int i = 0; i < n; i++) {
                Component c = parent.getComponent(i);
                int row = i / columns;
                int col = i % columns;

                int x = col * hexSize + (row % 2 == 1 ? hexSize / 2 : 0);
                int y = row * yOffset;

                componentMap.put(c, new Rectangle(x, y, hexSize, yOffset * 2));
                c.setBounds(x, y, hexSize, yOffset * 2);
            }
        }
    }

    private Dimension calculateSize(Container parent) {
        int parentWidth = parent.getWidth();
        int parentHeight = parent.getHeight();

        int columns = (int) Math.ceil(Math.sqrt(numCells));
        int rows = (int) Math.ceil((double) numCells / columns);

        int hexHeight = parentHeight / rows;
        int hexWidth = (int) (hexHeight / Math.sqrt(3) * 2);
        int hexSize = Math.min(hexWidth, hexHeight);

        int width = columns * hexSize + (hexSize / 2);
        int height = rows * (int) (hexSize * Math.sqrt(3) / 2) + (int) (hexSize * Math.sqrt(3) / 2);

        return new Dimension(width, height);
    }
}
