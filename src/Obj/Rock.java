package Obj;

import main.GamePanel;

public class Rock extends OBJ {
	private int fallCounter = 0;
	//private int animation = 0;

	public Rock(GamePanel gp, int x, int y) {
		super(gp);
		name = "Rock";
		this.XPosition = x;
		this.YPosition = y;
		image = gp.utool.rock;
		solidArea.x = 5;
		solidArea.y = 5;
		solidArea.width = 50;
		solidArea.height = 50;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;

		collision = true;
	}

	@Override
	public void update() {
		//Kollisionen der Felsbrocke und ihr Verhalten beim Treffen eines den weiteren Objekten
		checkCollision();
		//Im Fall einer kollision, soll das fallCounter zurückgesetzt werden
		if(collisionOn)
			fallCounter = 0;

		if(!collisionOn) {
			//Felsbrocke fällt nach eine halbe Sekunde herunter
			fallCounter++;
			if(fallCounter > 30) {
				YPosition = YPosition + speed;
			}

		}
		//Wenn Beim Treffen der Spielfigur oder eines Monsters während des Herunterfallens, werden sie umgebracht.
		CheckIfKill();
		//Überbrufe, ob es eine lawiene gibt
		lawiene();
	}

	private void CheckIfKill() {
		collisionOn = false;

		//Kollision mit MonsterA -> MonsterA umbringen und aus der Karte löschen
		int MonsterAIndex = gp.checker.checkEntity(this, gp.mapM.monsterA);
		if(MonsterAIndex != 999) {
			gp.controller.score = gp.controller.score + 20;
			gp.mapM.monsterA[ MonsterAIndex ] = null;
		}
		//Kollision mit MonsterB -> MonsterB umbringen und aus der Karte löschen
		int MonsterBIndex = gp.checker.checkEntity(this, gp.mapM.monsterB);
		if(MonsterBIndex != 999) {
			gp.controller.score = gp.controller.score + 30;
			gp.mapM.monsterB[ MonsterBIndex ] = null;
		}

		collisionOn = false;
		//Kollision mit der Spielfigur -> Spielfigur umbringen und aus der Karte löschen
		gp.checker.checkplayer(this);
		if(collisionOn) {
			gp.controller.kill("killed by Rock");
		}

	}


}
