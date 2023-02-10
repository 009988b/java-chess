package Chess;

import java.awt.Color;
import java.util.ArrayList;

import Chess.Pieces.Piece;
import Chess.Game;
import logic.Control;

import javax.swing.*;


public class Main{

	public static Game game;
	public static void main(String[] args) {
		System.setProperty("apple.laf.useScreenMenuBar", "true");
		System.setProperty("com.apple.mrj.application.apple.menu.about.name", "Chess");
		System.setProperty("apple.awt.application.name", "Chess");
		game = Game.Game();
		Control ctrl = new Control();				// Do NOT remove!
		ctrl.gameLoop();

		// Do NOT remove!
	}

	/* This is your access to things BEFORE the game loop starts */
	public static void start(){
		// TODO: Code your starting conditions here...NOT DRAW CALLS HERE! (no addSprite or drawString)
	}



	/* This is your access to the "game loop" (It is a "callback" method from the Control class (do NOT modify that class!))*/
	public static void update(Control ctrl) {
		// TODO: This is where you can code! (Starting code below is just to show you how it works)
		game.drawBoard(ctrl);
		game.drawPieces(ctrl);
		ctrl.drawString(1280/2, 30, game.errorMsg, Color.PINK);
		ctrl.drawString(1000, 200, "Turn #" + game.turnCount, Color.WHITE);
		if (game.turnCount % 2 != 0) {
			game.play(ctrl);
			game.currentTeam = 0;
		} else {
			game.play(ctrl);
			game.currentTeam = 1;
		}
	}
 }
