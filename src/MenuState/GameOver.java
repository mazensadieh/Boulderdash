package MenuState;

import MenuStateEnum.Commandnumm;
import main.GamePanel;
import main.GameState;

import java.awt.*;

public class GameOver extends Menu {

	public GameOver(GamePanel gp) {
		super(gp);
		commandnum = Commandnumm.Main_Menu;
	}

	@Override
	public void draw(Graphics2D g2) {

		this.g2 = g2;

		//Game Over State
		if(gp.gs == GameState.GAMEOVER) {
			drawMainScreen();
		}
	}

	@Override
	public void Menu_Name(int frameX, int frameY) {

		int textX, textY;

		//Title des Menus
		String text1 = "Game Over";
		textX = getXforCentredText(text1);
		textY = frameY + gp.tilesize * 2;
		g2.drawString(text1, textX, textY);

		//aktueller Score zu zeigen.
		String text2 = "Your Score is  " + gp.controller.score;
		textX = getXforCentredText(text1) - 50;
		textY += gp.tilesize;
		g2.drawString(text2, textX, textY);

		//zurück zum Haupt Menu
		String text3 = "Main Menu";
		textX += 50;
		textY += gp.tilesize * 2;

		if(commandnum == Commandnumm.Main_Menu) {
			g2.setColor(Choosed);
			g2.drawString(text3, textX, textY);
		} else {
			g2.setColor(NotChoosed);
			g2.drawString(text3, textX, textY);
		}

		//neues Spiel starten
		String text4 = "new Game";
		textX += 10;
		textY += gp.tilesize;

		if(commandnum == Commandnumm.NEW_GAME) {
			g2.setColor(Choosed);
			g2.drawString(text4, textX, textY);
		} else {
			g2.setColor(NotChoosed);
			g2.drawString(text4, textX, textY);
		}
	}

	@Override
	public void drawSubWindow(int x, int y, int width, int height) {
		Color c1 = new Color(49, 74, 102,228);
		g2.setColor(c1);
		g2.fillRoundRect(x, y + 70, width, height - 225, 35, 35);
		Color c = new Color(250, 131, 12);
		g2.setColor(c);
		g2.setStroke(new BasicStroke(5));
		g2.drawRoundRect(x + 3, y + 73, width - 5, height - 230, 25, 25);

	}

}
