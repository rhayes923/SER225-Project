package mainGame.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

import mainGame.Game.STATE;
import mainGame.spawn.*;
import mainGame.*;
import mainGame.gfx.*;

/**
 * The main menu
 * 
 * @author Brandon Loehle 5/30/16
 *
 */

public class Menu {

	private Game game;
	private Handler handler;
	private HUD hud;
	private Image img;
	private Image img2;
	private Image raytracing;
	private Image hoffmanpic;
	private int timer;
	private Random r;
	private ArrayList<Color> colorPick = new ArrayList<Color>();
	private int colorIndex;
	private Spawn1to5 spawner;


	public Menu(Game game, Handler handler, HUD hud, Spawn1to5 spawner) {
		this.game = game;
		this.handler = handler;
		this.hud = hud;
		this.spawner = spawner;
		timer = 10;
		r = new Random();
		img = null;
		img2 = null;

		try {
			URL imageURL = Game.class.getResource("images/dust-particles.png");
			img = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			URL imageURL = Game.class.getResource("images/paintbucket.png");
			img2 = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void tick() {
		timer--;
		if (timer <= 0) {
			handler.object.clear();
			colorIndex = r.nextInt(6);
			timer = 300;
		}
		handler.tick();
	}

	public void render(Graphics g) {
		if (game.gameState == STATE.Menu) {
			g.drawImage(img, 0, 0, Game.WIDTH, Game.HEIGHT, null);
			g.drawImage(img2, 850, 450, 100, 100, null);
			handler.render(g);
			
			Button b1 = new Button(50, 150, 350, 100);
			b1.setText("Waves");
			Button b2 = new Button(50, 300, 350, 100);
			b2.setText("Bosses");
			Button b3 = new Button(50, 450, 350, 100);
			b3.setText("Survival");
			Button b4 = new Button(450, 150, 350, 100);
			b4.setText("Credits");
			Button b5 = new Button(450, 300, 350, 100);
			b5.setText("Help");
			Button b6 = new Button(450, 450, 350, 100);
			b6.setText("Quit");
			Button b7 = new Button(850, 150, 350, 100);
			b7.setText("Host");
			Button b8 = new Button(850, 300, 350, 100);
			b8.setText("Join");
			
			Font font = new Font("Amoebic", 1, 80);
			g.setFont(font);
			g.setColor(Color.WHITE);
			g.drawString("Unknown Battleland", 200, 100);
			
			b1.render(g);
			b2.render(g);
			b3.render(g);
			b4.render(g);
			b5.render(g);
			b6.render(g);
			b7.render(g);
			b8.render(g);
		} else if (game.gameState == STATE.Help) {// if the user clicks on
			// "help"
			g.drawImage(img, 0, 0, Game.WIDTH, Game.HEIGHT, null);
			handler.render(g);

			img = null;

			try {
				URL imageURL = Game.class.getResource("images/dust-particles.png");
				img = Toolkit.getDefaultToolkit().getImage(imageURL);
			} catch (Exception e) {
				e.printStackTrace();
			}

			Font font = new Font("impact", 1, 33);
			Font font2 = new Font("impact", 1, 20);

			g.setFont(font);
			g.setColor(Color.WHITE);
			g.drawString("Help", 600, 46);

			g.drawString("To move the player you use either the arrow keys or the WASD keys.", 100, 100);
			g.setFont(font);
			g.drawString("Waves:", 100, 160);
			g.setFont(font2);
			g.drawString(" In this mode, your goal is to avoid enemies in order to advance levels, every 5th level you will encounter a boss, and ",
					100, 190);
			g.drawString(" exactly as the normal levels your goal is to avoid being hit by a boss for a certain period of time to advance a",
					100, 220);
			 g.drawString(" level. Upgrades obtained after completing a boss level can be used by pressing the shift key", 100, 250);
			g.setFont(font);
			g.drawString("Bosses:", 100, 320);
			g.setFont(font2);
			g.drawString(" Within Bosses, your goal is similar to Waves but instead of having bosses every 5th level, every level will be a boss.",
					100, 350);
			g.drawString(" There is an unlimited ammount of bosses so your objective is to obtain the highest score by surviving the longest.", 100, 380);
			g.setFont(font);
			g.drawString("Survival:", 100, 450);
			g.setFont(font2);
			g.drawString(" Within Survival, your goal is to survive as long as possible while enemies spawn and try to kill you. Poewr-ups are available",
					100, 480);
			g.drawString(" to help you stay alive. If you pick up a ham, you regain health, if you pick up shoes, you earn a light speed boost, if you ",
					100, 510);
			g.drawString("pick up a coin, you earn \"add 1000\" points to your score, if you pick up double points, you get double points for a limited ", 
					100, 540);
			g.drawString("time, and if you pick up a nuke, all enemies get cleared off of the screen.",
					100, 570);
			g.drawString("To mute the sound in game press the m key" , 100, 610);
			g.setFont(font2);
			g.setColor(Color.white);
			g.drawRect(566, 590, 133, 42);
			g.drawString("Back", 613, 620);
		}
		//If the user clicks on the credit menu option
		else if (game.gameState == STATE.Credits) {
			
			g.drawImage(img, 0, 0, Game.WIDTH, Game.HEIGHT, null);
			g.drawImage(raytracing, 852, 0, 426 , 360, null);
			g.drawImage(hoffmanpic, 852, 360, 426 , 360, null);
			handler.render(g);

			img = null;
			raytracing = null;
			hoffmanpic = null;

			try {
				URL imageURL = Game.class.getResource("images/dust-particles.png");
				img = Toolkit.getDefaultToolkit().getImage(imageURL);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				URL imageURL = Game.class.getResource("images/drawing.png");
				raytracing = Toolkit.getDefaultToolkit().getImage(imageURL);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				URL imageURL = Game.class.getResource("images/hoffmanpic.jpg");
				hoffmanpic = Toolkit.getDefaultToolkit().getImage(imageURL);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			Font font = new Font("impact", 1, 33);
			Font font2 = new Font("impact", 1, 20);

			g.setFont(font);
			g.setColor(Color.WHITE);
			g.drawString("Credits", 575, 46);

			g.drawString("Team A1 - Can be found spending their hackathon prize money online.", 100, 100);
			g.setFont(font);
			g.drawString("Team Members:", 100, 160);
			g.setFont(font2);
						
			g.drawString("Massimo \"Srum Master\" Angelillo - Huge fan of computer architecture class", 100, 190);
			g.drawString("Can be found at home working on his ray tracing project in scala ---------------------------------->", 100, 220);
						
			g.drawString("Kevin \"Git-Master\" Sangurima - Can't wait for SSBU to come out", 100, 275);
			g.drawString("Can be found in his room trying to learn new programming languages", 100, 305);
						
			g.drawString("Matthew \"The Quite One\" Crawford - Always came in clutch in the last minute", 100, 360);
			g.drawString("Can be found getting a new computer", 100, 390);
						
			g.drawString("Charles \"The Drifting Wind/Team A1's Manager\" Zhu - Best manager a team could have", 100, 445);
			g.drawString("Can be found enjoying his new car in QU's north lot", 100, 475);
						
			g.drawString("Mike Medvedev - Trying to record songs professionally", 100, 530);
			g.drawString("Can be found in his room playing the guitar", 100, 560);
			
			g.drawString("Professor Hoffman - \"I LOVE CHARTS AND TABLES!!\"", 100, 615);
			g.drawString("Thanks to: Matt Chieco, Will Eccles, Julio Argueta, Kyle Gorman, and Eamon Duffy for working ", 100, 640);
			g.drawString("on this project the previous semester", 100, 665);
			
			
			g.setColor(Color.white);
			g.drawRect(566, 650, 133, 42);
			g.drawString("Back", 613, 680);
		}
	}
}
