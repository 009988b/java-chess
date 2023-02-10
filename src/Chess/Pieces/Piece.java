package Chess.Pieces;


import Chess.Board;
import Chess.Coord;
import Chess.*;

import java.util.ArrayList;

abstract public class Piece{
    public int team;
    public String label;
    public Coord currentPos;

    public Coord lastPos;
    public Piece(int team, Coord pos) {
        this.team = team;
        this.currentPos = pos;
        this.lastPos = null;
        this.label = "";
    }

    public ArrayList<Coord> findValidDestinations(Board b) {
        return null;
    }
    public void move(Board b) {
        //Piece p = b.board.get(currentPos);
        //b.board.replace(currentPos,null);
        Game g = Main.game;
        b.board.remove(g.destination);
        b.board.put(g.destination,g.selectedPiece);
        b.board.remove(currentPos);
        b.board.put(currentPos,null);
        b.board.forEach((coord, piece) -> {
            if (coord.equals(currentPos)) {
                System.out.println("currentPos:");
                System.out.println(coord.x+""+coord.y+"piece:"+piece);
            }
            if (coord.equals(g.destination)) {
                System.out.println("destination:");
                System.out.println(coord.x+""+coord.y+"piece:"+piece);
            }
        });
        System.out.println(b.board.get(currentPos));
        currentPos = g.destination;
        g.destination = null;
        g.selectedPiece = null;
        g.selected = null;
        g.status = "select_unit";
        g.turnCount++;
        g.errorMsg = "";
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