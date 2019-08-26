package mainGame.enemy;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.image.ImageObserver;
import java.net.URL;
import java.util.Random;
import mainGame.*;

/**
 * @author Kyle Gorman 11/3/17
 *
 */

public class BossSeparates extends GameObject {
	
	private Handler handler;
	private int fireTimer = 20;
	private Player player;
	Random r = new Random();
	private Image img;
	private int size;
	
	private boolean onPath = false;
	private int target_x = 0;
	private int target_y = 0;
	private double angle;
	private double speed = 10;
	
	public BossSeparates(double x, double y, ID id, Handler handler, Player player, int size, int health, int velX, int velY) {
		super(x, y, id);
		this.handler = handler;
		this.velX = velX;
		this.velY = velY;
		img = getImage("images/finalBossGIF.gif");
		this.health = health;
		this.size = size;
		this.player = player;
	}

	@Override
	public void tick() {
		this.health -= 1;
		attackPlayer();
	}
	public boolean isDead() {
		if(this.health < 0) {
			return true;
		} else {
			return false;
		}
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
		g.drawImage(img, (int) this.x, (int) this.y, size, size, null);
		// HEALTH BAR
		g.setColor(Color.GRAY);
		g.fillRect(Game.WIDTH / 2 - 500, Game.HEIGHT - 150, 1000, 50);
		g.setColor(Color.RED);
		g.fillRect(Game.WIDTH / 2 - 500, Game.HEIGHT - 150, this.health/2, 50);
		g.setColor(Color.WHITE);
		g.drawRect(Game.WIDTH / 2 - 500, Game.HEIGHT - 150, 1000, 50);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) this.x, (int) this.y, size, size);
	}
	
	private boolean clampPosition(double _x, double _y, double error) {
		if(this.x > (target_x + error) || this.x < (target_x - error)) return false;
		if(this.y > (target_y + error) || this.y < (target_y - error)) return false;
		return true;
	}
	
	public void attackPlayer() {
		if(!onPath) {
			onPath = true;
			target_x = (int) (Math.random()*(Game.WIDTH - size));
			target_y = (int) (Math.random()*(Game.HEIGHT - size));
			double dist_x = target_x - this.x;
			double dist_y = target_y - this.y;
			angle = Math.atan(dist_y/dist_x);
			double dist = Math.sqrt(dist_x*dist_x + dist_y*dist_y);
			this.velX = speed*dist_x/dist;
			this.velY = speed*dist_y/dist;
		}
		this.x += this.velX;
		this.y += this.velY;
		if(clampPosition(target_x, target_y, 5)) onPath = false;
		
		//decrements fireTimer once every tick
		if (fireTimer >= 0) fireTimer--;
		//shoots a fire ball with 15 velocity when fireTimer is at 10 or 0, and sets fireTimer back to 20
		if (fireTimer <= 0 || fireTimer == 10) {
			handler.addObject(new FireballAttack(this.x, this.y, ID.FireballAttack, handler, player, 15));
			if (fireTimer <= 0 ) fireTimer = 20;
		}
		//shoots a fire ball with 20 velocity when fireTimer is at 15 or 5
		if (fireTimer == 5 || fireTimer == 15) {
			handler.addObject(new FireballAttack(this.x, this.y, ID.FireballAttack, handler, player, 20));
		}
	}
}
