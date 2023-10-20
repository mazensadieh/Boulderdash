package SaveLoad;

import java.io.Serializable;

public class DataStorage implements Serializable {

	public int Map;
	public int Score;
	public int Time;
	public int Health;
	public int AmountOfTakenDiamond;
	public int AmountOfDiamond;
	public int playerX;
	public int playerY;
	public int[] monsterAX = new int[ 10 ];
	public int[] monsterAY = new int[ 10 ];
	public int[] monsterBX = new int[ 10 ];
	public int[] monsterBY = new int[ 10 ];
	public int[] ObjX = new int[ 100 ];
	public int[] ObjY = new int[ 100 ];
	public String Character;
	public String name;
}