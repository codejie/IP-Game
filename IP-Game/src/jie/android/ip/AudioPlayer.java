package jie.android.ip;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.Disposable;

public class AudioPlayer implements Disposable {

	private final Resources resources;
	
	private Music music;
	private HashMap<String, Sound> mapSound = new HashMap<String, Sound>();
	
	private boolean musicEnabled = true;
	private boolean soundEnabled = false;//true;
	
	private String currentMusic;
	
	public AudioPlayer(final Resources resources) {
		this.resources = resources; 
	}
	
	@Override
	public void dispose() {
		if (music != null) {
			music.stop();
			music.dispose();
		}
	}

	public void playSound(final String res) {
		if (!soundEnabled) {
			return;
		}
		
		Sound inst = mapSound.get(res);
		if (inst == null) {
			inst = resources.getAssetManager().get(res);
			mapSound.put(res, inst);
		}
		inst.play();		
	}
	
	public void playMusic(final String res, boolean loop) {
		if (!musicEnabled) {
			return;
		}
		
		if (currentMusic.equals(res)) {
			if (music != null) {
				music.setLooping(loop);			
				if (!music.isPlaying()) {
					music.play();
				}
				return;
			}
		} else {
			currentMusic = res;			
		}

		music = Gdx.audio.newMusic(Gdx.files.internal(currentMusic));
		music.setLooping(loop);
		music.play();		
		
	}
	
	public void playMusic() {
		if (currentMusic != null) {
			playMusic(currentMusic, true);
		}
	}
	
	public void stopMusic() {
		if (music != null && music.isPlaying()) {
			music.stop();
		}
	}
	
	public void setDefaultMusic(final String res) {
		currentMusic = res;
	}
	
	public void enableSound(boolean enabled) {
		soundEnabled = enabled;
	}
	
	public void enableMusic(boolean enabled) {
		musicEnabled = enabled;
		if (musicEnabled) {
			playMusic();
		} else {
			stopMusic();
		}
	}

}
