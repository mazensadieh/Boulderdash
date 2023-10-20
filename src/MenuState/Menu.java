package MenuState;

import MenuStateEnum.Commandnumm;
import main.GamePanel;

import java.awt.*;

public class Menu {
	public Graphics2D g2;
	public GamePanel gp;
	public int StartTime = 10;
	public int PlayTime = StartTime;

	public Color Choosed = new Color(66, 222, 222);
	public Color NotChoosed = new Color(240,182,118);

	public Commandnumm commandnum;

	public Menu(GamePanel gp) {
		this.gp = gp;
	}

	public void draw(Graphics2D g2) {
		this.g2 = g2;
	}

	public void drawMainScreen() {

		SetFont();

		g2.setFont(g2.getFont().deriveFont(32F));

		// SUB WINDOW //
		int frameX = gp.tilesize * 4;
		int frameY = gp.tilesize;
		int frameWidth = gp.tilesize * 8;
		int frameHeight = gp.tilesize * 10;
		drawSubWindow(frameX, frameY, frameWidth, frameHeight);

			Menu_Name(frameX, frameY);

		gp.keyh.enter = false;
	}

	public void Menu_Name(int frameX, int frameY) {}


	//MenuWindow Anpassen
	public void drawSubWindow(int x, int y, int width, int height) {
		Color c1 = new Color(49, 74, 102,228);
		g2.setColor(c1);
		g2.fillRoundRect(x, y, width, height, 35, 35);
		Color c = new Color(250, 131, 12);
		g2.setColor(c);
		g2.setStroke(new BasicStroke(5));
		g2.drawRoundRect(x + 3, y + 3, width - 5, height - 5, 25, 25);

	}

	public int getXforCentredText(String text) {
		int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		return gp.screenwidth / 2 - length / 2;
	}

	public void SetFont(){

		int size = 35;
		Font serif_40 = new Font("Serif", Font.BOLD, size);

		g2.setFont(serif_40);

	}





}
