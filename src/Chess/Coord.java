package Chess;

public class Coord {
    public int x;
    public int y;

    public Coord(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        Coord c = (Coord) o;
        return (c.x == this.x && c.y == this.y);
    }
}
