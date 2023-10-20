package SaveLoad;


import main.GamePanel;

import java.io.*;

public class SaveLoad {
	public String name;
	public String highname;
	private GamePanel gp;


	public SaveLoad(GamePanel gp) {
		this.gp = gp;
	}

	//Spielstand speichern
	public void save(int SaveIndex) {
		try {
			//eine neue Datei erstellen
			ObjectOutputStream oos =
					new ObjectOutputStream(new FileOutputStream(new File("save" + SaveIndex + ".dat")));
			DataStorage ds = new DataStorage();
			//Spielstand zum Speichern vorbereiten
			ds.AmountOfTakenDiamond = gp.controller.AmountOfTakenDiamond;
			ds.AmountOfDiamond = gp.controller.AmountOfDiamond;
			ds.name = name;
			ds.Map = gp.mapM.CourrentMap;
			ds.Health = gp.controller.CorrentHP;
			ds.Score = gp.controller.score;
			ds.Time = gp.ui.PlayTime;
			ds.Character = gp.utool.choose;
			//Stand der Spielfigur zum Speichern vorbereiten
			ds.playerX = gp.mapM.player.XPosition;
			ds.playerY = gp.mapM.player.YPosition;
			//Stand des MonsterA zum Speichern vorbereiten
			for(int i = 0 ; i < gp.mapM.monsterA.length ; i++) {
				if(gp.mapM.monsterA[ i ] != null) {
					ds.monsterAX[ i ] = gp.mapM.monsterA[ i ].XPosition;
					ds.monsterAY[ i ] = gp.mapM.monsterA[ i ].YPosition;
				}
			}
			//Stand des MonsterB zum Speichern vorbereiten
			for(int i = 0 ; i < gp.mapM.monsterB.length ; i++) {
				if(gp.mapM.monsterB[ i ] != null) {
					ds.monsterBX[ i ] = gp.mapM.monsterB[ i ].XPosition;
					ds.monsterBY[ i ] = gp.mapM.monsterB[ i ].YPosition;
				}
			}
			//Stand der Objekte zum Speichern vorbereiten
			for(int i = 0 ; i < gp.mapM.obj.length ; i++) {
				if(gp.mapM.obj[ i ] != null) {
					ds.ObjX[ i ] = gp.mapM.obj[ i ].XPosition;
					ds.ObjY[ i ] = gp.mapM.obj[ i ].YPosition;
				}
			}

			//Der Spielstand in die erstellte Datei speichern
			oos.writeObject(ds);
			System.out.print("Game saved");

		} catch(Exception e) {
			System.out.println("Save Exception");
		}

	}

	//Spielstand laden
	public void load(int FileIndex) {
		try {
			//Speicher-Datei importieren
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("save" + FileIndex + ".dat")));
			DataStorage ds = (DataStorage) ois.readObject();

			//gespeichertes Spielstand laden
			name = ds.name;
			gp.mapM.CourrentMap = ds.Map;
			gp.controller.CorrentHP = ds.Health;
			gp.controller.score = ds.Score;
			gp.ui.PlayTime = ds.Time;
			gp.utool.choose = ds.Character;
			//aktuelle Entity laden
			gp.mapM.load_Entity();
			//Anzahl der eingesammelten und noch verfügbaren Diamanten laden
			gp.controller.AmountOfTakenDiamond = ds.AmountOfTakenDiamond;
			gp.controller.AmountOfDiamond = ds.AmountOfDiamond;
			//Position der Spielfigur laden
			gp.mapM.player.XPosition = ds.playerX;
			gp.mapM.player.YPosition = ds.playerY;

			//Position des MonsterA laden
			for(int i = 0 ; i < ds.monsterAX.length ; i++) {
				if(gp.mapM.monsterA[ i ] != null) {
					gp.mapM.monsterA[ i ].XPosition = ds.monsterAX[ i ];
					gp.mapM.monsterA[ i ].YPosition = ds.monsterAY[ i ];
					//tot MonsterA loeschen
					if(ds.monsterAX[ i ] == 0 && ds.monsterAY[ i ] == 0) {
						gp.mapM.monsterA[ i ] = null;
					}
				}
			}
			//Position des MonsterB laden
			for(int i = 0 ; i < ds.monsterBX.length ; i++) {
				if(gp.mapM.monsterB[ i ] != null) {
					gp.mapM.monsterB[ i ].XPosition = ds.monsterBX[ i ];
					gp.mapM.monsterB[ i ].YPosition = ds.monsterBY[ i ];
					//tot MonsterB loeschen
					if(ds.monsterBX[ i ] == 0 && ds.monsterBY[ i ] == 0) {
						gp.mapM.monsterB[ i ] = null;
					}
				}
			}

			//Position aller Objekte laden
			for(int i = 0 ; i < ds.ObjX.length ; i++) {
				if(gp.mapM.obj[ i ] != null) {
					gp.mapM.obj[ i ].XPosition = ds.ObjX[ i ];
					gp.mapM.obj[ i ].YPosition = ds.ObjY[ i ];
					//eingesammelten Diamanten und abgetragenes Dirt loeschen
					if(gp.mapM.obj[ i ].XPosition / gp.tilesize == 0 && gp.mapM.obj[ i ].YPosition / gp.tilesize == 0) {
						gp.mapM.obj[ i ] = null;
					}
				}
			}

		} catch(Exception e) {
			System.out.println("Load Exception");
		}
	}

	//HighScore speichern
	public void SaveHighScore() {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("HighScore.dat")));
			HighScoreDATA ds = new HighScoreDATA();
			//HighScore mit dem Name zum Speichern vorbereiten
			ds.HighScore = gp.controller.HighScore;
			ds.highname = highname;
			//HighScore mit dem Name in die erstellte Datei speichern
			oos.writeObject(ds);
		} catch(Exception e) {
			System.out.println("HighScore Save Exception");
		}

	}

	//HighScore laden
	public void LoadHighScore() {
		try {
			//Speicher-Datei importieren
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("HighScore.dat")));
			HighScoreDATA ds = (HighScoreDATA) ois.readObject();
			//HighScore mit dem Name laden
			gp.controller.HighScore = ds.HighScore;
			highname = ds.highname;
		} catch(Exception e) {
			System.out.println("HighScore Load Exception");
		}

	}

}
