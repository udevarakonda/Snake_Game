// MY FINAL VERSION

import javax.swing.*;
import java.awt.event.ActionEvent;

public class Manager {

    private final Board board = new Board();
    private ManagerGrid grid;

    private String lastKeyPressed = "";

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Manager manager = new Manager();
            manager.start();
        });
    }

    // Start the application (instance method)
    private void start() {
        grid = new ManagerGrid();
        updateGridFromBoard();
        addArrowKeyBindings();
        grid.showGrid();
    }

    private void updateGridFromBoard() {
        for (int i = 0; i < 18; i++) {
            for (int j = 0; j < 18; j++) {
                String symbol = board.getBoard()[i][j];
                grid.updateGrid(i, j, symbol);
            }
        }
    }

    private void addArrowKeyBindings() {
        JComponent pane = (JComponent) grid.getContentPane();

        bindKey(pane, "UP", () -> {
            //System.out.println("UP");
            if (!lastKeyPressed.equals("DOWN")) {
                if (board.new_board_frame("UP")) {
                updateGridFromBoard();
                lastKeyPressed = "UP";
                }
            }

        });

        bindKey(pane, "DOWN", () -> {
            //System.out.println("DOWN");

            if (!lastKeyPressed.equals("UP")) {
                if (board.new_board_frame("DOWN")) {
                updateGridFromBoard();
                lastKeyPressed = "DOWN";
                }
            }
        });

        bindKey(pane, "LEFT", () -> {
            //System.out.println("LEFT");

            if (!lastKeyPressed.equals("RIGHT")) {
               if (board.new_board_frame("LEFT")) {
                updateGridFromBoard();
                lastKeyPressed = "LEFT";
                } 
            }

        });

        bindKey(pane, "RIGHT", () -> {
            //System.out.println("RIGHT");

            if (!lastKeyPressed.equals("LEFT")) {
                if (board.new_board_frame("RIGHT")) {
                updateGridFromBoard();
                lastKeyPressed = "RIGHT";
                }
            }
        });
    }

    private void bindKey(JComponent component, String keyName, Runnable action) {
        String actionName = "key_" + keyName;
        component.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                 .put(KeyStroke.getKeyStroke("pressed " + keyName), actionName);
        component.getActionMap().put(actionName, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                action.run();
            }
        });
    }
}
