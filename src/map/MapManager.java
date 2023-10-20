package map;


import Entity.MonsterA;
import Entity.MonsterB;
import Entity.Player;
import Obj.*;
import main.GamePanel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

//Um eine Map erstellen zu können, haben wir die Klasse MapManager implementiert.
//Die Klasse dient dazu, die Map und die Position der Objekte zu speichern und diese dann einzulesen und
// zusammenzubringen
public class MapManager {
	private GamePanel gp;
	public Tile[] tile;
	public int[][][] mapTileNum;
	private final int MaxMap = 10;
	public final int Firstmap = 0;
	public int CourrentMap = Firstmap;
	public final String[] MapFile = new String[ MaxMap ]; //Array für verschiedenen Maps

	private final String[] ObjFile = new String[ 10 ]; ////Array für verschiedenen Maps-Objekte.

	public Player player;
	public OBJ[] obj = new OBJ[ 100 ];
	public MonsterA[] monsterA = new MonsterA[ 10 ];
	public MonsterB[] monsterB = new MonsterB[ 10 ];

	public MapManager(GamePanel gp) {
		this.gp = gp;
		tile = new Tile[ 9 ];
		mapTileNum = new int[ MaxMap ][ gp.maxscreencol ][ gp.maxscreenrow ];
		player = new Player(gp,0,0);
		getTileImage();
		setMapFile();
		setObjFile();
		loadMap();
	}

	//Load Tile Images
	private void getTileImage() {
		setup(0, gp.utool.Tile[ 0 ], false);
		setup(1, gp.utool.Tile[ 1 ], true);
		setup(2, gp.utool.Tile[ 2 ], true);
		setup(3, gp.utool.Tile[ 3 ], true);

	}


	private void setMapFile() {
		MapFile[ 0 ] = "Obj and Map File/Maps/Map1.txt";
		MapFile[ 1 ] = "Obj and Map File/Maps/Map2.txt";
		MapFile[ 2 ] = "Obj and Map File/Maps/Map3.txt";
		MapFile[ 3 ] = "Obj and Map File/Maps/Map4.txt";
		MapFile[ 4 ] = "Obj and Map File/Maps/Map5.txt";
		MapFile[ 5 ] = "Obj and Map File/Maps/Map6.txt";
		MapFile[ 6 ] = "Obj and Map File/Maps/Map7.txt";
		MapFile[ 7 ] = "Obj and Map File/Maps/Map8.txt";
		MapFile[ 8 ] = "Obj and Map File/Maps/Map9.txt";
		MapFile[ 9 ] = "Obj and Map File/Maps/Map10.txt";
	}

	private void setObjFile() {
		ObjFile[ 0 ] = "Obj and Map File/Objekts/Obj01.txt";
		ObjFile[ 1 ] = "Obj and Map File/Objekts/Obj02.txt";
		ObjFile[ 2 ] = "Obj and Map File/Objekts/Obj03.txt";
		ObjFile[ 3 ] = "Obj and Map File/Objekts/Obj04.txt";
		ObjFile[ 4 ] = "Obj and Map File/Objekts/Obj05.txt";
		ObjFile[ 5 ] = "Obj and Map File/Objekts/Obj06.txt";
		ObjFile[ 6 ] = "Obj and Map File/Objekts/Obj07.txt";
		ObjFile[ 7 ] = "Obj and Map File/Objekts/Obj08.txt";
		ObjFile[ 8 ] = "Obj and Map File/Objekts/Obj09.txt";
		ObjFile[ 9 ] = "Obj and Map File/Objekts/Obj10.txt";
	}

	//jedes Map bzw. Objektsverteilung wird vorher festgelegt.
	//die Reihnfolge der verschiedenen Leveln könnte random sein.

	//Make image 60*60
	private void setup(int index, BufferedImage image, boolean collision) {
		tile[ index ] = new Tile();
		tile[ index ].image = image;
		tile[ index ].collision = collision;
	}

