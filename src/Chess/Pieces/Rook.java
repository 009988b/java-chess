package Chess.Pieces;

import Chess.Board;
import Chess.Coord;

import java.util.ArrayList;

public class Rook extends Piece {

    public Rook(int team, Coord c) {
        super(team,c);
        this.label = "r";
    }

    @Override
    public ArrayList<Coord> findValidDestinations(Board b) {
        ArrayList<Coord> validMoves = new ArrayList<>();
        ArrayList<ArrayList<Coord>> paths = new ArrayList<>(4);
        paths.add(new ArrayList<>());
        paths.add(new ArrayList<>());
        paths.add(new ArrayList<>());
        paths.add(new ArrayList<>());


        for (int pathIdx = 0; pathIdx < 4; pathIdx++) {
            switch (pathIdx) {
                case 0:
                    for (int t = 1; t <= 7; t++) {
                        paths.get(pathIdx).add(new Coord(currentPos.x, currentPos.y - t));
                    }
                    break;
                case 1:
                    for (int l = 1; l <= 7; l++) {
                        paths.get(pathIdx).add(new Coord(currentPos.x - l, currentPos.y));
                    }
                    break;
                case 2:
                    for (int r = 1; r <= 7; r++) {
                        paths.get(pathIdx).add(new Coord(currentPos.x + r, currentPos.y));
                    }
                    break;
                case 3:
                    for (int bot = 1; bot <= 7; bot++) {
                        paths.get(pathIdx).add(new Coord(currentPos.x, currentPos.y + bot));
                    }
                    break;
            }
        }
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

