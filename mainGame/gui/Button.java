package mainGame.gui;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Button {
	private Font font;
	private int width;
	private int height;
	private int x;
	private int y;
	private String text;
	private Color background;
	private Color fontColor;
	
	public Button(int _x, int _y, int _w, int _h) {
		width = _w;
		height = _h;
		x = _x;
		y = _y;
		//default settings for buttons
		background = Color.WHITE;
		fontColor = Color.BLACK;
		text = "";
		font = new Font("Amoebic", 1, 80);
	}
	
	//draw the button to the screen
	public void render(Graphics g) {
		g.setColor(background);
		g.fillRect(x, y, width, height);
		g.setFont(font);
		g.setColor(fontColor);
		g.drawString(text, x + 20, y + 80);
	}
	
	//all the setters
	public void setBackgroundColor(Color c) {
		background = c;
	}
	public void setText(String t) {
		text = t;
	}
	public void setFont(Font f) {
		font = f;
	}
	public void setFontColor(Color c) {
		fontColor = c;
	}
	public void setPosition(int _x, int _y) {
		x = _x;
		y = _y;
	}
	public void setDimensions(int w, int h) {
		width = w;
		height = h;
	}
}