	public void draw(Graphics2D g2) {
		int col = 0;
		int row = 0;
		int x = 0;
		int y = 0;
		while(col < gp.maxscreencol && row < gp.maxscreenrow) {
			int tilenum = mapTileNum[ CourrentMap ][ col ][ row ];
			g2.drawImage(tile[ tilenum ].image, x, y, null);
			col++;
			x += gp.tilesize;
			if(col == gp.maxscreencol) {
				col = 0;
				x = 0;
				row++;
				y += gp.tilesize;
			}
		}

	}

	private void loadMap() {
		for(int i = 0 ; i <= MaxMap ; i++) {

			try {
				if(MapFile[ i ] != null) {
					InputStream is = getClass().getResourceAsStream(MapFile[ i ]);
					BufferedReader br = new BufferedReader(new InputStreamReader(is));

					int col = 0;
					int row = 0;

					while(col < gp.maxscreencol && row < gp.maxscreenrow) {
						String line = br.readLine();
						while(col < gp.maxscreencol) {
							String[] numbers = line.split(" ");
							int num = Integer.parseInt(numbers[ col ]);
							mapTileNum[ i ][ col ][ row ] = num;
							col++;
						}
						if(col == gp.maxscreencol) {
							col = 0;
							row++;
						}
					}
					br.close();
				}
			} catch(Exception e) {

			}
		}
	}

	public void load_Entity() {
		//macht die zusammenhang zwischen setMapFile() und setObjFile()
		gp.controller.AmountOfDiamond = 0;
		gp.controller.AmountOfTakenDiamond = 0;
		RemoveOldEntity();
		try {
			InputStream is = getClass().getResourceAsStream(ObjFile[ CourrentMap ]);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));

			int col = 0;
			int row = 0;
			int objNumber = 0;
			int monsterANumber = 0;
			int monsterBNumber = 0;
			gp.controller.AmountOfDiamond = 0;

			while(col < gp.maxscreencol && row < gp.maxscreenrow) {
				String line = br.readLine();
				//line by line Schleife
				while(col < gp.maxscreencol) {
					String[] numbers = line.split(" ");
					int num = Integer.parseInt(numbers[ col ]);
					if(num == 1) {
						//spielfigurposition auf dem Map
						player = new Player(gp, col * gp.tilesize, row * gp.tilesize);
					}
					if(num == 2) {
						//Door position auf dem Map
						obj[ objNumber ] = new Door(gp, col * gp.tilesize, row * gp.tilesize);
						objNumber++;
					}
					if(num == 3) {
						//Door position auf dem Map
						obj[ objNumber ] = new Dirt(gp, col * gp.tilesize, row * gp.tilesize);
						objNumber++;
					}
					if(num == 4) {
						//Door position auf dem Map
						obj[ objNumber ] = new Heart(gp, col * gp.tilesize, row * gp.tilesize);
						objNumber++;
					}
					if(num == 5) {
						//Door position auf dem Map
						obj[ objNumber ] = new Diamond(gp, col * gp.tilesize, row * gp.tilesize);
						gp.controller.AmountOfDiamond++;
						objNumber++;
					}
					if(num == 6) {
						//Door position auf dem Map
						obj[ objNumber ] = new Rock(gp, col * gp.tilesize, row * gp.tilesize);
						objNumber++;
					}
					if(num == 7) {
						//Door position auf dem Map
						monsterA[ monsterANumber ] = new MonsterA(gp, col * gp.tilesize, row * gp.tilesize);
						monsterANumber++;
					}

					if(num == 8) {
						//Door position auf dem Map
						monsterB[ monsterBNumber ] = new MonsterB(gp, col * gp.tilesize, row * gp.tilesize);
						monsterBNumber++;
					}
					if(num == 9) {
						obj[ objNumber ] = new Diamond(gp, col * gp.tilesize, row * gp.tilesize);
						gp.controller.AmountOfDiamond++;
						objNumber++;
						obj[ objNumber ] = new Dirt(gp, col * gp.tilesize, row * gp.tilesize);
						objNumber++;
					}

					col++;
				}
				if(col == gp.maxscreencol) {
					col = 0;
					row++;
				}
			}
			br.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private void RemoveOldEntity() {
		Arrays.fill(obj, null);
		Arrays.fill(monsterA, null);
		Arrays.fill(monsterB, null);
		player = null;
	}


}
