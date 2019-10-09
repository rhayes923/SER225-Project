package mainGame.pickup;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.net.URL;
import mainGame.*;

import mainGame.ID;

/**
 * Doubles the player's points for X amount of time
 * @author Ryan Hayes
 * 10/9/19
 *
 */

public class PickupDoublePoints extends Pickup {

	private Handler handler;
	private Image img;
	
	public PickupDoublePoints(double x, double y, ID id, String path, Handler handler) {
		super(x, y, id, path);
		this.handler = handler;
		
		img = null;
		try {
			URL imageURL = Game.class.getResource("");
			img = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return null;
	}

}
