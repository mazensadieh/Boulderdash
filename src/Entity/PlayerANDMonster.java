package Entity;

import main.GamePanel;

import java.awt.*;

// Bewegungen und Aussehen von Monster und Spielfigur
public class PlayerANDMonster extends Entity {

	public int spriteNum = 1;
	public int spriteCounter = 0;
	public boolean goalreched = false;
	public PlayerANDMonster(GamePanel gp) {
		this.gp = gp;
	}

	public void draw(Graphics2D g2) {
	}

	public void update() {
	}


	public void checkCollision() {
		collisionOn = false;
		gp.checker.checkTile(this);
		gp.checker.checkObject(this, false);
		gp.checker.checkEntity(this, gp.mapM.monsterA);
		gp.checker.checkEntity(this, gp.mapM.monsterB);
	}





}
