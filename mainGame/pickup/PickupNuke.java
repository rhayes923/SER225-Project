package mainGame.pickup;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.net.URL;
import mainGame.*;

/**
 * Kills all enemies currently spawned
 * @author Ryan Hayes
 * 10/9/19
 *
 */

public class PickupNuke extends Pickup {

	private Handler handler;
	private Image img;
	
	public PickupNuke(double x, double y, ID id, String path, Handler handler) {
		super(x, y, id, path);
		this.handler = handler;
		
		img = null;
		try {
			URL imageURL = Game.class.getResource("images/nuke.png");
			img = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(this.img, (int)this.x, (int)this.y, 96, 48, null);
		
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) this.x, (int) this.y, 96, 48);
	}
}
