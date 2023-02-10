package Chess;

import Chess.Pieces.Piece;

import java.awt.*;
import java.util.ArrayList;

import logic.Control;

public class Game {
    static Board board;
    public static int turnCount;
    private static Player p1;
    private static Player p2;
    private static String[] letters;

    public static String status; // Each turn progresses like this: Unit selection -> Destination selection -> Unit moves and board is updated -> next turn

    static Coord cursor;
    public static Coord selected;

    public static Coord destination;

    public static Piece selectedPiece;

    public static String errorMsg;

    static int currentTeam;

    static ArrayList<Coord> validMoves;

    private static Game game_instance = null; // Singleton

    public static Game Game() {
        if (game_instance == null) {
            game_instance = new Game();
            game_instance.initGame();
        }
        return game_instance;
    }

    private void initGame() {
        turnCount = 1;
        errorMsg = "";
        p1 = new Player();
        p2 = new Player();
        board = new Board();
        letters = new String[8];
        status = "select_unit";
        letters[0] = "A";
        letters[1] = "B";
        letters[2] = "C";
        letters[3] = "D";
        letters[4] = "E";
        letters[5] = "F";
        letters[6] = "G";
        letters[7] = "H";
        cursor = new Coord(0,0);
        selected = null;
    }

    public void handleInput(char key) {
        switch(key){
            case '%':								// ESC key
                System.exit(0);
                break;
            case 'w':
                if (cursor.y > 0 ) {
                    moveCursor(new Coord(0,-1));
                }
                break;
            case 'a':
                if (cursor.x > 0) {
                    moveCursor(new Coord(-1,0));
                }
                break;
            case 's':
                if (cursor.y < 7) {
                    moveCursor(new Coord(0,1));
                }
                break;
            case 'd':
                if (cursor.x < 7) {
                    moveCursor(new Coord(1,0));
                }
                break;
            case '=':
                if (status == "confirm_move") {
                    if (destination != null) {
                        //System.out.println(selectedPiece);
                        if (selectedPiece != null)
                            selectedPiece.move(board);
                        break;
                    }
                }
                if (status == "move_unit") {
                    if (validMoves.contains(cursor))
                        destination = cursor;
                    break;
                }
                if (selectedPiece != null && selected != null) {
                    if (selected.x == cursor.x && selected.y == cursor.y) {
                        status = "move_unit";
                        break;
                    }
                }
                selectedPiece = null;
                selected = cursor;
                break;
            case '*':
                if (status == "move_unit" || status == "confirm_move") {
                    status = "select_unit";
                    destination = null;
                    selected = null;
                    break;
                }
                if (status == "select_unit") {
                    selected = null;
                    break;
                }
                break;
            case 'm':
                // For mouse coordinates
                //Control.isMouseCoordsDisplayed = !Control.isMouseCoordsDisplayed;
                System.out.println(destination.x + "," + destination.y + ", piece: [" + selectedPiece.label + "]");

                break;
        }
    }
    
    public static void moveCursor(Coord by) {
        cursor = new Coord(cursor.x+by.x, cursor.y+by.y);
    }
    // Draws game board on to screen with labels
    public static void drawPieces(Control ctrl) {
        board.board.forEach((coord,piece) -> {
            if (piece != null) {
                int x = (1280/2)-(512/2)+24+(64*coord.x);
                int y = (720/2)-(512/2)+32+(64*(coord.y));
                ctrl.drawString(x,y,piece.label, Color.BLUE);
            }
        });
    }
    static void drawBoard(Control ctrl) {
        ctrl.addSpriteToFrontBuffer(0,0,"bg0");
        ctrl.addSpriteToFrontBuffer((1280/2)-(512/2),(720/2)-(512/2),"bg");
        for (int i = 0; i < 8; i++) {
            ctrl.drawString((1280/2)-(512/2)-24,(720/2)-(512/2)+32+(64*i), Integer.toString(8-i), Color.WHITE);
        }
        for (int j = 0; j < 8; j++) {
            ctrl.drawString((1280/2)-(512/2)+32+(64*j),(1280/2), letters[j], Color.WHITE);
        }
    }

