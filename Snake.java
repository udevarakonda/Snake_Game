import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class Snake {

    private ArrayList<int[]> coordinates = new ArrayList<>();   

    private int[] last_tail_location = {8,8};


    
    public Snake() {
        coordinates.add(new int[] {8, 8});
        coordinates.add(new int[] {9, 8});
        coordinates.add(new int[] {10, 8});

    }

    public static boolean containsArray(List<int[]> list, int[] target) {
    for (int[] arr : list) {
        if (Arrays.equals(arr, target)) {
            return true;
        }
    }
    return false;
    }


    public ArrayList<int[]> getSnakeCoordinateList () {
        return coordinates;
    }

    public boolean is_dead() {
        //System.out.println("hello");
        return (coordinates.get(coordinates.size()-1)[0] <= 0 
        || coordinates.get(coordinates.size()-1)[0] >= 17 
        || coordinates.get(coordinates.size()-1)[1] <= 0 
        || coordinates.get(coordinates.size()-1)[1] >= 17 ||
        containsArray(coordinates.subList(0, coordinates.size()-1), coordinates.get(coordinates.size()-1)));
    }

    public int getLength() {
        return coordinates.size();
    }

    public void add_to_body() {
        //System.out.println("adding to body at coordinates [" + last_tail_location[0] + " " + last_tail_location[1] + "]");
        int[] location_to_be_copied = new int[2];
        location_to_be_copied[0] = last_tail_location[0];
        location_to_be_copied[1] = last_tail_location[1];
        coordinates.add(0, location_to_be_copied);
        //System.out.println("new tail is [" + coordinates.get(0)[0] + " " + coordinates.get(0)[1] + "]");
    }

    private void move_body_without_head() {
        for (int i = 0; i < coordinates.size() - 1; i++) {
            coordinates.get(i)[0] = coordinates.get(i + 1)[0];
            coordinates.get(i)[1] = coordinates.get(i + 1)[1];
        }
    }

    private void copy_last_tail_location() {
        //System.out.println("Saving Last Tail Location at coordinates [" + last_tail_location[0] + " " + last_tail_location[1] + "]");
        last_tail_location[0] = coordinates.get(0)[0];
        last_tail_location[1] = coordinates.get(0)[1];
        //System.out.println("Saved Last Tail Location at coordinates [" + last_tail_location[0] + " " + last_tail_location[1] + "]");
    }

    public void move_snake(String direction) {
        copy_last_tail_location();
        //System.out.println("1: Last Tail Location is [" + last_tail_location[0] + " " + last_tail_location[1] + "]");
        move_body_without_head();

        int[] head = coordinates.get(coordinates.size() - 1);
        
        switch (direction.toUpperCase()) {
            case "UP":
                head[1] -= 1;
                break;
            case "DOWN":
                head[1] += 1;
                break;
            case "LEFT":
                head[0] -= 1;
                break;
            case "RIGHT":
                head[0] += 1;
                break;
            default:
                throw new IllegalArgumentException("Invalid direction: " + direction);
        }

        //System.out.println("2: Last Tail Location is [" + last_tail_location[0] + " " + last_tail_location[1] + "]");
}


}