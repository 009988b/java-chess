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
			if (Main.gameStatus == "move_unit") {
				if (Main.validMoves.contains(Main.cursor))
					Main.destination = Main.cursor;
				break;
			}
			if (Main.selectedPiece != null) {
				if (Main.destination != null) {
					Main.selectedPiece.move(Main.board);
					Main.gameStatus = "select_unit";
					Main.errorMsg = "";
					Main.destination = null;
					break;
				}
				if (Main.selected.x == Main.cursor.x && Main.selected.y == Main.cursor.y) {
					Main.gameStatus = "move_unit";
					break;
				}
			}

			Main.selectedPiece = null;
			Main.selected = Main.cursor;
			break;
		case '*':
			if (Main.gameStatus == "move_unit") {
				Main.gameStatus = "select_unit";
				break;
			}
			Main.selected = null;
			break;
		case 'm':
			// For mouse coordinates
			//Control.isMouseCoordsDisplayed = !Control.isMouseCoordsDisplayed;
			break;
		}
	}
}