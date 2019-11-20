package mainGame.input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import mainGame.Game.STATE;
import mainGame.*;

/**
 * Handles key input from the user
 * 
 * @author Brandon Loehle 5/30/16
 *
 */

public class KeyInput extends KeyAdapter {

	private Handler handler;
	private boolean[] keyDown = new boolean[5];
	private int speed;
	private Game game;
	private Player player;
	private Upgrades upgrades;
	private String ability;

	// uses current handler created in Game as parameter
	public KeyInput(Handler handler, Game game, Player player, Upgrades upgrades) {
		this.handler = handler;
		this.speed = Player.playerSpeed;
		this.game = game;
		this.player = player;
		this.upgrades = upgrades;

		keyDown[0] = false;
		keyDown[1] = false;
		keyDown[2] = false;
		keyDown[3] = false;
		keyDown[4] = false;

	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		this.speed = Player.playerSpeed;

		// finds what key strokes associate with Player
		for (int i = 0; i < handler.object.size(); i++) {
			@SuppressWarnings("unused")
			GameObject tempObject = handler.object.get(i);

			// using only if's allows multiple keys to be triggered at once
			// if (tempObject.getId() == ID.Player) {
			// find the player object, as he is the only one the
			// user can control key events for player 1
			if ((key == KeyEvent.VK_UP || key == KeyEvent.VK_W) && Handler.getTimer() <= 0) {
				player.setVelY(-(this.speed));
				keyDown[0] = true;
			}
			if ((key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) && Handler.getTimer() <= 0) {
				player.setVelX(-(this.speed));
				keyDown[1] = true;
			}
			if ((key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) && Handler.getTimer() <= 0) {
				player.setVelY(this.speed);
				keyDown[2] = true;
			}
			if ((key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) && Handler.getTimer() <= 0) {
				player.setVelX(this.speed);
				keyDown[3] = true;
			}
			if (key == KeyEvent.VK_SPACE && Handler.getTimer() <= 0) {
				upgrades.levelSkipAbility();
			}
			if (key == KeyEvent.VK_SHIFT && Handler.getTimer() <= 0) {
				ability = upgrades.getAbility();
				if (ability.equals("clearScreen")) {
					upgrades.clearScreenAbility();
				} else if (ability.equals("levelSkip")) {
					upgrades.levelSkipAbility();
				} else if (ability.equals("freezeTime")) {
					upgrades.freezeTimeAbility();
				} else if (ability.equals("")) {

				}
			}

		}
		if (key == KeyEvent.VK_ESCAPE) {
			if (game.gameState == STATE.Wave || game.gameState == STATE.Bosses 
					|| game.gameState == STATE.Survival) {
				if (game.isPaused() == true) {
					game.unPause();
				} else {
					game.pause();
				}
			}
		}

		if (key == KeyEvent.VK_M) {
			game.musicKeyPressed();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);

			if (tempObject.getId() == ID.Player) {
				// key events for player 1
				if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
					keyDown[0] = false;
				} else if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
					keyDown[1] = false;
				} else if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
					keyDown[2] = false;
				} else if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
					keyDown[3] = false;
					keyDown[4] = false;
				}

				// vertical movement
				if (!keyDown[0] && !keyDown[2]) {
					tempObject.setVelY(0);
				}
				// horizontal movement
				if (!keyDown[1] && !keyDown[3]) {
					tempObject.setVelX(0);
				}
			}

		}

		// if (key == KeyEvent.VK_ESCAPE) System.exit(1);
	}

}
