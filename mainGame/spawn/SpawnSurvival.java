package mainGame.spawn;

import java.awt.Color;
import java.util.Random;
import mainGame.enemy.*;
import mainGame.*;
import mainGame.gui.*;
import mainGame.pickup.*;

/**
 * A type of game mode in the game
 * 
 * @author Kyle Gorman 10/16/17
 *
 */

public class SpawnSurvival {

	private Handler handler;
	private HUD hud;
	private Game game;
	private int EnemySpawnTimer;
	private int DropSpawnTimer;
	private int differentEnemies;
	private int differentDrops;
	private Random r;
	private String[] side = {"left", "right", "top", "bottom"};
	private int trackerTimer;
	private Color trackerColor;
	private int count;
	private int EnemySpawnNum;
	private int DropSpawnNum;
	private int tempCounter;
	private boolean textThere;
	private Player player;
	
	public SpawnSurvival(Handler handler, HUD hud, Game game, Player player) {
		this.handler = handler;
		this.hud = hud;
		this.game = game;
		this.player = player;
		handler.object.clear();
		hud.health = 100;
		hud.setScore(0);
		hud.setLevel(1);
		Player.doublePointsActive = false;
		
		EnemySpawnTimer = 0;
		DropSpawnTimer = 0;
		r = new Random();
		//different types of enemies added
		differentEnemies = 10;
		differentDrops = 7;
		trackerTimer = 1000;
		trackerColor = Color.blue;
		count = 0;
		EnemySpawnNum = -1;
		DropSpawnNum = -1;
		tempCounter = 0;
		textThere = false;
	}

