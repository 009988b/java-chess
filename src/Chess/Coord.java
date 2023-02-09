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
        boolean result = (c.x == this.x && c.y == this.y);
        return result;
    }
    @Override
    public int hashCode() {
        return (x) + ((y+1)*31);
    }
}
