package mainGame.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import mainGame.Game.STATE;
import mainGame.*;

/**
 * The main menu
 * 
 * @author Brandon Loehle 5/30/16
 *
 */

public class ColorPickerScreen {

	private Game game;
	private int x,y;
	private Image img;
	private Image ship = Toolkit.getDefaultToolkit().getImage(Game.class.getResource("images/ship.png"));
	private Image purpleShip = Toolkit.getDefaultToolkit().getImage(Game.class.getResource("images/purpleShip.png"));
	private Image greenShip = Toolkit.getDefaultToolkit().getImage(Game.class.getResource("images/greenShip.png"));
	private Image orangeShip = Toolkit.getDefaultToolkit().getImage(Game.class.getResource("images/orangeShip1.png"));
	private Image redGrayShip = Toolkit.getDefaultToolkit().getImage(Game.class.getResource("images/redGrayShip.png"));

	public ColorPickerScreen(Player player, Game game) {
		this.game = game;
		x = 0;
		y = 0;
		img = null;
//		try {
//			URL imageURL = Game.class.getResource("images/colorpickerbackground.jpg");
//			img = Toolkit.getDefaultToolkit().getImage(imageURL);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}
	
	public void render(Graphics g) {
		if(game.gameState == STATE.Color) {
			g.drawImage(img, 0, 0, Game.WIDTH, Game.HEIGHT, null);
			
			Font font = new Font("Amoebic", 1, 80);
			g.setFont(font);
			g.setColor(Color.gray);
			g.drawString("Character", 450, 75);
			g.drawString("Tail Color", 450, 425);
			
			//Prints the images for the character in the character/color picker menu
			g.drawImage(ship, x+70, y+120, 125, 125, null);			
			g.drawImage(purpleShip, x+320, y+120, 125, 125, null);					
			g.drawImage(orangeShip, x+570, y+120, 125, 125, null);			
			g.drawImage(redGrayShip, x+820, y+120, 125, 125, null);			
			g.drawImage(greenShip, x+1070, y+120, 125, 125, null);			
			
			//Prints the ovals and for the tail color picker in the character/color picker menu
			g.setColor(Color.white);
			g.drawOval(x+70, y+440, 125, 125);
			g.fillOval(x+70, y+440, 125, 125);
			g.setColor(Color.blue);
			g.drawOval(x+320, y+440, 125, 125);
			g.fillOval(x+320, y+440, 125, 125);
			g.setColor(Color.yellow);
			g.drawOval(x+570, y+440, 125, 125);
			g.fillOval(x+570, y+440, 125, 125);
			g.setColor(Color.cyan);
			g.drawOval(x+820, y+440, 125, 125);
			g.fillOval(x+820, y+440, 125, 125);
			g.setColor(Color.gray);
			g.drawOval(x+1070, y+440, 125, 125);
			g.fillOval(x+1070, y+440, 125, 125);
			g.setColor(Color.green);
			g.drawOval(x+70, y+585, 125, 125);
			g.fillOval(x+70, y+585, 125, 125);
			g.setColor(Color.magenta);
			g.drawOval(x+320, y+585, 125, 125);
			g.fillOval(x+320, y+585, 125, 125);
			g.setColor(Color.orange);
			g.drawOval(x+570, y+585, 125, 125);
			g.fillOval(x+570, y+585, 125, 125);
			g.setColor(Color.pink);
			g.drawOval(x+820, y+585, 125, 125);
			g.fillOval(x+820, y+585, 125, 125);
			g.setColor(Color.red);
			g.drawOval(x+1070, y+585, 125, 125);
			g.fillOval(x+1070, y+585, 125, 125);
			
			
			Font font2 = new Font("Amoebic", 1, 50);
			g.setColor(Color.gray);
			g.drawRect(1000, 10, 250, 60);
			g.setFont(font2);
			g.setColor(Color.gray);
			g.drawString("Menu", 1065, 55);
		}
	}	
}

