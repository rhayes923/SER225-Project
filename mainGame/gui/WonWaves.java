package mainGame.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;

import mainGame.Game;
import mainGame.Handler;

public class WonWaves {
	private Handler handler;
	private HUD hud;
	private Color color;
	private String text;
	private String backText;

	public WonWaves(Handler handler, HUD hud) {
		this.handler = handler;
		this.hud = hud;
		this.color = Color.white;
		backText = "Click anywhere to return to the menu";
	}

	public void tick() {
		handler.clearPlayer();
	}

	public void render(Graphics g) {
		Font font = new Font("Amoebic", 1, 100);
		Font font2 = new Font("Amoebic", 1, 60);

		g.setFont(font);
		g.setColor(this.color);

		text = "You Won Waves Mode!";
		g.drawString(text, Game.WIDTH / 2 - getTextWidth(font, text) / 2, Game.HEIGHT / 2 - 150);
		g.setFont(font2);
		text = "Score: " + hud.getScore();
		g.drawString(text, Game.WIDTH / 2 - getTextWidth(font2, text) / 2, Game.HEIGHT / 2 - 50);
		g.setFont(font2);
		g.drawString(backText, Game.WIDTH / 2 - getTextWidth(font2, backText) / 2, Game.HEIGHT / 2 + 150);

	}

	/**
	 * Function for getting the pixel width of text
	 * 
	 * @param font
	 *            the Font of the test
	 * @param text
	 *            the String of text
	 * @return width in pixels of text
	 */
	public static int getTextWidth(Font font, String text) {
		AffineTransform at = new AffineTransform();
		FontRenderContext frc = new FontRenderContext(at, true, true);
		int textWidth = (int) (font.getStringBounds(text, frc).getWidth());
		return textWidth;
	}
}
