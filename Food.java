// MY FINAL VERSION

public class Food {
    
    private int[] location = new int[2];

    public Food(int x, int y) {
        setLocation(x, y);
    }

    public int[] getLocation() {
        return location;
    }

    public void setLocation(int x, int y) {
        location[0] = x;
        location[1] = y;
    }
}
