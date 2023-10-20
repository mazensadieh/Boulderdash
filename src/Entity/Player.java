package Entity;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends PlayerANDMonster {

	private BufferedImage image = null;

	public Player(GamePanel gp, int x, int y) {
		super(gp);
		solidArea = new Rectangle(15, 15, 30, 30);
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		this.XPosition = x;
		this.YPosition = y;
		speed = 6;
		direction = "down"; //down als start Wert
	}

	// Spielfigür auf dem Map zeigen
	public void draw(Graphics2D g2) {
		//die verschiedene Spielfigurimages zuladen abha. von unserem gedruckten Pfeil.

		switch(direction) {
			case "up" -> {
				if(spriteNum == 1) {
					image = gp.utool.ChoosedPlayer[ 6 ];
				}
				if(spriteNum == 2) {
					image = gp.utool.ChoosedPlayer[ 7 ];
				}

			}
			case "down" -> {
				if(spriteNum == 1) {
					image = gp.utool.ChoosedPlayer[ 4 ];
				}
				if(spriteNum == 2) {
					image = gp.utool.ChoosedPlayer[ 5 ];
				}

			}
			case "left" -> {
				if(spriteNum == 1) {
					image = gp.utool.ChoosedPlayer[ 10];
				}
				if(spriteNum == 2) {
					image = gp.utool.ChoosedPlayer[ 11 ];
				}

			}
			case "right" -> {
				if(spriteNum == 1) {
					image = gp.utool.ChoosedPlayer[ 8 ];
				}
				if(spriteNum == 2) {
					image = gp.utool.ChoosedPlayer[ 9 ];
				}

			}
		}
		if(spriteNum == 3) {
			image = gp.utool.ChoosedPlayer[ 0 ];
		}
		g2.drawImage(image, XPosition, YPosition, gp.tilesize, gp.tilesize, null);
	}

	public void update() {
		// überprufe ob Spielfigür zum Diamant und Tür kommen kann
		checkifreachable();

		// Richtung von Spielfigür erkennen
		if(gp.keyh.left || gp.keyh.right || gp.keyh.up || gp.keyh.down) {
			if(gp.keyh.up) {
				direction = "up";
			} else if(gp.keyh.down) {
				direction = "down";
			} else if(gp.keyh.right) {
				direction = "right";
			} else {
				direction = "left";
			}
			// Kollision mit WAS?
			collisionOn = false;

			int objIndex = gp.checker.checkObject(this, true);
			gp.controller.ObjectColision(objIndex);
			int monsterAindex = gp.checker.checkEntity(this, gp.mapM.monsterA);
			int monsterBindex = gp.checker.checkEntity(this, gp.mapM.monsterB);
			if(monsterAindex != 999 || monsterBindex != 999) {
				gp.controller.kill("killed by Monster");
			}
			gp.checker.checkTile(this);
			// Bewegung von Spielfigür
			if(!collisionOn) {
				switch (direction) {
					case "up" -> YPosition -= speed;
					case "down" -> YPosition += speed;
					case "left" -> XPosition -= speed;
					case "right" -> XPosition += speed;
				}
			}

			//dient dazu, dass unser Spielfigurimages mit jedem Schritt geändert wurde.
			spriteCounter++;
			if(spriteCounter > 10) {

				if(spriteNum == 1) {
					spriteNum = 2;
				} else if(spriteNum == 2 ||spriteNum ==3) {
					spriteNum = 1;
				}
				spriteCounter = 0;
			}

		}else{
			spriteNum = 3;
		}
	}

	private int counter = 0;
	public void checkifreachable() {
		int DiamonCount = 0;
		int playercol = ( XPosition + solidArea.x ) / gp.tilesize;
		int playerrow = ( YPosition + solidArea.y ) / gp.tilesize;
		goalreched = false;
		boolean winable = true;
		String text = "";
		for(int i = 0 ; i < gp.mapM.obj.length ; i++) {
			if(gp.mapM.obj[ i ] != null) {
				String name = gp.mapM.obj[ i ].name;

				switch(name) {
					case "Diamond" -> {
						int diamandcol = ( gp.mapM.obj[ i ].XPosition + gp.mapM.obj[ i ].solidArea.x ) / gp.tilesize;
						int diamandrow = ( gp.mapM.obj[ i ].YPosition + gp.mapM.obj[ i ].solidArea.y ) / gp.tilesize;
						gp.pathFinder.reachablesetNode(playercol, playerrow, diamandcol, diamandrow);
						if(playercol == diamandcol && playerrow == diamandrow) {
							goalreched = true;
						}
						if(gp.pathFinder.search() || goalreched) {
							DiamonCount++;
						}
					}
					case "Door" -> {
						int doorcol = ( ( gp.mapM.obj[ i ].XPosition + gp.mapM.obj[ i ].solidArea.x ) / gp.tilesize );
						int doorrow = ( ( gp.mapM.obj[ i ].YPosition + gp.mapM.obj[ i ].solidArea.y ) / gp.tilesize );
						gp.pathFinder.reachablesetNode(playercol, playerrow, doorcol, doorrow);
						if(playercol == doorcol && playerrow == doorrow) {
							goalreched = true;
						}
						if(!gp.pathFinder.search() && !goalreched) {
							text = "Door can't be reached";
							winable = false;
						}
					}
				}
			}
		}
		if(DiamonCount < ( ( ( gp.controller.AmountOfDiamond * 80 ) / 100 ) - gp.controller.AmountOfTakenDiamond )) {
			text = "required Diamonds not reachable";
			winable = false;
		}
		counter++;
		if(winable) {
			counter = 0;
		} else if(counter > 120) {
			gp.controller.kill(text);
			counter = 0;
		}


	}

}
