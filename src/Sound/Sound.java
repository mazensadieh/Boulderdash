package Sound;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class Sound {
	private Clip clip;
	private URL[] soundURL = new URL[ 30 ];

	//Musik-Dateien importieren
	public Sound() {
		soundURL[ 0 ] = getClass().getResource("Game Sounds/MenuClickSound.wav");
		soundURL[ 1 ] = getClass().getResource("Game Sounds/MenuMusic.wav");
		soundURL[ 2 ] = getClass().getResource("Game Sounds/MenuENTER.wav");
		soundURL[ 3 ] = getClass().getResource("Game Sounds/PlayMusic.wav");
		soundURL[ 4 ] = getClass().getResource("Game Sounds/push-100769.wav");
		soundURL[ 5 ] = getClass().getResource("Game Sounds/Door Opening.wav");
		soundURL[ 6 ] = getClass().getResource("Game Sounds/heartbeat.wav");
		soundURL[ 7 ] = getClass().getResource("Game Sounds/diamond.wav");
		soundURL[ 8 ] = getClass().getResource("Game Sounds/negative_beeps-6008.wav");
		soundURL[ 9 ] = getClass().getResource("Game Sounds/winsquare-6993.wav");

	}

	//Musik-Datei laden
	public void setFile(int i) {
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[ i ]);
			clip = AudioSystem.getClip();
			clip.open(ais);
		} catch(Exception e) {

		}
	}

	//Musik abspielen
	public void play() {
		clip.start();
	}

	//Musikschleife unendlich
	public void loop() {
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}

	//Musikschleife stoppen
	public void stop() {
		clip.stop();
	}


}
