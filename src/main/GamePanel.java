package main;

import MenuState.*;
import Obj.ObjController;
import SaveLoad.SaveLoad;
import Sound.Sound;
import ai.CollisionCheck;
import ai.PathFinder;
import map.MapManager;


import javax.swing.*;
import java.awt.*;

//In die Klasse GamePanel haben wir das Spielfeld und seine Funktionalität implementiert
public class GamePanel extends JPanel implements Runnable {
	//Screan Settings
	public final int tilesize = 60;   //60*60 tile
	public final int maxscreencol = 16; // 16 column
	public final int maxscreenrow = 12; // 12 rows
	public final int screenheight = tilesize * maxscreenrow;    //720 pixels
	public final int screenwidth = tilesize * maxscreencol;    //960 pixels

	//Game State
	public GameState gs = GameState.MAINMENU;
	public Imageloader utool = new Imageloader(this);
	public MapManager mapM= new MapManager(this);

	public UI ui = new UI(this);
	//MENU Draw
	public MainMenu menu = new MainMenu(this);
	public Pause pause = new Pause(this);
	public GameOver gameover = new GameOver(this);
	public Dead dead = new Dead(this);
	public LevelWin win = new LevelWin(this);
	public SaveLoad saveLoad = new SaveLoad(this);
	public Finish finish = new Finish(this);
	Choose_Character choose_character = new Choose_Character(this);
	//Graphics
	private Graphics2D g2;
	//KeyHandler
	public keyHandler keyh = new keyHandler(this);
	//Map
	public CollisionCheck checker = new CollisionCheck(this);
	public PathFinder pathFinder = new PathFinder(this);
	public ObjController controller = new ObjController(this);
	private Sound SE = new Sound();
	private Sound music = new Sound();
	public SaveMenu saveMenu = new SaveMenu(this);
	public NameMenu nameMenu = new NameMenu(this);
	private Thread gameThread;

	//Constructor to set up (Screan Width and height) / (keyListener)
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenwidth, screenheight));
		this.addKeyListener(keyh);
		this.setFocusable(true);
		saveLoad.LoadHighScore();
	}

	//First game run state
	public void setupGame() {
		gs = GameState.MAINMENU;
		playMusic(1);
	}



	public void startgameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	@Override
	public void run() {
		double drawInterval = 1000000000.0/60;
		double nextDrawTime = System.nanoTime()+drawInterval;

		while (gameThread !=null){
			update();
			repaint();

			try {
				double remainingTime= nextDrawTime-System.nanoTime();
				remainingTime=remainingTime/1000000;
				if(remainingTime<0){
					remainingTime=0;
				}
				Thread.sleep((long) remainingTime);
				nextDrawTime+=drawInterval;
			}catch (InterruptedException e){
				e.printStackTrace();
			}
		}
	}

	private void update() {
		saveMenu.update();
		if(gs == GameState.PLAY) {
			if(mapM.player != null)
					mapM.player.update();
			for(int i = 0 ; i < mapM.monsterA.length ; i++)
				if(mapM.monsterA[ i ] != null)
					mapM.monsterA[ i ].update();

			for(int i = 0 ; i < mapM.monsterB.length ; i++)
				if(mapM.monsterB[ i ] != null)
					mapM.monsterB[ i ].update();

			for(int i = 0 ; i < mapM.obj.length ; i++)
				if(mapM.obj[ i ] != null)
					mapM.obj[ i ].update();
		}
	}



	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g2 = (Graphics2D)g;
		mapM.draw(g2);
		for(int i = 0 ; i < mapM.obj.length ; i++) {
			if(mapM.obj[ i ] != null) {
				mapM.obj[ i ].draw(g2);
			}
			if(mapM.obj[ i ] != null) {
				if(mapM.player.YPosition < mapM.obj[ i ].YPosition) {
					mapM.player.draw(g2);
					mapM.obj[ i ].draw(g2);
				} else {
					mapM.obj[ i ].draw(g2);
					mapM.player.draw(g2);
				}
			}
		}
		for(int i = 0 ; i < mapM.monsterB.length ; i++)
			if(mapM.monsterB[ i ] != null)
				mapM.monsterB[ i ].draw(g2);
		for(int i = 0 ; i < mapM.monsterA.length ; i++)
			if(mapM.monsterA[ i ] != null)
				mapM.monsterA[ i ].draw(g2);
		ui.draw(g2);
		//Draw multi Menus
		if(gs == GameState.MAINMENU) {
			ui.PlayTime = ui.StartTime;
			menu.draw(g2);
		} else if(gs == GameState.WIN) {
			win.draw(g2);
		} else if(gs == GameState.PAUSE) {
			pause.draw(g2);
		} else if(gs == GameState.GAMEOVER) {
			gameover.draw(g2);
		} else if(gs == GameState.DEAD) {
			dead.draw(g2);
		}
		else if(gs == GameState.Finish){
			finish.draw(g2);
		}
		else if(gs == GameState.Choose_Character){
			choose_character.draw(g2);
		}
		g2.dispose();
	}

	public void playMusic(int i) {
	//	music.setFile(i);
		//music.play();
	//	music.loop();
	}

	public void stopMusic() {
		//music.stop();
	}

	public void playSE(int i) {
		//SE.setFile(i);
	//	SE.play();
	}
}

