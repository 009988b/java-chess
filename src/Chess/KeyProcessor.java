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
		/*switch(key){
		case '%':								// ESC key
			System.exit(0);
			break;
		case 'w':
			if (Main.game.cursor.y > 0 ) {
				Main.game.moveCursor(new Coord(0,-1));
			}
			break;
		case 'a':
			if (Main.game.cursor.x > 0) {
				Main.game.moveCursor(new Coord(-1,0));
			}
			break;
		case 's':
			if (Main.game.cursor.y < 7) {
				Main.game.moveCursor(new Coord(0,1));
			}
			break;
		case 'd':
			if (Main.game.cursor.x < 7) {
				Main.game.moveCursor(new Coord(1,0));
			}
			break;
		case '=':
			if (Main.game.status == "confirm_move") {
				if (Main.game.destination != null) {
					//System.out.println(Main.game.selectedPiece);
					if (Main.game.selectedPiece != null)
						Main.game.selectedPiece.move(Main.game.board);
					break;
				}
			}
			if (Main.game.status == "move_unit") {
				if (Main.game.validMoves.contains(Main.game.cursor))
					Main.game.destination = Main.game.cursor;
				break;
			}
			if (Main.game.selectedPiece != null && Main.game.selected != null) {
				if (Main.game.selected.x == Main.game.cursor.x && Main.game.selected.y == Main.game.cursor.y) {
					Main.game.status = "move_unit";
					break;
				}
			}
			Main.game.selectedPiece = null;
			Main.game.selected = Main.game.cursor;
			break;
		case '*':
			if (Main.game.status == "move_unit" || Main.game.status == "confirm_move") {
				Main.game.status = "select_unit";
				Main.game.destination = null;
				Main.game.selected = null;
				break;
			}
			if (Main.game.status == "select_unit") {
				Main.game.selected = null;
				break;
			}
			break;
		case 'm':
			// For mouse coordinates
			//Control.isMouseCoordsDisplayed = !Control.isMouseCoordsDisplayed;
			System.out.println(Main.game.destination.x + "," + Main.game.destination.y + ", piece: [" + Main.game.selectedPiece.label + "]");

			break;
		}*/
		Main.game.handleInput(key);
	}
}