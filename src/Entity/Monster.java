package Entity;

import main.GamePanel;

import java.awt.*;

public class Monster extends PlayerANDMonster {
	public int MovingCounter = 0;

	public Monster(GamePanel gp) {
		super(gp);
		solidArea = new Rectangle(10, 5, 38, 48);
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
	}

	public void update() {

	}

}
