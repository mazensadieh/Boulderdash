package Obj;

import main.GamePanel;

public class Diamond extends OBJ {
	private int animationNummer = 0;
	private int fallCounter = 0;

	public Diamond(GamePanel gp, int x, int y) {
		super(gp);
		name = "Diamond";

		image = gp.utool.Diamond[ 0 ];
		this.XPosition = x;
		this.YPosition = y;
		solidArea.x = 5;
		solidArea.y = 5;
		solidArea.width = 54;
		solidArea.height = 50;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		collision = true;
	}

	@Override
	public void update() {


		animation();
		checkCollision();
		if(collisionOn)
			fallCounter = 0;
		if(!collisionOn) {
			gp.checker.checkplayer(this);
			if(collisionOn) {
				int index = DiamondIndex();
				if(index != 999)
					gp.controller.CollectDiamand(index);
			}
			fallCounter++;
			if(fallCounter > 2) {
				YPosition = YPosition + speed;
			}
		}
		lawiene();
	}

	private void animation() {
		if(animationNummer <= 50)
			image = gp.utool.Diamond[ animationNummer / 5 ];

		animationNummer++;
		if(animationNummer > 44)
			animationNummer = 0;


	}
	//Index des Diamants finden
	private int DiamondIndex() {
		for(int i = 0 ; i < gp.mapM.obj.length ; i++) {
			if(gp.mapM.obj[ i ] != null && gp.mapM.obj[ i ] == this) {
				return i;
			}
		}
		return 999;
	}


}
