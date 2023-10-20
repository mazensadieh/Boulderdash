package main;

import java.awt.*;

// Die UI klasse darstellet den Hintergrund des Spiels.

public class UI {
	public int DefaultTime = 30;
	private final GamePanel gp;

	private Graphics2D g2;
	private final Font serif_40;
	private boolean MessageOn = false;
	private String Message = "";
	private Color MessageColor;
	private int MesssageCounter = 0;
	public int StartTime = 30;
	public int PlayTime = StartTime;
	private int timeCounter = 0;

	public UI(GamePanel gp) {

		this.gp = gp;

		//Font anpassen
		serif_40 = new Font("Serif", Font.BOLD, 40);

	}

	public void draw(Graphics2D g2) {

		this.g2 = g2;

		//Play State aufrufen
		if(gp.gs == GameState.PLAY) {
			drawplayState();
		}

	}


	//Spieler Leben anzeigen
	private void drawPlayerLife() {

		int i = 0;

		//akteulles Leben anzeigen
		while(i < gp.controller.CorrentHP) {

			g2.drawImage(gp.utool.Heart[0], 5, 5, gp.tilesize, gp.tilesize, null);
			i++;
		}
	}

	//obere Bar des Spiel anzeigen.
	private void drawplayState() {

		drawPlayerLife();

		g2.setFont(serif_40);
		g2.setColor(Color.white);

		//Score anzeigen
		String text = "Score ";
		g2.drawString(text + gp.controller.score, 635, 45);
		g2.drawString("X" + gp.controller.CorrentHP, 70, 40);

		//LevelDiamond anzeigen
		DrawToWinDiamond();
		//PlayTime anzeigen
		DrawTimer();

	}

	//Timer-Funktionalität
	private void DrawTimer() {


		g2.drawImage(gp.utool.Timer, 840, 0, gp.tilesize, gp.tilesize, null);
		if(PlayTime > 7){
			g2.setColor(Color.WHITE);

		}else{
			g2.setColor(Color.RED);
		}
		g2.drawString(""+ PlayTime, 900, 45);


		timeCounter++;
		if(timeCounter > 60) {

			timeCounter = 0;
			PlayTime--;
			if(PlayTime == 0) {

				gp.controller.kill("TIME OFF");
			}

		}
	}

	//geforderte Diamanten anzeigen
	private void DrawToWinDiamond() {
		g2.drawImage(gp.utool.Diamond[0], 150, 0, gp.tilesize, gp.tilesize, null);

		int DiamondRemain = ( ( ( gp.controller.AmountOfDiamond * 80 ) / 100 ) - gp.controller.AmountOfTakenDiamond );

		if(!(DiamondRemain > 0)){

			g2.drawString("", 225, 40);

		}else{
			g2.drawString("X" + DiamondRemain, 225, 40);
		}

	}

	//Messages während des Spiels anzeigen
	public void showMessage(String text, Color color) {
		Message = text;
		MessageColor = color;
		MessageOn = true;

		//Message
		g2.setColor(MessageColor);
		g2.setFont(g2.getFont().deriveFont(25F));
		g2.drawString(Message, 10, 200);

		MesssageCounter++;
		if(MesssageCounter > 120) {
			MesssageCounter = 0;
			MessageOn = false;
		}
	}

}
