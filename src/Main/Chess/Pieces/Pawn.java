package Main.Chess.Pieces;

import Main.Chess.Board;
import Main.Coord;

import java.util.ArrayList;

public class Pawn extends Piece {

    public Pawn(int team, Coord c) {
        super(team,c);
        this.label = "p";
    }

    @Override
    public ArrayList<Coord> findValidDestinations(Board b) {
        ArrayList<Coord> validMoves = new ArrayList<>();
        ArrayList<Coord> path = new ArrayList<>();
        switch (team) {
            case 0:
                //white
                path.add(new Coord(currentPos.x, currentPos.y-1));
                path.add(new Coord(currentPos.x, currentPos.y-2));
                break;
            case 1:
                //black
                path.add(new Coord(currentPos.x, currentPos.y+1));
                path.add(new Coord(currentPos.x, currentPos.y+2));
                break;
        }
        int idx = getPathObstructedIdx(path, b);
        for (int i = 0; i < idx; i++) {
            Coord pt = path.get(i);
            if ((pt.x >= 0 && pt.x <= 7) && (pt.y >= 0 && pt.y <= 7))
                validMoves.add(path.get(i));
        }
        return validMoves;
    }
}