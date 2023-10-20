package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import MenuStateEnum.Commandnumm;

//In die Klasse KeyHandler haben wir alle möglichen Eingabeoptionen implementiert. Bspw. Das Wählen einer option
//aus dem Hauptmenü/Pausenmenü oder die Pfeileneingaben, um die Spielfigur zu bewegen
public class keyHandler implements KeyListener {
	public boolean up, down, left, right, enter;
	private final GamePanel gp;

	public keyHandler(GamePanel gp) {
		this.gp = gp;
	}


	//verschiedene Menüs aufrufen nach dem gedruckten Taste.
	@Override
	public void keyTyped(KeyEvent e) {
		int code = e.getKeyCode();

		if(gp.gs == GameState.MAINMENU) {
			titleState(code);
		}
		if(gp.gs == GameState.PLAY) {
			playState(code);
		}
		if(gp.gs == GameState.DEAD) {
			DeadState(code);
		}
		if(gp.gs == GameState.PAUSE) {
			PauseState(code);
		}
		if(gp.gs == GameState.GAMEOVER) {
			GameOverState(code);
		}
		if(gp.gs == GameState.WIN) {
			LevelWin(code);
		}
		if(gp.gs == GameState.DEAD) {
			DeadState(code);
		}
		if(gp.gs == GameState.Finish) {
			FinishState(code);
		}
		if(gp.gs==GameState.Choose_Character){
			ChooseState(code);
		}
	}

	//verschiedene Menüs aufrufen nach dem gedruckten Taste.
	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();

