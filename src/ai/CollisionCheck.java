package ai;

import Entity.Entity;
import main.GamePanel;

public class CollisionCheck {
	private GamePanel gp;

	public CollisionCheck(GamePanel gp) {
		this.gp = gp;
	}

	public void checkTile(Entity entity) {
		int entityLeftX = entity.XPosition + entity.solidArea.x;
		int entityRightX = entity.XPosition + entity.solidArea.x + entity.solidArea.width;
		int entityTopY = entity.YPosition + entity.solidArea.y;
		int entityBottomY = entity.YPosition + entity.solidArea.y + entity.solidArea.height;

		int entityLeftCol = entityLeftX / gp.tilesize;
		int entityRightCol = entityRightX / gp.tilesize;
		int entityTopRow = entityTopY / gp.tilesize;
		int entityBottomRow = entityBottomY / gp.tilesize;

		int tileNum1, tileNum2;

		switch(entity.direction) {
			case "up":
				entityTopRow = ( entityTopY - entity.speed ) / gp.tilesize;
				tileNum1 = gp.mapM.mapTileNum[ gp.mapM.CourrentMap ][ entityLeftCol ][ entityTopRow ];
				tileNum2 = gp.mapM.mapTileNum[ gp.mapM.CourrentMap ][ entityRightCol ][ entityTopRow ];
				if(gp.mapM.tile[ tileNum1 ].collision || gp.mapM.tile[ tileNum2 ].collision) {
					entity.collisionOn = true;
				}
				break;
			case "down":
				entityBottomRow = ( entityBottomY + entity.speed ) / gp.tilesize;
				tileNum1 = gp.mapM.mapTileNum[ gp.mapM.CourrentMap ][ entityLeftCol ][ entityBottomRow ];
				tileNum2 = gp.mapM.mapTileNum[ gp.mapM.CourrentMap ][ entityRightCol ][ entityBottomRow ];
				if(gp.mapM.tile[ tileNum1 ].collision || gp.mapM.tile[ tileNum2 ].collision) {
					entity.collisionOn = true;
				}
				break;
			case "left":
				entityLeftCol = ( entityLeftX - entity.speed ) / gp.tilesize;
				tileNum1 = gp.mapM.mapTileNum[ gp.mapM.CourrentMap ][ entityLeftCol ][ entityTopRow ];
				tileNum2 = gp.mapM.mapTileNum[ gp.mapM.CourrentMap ][ entityLeftCol ][ entityBottomRow ];
				if(gp.mapM.tile[ tileNum1 ].collision || gp.mapM.tile[ tileNum2 ].collision) {
					entity.collisionOn = true;
				}
				break;
			case "right":
				entityRightCol = ( entityRightX + entity.speed ) / gp.tilesize;
				tileNum1 = gp.mapM.mapTileNum[ gp.mapM.CourrentMap ][ entityRightCol ][ entityTopRow ];
				tileNum2 = gp.mapM.mapTileNum[ gp.mapM.CourrentMap ][ entityRightCol ][ entityBottomRow ];
				if(gp.mapM.tile[ tileNum1 ].collision || gp.mapM.tile[ tileNum2 ].collision) {
					entity.collisionOn = true;
				}
				break;
		}
	}

	public int checkObject(Entity entity, boolean player) {
		int index = 999;

		for(int i = 0 ; i < gp.mapM.obj.length ; i++) {
			if(gp.mapM.obj[ i ] != null && gp.mapM.obj[ i ] != entity) {
				entity.solidArea.x = entity.XPosition + entity.solidArea.x;
				entity.solidArea.y = entity.YPosition + entity.solidArea.y;

				gp.mapM.obj[ i ].solidArea.x = gp.mapM.obj[ i ].XPosition + gp.mapM.obj[ i ].solidArea.x;
				gp.mapM.obj[ i ].solidArea.y = gp.mapM.obj[ i ].YPosition + gp.mapM.obj[ i ].solidArea.y;

				switch(entity.direction) {
					case "up":
						entity.solidArea.y -= entity.speed;
						break;
					case "down":
						entity.solidArea.y += entity.speed;
						break;
					case "left":
						entity.solidArea.x -= entity.speed;
						break;
					case "right":
						entity.solidArea.x += entity.speed;
						break;
				}
				if(entity.solidArea.intersects(gp.mapM.obj[ i ].solidArea)) {
					if(gp.mapM.obj[ i ].collision) {
						entity.collisionOn = true;
						index = i;
					}
					if(player) {
						if(gp.mapM.obj[ i ].name == "Dirt")
							entity.collisionOn = false;
						index = i;
					}

				}
				entity.solidArea.x = entity.solidAreaDefaultX;
				entity.solidArea.y = entity.solidAreaDefaultY;
				gp.mapM.obj[ i ].solidArea.x = gp.mapM.obj[ i ].solidAreaDefaultX;
				gp.mapM.obj[ i ].solidArea.y = gp.mapM.obj[ i ].solidAreaDefaultY;
			}
		}

		return index;
	}

	public void checkplayer(Entity entity) {

		entity.solidArea.x = entity.XPosition + entity.solidArea.x;
		entity.solidArea.y = entity.YPosition + entity.solidArea.y;

		gp.mapM.player.solidArea.x = gp.mapM.player.XPosition + gp.mapM.player.solidArea.x;
		gp.mapM.player.solidArea.y = gp.mapM.player.YPosition + gp.mapM.player.solidArea.y;

		switch(entity.direction) {
			case "up":
			case "right":
			case "left":
			case "down":
				if(entity.solidArea.intersects(gp.mapM.player.solidArea)) {
					entity.collisionOn = true;

				}
				break;
		}
		entity.solidArea.x = entity.solidAreaDefaultX;
		entity.solidArea.y = entity.solidAreaDefaultY;
		gp.mapM.player.solidArea.x = gp.mapM.player.solidAreaDefaultX;
		gp.mapM.player.solidArea.y = gp.mapM.player.solidAreaDefaultY;
	}

	public int checkEntity(Entity entity, Entity[] target) {
		int index = 999;

		for(int i = 0 ; i < target.length ; i++) {
			if(target[ i ] != null) {
				entity.solidArea.x = entity.XPosition + entity.solidArea.x;
				entity.solidArea.y = entity.YPosition + entity.solidArea.y;

				target[ i ].solidArea.x = target[ i ].XPosition + target[ i ].solidArea.x;
				target[ i ].solidArea.y = target[ i ].YPosition + target[ i ].solidArea.y;

				switch(entity.direction) {
					case "up":
						entity.solidArea.y -= entity.speed;
						break;
					case "down":
						entity.solidArea.y += entity.speed;
						break;
					case "left":
						entity.solidArea.x -= entity.speed;
						break;
					case "right":
						entity.solidArea.x += entity.speed;
						break;
				}
				if(entity.solidArea.intersects(target[ i ].solidArea)) {
					if(target[ i ] != entity) {
						entity.collisionOn = true;
						index = i;
					}
				}
				entity.solidArea.x = entity.solidAreaDefaultX;
				entity.solidArea.y = entity.solidAreaDefaultY;
				target[ i ].solidArea.x = target[ i ].solidAreaDefaultX;
				target[ i ].solidArea.y = target[ i ].solidAreaDefaultY;
			}
		}

		return index;
	}

}



