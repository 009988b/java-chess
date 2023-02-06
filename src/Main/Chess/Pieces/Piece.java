package Main.Chess.Pieces;


import Main.Chess.Board;
import Main.Coord;
import Main.*;

import java.util.ArrayList;

abstract public class Piece{
    public int team;
    public String label;
    public Coord currentPos;
    public Piece(int team, Coord pos) {
        this.team = team;
        this.currentPos = pos;
        this.label = "";
    }

    public ArrayList<Coord> findValidDestinations(Board b) {
        return null;
    }
    public void move(Board b) {
        b.board.forEach((coord,piece) -> {
            if (coord.x == currentPos.x && coord.y == currentPos.y) {
                b.board.replace(coord, null);
            }
            if (coord.x == Main.destination.x && coord.y == Main.destination.y) {
                if (piece != null) {
                    if (piece.team != this.team) {
                        //oppposing piece destroyed
                        b.board.replace(coord, null);
                    }
                }
                currentPos = Main.destination;
                b.board.replace(coord, this);
                //Main.destination = null;
                Main.gameStatus = "select_unit";
                Main.turnCount++;
            }
        });
    }

    public int getPathObstructedIdx(ArrayList<Coord> path, Board b) {
        final int[] idx = {path.size()};
        path.forEach(pt -> {
            Piece p = b.findPieceAtCoord(pt);
            if (p != null) {
                if (p.team == this.team) {
                    //path obstructed at
                    idx[0] = path.indexOf(pt);
                }
            }
        });
        return idx[0];
    }
}