    public static void play(Control ctrl) {
        ctrl.drawString(1000, 250, "Player " + (currentTeam+1) + "'s turn", Color.WHITE);
        if (status == "move_unit" || status == "confirm_move") {
            handleMovement(ctrl);
        }
        if (status == "select_unit") {
            handleSelection(ctrl);
        }
    }

    // Handles cursor drawing and unit selection logic
    private static void handleSelection(Control ctrl) {
        int x = (1280/2)-(512/2)+24+(64*cursor.x);
        int y = (720/2)-(512/2)+32+(64* cursor.y);
        ctrl.drawString(x,y,"C", Color.GREEN);
        if (selected != null) {
            if (selectedPiece == null) {
                Piece p = board.findPieceAtCoord(selected);
                //System.out.println(selected.x + "," + selected.y);

                selectedPiece = p;
            } else if (selectedPiece.currentPos.x != selected.x && selectedPiece.currentPos.y != selected.y) {
                selectedPiece = null;
            }
            int xs = (1280/2)-(512/2)+24+(64* selected.x);
            int ys = (720/2)-(512/2)+32+(64* selected.y);
            if (selectedPiece != null) {
                if (selectedPiece.team == currentTeam) {
                    ctrl.drawString(950, 300, "Selected [" + selectedPiece.label + "] at " + letters[selected.x] + "" + (8-selected.y), Color.WHITE);
                    ctrl.drawString(950, 350, "owned by: Player " + selectedPiece.team , Color.WHITE);
                    ctrl.drawString(950, 380, status , Color.WHITE);
                    errorMsg = "";
                } else {
                    errorMsg = "Error: Cannot select Player " + (selectedPiece.team+1) + "'s piece.";
                    selectedPiece = null;
                }
            }
            if (destination == null) {
                ctrl.drawString(950, 530, "Press [BACKSPC] to select a different unit" , Color.WHITE);
            }
            ctrl.drawString(xs,ys,"S", Color.ORANGE);
            if (cursor.equals(selected)) {
                ctrl.drawString(950, 480, "Press [ENTER] to move this unit" , Color.WHITE);
            }
        }
    }
    // Get screen point from board pt
    private static Coord getScreenPt(Coord pt) {
        int x = (1280/2)-(512/2)+24+(64*pt.x);
        int y = (720/2)-(512/2)+32+(64*pt.y);
        return new Coord(x,y);
    }
    // Handles unit movement destination selection and confirmation
    private static void handleMovement(Control ctrl) {
        Coord cursor_pt = getScreenPt(cursor);
        ctrl.drawString(cursor_pt.x,cursor_pt.y,"C", Color.GREEN);
        Coord selected_pt = getScreenPt(selected);
        ctrl.drawString(selected_pt.x,selected_pt.y,"S", Color.ORANGE);
        if (destination != null) {
            if (validMoves.contains(destination)) {
                status = "confirm_move";
            }
            Coord destination_pt = getScreenPt(destination);
            ctrl.drawString(destination_pt.x,destination_pt.y,"D", Color.RED);
            ctrl.drawString(950, 430, letters[selected.x] + "" + (8-selected.y) + " to " + letters[destination.x] + "" + (8- destination.y) + "?" , Color.WHITE);
            ctrl.drawString(950, 480, "[ENTER] again to confirm move." , Color.WHITE);
            ctrl.drawString(950, 530, "[BACKSPC] to change destination" , Color.WHITE);
        }
        ctrl.drawString(950, 300, "Selected [" + selectedPiece.label + "] at " + letters[selected.x] + "" + (8-selected.y), Color.WHITE);
        ctrl.drawString(950, 350, "owned by: Player " + selectedPiece.team+1 , Color.WHITE);
        if (destination == null) {
            ctrl.drawString(950, 530, "[BACKSPC] to select a different unit" , Color.WHITE);
        }
        ctrl.drawString(950, 380, status , Color.WHITE);
        validMoves = selectedPiece.findValidDestinations(board);
        validMoves.forEach((m) -> {
            Coord pt = getScreenPt(m);
            ctrl.drawString(pt.x,pt.y,"*", Color.YELLOW);
        });
    }
}
