package Obj;

import main.GamePanel;

public class Heart extends OBJ {
	private int animationNummer = 0;

	public Heart(GamePanel gp, int x, int y) {
		super(gp);
		name = "Heart";
		image = gp.utool.Heart[ 0 ];
		collision = true;
		this.XPosition = x;
		this.YPosition = y;
	}

	@Override
	public void update() {
		animation();
	}

	//Das Herz muss sich in ständiger Bewegung um sich selbst drehen.
	private void animation() {
		if(animationNummer <= 50)
			image = gp.utool.Heart[ animationNummer / 5 ];

		animationNummer++;
		if(animationNummer == 55)
			animationNummer = 0;


	}


}

