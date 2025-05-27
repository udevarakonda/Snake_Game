// MY FINAL VERSION

import java.util.ArrayList;

public class Board {
    private String[][] board = new String[18][18];

    Snake snake;
    Food food;


    public Board() {
        for (int i = 0; i < 18; i++) {
            board[0][i] = Integer.toString(i);
            board[17][i] = Integer.toString(i);
            board[i][0] = Integer.toString(i);
            board[i][17] = Integer.toString(i);
        }

        for (int i = 0; i < 18; i++) {
            for (int j = 0; j < 18; j++) {
                if (board[i][j] == null) {
                    if ((i % 2 != 0 && j % 2 != 0) || (i % 2 == 0 && j % 2 == 0)) {
                        board[i][j] = "□";
                    }
                    else {
                        board[i][j] = "△";
                    }
                }
            }
        }

        snake = new Snake();
        food = new Food(13, 8); 

        update_board();
    }

    public String[][] getBoard() {
        return board;
    }
    public boolean new_board_frame(String direction) {
        if (snake.is_dead()) {
            return false;
        }

        if (is_snake_touching_food()) {
            //System.out.println("You are touching the food");
            snake.move_snake(direction);
            snake.add_to_body();
            regenerate_food();
        }
        else {
            snake.move_snake(direction);
        }

        update_board();

        return true;
    }


    private void update_board() {

        for (int i = 1; i < 17; i++) {
            for (int j = 1; j < 17; j++) {
                if (!board[i][j].equals("■")) {
                    if ((i % 2 != 0 && j % 2 != 0) || (i % 2 == 0 && j % 2 == 0)) {
                        board[i][j] = "□";
                    }
                    else {
                        board[i][j] = "△";
                    }
                }
            }
        }

        board[food.getLocation()[0]][food.getLocation()[1]] = "";

        for (int i = 0; i < snake.getSnakeCoordinateList().size(); i++) {
            //System.out.println(i);
            //System.out.println("Current Coordinate: " + snake.getSnakeCoordinateList().get(i)[0] + " " + snake.getSnakeCoordinateList().get(i)[1]);
            board[snake.getSnakeCoordinateList().get(i)[0]][snake.getSnakeCoordinateList().get(i)[1]] = "●";
           // printGridWithBorders(board);
        } 


    }

    private boolean is_snake_touching_food() {
        ArrayList<int[]> snake_list = snake.getSnakeCoordinateList();
        int[] head = snake_list.get(snake_list.size()-1);

        return (head[0] == food.getLocation()[0] && head[1] == food.getLocation()[1]);
    }

    public void regenerate_food() {
        int[] new_coords = {(int) (Math.random() * 16) + 1, (int) (Math.random() * 16) + 1};

        while (Snake.containsArray(snake.getSnakeCoordinateList(), new_coords)) {
            new_coords[0] = (int) (Math.random() * 16) + 1;
            new_coords[1] = (int) (Math.random() * 16) + 1;
        }

        food.setLocation(new_coords[0], new_coords[1]);


    }

    public static void printGridWithBorders(String[][] grid) {
        int size = grid.length;

        // Print top column numbers
        System.out.print("   "); // Padding for left corner
        for (int col = 0; col < size; col++) {
            System.out.printf("%3d", col);
        }
        System.out.println();

        for (int row = 0; row < size; row++) {
            // Print row number
            System.out.printf("%3d", row);

            // Print grid row
            for (int col = 0; col < size; col++) {
                System.out.printf("%3s", grid[col][row]);
            }
            System.out.println();
    }
}

}
    


