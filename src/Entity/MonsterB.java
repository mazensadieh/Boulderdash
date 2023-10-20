package Entity;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class MonsterB extends Monster {
	private boolean SoundPlayed = false;
	private boolean onPath = false;

	public MonsterB(GamePanel gp, int x, int y) {
		super(gp);
		direction = "down";
		speed = 3;
		this.XPosition = x;
		this.YPosition = y;
	}

	// Monster auf dem Map zeigen
	public void draw(Graphics2D g2) {
		//die verschiedene Monsterimages zuladen abha. von der Richtung.
		BufferedImage image = null;
		switch(direction) {
			case "up" -> {
				if(spriteNum == 1) {
					image = gp.utool.MonsterB[ 6 ];
				}
				if(spriteNum == 2) {
					image = gp.utool.MonsterB[ 7 ];
				}
			}
			case "down" -> {
				if(spriteNum == 1) {
					image = gp.utool.MonsterB[ 0 ];
				}
				if(spriteNum == 2) {
					image = gp.utool.MonsterB[ 1 ];
				}
			}
			case "left" -> {
				if(spriteNum == 1) {
					image = gp.utool.MonsterB[ 2 ];
				}
				if(spriteNum == 2) {
					image = gp.utool.MonsterB[ 3 ];
				}
			}
			case "right" -> {
				if(spriteNum == 1) {
					image = gp.utool.MonsterB[ 4 ];
				}
				if(spriteNum == 2) {
					image = gp.utool.MonsterB[ 5 ];
				}
			}
		}
		g2.drawImage(image, XPosition, YPosition, gp.tilesize, gp.tilesize, null);
	}

	public void update() {
		int goalCol = ( gp.mapM.player.XPosition + gp.mapM.player.solidArea.x ) / gp.tilesize;
		int goalRow = ( gp.mapM.player.YPosition + gp.mapM.player.solidArea.y ) / gp.tilesize;
		searchPath(goalCol, goalRow);

		if(!onPath) {
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

			//dient dazu, dass unser Monsterimages mit jedem Schritt geändert wurde.
			spriteCounter++;
			if(spriteCounter > 10) {
				if(spriteNum == 1) {
					spriteNum = 2;
				} else if(spriteNum == 2) {
					spriteNum = 1;
				}
				spriteCounter = 0;
			}
		}

	}
	// überprufe ob das Monster zum Spielfigeur kommen kann
	public void searchPath(int goalCol, int goalRow) {

		int startCol = ( XPosition + solidArea.x ) / gp.tilesize;
		int startRow = ( YPosition + solidArea.y ) / gp.tilesize;
		goalreched = false;

		gp.pathFinder.setNode(startCol, startRow, goalCol, goalRow);

		if(!gp.pathFinder.search()) {
			onPath = false;
			SoundPlayed = false;
		} else {
			onPath = true;
			if(SoundPlayed) {
				gp.playSE(2);
				SoundPlayed = true;
			}
			int nextX = gp.pathFinder.pathList.get(0).col * gp.tilesize;
			int nextY = gp.pathFinder.pathList.get(0).row * gp.tilesize;

			int enLeftX = XPosition + solidArea.x;
			int enRightX = XPosition + solidArea.x + solidArea.width;
			int enTopY = YPosition + solidArea.y;
			int enBottomY = YPosition + solidArea.y + solidArea.height;

			if(enTopY > nextY && enLeftX >= nextX && enRightX < nextX + gp.tilesize) {
				direction = "up";
			} else if(enTopY < nextY && enLeftX >= nextX && enRightX < nextX + gp.tilesize) {
				direction = "down";
			} else if(enTopY >= nextY && enBottomY < nextY + gp.tilesize) {
				if(enLeftX > nextX) {
					direction = "left";
				}
				if(enLeftX < nextX) {
					direction = "right";
				}
			} else if(enTopY > nextY && enLeftX > nextX) {
				direction = "up";
				checkCollision();
				if(collisionOn) {
					direction = "left";
				}
			} else if(enTopY > nextY && enLeftX < nextX) {
				direction = "up";
				checkCollision();
				if(collisionOn) {
					direction = "right";
				}
			} else if(enTopY < nextY && enLeftX > nextX) {
				direction = "down";
				checkCollision();
				if(collisionOn) {
					direction = "left";
				}
			} else if(enTopY < nextY && enLeftX < nextX) {
				direction = "down";
				checkCollision();
				if(collisionOn) {
					direction = "right";
				}
			}

			int nextcol = gp.pathFinder.pathList.get(0).col;
			int nextRow = gp.pathFinder.pathList.get(0).row;

			if(nextcol == goalCol && nextRow == goalRow) {
				goalreched = true;
				onPath = false;
			}


		}

	}


}
