package MenuState;

import MenuStateEnum.Commandnumm;
import main.GamePanel;
import main.GameState;

import java.awt.*;

public class Pause extends Menu {

	public Pause(GamePanel gp) {
		super(gp);
	}

	@Override
	public void draw(Graphics2D g2) {

		this.g2 = g2;

		//Pause State
		if(gp.gs == GameState.PAUSE) {
			drawMainScreen();
		}
	}

	@Override
	public void Menu_Name(int frameX, int frameY) {

		int textX, textY;

		//Title des Menus
		String text1 = "Pause Menu";
		textX = getXforCentredText(text1);
		textY = frameY + gp.tilesize;
		g2.drawString(text1, textX, textY);


		//Fortsetzen
		String text2 = "Continue";
		textY += gp.tilesize * 2;
		textX = getXforCentredText(text2);

		if(commandnum == Commandnumm.CONTINUE) {
			g2.setColor(Choosed);
			g2.drawString(text2, textX, textY);
		} else {
			g2.setColor(NotChoosed);
			g2.drawString(text2, textX, textY);
		}

		//Speichern
		String text4 = "Save & Load";
		textX = getXforCentredText(text4);
		textY += gp.tilesize;

		if(commandnum == Commandnumm.SAVE) {
			g2.setColor(Choosed);
			g2.drawString(text4, textX, textY);
		} else {
			g2.setColor(NotChoosed);
			g2.drawString(text4, textX, textY);
		}

		//zurück zum Haupt Menu
		String text3 = "Main Menu";
		textY += gp.tilesize * 2;
		textX = getXforCentredText(text3);

		if(commandnum == Commandnumm.Main_Menu) {
			g2.setColor(Choosed);
			g2.drawString(text3, textX, textY);
		} else {
			g2.setColor(NotChoosed);
			g2.drawString(text3, textX, textY);
		}

	}

}
