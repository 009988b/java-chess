package Main.Chess;

import Main.Chess.Pieces.*;
import Main.Coord;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicReference;

public class Board{
    public HashMap<Coord, Piece> board;

    void init() {
        board = new HashMap<Coord,Piece>();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Coord pos = new Coord(i,j);
                //BLACK PLAYER'S PIECES
                if (j == 0) {
                    if (i == 0 || i == 7) {
                        board.put(pos, new Rook(1,pos));
                    }
                    if (i == 1 || i == 6) {
                        board.put(pos, new Knight(1,pos));
                    }
                    if (i == 2 || i == 5) {
                        board.put(pos, new Bishop(1,pos));
                    }
                    if (i == 4) {
                        board.put(pos, new King(1,pos));
                    }
                    if (i == 3) {
                        board.put(pos, new Queen(1,pos));
                    }
                }
                if (j == 1) {
                    board.put(pos, new Pawn(1,pos));
                }
                //WHITE PLAYER'S PIECES
                else if (j == 6) {
                    board.put(new Coord(i, j), new Pawn(0,pos));
                } else if (j == 7) {
                    if (i == 0 || i == 7) {
                        board.put(pos, new Rook(0,pos));
                    }
                    if (i == 1 || i == 6) {
                        board.put(pos, new Knight(0,pos));
                    }
                    if (i == 2 || i == 5) {
                        board.put(pos, new Bishop(0,pos));
                    }
                    if (i == 4) {
                        board.put(pos, new King(0,pos));
                    }
                    if (i == 3) {
                        board.put(pos, new Queen(0,pos));
                    }
                } else {
                    board.put(new Coord(i,j), null);
                }
            }
        }
    }
    public Board() {
        init();
    }

    public Piece findPieceAtCoord(Coord c) {
        final Piece[] p = {null};
        int checks = 0;
        if (c.x >= 0 && c.x <= 7 && (c.y >= 0 && c.y <= 7)) {
            while (checks < 10) {
                this.board.forEach((coord, piece) -> {
                    if (coord.x == c.x & coord.y == c.y) {
                        System.out.println("searching for:" +c.x+","+c.y);
                        System.out.println(coord.x+","+coord.y);
                        System.out.println("found");
                        System.out.println(piece);
                        if (piece != null)
                            p[0] = piece;
                    }
                });
                checks++;
            }
        }
        return p[0];
    }
}