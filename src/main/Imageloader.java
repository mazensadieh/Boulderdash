package main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Imageloader {
	public String choose="Boy";
	private GamePanel gp;
	public BufferedImage[] Heart = new BufferedImage[ 11 ];
	public BufferedImage[] Diamond = new BufferedImage[ 9 ];
	public BufferedImage[] Door = new BufferedImage[ 2 ];
	public BufferedImage rock;
	public BufferedImage Timer;
	public BufferedImage HinterGrund;
	public BufferedImage[] Dirt = new BufferedImage[ 6 ];
	public BufferedImage[] ChoosedPlayer = new BufferedImage[12];
	public BufferedImage[] PlayerBoy = new BufferedImage[ 12 ];
	public BufferedImage[] PlayerGirl = new BufferedImage[ 12 ];
	public BufferedImage[] Tile = new BufferedImage[ 4 ];
	public BufferedImage[] MonsterB = new BufferedImage[ 8 ];
	public BufferedImage[] MonsterA = new BufferedImage[ 4 ];



	public Imageloader(GamePanel gp) {
		this.gp = gp;
		ObjBilderSet();
		getplayerBoyimage();
		getplayerGirlimage();
		getmonsterAimage();
		getmonsterBimage();
		TileImage();
	}

	private BufferedImage scaleImage(BufferedImage original, int width, int height) {
		BufferedImage scaledImage = new BufferedImage(width, height, original.getType());
		Graphics2D g2 = scaledImage.createGraphics();
		g2.drawImage(original, 0, 0, width, height, null);
		g2.dispose();
		return scaledImage;
	}

	private void ObjBilderSet() {

		for(int i = 0 ; i < Heart.length ; i++) {
			Heart[ i ] = setup("Bilder/Heart/" + i + ".png");
		}

		for(int i = 0 ; i < Diamond.length ; i++) {
			Diamond[ i ] = setup("Bilder/Diamond/" + i + ".png");
		}

		for(int i = 0 ; i < Dirt.length ; i++) {
			Dirt[ i ] = setup("Bilder/Dirt/" + i + ".png");
		}

		for(int i = 0 ; i < Door.length ; i++) {
			Door[ i ] = setup("Bilder/Door/" + i + ".png");
		}

		rock = setup("Bilder/Rock/rock.png");

		Timer = setup("Bilder/Timer/1.png");

		HinterGrund = setup("Bilder/Hintergrund/1.png");

	}

	private void getplayerBoyimage() {
		for(int i = 0 ; i < PlayerBoy.length ; i++) {
			PlayerBoy[ i ] = setup("Bilder/PlayerBoy/" + i + ".png");
		}
	}
	private void getplayerGirlimage() {
		for(int i = 0 ; i < PlayerGirl.length ; i++) {
			PlayerGirl[ i ] = setup("Bilder/PlayerGirl/" + i + ".png");
		}
	}
	public void ChoosedPlayer(){
		if(Objects.equals(choose, "Boy"))
			for(int i = 0 ; i < PlayerBoy.length ; i++) {
			ChoosedPlayer[i] = PlayerBoy[ i ];
			}
		else
			for(int i = 0 ; i < PlayerGirl.length ; i++) {
				ChoosedPlayer[i] = PlayerGirl[ i ];
			}
	}

	private void TileImage() {
		for(int i = 0 ; i < Tile.length ; i++) {
			Tile[ i ] = setup("Bilder/Tile/" + i + ".png");
		}
	}

	private void getmonsterAimage() {

		for(int i = 0 ; i < MonsterA.length ; i++) {
			MonsterA[ i ] = setup("Bilder/MonsterA/" + i + ".png");
		}
	}

	private void getmonsterBimage() {
		for(int i = 0 ; i < MonsterB.length ; i++) {
			MonsterB[ i ] = setup("Bilder/MonsterB/" + i + ".png");
		}
	}

	private BufferedImage setup(String ImagePath) {
		BufferedImage image = null;
		try {
			image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(ImagePath)));

			if(ImagePath == "Bilder/Hintergrund/1.png"){

				image = scaleImage(image, 1000, 1000);

			}else{

				image = scaleImage(image, gp.tilesize, gp.tilesize);
			}



		} catch(IOException e) {
			e.printStackTrace();
		}
		return image;
	}
}
