import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class ManagerGrid extends JFrame {
    private final JPanel[][] grid = new JPanel[18][18];
    private final Map<String, Color> symbolColorMap = new HashMap<>();

    public ManagerGrid() {
        setTitle("Manager Grid");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        setLayout(new GridLayout(18, 18));

        // Initialize symbol-to-color mapping
        symbolColorMap.put("■", new Color(100, 180, 100));
        symbolColorMap.put("□", new Color(144, 238, 144));
        symbolColorMap.put("△", new Color(152, 251, 152));
        symbolColorMap.put("●", Color.RED);
        symbolColorMap.put("", Color.YELLOW);

        // Build the grid
        for (int row = 0; row < 18; row++) {
            for (int col = 0; col < 18; col++) {
                JPanel cell = new JPanel();
                cell.setBackground(Color.WHITE);
                // Removed border
                grid[row][col] = cell;
                add(cell);
            }
        }
    }

    // Method to pop up the window
    public void showGrid() {
        setVisible(true);
    }

    // Method to update grid cell color
    public void updateGrid(int x, int y, String symbol) {
        if (x >= 0 && x < 18 && y >= 0 && y < 18) {
            Color color = symbolColorMap.get(symbol);
            if (color != null) {
                grid[y][x].setBackground(color); // row = y, column = x
            } else {
                grid[y][x].setBackground(new Color(100, 180, 100));
            }
        } else {
            System.err.println("Invalid coordinates: (" + x + ", " + y + ")");
        }
    }
}
