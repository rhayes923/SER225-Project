package mainGame.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import mainGame.Game.STATE;
import mainGame.*;

/**
 * The main Heads Up Display of the game
 * 
 * @author Brandon Loehle 5/30/16
 *
 */

public class HUD {
	
	public double health;
	private double healthMax;
	private double greenValue;
	private int score;
	private int level;
	private boolean regen;
	private int timer;
	public static int doublePointsTimer;
	private int healthBarWidth;
	private int healthBarModifier;
	private boolean doubleHealth;
	private String ability;
	private int abilityUses;
	private Color scoreColor;
	private int extraLives;
	public Game game;
	private Handler handler;
	private int levelTimer;
	
	public HUD(Game game, Handler handler) {
		this.game = game;
		health = 100;
		healthMax = 100;
		greenValue = 255;
		score = 00000000000;
		level = 0;
		regen = false;
		timer = 60;
		doublePointsTimer = 1000;
		healthBarWidth = 200;
		healthBarModifier = 2;
		doubleHealth = false;
		ability = "";
		abilityUses = 0;
		scoreColor = Color.white;
		extraLives = 0;
		levelTimer = 22;
	}
	public void tick() {
		health = Game.clamp(health, 0, health);

		greenValue = health * healthBarModifier;

		greenValue = Game.clamp(greenValue, 0, 255);

		if (!Player.doublePointsActive) {
			score++;
		} else {
			score = score + 2;
			doublePointsTimer--;
			if (doublePointsTimer == 0) {
				Player.doublePointsActive = false;
				doublePointsTimer = 1000;
			}
		}

		if (regen) {// regenerates health if that ability has been unlocked
			timer--;
			if (timer == 0 && health < healthMax) {
				health += 1;
				timer = 60;
			}
		}
	}

	public void render(Graphics g) {
		Font font = new Font("Amoebic", 1, 20);

		g.setColor(Color.GRAY);
		g.fillRect(15, 15, healthBarWidth, 64);
		g.setColor(new Color(75, (int) greenValue, 0));
		g.fillRect(15, 15, (int) health * 2, 64);
		g.setColor(scoreColor);
		g.drawRect(15, 15, healthBarWidth, 64);

		g.setFont(font);

		if(level != 101 && game.getGameState() == STATE.Wave)
			g.drawString("Time left: " + levelTimer, 15, 215);
		
		if (!Player.doublePointsActive) {
			g.drawString("Score: " + score, 15, 115);
		} else {
			g.drawString("Score: " + score + " [2X POINTS] " + doublePointsTimer, 15, 115);
		}
		if(game.getGameState() == STATE.Wave || game.getGameState() == STATE.Bosses) {
			if(level != 101) {
			g.drawString("Level: " + level, 15, 150);
			} else if (level == 101) { 
				g.drawString("Level: Boss", 15, 150);
			}
		}
		if(game.getGameState() == STATE.Wave)
			g.drawString("Extra Lives: " + extraLives, 15, 185);
		
		if (ability.equals("freezeTime")) {
			g.drawString("Time Freezes: " + abilityUses, Game.WIDTH - 300, 64);
		} else if (ability.equals("clearScreen")) {
			g.drawString("Screen Clears: " + abilityUses, Game.WIDTH - 300, 64);
		} else if (ability.equals("levelSkip")) {
			g.drawString("Level Skips: " + abilityUses, Game.WIDTH - 300, 64);
		}
	}

	public void setAbility(String ability) {
		this.ability = ability;
	}

	public int getAbilityUses() {
		return this.abilityUses;
	}

	public void setAbilityUses(int abilityUses) {
		this.abilityUses = abilityUses;
	}

	public void updateScoreColor(Color color) {
		this.scoreColor = color;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getScore() {
		return score;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	
	public void setLevelTimer(int levelTimer) {
		this.levelTimer = levelTimer;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public void setRegen() {
		regen = true;
	}

	public void resetRegen() {
		regen = false;
	}

	public void setExtraLives(int lives) {
		this.extraLives = lives;
	}

	public int getExtraLives() {
		return this.extraLives;
	}

	public void healthIncrease() {
		doubleHealth = true;
		if(healthMax < 200)
			healthMax = 200;
		else
			healthMax = 250;
		this.health = healthMax;
		healthBarModifier = 1;
		healthBarWidth = 400;
	}

	public void resetHealth() {
		doubleHealth = false;
		healthMax = 100;
		this.health = healthMax;
		healthBarModifier = 2;
		healthBarWidth = 200;
	}

	public void restoreHealth() {
		this.health = healthMax;
	}
	public void increaseMaxHealth(int newHealth) {
		healthMax += newHealth;
		healthBarWidth = (int) healthMax * 2;
		if(healthMax >= 200)
			healthBarModifier = 1;
	}
	public double getHealthMax() {
		return healthMax;
	}
	public void clearUpgrades() {
		ability = "";
	}
	public String getAbility() {
		return ability;
	}
}