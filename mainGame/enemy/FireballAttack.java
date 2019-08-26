package mainGame.enemy;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.net.URL;
import java.util.Random;

import mainGame.Game;
import mainGame.GameObject;
import mainGame.Handler;
import mainGame.ID;
import mainGame.Player;

public class FireballAttack extends GameObject {
		
		private Handler handler;
		Random r = new Random();
		private Image img;
		private double ang;

		public FireballAttack(double x, double y, ID id, Handler handler, Player player, double vel) {
			super(x, y, id);
			this.handler = handler;
			
			//returns the angle between the boss and the player (angle is between PI/2 and -PI/2)
			this.ang = java.lang.Math.atan((this.y - player.y)/(this.x - player.x));
			
			//sets the x and y velocities as components of the velocity the fireball should have
			//keeps velocity consistent in all directions
			if (player.x > this.x) {
				velX = vel * java.lang.Math.cos(ang);
				velY = vel * java.lang.Math.sin(ang);
			}
			if (player.x < this.x) {
				velX = -vel * java.lang.Math.cos(ang);
				velY = -vel * java.lang.Math.sin(ang);
			}
			img = getImage("images/fireball.gif");
		}
		
		@Override
		public void tick() {
			this.x += velX;
			this.y += velY;
			// remove the bullet if it's off the screen
			if (this.x <= -4 || this.x >= 1280 || this.y <= -4 || this.y >= 720)
				handler.removeObject(this);
		}
		
		public Image getImage(String path) {
			Image image = null;
			try {
				URL imageURL = Game.class.getResource(path);
				image = Toolkit.getDefaultToolkit().getImage(imageURL);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			
			return image;
		}
		
		@Override
		public void render(Graphics g) {
			g.drawImage(img, (int) this.x, (int) this.y, 30, 75, null);
		}
		
		@Override
		public Rectangle getBounds() {
			return new Rectangle((int) this.x, (int) this.y, 30, 75);
		}
		
		//public static double abs(double a);

	}