		//Title State
		if(gp.gs == GameState.MAINMENU) {
			titleState(code);
		}
		if(gp.gs == GameState.PLAY) {
			playState(code);
		}
		if(gp.gs == GameState.DEAD) {
			DeadState(code);
		}
		if(gp.gs == GameState.PAUSE) {
			PauseState(code);
		}
		if(gp.gs == GameState.GAMEOVER) {
			GameOverState(code);
		}
		if(gp.gs == GameState.WIN) {
			LevelWin(code);
		}
		if(gp.gs == GameState.DEAD) {
			DeadState(code);
		}
		if(gp.gs == GameState.Finish) {
			FinishState(code);
		}
		if(gp.gs==GameState.Choose_Character){
			ChooseState(code);
		}

	}



	//Taste freigeben
	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();

		if(code == KeyEvent.VK_UP || code == KeyEvent.VK_W) {
			up = false;
		}
		if(code == KeyEvent.VK_DOWN || code == KeyEvent.VK_S) {
			down = false;
		}
		if(code == KeyEvent.VK_LEFT || code == KeyEvent.VK_A) {
			left = false;
		}
		if(code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_D) {
			right = false;
		}

	}

	//das Spiel erfolgt über rechts liegende Pfeile oder links liegende Buchstaben
	private void playState(int code) {

		if(code == KeyEvent.VK_UP || code == KeyEvent.VK_W) {
			up = true;
		} else if(code == KeyEvent.VK_DOWN || code == KeyEvent.VK_S) {
			down = true;
		} else if(code == KeyEvent.VK_LEFT || code == KeyEvent.VK_A) {
			left = true;
		} else if(code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_D) {
			right = true;
		}
		if(code == KeyEvent.VK_ESCAPE) {
			gp.gs = GameState.PAUSE;
		}
	}


	//Hauptmenü steuern
	private void titleState(int code) {

		//UP gedruckt
		if(code == KeyEvent.VK_UP || code == KeyEvent.VK_W) {
			gp.playSE(0);
			//change to previous option
			if(gp.menu.commandnum == Commandnumm.NEW_GAME)
				gp.menu.commandnum = Commandnumm.QUIT;
			else if(gp.menu.commandnum == Commandnumm.LOAD_GAME)
				gp.menu.commandnum = Commandnumm.NEW_GAME;
			else
				gp.menu.commandnum = Commandnumm.LOAD_GAME;
		}


		//Down gedruckt
		if(code == KeyEvent.VK_DOWN || code == KeyEvent.VK_S) {
			gp.playSE(0);
			//change to next option

			if(gp.menu.commandnum == Commandnumm.NEW_GAME)
				gp.menu.commandnum = Commandnumm.LOAD_GAME;
			else if(gp.menu.commandnum == Commandnumm.LOAD_GAME)
				gp.menu.commandnum = Commandnumm.QUIT;
			else
				gp.menu.commandnum = Commandnumm.NEW_GAME;


		}

		//Enter gedruckt
		if(code == KeyEvent.VK_ENTER) {

			gp.playSE(2);

			//New Game
			if(gp.menu.commandnum == Commandnumm.NEW_GAME) {
				//gp.controller.NewGame();
				gp.nameMenu.nameMenusetVisible(true);
			}

			//Load Game
			if(gp.menu.commandnum == Commandnumm.LOAD_GAME) {
				gp.saveMenu.savemenu(true);
				//gp.saveLoad.load();

			}

			//Quit
			if(gp.menu.commandnum == Commandnumm.QUIT) {
				System.exit(0);
			}
		}
	}

	//Deadmenü steuern
	private void DeadState(int code) {

		//UP gedruckt
		if(code == KeyEvent.VK_UP || code == KeyEvent.VK_W) {

			if(gp.dead.commandnum == Commandnumm.NEW_GAME)
				gp.dead.commandnum = Commandnumm.QUIT;

			else if(gp.dead.commandnum == Commandnumm.QUIT)
				gp.dead.commandnum = Commandnumm.RETRY;

			else
				gp.dead.commandnum = Commandnumm.NEW_GAME;

		}

		//Down gedruckt
		if(code == KeyEvent.VK_DOWN || code == KeyEvent.VK_S) {

			if(gp.dead.commandnum == Commandnumm.NEW_GAME)
				gp.dead.commandnum = Commandnumm.RETRY;

			else if(gp.dead.commandnum == Commandnumm.QUIT)
				gp.dead.commandnum = Commandnumm.NEW_GAME;

			else
				gp.dead.commandnum = Commandnumm.QUIT;
		}

		//Enter gedruckt
		if(code == KeyEvent.VK_ENTER) {

			//New Game
			if(gp.dead.commandnum == Commandnumm.NEW_GAME) {
				gp.controller.NewGame();
			}

			if(gp.dead.commandnum == Commandnumm.RETRY) {
				gp.playMusic(3);
				gp.mapM.load_Entity();
				gp.gs = GameState.PLAY;

			}

			//Quit
			if(gp.dead.commandnum == Commandnumm.QUIT) {
				System.exit(0);
			}
		}
	}

	//GameOvermenü steuern, Wenn die Zeit um ist oder alle Leben verlieren.
	private void GameOverState(int code) {

		if(code == KeyEvent.VK_ENTER) {
			if(gp.gameover.commandnum == Commandnumm.Main_Menu) {
				gp.stopMusic();
				gp.playMusic(1);
				gp.gs = GameState.MAINMENU;
			}
			if(gp.gameover.commandnum == Commandnumm.NEW_GAME) {
				gp.controller.NewGame();
			}

		}

		if(code == KeyEvent.VK_UP || code == KeyEvent.VK_W || code == KeyEvent.VK_DOWN || code == KeyEvent.VK_S) {

			if(gp.gameover.commandnum == Commandnumm.Main_Menu)
				gp.gameover.commandnum = Commandnumm.NEW_GAME;
			else
				gp.gameover.commandnum = Commandnumm.Main_Menu;

		}
	}


	//Pausemenü steuern
	private void PauseState(int code) {

		if(code == KeyEvent.VK_UP || code == KeyEvent.VK_W) {

			if(gp.pause.commandnum == Commandnumm.CONTINUE)
				gp.pause.commandnum = Commandnumm.Main_Menu;
			else if(gp.pause.commandnum == Commandnumm.SAVE)
				gp.pause.commandnum = Commandnumm.CONTINUE;
			else
				gp.pause.commandnum = Commandnumm.SAVE;
		} else if(code == KeyEvent.VK_DOWN || code == KeyEvent.VK_S) {

			if(gp.pause.commandnum == Commandnumm.SAVE)
				gp.pause.commandnum = Commandnumm.Main_Menu;
			else if(gp.pause.commandnum == Commandnumm.CONTINUE)
				gp.pause.commandnum = Commandnumm.SAVE;
			else
				gp.pause.commandnum = Commandnumm.CONTINUE;
		}

		if(code == KeyEvent.VK_ENTER) {

			if(gp.pause.commandnum == Commandnumm.CONTINUE)
				gp.gs = GameState.PLAY;

			else if(gp.pause.commandnum == Commandnumm.SAVE) {
				gp.saveMenu.savemenu(true);
			}else if(gp.gameover.commandnum == Commandnumm.Main_Menu) {
				gp.stopMusic();
				gp.playMusic(1);
				gp.gs = GameState.MAINMENU;
			}
		}


	}

	//Levelmenü steuern
	private void LevelWin(int code) {

		//UP gedruckt
		if(code == KeyEvent.VK_UP || code == KeyEvent.VK_W) {

			if(gp.win.commandnum == Commandnumm.CONTINUE)
				gp.win.commandnum = Commandnumm.Main_Menu;

			else if(gp.win.commandnum == Commandnumm.Main_Menu)
				gp.win.commandnum = Commandnumm.SAVE;

			else
				gp.win.commandnum = Commandnumm.CONTINUE;
		}


		//Down gedruckt
		if(code == KeyEvent.VK_DOWN || code == KeyEvent.VK_S) {

			if(gp.win.commandnum == Commandnumm.CONTINUE)
				gp.win.commandnum = Commandnumm.SAVE;

			else if(gp.win.commandnum == Commandnumm.Main_Menu)
				gp.win.commandnum = Commandnumm.CONTINUE;

			else
				gp.win.commandnum = Commandnumm.Main_Menu;
		}

		//Enter gedruckt
		if(code == KeyEvent.VK_ENTER) {

			//Speichern
			if(gp.win.commandnum == Commandnumm.SAVE) {
				gp.saveMenu.savemenu(true);
			}

			//Fortsetzen
			if(gp.win.commandnum == Commandnumm.CONTINUE) {

				gp.playMusic(3);
				gp.mapM.load_Entity();
				gp.gs = GameState.PLAY;

			}

			//zurück zum Haupt Menu
			if(gp.win.commandnum == Commandnumm.Main_Menu) {
				gp.stopMusic();
				gp.playMusic(1);
				gp.gs = GameState.MAINMENU;
			}
		}

	}
	//SpielFinishmenü steuern
	private void FinishState(int code) {

		if(code == KeyEvent.VK_ENTER) {
			if(gp.finish.commandnum == Commandnumm.Main_Menu) {
				gp.stopMusic();
				gp.playMusic(1);
				gp.gs = GameState.MAINMENU;
			}
			if(gp.finish.commandnum == Commandnumm.QUIT) {
				System.exit(0);
			}

		}

		if(code == KeyEvent.VK_UP || code == KeyEvent.VK_W || code == KeyEvent.VK_DOWN || code == KeyEvent.VK_S) {

			if(gp.finish.commandnum == Commandnumm.Main_Menu)
				gp.finish.commandnum = Commandnumm.QUIT;
			else
				gp.finish.commandnum = Commandnumm.Main_Menu;

		}
	}

	//SpielfigurWahlmenü steuern
	private void ChooseState(int code) {
		if(code == KeyEvent.VK_ENTER) {
			if(gp.choose_character.commandnum == Commandnumm.FirstCharackter) {
				gp.utool.choose = "Boy";
				gp.utool.ChoosedPlayer();
				gp.controller.NewGame();
			}
			if(gp.choose_character.commandnum == Commandnumm.SecondCharackter) {
				gp.utool.choose = "Girl";
				gp.utool.ChoosedPlayer();
				gp.controller.NewGame();
			}
			if(gp.choose_character.commandnum == Commandnumm.Main_Menu) {
				gp.gs = GameState.MAINMENU;
			}
		}
		if(code == KeyEvent.VK_UP || code == KeyEvent.VK_W || code == KeyEvent.VK_DOWN || code == KeyEvent.VK_S) {
			if(gp.choose_character.commandnum == Commandnumm.Main_Menu)
				gp.choose_character.commandnum = Commandnumm.FirstCharackter;
			else
				gp.choose_character.commandnum = Commandnumm.Main_Menu;

		}

		if(code == KeyEvent.VK_LEFT || code == KeyEvent.VK_A || code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_D) {

			if(gp.choose_character.commandnum == Commandnumm.SecondCharackter)
				gp.choose_character.commandnum = Commandnumm.FirstCharackter;
			else
				gp.choose_character.commandnum = Commandnumm.SecondCharackter;

		}
	}

}
