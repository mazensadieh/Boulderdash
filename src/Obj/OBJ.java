package Obj;

import Entity.Entity;
import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class OBJ extends Entity {

	public String name;
	public boolean collision = false;
	public BufferedImage image;
	public boolean remove = false;
	private boolean rightfree = true;
	private boolean leftfree = true;
	private int SlidelIndex = 0;

	public int fallCounter = 0;

	public OBJ(GamePanel gp) {
		this.gp = gp;
	}

	public void draw(Graphics2D g2) {
		g2.drawImage(image, XPosition, YPosition, gp.tilesize, gp.tilesize, null);
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

	public void lawiene() {
		collisionOn = false;
		int botomindex = gp.checker.checkObject(this, false);
		rightfree = true;
		leftfree = true;
		int col = XPosition / gp.tilesize;
		int row = YPosition / gp.tilesize;
		//int coll = (XPosition+solidArea.width+solidAreaDefaultX) / gp.tilesize;

		if((botomindex != 999) && !Objects.equals(gp.mapM.obj[ botomindex ].name, "Dirt")) {

			for(int i = 0 ; i < gp.mapM.obj.length ; i++) {

				if(gp.mapM.obj[ i ] != null) {

					int ObjCol = ( gp.mapM.obj[ i ].XPosition ) / gp.tilesize;
					int ObjRow = gp.mapM.obj[ i ].YPosition / gp.tilesize;
					// CHECKE RIGHT

					if(ObjCol == col + 1 && (ObjRow == row + 1 || ObjRow == row)) {
						rightfree = false;

					}
					else if(ObjRow == row - 1 && (ObjCol == col || ObjCol == col + 1 )) {
						rightfree = false;

					}

					else if(ObjCol == col && ObjRow == row + 1 && gp.mapM.obj[ i ] != gp.mapM.obj[ botomindex ]) {
						rightfree = false;

					}

					//CHECKE LEFT
					if(ObjCol == col && ObjRow == row - 1) {
						leftfree = false;
						//System.out.println("L1");

					}
					else if(ObjCol == col - 1 && (ObjRow == row - 1 || ObjRow == row + 1 || ObjRow == row )) {
						leftfree = false;
						//System.out.println("L2");

					}

				}

			}

			laweinechecktile();
			if(rightfree && leftfree) {
				leftfree = false;

			}

			if(rightfree) {
				direction = "right";
				checkCollision();
				if(!collisionOn) {
					SlidelIndex = XPosition / gp.tilesize;
					while(XPosition / gp.tilesize != SlidelIndex + 1) {

							XPosition = XPosition + speed;
					}
				}
			}

			if(leftfree) {
				direction = "left";
				checkCollision();
				if(!collisionOn) {
					SlidelIndex = XPosition / gp.tilesize;
					while(XPosition / gp.tilesize != SlidelIndex - 1) {

							XPosition = XPosition - speed;


					}
				}
			}

		}

			direction = "down";



	}

	private void laweinechecktile() {
		int col = (XPosition+solidAreaDefaultX) / gp.tilesize;
		int row = (YPosition+solidAreaDefaultY) / gp.tilesize;
		if(col != 0 && row != 0) {
			int tileNum1, tileNum2, tileNum3, tileNum4;
			tileNum1 = gp.mapM.mapTileNum[ gp.mapM.CourrentMap ][ col + 1 ][ row ];
			tileNum2 = gp.mapM.mapTileNum[ gp.mapM.CourrentMap ][ col + 1 ][ row + 1 ];
			tileNum3 = gp.mapM.mapTileNum[ gp.mapM.CourrentMap ][ col - 1 ][ row ];
			tileNum4 = gp.mapM.mapTileNum[ gp.mapM.CourrentMap ][ col - 1 ][ row + 1 ];
			if(gp.mapM.tile[ tileNum1 ].collision || gp.mapM.tile[ tileNum2 ].collision) {
				rightfree = false;

			}
			if(gp.mapM.tile[ tileNum3 ].collision || gp.mapM.tile[ tileNum4 ].collision) {
				leftfree = false;

			}
		}

	}

}
