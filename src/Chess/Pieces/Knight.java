package Chess.Pieces;

import Chess.Board;
import Chess.Coord;

import java.util.ArrayList;

public class Knight extends Piece {

    public Knight(int team, Coord c) {
        super(team, c);
        this.label = "k";
    }

    @Override
    public ArrayList<Coord> findValidDestinations(Board b) {
        ArrayList<Coord> validMoves = new ArrayList<>();
        ArrayList<ArrayList<Coord>> paths = new ArrayList<>(4);
        for (int init = 0; init < 8; init++) {
            paths.add(new ArrayList<>());
        }
        //x [] x [] x y-2x+1,y-2x-1
        //[] x x x [] y-1x+2,y-1x-2
        // x x k x x
        //[] x x x [] y+1x+2,y+1x-2
        //x [] x [] x y+2x+1,y+2x-1

        paths.get(0).add(new Coord(currentPos.x-1,currentPos.y-2));
        paths.get(1).add(new Coord(currentPos.x+1,currentPos.y-2));
        paths.get(2).add(new Coord(currentPos.x+2,currentPos.y-1));
        paths.get(3).add(new Coord(currentPos.x-2,currentPos.y-1));
        paths.get(4).add(new Coord(currentPos.x+2,currentPos.y+1));
        paths.get(5).add(new Coord(currentPos.x-2,currentPos.y+1));
        paths.get(6).add(new Coord(currentPos.x-1,currentPos.y+2));
        paths.get(7).add(new Coord(currentPos.x+1,currentPos.y+2));

        paths.forEach((path) -> {
            if (path.get(0) != null) {
                Coord pt = path.get(0);

                boolean obstructed = isTileObstructed(pt,b);
                if ((pt.x >= 0 && pt.x <= 7) && (pt.y >= 0 && pt.y <= 7) && !obstructed)
                    validMoves.add(pt);
            }

        });
        validMoves.forEach((move) -> {
            System.out.println(move.x + " " + move.y);
        });
        return validMoves;
    }
}
