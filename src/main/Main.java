package main;

import javax.swing.*;

public class Main {

	public static JFrame windows;

	public static void main(String[] args) {
		windows = new JFrame();
		windows.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		windows.setResizable(false);
		windows.setTitle("dash");

		GamePanel gamePanel = new GamePanel();
		windows.add(gamePanel);
		windows.pack();
		windows.setLocationRelativeTo(null);
		windows.setVisible(true);

		gamePanel.setupGame();
		gamePanel.startgameThread();
	}
}