	public void tick() {
		tempCounter++;
		// updates the trackers color
		if(trackerTimer == 999) {
			trackerColor = Color.blue;
		} else if (trackerTimer == 500) {
			trackerColor = Color.black;
		} else if (trackerTimer == 0) {
			trackerTimer = 1000;
		}
		//prevents the trackers from spawning invisible 
		if(count == 1) {
			trackerTimer--;
		}
		if(EnemySpawnNum == -1) {
			if(!textThere) {
				handler.addObject(new LevelText(Game.WIDTH / 2 - 675, Game.HEIGHT / 2 - 200, "Good luck!",
							ID.SurvivalText));
				textThere = true;
			}
			if(tempCounter > 120) {
				handler.clearEnemies();
				tempCounter = 0;
			}
		}
		if(EnemySpawnTimer == 120) {
			EnemySpawnNum = randEnemy();
			//spawnNum = 9;
			if(EnemySpawnNum == 0) {
				//spawns Basic enemy
				
				handler.addObject(
						new EnemyBasic(r.nextInt(((Game.WIDTH - 50) - 50) + 1 )+ 50 , r.nextInt(((Game.HEIGHT - 50) - 50) + 1 )+ 50, 9, 9, ID.EnemyBasic, handler));
				EnemySpawnTimer = 0;

			} else if(EnemySpawnNum == 1) {
				//spawns Burst enemy

				handler.addObject(
						new EnemyBurst(-200, 200, 50, 50, 200, side[r.nextInt(4)], ID.EnemyBurst, handler));
				EnemySpawnTimer = 0;

			} else if(EnemySpawnNum == 2) {
				//spawns Sweep enemy

				int sweepTemp = (int) (Math.random()*4);
				if (sweepTemp == 0) {
					handler.addObject(
							new EnemySweep(r.nextInt(((Game.WIDTH - 50) - 50) + 1 )+ 50, r.nextInt(((Game.HEIGHT - 50) - 50) + 1 )+ 50, 15, 1, ID.EnemySweep, handler));
				} else if (sweepTemp == 1) {
					handler.addObject(
							new EnemySweep(r.nextInt(((Game.WIDTH - 50) - 50) + 1 )+ 50, r.nextInt(((Game.HEIGHT - 50) - 50) + 1 )+ 50, 15, -1, ID.EnemySweep, handler));
				} else if (sweepTemp == 2) {
					handler.addObject(
							new EnemySweep(r.nextInt(((Game.WIDTH - 50) - 50) + 1 )+ 50, r.nextInt(((Game.HEIGHT - 50) - 50) + 1 )+ 50, 15, 3, ID.EnemySweep, handler));
				} else if (sweepTemp == 3) {
					handler.addObject(
							new EnemySweep(r.nextInt(((Game.WIDTH - 50) - 50) + 1 )+ 50, r.nextInt(((Game.HEIGHT - 50) - 50) + 1 )+ 50, 15, -3, ID.EnemySweep, handler));
				}
				EnemySpawnTimer = 0;

			} else if(EnemySpawnNum == 3) {
				//spawns Smart enemy

				handler.addObject(
						new EnemySmart(r.nextInt(((Game.WIDTH - 50) - 50) + 1 )+ 50, r.nextInt(((Game.HEIGHT - 50) - 50) + 1 )+ 50, -5, ID.EnemySmart, handler));
				EnemySpawnTimer = 0;

			} else if(EnemySpawnNum == 4) {
				//spawns Shooter enemy

				handler.addObject(
						new EnemyShooter(r.nextInt(((Game.WIDTH - 50) - 50) + 1 )+ 50, r.nextInt(((Game.HEIGHT - 50) - 50) + 1 )+ 50, 100, 100,-20, 10, ID.EnemyShooter, this.handler));
				EnemySpawnTimer = 0;
        
			} else if(EnemySpawnNum == 5) {
				//spawns Tracker enemy

				count = 1;
				handler.addObject(
						new EnemyTracker(r.nextInt(((Game.WIDTH - 50) - 50) + 1 )+ 50, r.nextInt(((Game.HEIGHT - 50) - 50) + 1 )+ 50, -5, ID.EnemyTracker, handler, trackerColor, trackerTimer, game));
				EnemySpawnTimer = 0;
			} else if (EnemySpawnNum == 6) {
				//spawns Expansion enemy

				handler.addObject(new EnemyExpand(r.nextInt(((Game.WIDTH - 50) - 50) + 1 )+ 50, r.nextInt(((Game.HEIGHT - 50) - 50) + 1 )+ 50, 100, 100, ID.EnemyExpand, this.handler));
				EnemySpawnTimer = 0;
			} else if (EnemySpawnNum == 7) {
				//spawns Minishooter enemy

				handler.addObject(new EnemyMiniShooter(r.nextInt(((Game.WIDTH - 50) - 50) + 1 )+ 50, r.nextInt(((Game.HEIGHT - 50) - 50) + 1 )+ 50, 75, 75, -10, 50, ID.EnemyMiniShooter, this.handler, this.game));
				handler.addObject(new EnemyMiniShooter(r.nextInt(((Game.WIDTH - 50) - 50) + 1 )+ 50, r.nextInt(((Game.HEIGHT - 50) - 50) + 1 )+ 50, 75, 75, -10, 50, ID.EnemyMiniShooter, this.handler, this.game));
				EnemySpawnTimer = 0;
        
			} else if (EnemySpawnNum == 8) {
				//spawns Porcupine enemy
				
				handler.addObject(new EnemyPorcupine(r.nextInt(((Game.WIDTH - 50) - 50) + 1 )+ 50, r.nextInt(((Game.HEIGHT - 50) - 50) + 1 )+ 50, 100, 100, ID.EnemyPorcupine, this.handler, -1, -2, 10, this.game));
				EnemySpawnTimer = 0;
				
			} else if (EnemySpawnNum == 9) {
				//spawns enemy move left and right
				
				handler.addObject(new EnemyMoveLeft(r.nextInt(((Game.WIDTH - 50) - 50) + 1 )+ 50, r.nextInt(((Game.HEIGHT - 50) - 50) + 1 )+ 50, 75, 75, ID.EnemyMove, this.handler));
				handler.addObject(new EnemyMoveRight(r.nextInt(((Game.WIDTH - 50) - 50) + 1 )+ 50, r.nextInt(((Game.HEIGHT - 50) - 50) + 1 )+ 50, 75, 75, ID.EnemyMove, this.handler));
				EnemySpawnTimer = 0;
				
			} 
		}
		
		if(DropSpawnTimer == 240) {
			
			DropSpawnNum = randDrop();
			
			if (DropSpawnNum == 0) {
				//spawns Health pickup

				handler.addPickup(new PickupHealth(r.nextInt(((Game.WIDTH - 50) - 50) + 1 )+ 50, r.nextInt(((Game.HEIGHT - 50) - 50) + 1 )+ 50, ID.PickupHealth, "images/ham.png", this.handler));
				DropSpawnTimer = 0;
			} else if (DropSpawnNum == 1) {
				//spawns Speed pickup

				handler.addPickup(new PickupSpeed(r.nextInt(((Game.WIDTH - 50) - 50) + 1 )+ 50, r.nextInt(((Game.HEIGHT - 50) - 50) + 1 )+ 50, ID.PickupSpeed, "images/shoesAreForCasuals.png", this.handler));
				DropSpawnTimer = 0;
			} else if (DropSpawnNum == 2) {
				//spawns Score pickup

				handler.addPickup(new PickupScore(r.nextInt(((Game.WIDTH - 50) - 50) + 1 )+ 50, r.nextInt(((Game.HEIGHT - 50) - 50) + 1 )+ 50, ID.PickupScore, "images/coin.png", this.handler));
				DropSpawnTimer = 0;
				
				
			} else if (DropSpawnNum == 3) {
				//spawns Double Points pickup

				handler.addPickup(new PickupDoublePoints(r.nextInt(((Game.WIDTH - 50) - 50) + 1 )+ 50, r.nextInt(((Game.HEIGHT - 50) - 50) + 1 )+ 50, ID.PickupDoublePoints, "images/doublepoints.png", this.handler));
				DropSpawnTimer = 0;

		
			} else if (DropSpawnNum == 4) {
				//spawns Nuke pickup

				handler.addPickup(new PickupNuke(r.nextInt(((Game.WIDTH - 50) - 50) + 1 )+ 50, r.nextInt(((Game.HEIGHT - 50) - 50) + 1 )+ 50, ID.PickupNuke, "images/nuke.png", this.handler));
				DropSpawnTimer = 0;

			} else if (DropSpawnNum == 5) {
				//spawns Shrink pickup
				
				handler.addPickup(new PickupShrink(r.nextInt(((Game.WIDTH - 50) - 50) + 1 )+ 50, r.nextInt(((Game.HEIGHT - 50) - 50) + 1)+ 50, ID.PickupShrink, "images/minimushroom.png", this.handler));
				DropSpawnTimer = 0;
			} else if (DropSpawnNum == 6) {
				//spawns Max Health Increase pickup
				
				handler.addPickup(new PickupHealthIncrease(r.nextInt(((Game.WIDTH - 50) - 50) + 1 )+ 50, r.nextInt(((Game.HEIGHT - 50) - 50) + 1)+ 50, ID.PickupHealthIncrease, "images/heart.png", this.handler));
				DropSpawnTimer = 0;
			}
		}
		
		EnemySpawnTimer++;
		DropSpawnTimer++;

	}

	public int randEnemy() {
		return (int) (Math.random()*(differentEnemies));
	}
	
	public int randDrop() {
		return (int) (Math.random()*(differentDrops));
	}

	public void restart() {
		EnemySpawnTimer = 0;
		handler.object.clear();
		handler.pickups.clear();
		hud.health=100;
		player.resetVel();
		player.resetLoc();
		Player.doublePointsActive = false;
	}
}