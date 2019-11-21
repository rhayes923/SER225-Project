package mainGame.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

import mainGame.*;

/**
 * After completing a boss, this screen appears. The upgrade stays effective the
 * rest of the game. A user cannot choose the same upgrade twice
 * 
 * @author Brandon Loehle 5/30/16
 *
 */

public class UpgradeScreen {

	private Game game;
	private Handler handler;
	private HUD hud;
	private String text;
	private String[] paths = { "images/clearscreenability.png", "images/decreaseplayersize.png", "images/extralife.png",
		"images/healthincrease.png", "images/healthregeneration.png", "images/improveddamageresistance.png",
		"images/levelskipability.png", "images/freezetimeability.png", "images/speedboost.png" }; //array of the different upgrade images
																									//the different images are linked to the different effects of the upgrades
	private ArrayList<String> imagePaths = new ArrayList<String>();
	private Random r = new Random();

	public UpgradeScreen(Game game, Handler handler, HUD hud) {
		this.game = game;
		this.handler = handler;
		this.hud = hud;
		addPaths();
		text = "";
	}

	public void tick() {

	}

	public void render(Graphics g) { //renders the actual buttons and images for the upgrades on the upgrade screen
		Font font = new Font("Amoebic", 1, 116);
		text = "Select an Upgrade!";
		g.setFont(font);
		g.setColor(Color.WHITE);
		g.drawString(text, Game.WIDTH / 2 - getTextWidth(font, text) / 2, 133);

		g.drawImage(getImage(imagePaths.get(0)), 210, 200, 860, 150, null);
		g.drawImage(getImage(imagePaths.get(1)), 210, 200 + 150, 860, 150, null);
		g.drawImage(getImage(imagePaths.get(2)), 210, 200 + 2 * 150, 860, 150, null);
	}

	/*
	 * Reset the paths to each picture
	 */
	public void resetPaths() { //randomizes the upgrades in the array so that the same ones are not used in the same order every time
		for (int i = 0; i < paths.length; i++) {
			int random = r.nextInt(paths.length);
			String temp = paths[i];
			paths[i] = paths[random];
			paths[random] = temp;
		}
	}

	public void addPaths() { //adds more upgrades and their paths to the array when all the upgrades are used up
								//this is not really used right now as we only get to the upgrade screen twice a game, and we have 9 upgrades
									//adds the new upgrade to the upgrade screen as the upgrades are used
		for (int i = 0; i < paths.length; i++) {
			imagePaths.add(paths[i]);
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

	public int getTextWidth(Font font, String text) {
		AffineTransform at = new AffineTransform();
		FontRenderContext frc = new FontRenderContext(at, true, true);
		int textWidth = (int) (font.getStringBounds(text, frc).getWidth());
		return textWidth;
	}
	
	public String getPath(int x) {
		return imagePaths.remove(x);
	}

	public void removeUpgradeOption(int x) { //removes the upgrade option after it is selected
		imagePaths.remove(x);
	}

	public void mousePressed(MouseEvent e) {

	}

	public void mouseReleased(MouseEvent e) {

	}
	
	public void resetUpgradeScreen() {
		this.resetPaths();
		this.addPaths();
	}

}