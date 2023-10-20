package MenuState;

import MenuStateEnum.Commandnumm;
import main.GameState;
import main.GamePanel;
import java.awt.*;

public class MainMenu extends Menu {

	public MainMenu(GamePanel gp) {
		super(gp);
		commandnum = Commandnumm.NEW_GAME;
	}

	@Override
	public void draw(Graphics2D g2) {

		this.g2 = g2;

		if(gp.gs == GameState.MAINMENU) {
			drawMainScreen();
		}
	}

	public void Menu_Name(int frameX, int frameY) {

		//Hintergrund zeichnen
		g2.drawImage(gp.utool.HinterGrund, 0, 0, 960, 720, null);

		SetNameAndHighScore();

		//neus Spiel starten
		String text = "New Game";
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));
		int textX = getXforCentredText(text);
		int textY = gp.tilesize * 8;




		//Text Farbe Anpassung beim Auswahl.
		if(commandnum == Commandnumm.NEW_GAME) {
			g2.setColor(Choosed);
			g2.drawString(text, textX, textY);
			PlayTime = StartTime;
			gp.controller.score = 0;
		} else {
			g2.setColor(NotChoosed);
			g2.drawString(text, textX, textY);
		}

		//ein gespeichertes Spiel zurück zu setzen
		text = "Load Game";
		textX = getXforCentredText(text);
		textY += gp.tilesize;
		g2.drawString(text, textX, textY);

		if(commandnum == Commandnumm.LOAD_GAME) {
			g2.setColor(Choosed);
			g2.drawString(text, textX, textY);
		} else {
			g2.setColor(NotChoosed);
			g2.drawString(text, textX, textY);
		}

		// Spiel verlassen
		text = "Quit";
		textX = getXforCentredText(text);
		textY += gp.tilesize*2;
		g2.drawString(text, textX, textY);

		if(commandnum == Commandnumm.QUIT) {
			g2.setColor(Choosed);
			g2.drawString(text, textX, textY);
		} else {
			g2.setColor(NotChoosed);
			g2.drawString(text, textX, textY);
		}
	}

	private void SetNameAndHighScore() {

		g2.setColor(new Color(240,182,118));
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 60F));
		String Title = "Top Score";
		String TextDefault = "";
		String PlayerName = gp.saveLoad.highname;
		String TextToPrint;

		//Name und HighScore wird als leeres Feld gezeigt beim ersten Ablauf des Spiels.
		if(gp.controller.HighScore == 0){
			TextToPrint = TextDefault;
		}else{
			TextToPrint = PlayerName + " " + gp.controller.HighScore;
		}
		int textX = getXforCentredText(TextToPrint);
		int textY = gp.tilesize * 6;
		g2.drawString(TextToPrint, textX, textY-25);

		//Top Score Title anzeigen
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40F));
		textX = getXforCentredText(Title);
		textY = gp.tilesize * 5;
		g2.drawString(Title, textX, textY-25);

	}
}
