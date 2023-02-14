package Chess.Pieces;

import Chess.Board;
import Chess.Coord;

import java.util.ArrayList;

public class King extends Piece {

    public King(int team, Coord c) {
        super(team,c);
        this.label = "k";
    }

    @Override
    public ArrayList<Coord> findValidDestinations(Board b) {
        ArrayList<Coord> validMoves = new ArrayList<>();
        ArrayList<ArrayList<Coord>> paths = new ArrayList<>(4);
        for (int init = 0; init < 7; init++) {
            paths.add(new ArrayList<>());
        }
        paths.get(0).add(new Coord(currentPos.x-1,currentPos.y));
        paths.get(1).add(new Coord(currentPos.x,currentPos.y+1));
        paths.get(2).add(new Coord(currentPos.x,currentPos.y-1));
        paths.get(3).add(new Coord(currentPos.x+1,currentPos.y+1));
        paths.get(4).add(new Coord(currentPos.x+1,currentPos.y-1));
        paths.get(5).add(new Coord(currentPos.x-1,currentPos.y+1));
        paths.get(6).add(new Coord(currentPos.x-1,currentPos.y-1));
        paths.get(7).add(new Coord(currentPos.x+1,currentPos.y));

        paths.forEach((path) -> {
            int idx = getPathObstructedIdx(path, b);
            System.out.println(idx);
            for (int i = 0; i < idx; i++) {
                Coord pt = path.get(i);
                if ((pt.x >= 0 && pt.x <= 7) && (pt.y >= 0 && pt.y <= 7))
                    validMoves.add(path.get(i));
            }
        });
        validMoves.forEach((move) -> {
            //System.out.println(move.x + " " + move.y);
        });
        return validMoves;
    }
}
