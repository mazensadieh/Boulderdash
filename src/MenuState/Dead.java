package MenuState;

import MenuStateEnum.Commandnumm;
import main.GamePanel;
import main.GameState;

import java.awt.*;

public class Dead extends Menu {
	public Dead(GamePanel gp) {
		super(gp);
		commandnum = Commandnumm.Main_Menu;
	}

	@Override
	public void draw(Graphics2D g2) {

		this.g2 = g2;

		//Dead State
		if(gp.gs == GameState.DEAD) {
			drawMainScreen();
		}
	}

	@Override
	public void Menu_Name(int frameX, int frameY) {

		int textX, textY;

		//Title des Menus
		String text1 = "you lost a Life";
		textX = getXforCentredText(text1);
		textY = frameY + gp.tilesize * 2;
		g2.drawString(text1, textX, textY);

		//Todesursache zeigen
		text1 = gp.controller.ReasonOfKill;
		textX = getXforCentredText(text1);
		textY += gp.tilesize ;
		g2.drawString(text1, textX, textY);

		//Fortsetzen
		String text2 = "Continue";
		textX = getXforCentredText(text2);
		textY += gp.tilesize * 2;
		g2.drawString(text2, textX, textY);
		if(commandnum == Commandnumm.RETRY) {
			g2.setColor(Choosed);
			g2.drawString(text2, textX, textY);
		} else {
			g2.setColor(NotChoosed);
			g2.drawString(text2, textX, textY);
		}

		//neues Spiel starten
		String text3 = "New Game";
		textX = getXforCentredText(text3);
		textY += gp.tilesize;

		if(commandnum == Commandnumm.NEW_GAME) {
			g2.setColor(Choosed);
			g2.drawString(text3, textX, textY);
		} else {
			g2.setColor(NotChoosed);
			g2.drawString(text3, textX, textY);
		}

		//Spiel verlassen
		String text4 = "Quit";
		textX = getXforCentredText(text4);
		textY += gp.tilesize;

		if(commandnum == Commandnumm.QUIT) {
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
