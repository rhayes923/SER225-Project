package mainGame.enemy;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.net.URL;
import mainGame.*;

/**
 * A type of enemy in the game
 * 
 * @author Eamon Duffy 11/1/17
 *
 */

public class RollBoss2 extends GameObject {

	private Handler handler;
	private Image img;
	private float alpha = 0;
	private double life = 0.005;

	public RollBoss2(double x, double y, int velX, int velY, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		this.velX = velX;
		this.velY = velY;
		img = getImage("images/Angry-Balls2.png");
		this.health = 2000;
	}

	@Override
	public void tick() {
		if (alpha < 0.995) // this handles the boss fading in to the game
			alpha += life + 0.001;
		else {
			this.x += velX;
			this.y += velY;
			this.health -= 1;
		
			if (this.y <= -100 || this.y >= Game.HEIGHT - 200)
				velY *= -1;
			if (this.x <= -100 || this.x >= Game.WIDTH - 200)
				velX *= -1;
		}
	}

	@Override
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setComposite(makeTransparent(alpha));
		g.drawImage(img, (int) this.x, (int) this.y, 300, 300, null);
		g2d.setComposite(makeTransparent(1));
		
		// HEALTH BAR
				g.setColor(Color.GRAY);
				g.fillRect(Game.WIDTH / 2 - 500, Game.HEIGHT - 150, 1000, 50);
				g.setColor(Color.RED);
				g.fillRect(Game.WIDTH / 2 - 500, Game.HEIGHT - 150, this.health/2, 50);
				g.setColor(Color.WHITE);
				g.drawRect(Game.WIDTH / 2 - 500, Game.HEIGHT - 150, 1000, 50);
	}
	
	private AlphaComposite makeTransparent(float alpha) {
		int type = AlphaComposite.SRC_OVER;
		return (AlphaComposite.getInstance(type, alpha));
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) this.x, (int) this.y, 250, 250);
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

}
