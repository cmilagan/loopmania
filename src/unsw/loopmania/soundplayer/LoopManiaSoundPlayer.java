package unsw.loopmania.soundplayer;

import javafx.scene.media.AudioClip;
import unsw.loopmania.LoopManiaApplication;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class LoopManiaSoundPlayer {

	@SuppressWarnings("serial")
	private static Map<LoopManiaSound, AudioClip> sounds = new HashMap<LoopManiaSound, AudioClip>() {
		
		{
			put(LoopManiaSound.FIGHT, new AudioClip(new File("src/sounds/fight.mp3").toURI().toString()));
			put(LoopManiaSound.ITEM_EQUIP, new AudioClip(new File("src/sounds/equip.mp3").toURI().toString()));
			put(LoopManiaSound.POTION, new AudioClip(new File("src/sounds/potion.mp3").toURI().toString()));
			put(LoopManiaSound.SHOP_ENTER, new AudioClip(new File("src/sounds/shop.mp3").toURI().toString()));
			put(LoopManiaSound.WIN, new AudioClip(new File("src/sounds/win.mp3").toURI().toString()));
			put(LoopManiaSound.GAME_OVER, new AudioClip(new File("src/sounds/gameover.mp3").toURI().toString()));
			put(LoopManiaSound.MUSIC, new AudioClip(new File("src/sounds/bgm.mp3").toURI().toString()));
			put(LoopManiaSound.CLICK, new AudioClip(new File("src/sounds/click.mp3").toURI().toString()));
			put(LoopManiaSound.BUILD, new AudioClip(new File("src/sounds/build.mp3").toURI().toString()));
			put(LoopManiaSound.ERROR, new AudioClip(new File("src/sounds/error.mp3").toURI().toString()));
			put(LoopManiaSound.REVIVE, new AudioClip(new File("src/sounds/onering.mp3").toURI().toString()));

		}
	};

	// static private void adjustVolume() {
	// 	sounds.forEach((type, audio) -> audio.setVolume(LoopManiaApplication.getGameVolume() / 100D));
	// }

	static public void playSoundEffect(LoopManiaSound sound_type) {
		// adjustVolume();
		sounds.get(sound_type).play();
	}

	static public void playBGM() {
		// adjustVolume();
		AudioClip MUSIC = sounds.get(LoopManiaSound.MUSIC);
		MUSIC.setCycleCount(AudioClip.INDEFINITE);
		;
		MUSIC.stop();
		MUSIC.play();
	}

	static public void stopBGM() {
		AudioClip MUSIC = sounds.get(LoopManiaSound.MUSIC);
		MUSIC.stop();
	}
}