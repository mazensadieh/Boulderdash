package Entity;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class MonsterA extends Monster {

	public MonsterA(GamePanel gp, int x, int y) {
		super(gp);
		direction = "down";
		speed = 2;
		this.XPosition = x;
		this.YPosition = y;
	}

	// Monster auf dem Map zeigen
	public void draw(Graphics2D g2) {
		//die verschiedene Spielfigurimages zuladen abha. von unserem gedruckten Pfeil.
		BufferedImage image = switch(direction) {
			case "up" -> gp.utool.MonsterA[ 3 ];
			case "down" -> gp.utool.MonsterA[ 2 ];
			case "left" -> gp.utool.MonsterA[ 1 ];
			case "right" -> gp.utool.MonsterA[ 0 ];
			default -> null;
		};
		g2.drawImage(image, XPosition, YPosition, gp.tilesize, gp.tilesize, null);
	}

	public void update() {
		MovingCounter++;
		if(MovingCounter == 120) {
			Random random = new Random();
			int Richtung = random.nextInt(4) ;

			// wenn 0 bewegt sicht das Monster nach üben
			if(Richtung == 0) {
				direction = "up";
			}
			// wenn 0 bewegt sicht das Monster nach unten
			if(Richtung == 1) {
				direction = "down";
			}
			// wenn 0 bewegt sicht das Monster nach links
			if(Richtung == 2) {
				direction = "left";
			}
			// wenn 0 bewegt sicht das Monster nach rechts
			if(Richtung == 3) {
				direction = "right";
			}
			MovingCounter = 0;
		}
		checkCollision();
		if(!collisionOn) {
			// bewegung von Monster
			switch(direction) {
				case "up" -> YPosition -= speed;
				case "down" -> YPosition += speed;
				case "left" -> XPosition -= speed;
				case "right" -> XPosition += speed;
			}
			// Kollision mit Spielfigür
			gp.checker.checkplayer(this);
			if(collisionOn) {
				gp.controller.kill("killed by Monster");
			}
		}
	}

}
