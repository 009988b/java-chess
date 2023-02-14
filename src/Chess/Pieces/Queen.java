package Chess.Pieces;

import Chess.Board;
import Chess.Coord;

import java.util.ArrayList;

public class Queen extends Piece {

    public Queen(int team, Coord c) {
        super(team,c);
        this.label = "q";
    }

    @Override
    public ArrayList<Coord> findValidDestinations(Board b) {
        ArrayList<Coord> validMoves = new ArrayList<>();
        ArrayList<ArrayList<Coord>> paths = new ArrayList<>(4);
        for (int init = 0; init < 8; init++)
            paths.add(new ArrayList<>());

        for (int pathIdx = 0; pathIdx < 7; pathIdx++) {
            switch (pathIdx) {
                case 0:
                    for (int tr = 1; tr <= 7; tr++) {
                        paths.get(pathIdx).add(new Coord(currentPos.x + tr, currentPos.y - tr));
                    }
                    break;
                case 1:
                    for (int tl = 1; tl <= 7; tl++) {
                        paths.get(pathIdx).add(new Coord(currentPos.x - tl, currentPos.y - tl));
                    }
                    break;
                case 2:
                    for (int br = 1; br <= 7; br++) {
                        paths.get(pathIdx).add(new Coord(currentPos.x + br, currentPos.y + br));
                    }
                    break;
                case 3:
                    for (int bl = 1; bl <= 7; bl++) {
                        paths.get(pathIdx).add(new Coord(currentPos.x - bl, currentPos.y + bl));
                    }
                    break;
                case 4:
                    for (int t = 1; t <= 7; t++) {
                        paths.get(pathIdx).add(new Coord(currentPos.x, currentPos.y - t));
                    }
                    break;
                case 5:
                    for (int l = 1; l <= 7; l++) {
                        paths.get(pathIdx).add(new Coord(currentPos.x - l, currentPos.y));
                    }
                    break;
                case 6:
                    for (int r = 1; r <= 7; r++) {
                        paths.get(pathIdx).add(new Coord(currentPos.x + r, currentPos.y));
                    }
                    break;
                case 7:
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
