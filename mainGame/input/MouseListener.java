package mainGame.input;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import mainGame.Game.STATE;
import mainGame.spawn.*;
import mainGame.*;
import mainGame.gui.*;


public class MouseListener extends MouseAdapter {

	private Game game;
	private Handler handler;
	private HUD hud;
	private Spawn1to5 spawner;
	private Spawn5to10 spawner2;
	private SpawnSurvival spawnSurvival;
	private SpawnBosses spawnBosses;
	private SpawnMultiplayer spawnMulti;
	private UpgradeScreen upgradeScreen;
	private Upgrades upgrades;
	private Player player;
	private Leaderboard leaderboard;
	private LeaderboardDisplay leaderboardDisplay;

	public MouseListener(Game game, Handler handler, HUD hud, Spawn1to5 spawner, 
			Spawn5to10 spawner2, SpawnSurvival spawnSurvival, UpgradeScreen upgradeScreen, 
			SpawnMultiplayer spawnM, Player player, Upgrades upgrades, 
			Leaderboard leaderboard, SpawnBosses spawnBosses, 
			LeaderboardDisplay leaderboardDisplay) {
		this.game = game;
		this.handler = handler;
		this.hud = hud;
		this.spawner = spawner;
		this.spawner2 = spawner2;
		this.upgradeScreen = upgradeScreen;
		this.player = player;
		this.upgrades = upgrades;
		this.spawnSurvival = spawnSurvival;
		this.spawnMulti = spawnM;
		this.leaderboard = leaderboard;
		this.spawnBosses = spawnBosses;
		this.leaderboardDisplay = leaderboardDisplay;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (game.gameState == STATE.Host || game.gameState == STATE.Join)
			return;
		int mx = e.getX();
		int my = e.getY();
		if (!game.isPaused()) { // game is not paused
			if (game.gameState == STATE.GameOver) {
				if (player.checkGame() == "waves") {
					upgrades.resetUpgrades();
					spawner.restart();
					spawner.addLevels();
					spawner2.restart();
					spawner2.addLevels();
					Spawn1to5.LEVEL_SET = 1;
					game.gameState = STATE.Menu;
				} else if (player.checkGame() == "survival") {
					spawnSurvival.restart();
					game.gameState = STATE.Menu;
				} else if (player.checkGame() == "bosses") {
					spawnBosses.restart();
					game.gameState = STATE.Menu;
				} else if (handler.isMulti()) {
					spawnMulti.reset();
					game.gameState = STATE.Menu;
				}
			} else if (game.gameState == STATE.WonWaves) {
				upgrades.resetUpgrades();
				spawner.restart();
				spawner.addLevels();
				spawner2.restart();
				spawner2.addLevels();
				Spawn1to5.LEVEL_SET = 1;
				game.gameState = STATE.Menu;
			}

			else if (game.gameState == STATE.Wave) {
			}
			else if (game.gameState == STATE.Multiplayer) {
			}
			else if (game.gameState == STATE.Bosses) {			
			}
			else if (game.gameState == STATE.Survival) {
			}

			else if (game.gameState == STATE.Upgrade) {
				if (mouseOver(mx, my, 210, 210, 860, 150)) {
					upgrades.activateUpgrade(upgradeScreen.getPath(1));
					upgradeScreen.removeUpgradeOption(1);
				} else if (mouseOver(mx, my, 210, 200 + 150, 860, 150)) {
					upgrades.activateUpgrade(upgradeScreen.getPath(2));
					upgradeScreen.removeUpgradeOption(2);
				} else if (mouseOver(mx, my, 100, 200 + 2 * 150, 860, 150)) {
					upgrades.activateUpgrade(upgradeScreen.getPath(3));
					upgradeScreen.removeUpgradeOption(3);
				}
				game.gameState = STATE.Wave;
			}

			else if (game.gameState == STATE.Menu) {
				// Waves Button
				if (mouseOver(mx, my, 50, 150, 350, 100)) {
					handler.object.clear();
					game.gameState = STATE.Wave;
					handler.addObject(player);
				}
				// Bosses Mode
				else if (mouseOver(mx, my, 50, 300, 350, 100)) {
					handler.object.clear();
					game.gameState = STATE.Bosses;
					handler.addObject(player);
				}
				// Survival Mode
				else if (mouseOver(mx, my, 50, 450, 350, 100)) {
					hud.setScore(0);
					handler.object.clear();
					game.gameState = STATE.Survival;
					handler.addObject(player);
				}
				// Credits Button
				else if (mouseOver(mx, my, 450, 150, 350, 100)) {
					game.gameState = STATE.Credits;
				} 
				// Help Button
				else if (mouseOver(mx, my, 450, 300, 350, 100)) {
					game.gameState = STATE.Help;
				}
				// Quit Button
				else if (mouseOver(mx, my, 450, 450, 350, 100)) {
					System.exit(1);
				}
				//Multiplayer Host Button
				else if (mouseOver(mx, my, 850, 150, 350, 100)) {
					game.gameState = STATE.Host;
				}
				//Multiplayer Join Button
				else if (mouseOver(mx, my, 850, 300, 350, 100)) {
					game.gameState = STATE.Join;
				}
				// Color Picker Mode
				else if (mouseOver(mx, my, 850, 450, 100, 100)) {
					handler.object.clear();
					game.gameState = STATE.Color;
					handler.addObject(player);
				}
			}
			// Back Button for Help screen
			else if (game.gameState == STATE.Help) {
				if (mouseOver(mx, my, 566, 590, 133, 42)) {
					game.gameState = STATE.Menu;
					return;
				}
			}
			// Leaderboard screen
			else if (game.gameState == STATE.Leaderboard) {
				if(leaderboard.getUser() != "") {
					if (mouseOver(mx, my, 353, 490, 566, 166)) {
						leaderboard.loadLeaderboard();
						leaderboardDisplay.refresh();
						game.gameState = STATE.LeaderboardDisplay;
						return;
					}
				}
			} else if (game.gameState == STATE.Credits) {
				if (mouseOver(mx, my, 566, 650, 133, 42)) {
					game.gameState = STATE.Menu;
					return;
				}
			} else if(game.gameState == STATE.Color) {
				int x = 0;
				int y = 0;
				if(mouseOver(mx, my,x+70, y+75, 125, 125)) {
					player.updateColors(Toolkit.getDefaultToolkit().getImage(Game.class.getResource("images/test_pixelart.png")), null);
				} else if(mouseOver(mx, my, x+320, y+75, 125, 125)) {
					player.updateColors(Toolkit.getDefaultToolkit().getImage(Game.class.getResource("images/mario.gif")), null);
				} else if(mouseOver(mx, my, x+570, y+75, 160, 160)) {
					player.updateColors(Toolkit.getDefaultToolkit().getImage(Game.class.getResource("images/saitamaONE.png")), null);
				} else if(mouseOver(mx, my,x+820, y+75, 125, 125)) {
					player.updateColors(Toolkit.getDefaultToolkit().getImage(Game.class.getResource("images/pikachu.png")), null);
				} else if(mouseOver(mx, my,x+1070, y+75, 125, 125)) {
					player.updateColors(Toolkit.getDefaultToolkit().getImage(Game.class.getResource("images/octocat.png")), null);
				} else if(mouseOver(mx, my,x+70, y+215, 125, 125)) {
					player.updateColors(Toolkit.getDefaultToolkit().getImage(Game.class.getResource("images/redit.png")), null);
				} else if(mouseOver(mx, my,x+320, y+215, 125, 125)) {
					player.updateColors(Toolkit.getDefaultToolkit().getImage(Game.class.getResource("images/abra.gif")), null);
				} else if(mouseOver(mx, my,x+570, y+215, 125, 125)) {
					player.updateColors(Toolkit.getDefaultToolkit().getImage(Game.class.getResource("images/giphy.gif")), null);
				} else if(mouseOver(mx, my,x+820, y+215, 125, 125)) {
					player.updateColors(Toolkit.getDefaultToolkit().getImage(Game.class.getResource("images/kingdom.png")), null);
				} else if(mouseOver(mx, my,x+1070, y+215, 125, 125)) {
					player.updateColors(Toolkit.getDefaultToolkit().getImage(Game.class.getResource("images/cat.png")), null);
				} else if(mouseOver(mx, my,x+70, y+440, 125, 125)) {
					player.updateColors(null, new Color(255,255,255,90));
				} else if(mouseOver(mx, my, x+320, y+440, 125, 125)) {
					player.updateColors(null, new Color(0, 0, 255, 90));
				} else if(mouseOver(mx, my, x+570, y+440, 125, 125)) {
					player.updateColors(null, new Color(255, 255, 0, 90));
				} else if(mouseOver(mx, my,x+820, y+440, 125, 125)) {
					player.updateColors(null, new Color(0, 255, 255, 90));
				} else if(mouseOver(mx, my,x+1070, y+440, 125, 125)) {
					player.updateColors(null, new Color(120, 120, 120, 90));
				} else if(mouseOver(mx, my,x+70, y+585, 125, 125)) {
					player.updateColors(null, new Color(0, 255, 0, 90));
				} else if(mouseOver(mx, my,x+320, y+585, 125, 125)) {
					player.updateColors(null, new Color(255, 0, 255, 90));
				} else if(mouseOver(mx, my,x+570, y+585, 125, 125)) {
					player.updateColors(null, new Color(255, 128, 0, 90));
				} else if(mouseOver(mx, my,x+820, y+585, 125, 125)) {
					player.updateColors(null, new Color(255, 0, 127, 90));
				} else if(mouseOver(mx, my,x+1070, y+585, 125, 125)) {
					player.updateColors(null, new Color(255, 0, 0, 90));
				} else if (mouseOver(mx, my,1000, 10, 250, 60)) {
					game.gameState = STATE.Menu;
					handler.clearPlayer();
				}
			} else if(game.gameState == STATE.LeaderboardDisplay) {
				if(mouseOver(mx,my,0,0,Game.WIDTH,Game.HEIGHT)) {
					leaderboard.reset();
					game.gameState = STATE.Menu;
				}
			}
		} else { // game is paused
			// PauseMenu-> Resume
			if (mouseOver(mx, my, 445, 37, 390, 329)) {
				game.unPause();
				return;
				//PauseMenu-> Main Menu
			} if (mouseOver(mx, my, 445, 372, 390, 337)) {
				// TODO: make one method in the handler for resetGame() that does all of the following things
				// If the user clicks on "Resume" the game unpauses
				game.unPause();
				// If the user clicks on "Quit" the game takes you to the Main Menu Page
				game.gameState = STATE.Menu;
				// After the user quits survival, these methods clear the enemies, a player and resets the pickups
				handler.clearEnemies();
				handler.clearPlayer();
				handler.pickups.clear();
				// These methods reset all of the HUD items
				hud.setScore(0);
				hud.updateScoreColor(Color.white);
				hud.setHealth(100);
				hud.setLevel(1);
				hud.setExtraLives(0);
				// These methods make it so that when the player quits and plays survival, the player is on level 1
				upgrades.resetUpgrades();
				spawner.restart();
				spawner.addLevels();
				spawner2.restart();
				spawner2.addLevels();
				Spawn1to5.LEVEL_SET = 1;
				// After the player quits boss mode, the spawn of bosses order restarts to the first one.
				spawnBosses.restart();
				return;
			}
		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	/**
	 * Helper method to detect is the mouse is over a "button" drawn via
	 * Graphics
	 * @param mx
	 *            mouse x position
	 * @param my
	 *            mouse y position
	 * @param x
	 *            button x position
	 * @param y
	 *            button y position
	 * @param width
	 *            button width
	 * @param height
	 *            button height
	 * @return boolean, true if the mouse is contained within the button
	 */
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
		if (mx > x && mx < x + width) {
			if (my > y && my < y + height) {
				return true;
			} else
				return false;
		} else
			return false;
	}
}

