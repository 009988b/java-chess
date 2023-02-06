package Main;

import java.awt.Color;
import java.util.ArrayList;

import Main.Chess.Board;
import Main.Chess.Pieces.Piece;
import logic.Control;


public class Main{
	// Fields (Static) below...
	// End Static fields...
	public static Board board;
	public static int turnCount;
	private static Player p1;
	private static Player p2;

	private static String[] letters;

	public static String gameStatus; // Each turn progresses like this: Unit selection -> Destination selection -> Unit moves and board is updated -> next turn

	public static Coord cursor;
	public static Coord selected;

	public static Coord destination;
	
	public static Piece selectedPiece;

	public static void main(String[] args) {
		Control ctrl = new Control();				// Do NOT remove!
		ctrl.gameLoop();							// Do NOT remove!
	}

	/* This is your access to things BEFORE the game loop starts */
	public static void start(){
		// TODO: Code your starting conditions here...NOT DRAW CALLS HERE! (no addSprite or drawString)
		turnCount = 1;
		p1 = new Player();
		p2 = new Player();
		board = new Board();
		letters = new String[8];
		gameStatus = "select_unit";
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



	/* This is your access to the "game loop" (It is a "callback" method from the Control class (do NOT modify that class!))*/
	public static void update(Control ctrl) {
		// TODO: This is where you can code! (Starting code below is just to show you how it works)
		drawBoard(ctrl);
		board.board.forEach((coord,piece) -> {
			if (piece != null) {
				int x = (1280/2)-(512/2)+24+(64*coord.x);
				int y = (720/2)-(512/2)+32+(64*coord.y);
				ctrl.drawString(x,y,piece.label, Color.BLUE);
			}
		});
		ctrl.drawString(1000, 200, "Turn #" + turnCount, Color.WHITE);
		if (turnCount % 2 != 0) {
			ctrl.drawString(1000, 250, "Player " + p1.team + "'s turn", Color.WHITE);
			if (gameStatus == "move_unit") {
				handleMovement(ctrl);
			}
			if (gameStatus == "select_unit") {
				handleSelection(ctrl);
			}
		} else {
			ctrl.drawString(1000, 250, "Player " + p2.team + "'s turn", Color.WHITE);
			handleSelection(ctrl);
		}
	}

	public static void moveCursor(Coord by) {
		cursor = new Coord(cursor.x+by.x, cursor.y+by.y);
	}
	// Draws game board on to screen with labels
	private static void drawBoard(Control ctrl) {
		ctrl.addSpriteToFrontBuffer(0,0,"bg0");
		ctrl.addSpriteToFrontBuffer((1280/2)-(512/2),(720/2)-(512/2),"bg");
		for (int i = 0; i < 8; i++) {
			ctrl.drawString((1280/2)-(512/2)-24,(720/2)-(512/2)+32+(64*i), Integer.toString(8-i), Color.WHITE);
		}
		for (int j = 0; j < 8; j++) {
			ctrl.drawString((1280/2)-(512/2)+32+(64*j),(1280/2), letters[j], Color.WHITE);
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
				ctrl.drawString(950, 300, "Selected [" + selectedPiece.label + "] at " + letters[selected.x] + "" + (8-selected.y), Color.WHITE);
				ctrl.drawString(950, 350, "owned by: Player " + selectedPiece.team , Color.WHITE);
				ctrl.drawString(950, 380, gameStatus , Color.WHITE);
			}
			if (destination == null) {
				ctrl.drawString(950, 530, "Press [BACKSPC] to select a different unit" , Color.WHITE);
			}
			ctrl.drawString(xs,ys,"S", Color.ORANGE);
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
			Coord destination_pt = getScreenPt(destination);
			ctrl.drawString(destination_pt.x,destination_pt.y,"D", Color.RED);
			ctrl.drawString(950, 430, letters[selected.x] + "" + (8-selected.y) + " to " + letters[destination.x] + "" + (8- destination.y) + "?" , Color.WHITE);
			ctrl.drawString(950, 480, "[M] to confirm move." , Color.WHITE);
			ctrl.drawString(950, 530, "[BACKSPC] to change destination" , Color.WHITE);
		}
		ctrl.drawString(950, 300, "Selected [" + selectedPiece.label + "] at " + letters[selected.x] + "" + (8-selected.y), Color.WHITE);
		ctrl.drawString(950, 350, "owned by: Player " + selectedPiece.team , Color.WHITE);
		if (destination == null) {
			ctrl.drawString(950, 530, "[BACKSPC] to select a different unit" , Color.WHITE);
		}
		ctrl.drawString(950, 380, gameStatus , Color.WHITE);
		ArrayList<Coord> valid_moves = Main.selectedPiece.findValidDestinations(board);
		valid_moves.forEach((m) -> {
			Coord pt = getScreenPt(m);
			ctrl.drawString(pt.x,pt.y,"*", Color.YELLOW);
		});
	}
 }
