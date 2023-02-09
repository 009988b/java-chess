/* This will handle the "Hot Key" system. */

package Chess;

import timer.stopWatchX;

public class KeyProcessor{
	// Static Fields
	private static char last = ' ';			// For debouncing purposes
	private static stopWatchX sw = new stopWatchX(250);
	
	// Static Method(s)
	public static void processKey(char key){
		if(key == ' ')				return;
		// Debounce routine below...
		if(key == last)
			if(sw.isTimeUp() == false)			return;
		last = key;
		sw.resetWatch();
		
		/* TODO: You can modify values below here! */
		switch(key){
		case '%':								// ESC key
			System.exit(0);
			break;
		case 'w':
			if (Main.cursor.y > 0 ) {
				Main.moveCursor(new Coord(0,-1));
			}
			break;
		case 'a':
			if (Main.cursor.x > 0) {
				Main.moveCursor(new Coord(-1,0));
			}
			break;
		case 's':
			if (Main.cursor.y < 7) {
				Main.moveCursor(new Coord(0,1));
			}
			break;
		case 'd':
			if (Main.cursor.x < 7) {
				Main.moveCursor(new Coord(1,0));
			}
			break;
		case '=':
			if (Main.gameStatus == "confirm_move") {
				if (Main.destination != null) {
					//System.out.println(Main.selectedPiece);
					if (Main.selectedPiece != null)
						Main.selectedPiece.move(Main.board);
					break;
				}
			}
			if (Main.gameStatus == "move_unit") {
				if (Main.validMoves.contains(Main.cursor))
					Main.destination = Main.cursor;
				break;
			}
			if (Main.selectedPiece != null && Main.selected != null) {
				if (Main.selected.x == Main.cursor.x && Main.selected.y == Main.cursor.y) {
					Main.gameStatus = "move_unit";
					break;
				}
			}
			Main.selectedPiece = null;
			Main.selected = Main.cursor;
			break;
		case '*':
			if (Main.gameStatus == "move_unit" || Main.gameStatus == "confirm_move") {
				Main.gameStatus = "select_unit";
				Main.destination = null;
				Main.selected = null;
				break;
			}
			if (Main.gameStatus == "select_unit") {
				Main.selected = null;
				break;
			}
			break;
		case 'm':
			// For mouse coordinates
			//Control.isMouseCoordsDisplayed = !Control.isMouseCoordsDisplayed;
			System.out.println(Main.destination.x + "," + Main.destination.y + ", piece: [" + Main.selectedPiece.label + "]");

			break;
		}
	}
}