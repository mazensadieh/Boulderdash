package Obj;

import main.GamePanel;

public class Dirt extends OBJ {
	private int removecounter = 0;

	public Dirt(GamePanel gp, int x, int y) {
		super(gp);
		this.XPosition = x;
		this.YPosition = y;
		name = "Dirt";
		image = gp.utool.Dirt[ 0 ];
		solidArea.x = 0;
		solidArea.y = 0;
		solidArea.width = 60;
		solidArea.height = 60;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		collision = true;
	}

	@Override
	public void update() {
		if(remove) {
			//Lösche die Erde
			if(removecounter < 6)
				image = gp.utool.Dirt[ removecounter ];
			if(removecounter >= 6)
				gp.controller.dirtremove(this);
			removecounter++;
		}
	}


}
