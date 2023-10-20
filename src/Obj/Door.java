package Obj;

import main.GamePanel;

public class Door extends OBJ {
	private boolean sound = false;

	public Door(GamePanel gp, int x, int y) {
		super(gp);
		name = "Door";
		image = gp.utool.Door[ 0 ];
		collision = true;
		this.XPosition = x;
		this.YPosition = y;
	}
	//Überprüfen ob der Spieler 80 Porzent der Level-Diamanten gesammelt hat
	//Beim Erreichen der Voraussetzung, öffnet sich die Tür
	@Override
	public void update() {
		if(( gp.controller.AmountOfDiamond * 80 ) / 100 <= gp.controller.AmountOfTakenDiamond) {
			if(!sound) {
				gp.playSE(5);
				sound = true;
			}
			gp.controller.DoorClosed = false;
			image = gp.utool.Door[ 1 ];
		}
	}
}
