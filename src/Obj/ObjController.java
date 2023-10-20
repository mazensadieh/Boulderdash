package Obj;


import main.GamePanel;
import main.GameState;

import java.awt.*;

public class ObjController {
	final private int MaxHP = 4;
	public int AmountOfDiamond = 0;
	public int AmountOfTakenDiamond = 0;
	public int score = 0;
	public int HighScore;
	public int defaultScore = 0;
	public int DefaultHP = 3;
	public int CorrentHP = DefaultHP;
	public boolean DoorClosed = true;
	public String ReasonOfKill;
	private int lastgamescore = 0;
	private GamePanel gp;
	private boolean SoundOn = false;

	public ObjController(GamePanel gp) {
		this.gp = gp;
	}

	//Kollisionen der Spielfigur und ihr Verhalten beim Treffen eines den weiteren Objekten
	public void ObjectColision(int i) {
		if(i != 999) {
			String objectName = gp.mapM.obj[ i ].name;
			switch(objectName) {
				//Wenn das OBJ Diamant ist, sammle es
				case "Diamond":
					CollectDiamand(i);
					break;
				//Wenn das OBJ Tür ist, überprüfe ob sie geöffnet ist
				case "Door":
					Door();
					break;
				case "Dirt":
					//Wenn das OBJ Erde ist, spiele die Musik nur einmal ab und trage die Erde ab
					if(!SoundOn) {
						gp.playSE(4);
						SoundOn = true;
					}
					gp.mapM.obj[ i ].remove = true;
					break;
				//Wenn das OBJ Herz ist, sammle es
				case "Heart":
					gp.playSE(6);
					CollectHP(i);
					break;
				//Wenn das OBJ Felsbrocke ist, verschiebe ihn
				case "Rock":
					puchRock(i);
					break;

			}
		}
	}

	// Wird ein neuer Score erreicht, dann überprüfe, ob er als neuer Highscore gespeichert werden soll.
	private void CheckHighScore() {
		if(HighScore < score) {
			HighScore = score;
			gp.saveLoad.highname = gp.saveLoad.name;
			gp.saveLoad.SaveHighScore();
		}
	}

	//Die verbleibende Zeit in einer Score umwandeln
	private void RemainingTimeToScore() {
		if(gp.ui.PlayTime > 0) {
			score = score + ( gp.ui.PlayTime ) * 5;
			gp.ui.PlayTime--;
		}
	}

	//Dirt löschen
	public void dirtremove(Dirt dirt) {
		for(int i = 0 ; i < gp.mapM.obj.length ; i++)
			if(gp.mapM.obj[ i ] != null && gp.mapM.obj[ i ] == dirt) {
				gp.mapM.obj[ i ] = null;
				SoundOn = false;
			}
	}

	//Wenn der Spieler weniger als vier Leben hat, sammlt die Spielfigur ein weiteres und dann wird eine Meldung angezeigt
	private void CollectHP(int i) {
		gp.mapM.obj[ i ] = null;
		if(gp.controller.CorrentHP < gp.controller.MaxHP) {
			gp.controller.CorrentHP++;
			gp.ui.showMessage("You got an HP", Color.red);
		} else
			gp.ui.showMessage("You Have MAX HP", Color.red);
	}

	//Die Spielfigur sammlet ein Diamant und eine Meldung wird angezeigt
	public void CollectDiamand(int i) {
		gp.playSE(7);
		gp.mapM.obj[ i ] = null;
		AmountOfTakenDiamond++;
		score = score + 15;
		gp.ui.showMessage("You got a Diamond", Color.blue);
	}


	private void Door() {
		if(!DoorClosed) {
			//Wenn die Tür geöffnet ist, dann soll der Score aktualisiert werden
			gp.playSE(9);
			RemainingTimeToScore();
			CheckHighScore();
			gp.stopMusic();
			if(gp.mapM.CourrentMap == 9) {
				//Wenn die aktuelle Karte die letzte ist -> END
				gp.gs = GameState.Finish;
			} else {
				//Wenn die aktuelle Karte nicht die letzte ist -> lade die nächste Karte
				gp.mapM.CourrentMap++;
				lastgamescore = score;
				gp.ui.PlayTime = gp.ui.StartTime;
				gp.mapM.load_Entity();
				DoorClosed = true;
				gp.gs = GameState.WIN;
			}
		} else {
			//Ist die Tür nicht geöffnet -> zeige eine entsprechende Meldung
			gp.ui.showMessage("You need " + ( ( ( AmountOfDiamond * 80 ) / 100 ) - AmountOfTakenDiamond ) + " more " + "Diamond", Color.blue);
		}
	}

	public void kill(String reasonOfKill) {
		//In die erste Sekunde ist die Spielfigur unsterblich
		if(gp.ui.PlayTime != gp.ui.StartTime) {
			this.ReasonOfKill = reasonOfKill;
			gp.stopMusic();
			gp.playSE(8);
			//Hat der Spieler Leben noch zur Verfügung -> lade der Level neu
			if(CorrentHP > 1) {
				score = gp.controller.score;
				gp.ui.PlayTime = gp.ui.DefaultTime;
				CorrentHP--;
				gp.gs = GameState.DEAD;
				gp.mapM.load_Entity();
			} else {
				//Hat der Spieler Keine weitere Leben zur Verfügung -> GameOver
				gp.gs = GameState.GAMEOVER;
			}
		}
	}

	private void puchRock(int i) {
		//Überprüfe ob der Felsbrocke verschiebbar ist. Wenn ja, dann verschieben ihr.
		gp.mapM.obj[ i ].collisionOn = false;
		gp.checker.checkTile(gp.mapM.obj[ i ]);
		gp.checker.checkObject(gp.mapM.obj[ i ], false);
		if(gp.mapM.obj[ i ].collisionOn) {
			gp.mapM.obj[ i ].direction = gp.mapM.player.direction;
			gp.mapM.obj[ i ].collisionOn = false;
			gp.checker.checkTile(gp.mapM.obj[ i ]);
			gp.checker.checkObject(gp.mapM.obj[ i ], false);
			if(!gp.mapM.obj[ i ].collisionOn) {
				if(gp.mapM.obj[ i ].direction == "right")
					gp.mapM.obj[ i ].XPosition = gp.mapM.obj[ i ].XPosition + 4;

				if(gp.mapM.obj[ i ].direction == "left")

					gp.mapM.obj[ i ].XPosition = gp.mapM.obj[ i ].XPosition - 4;

			}
			gp.mapM.obj[ i ].direction = "down";
		}
	}

	//Neues Spiel laden
	public void NewGame() {
		gp.ui.PlayTime = gp.ui.DefaultTime;
		score = defaultScore;
		CorrentHP = DefaultHP;
		gp.mapM.CourrentMap = gp.mapM.Firstmap;
		gp.stopMusic();
		gp.mapM.load_Entity();
		gp.playMusic(3);
		gp.gs = GameState.PLAY;

	}

}
