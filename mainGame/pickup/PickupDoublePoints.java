package mainGame.pickup;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.net.URL;
import mainGame.*;

/**
 * Doubles the player's points for a limited amount of time
 * @author Ryan Hayes
 * 10/9/19
 *
 */

public class PickupDoublePoints extends Pickup {

	private Image img;
	
	public PickupDoublePoints(double x, double y, ID id, String path) {
		super(x, y, id, path);
		
		img = null;
		try {
			URL imageURL = Game.class.getResource("images/doublepoints.png");
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
		g.drawImage(this.img, (int)this.x, (int)this.y, 48, 48, null);
		
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) this.x, (int) this.y, 48, 48);
	}
}
