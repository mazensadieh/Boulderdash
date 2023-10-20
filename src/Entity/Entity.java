package Entity;

import main.GamePanel;

import java.awt.*;
// Bewegungen und Aussehen von Spielfigur, Monsters und OBJ

public class Entity {

	public GamePanel gp;
	public Rectangle solidArea = new Rectangle(0, 0, 60, 55);
	public int solidAreaDefaultX, solidAreaDefaultY;
	public int XPosition, YPosition;
	public int speed = 4;
	public boolean collisionOn = false;
	public String direction = "down";

}