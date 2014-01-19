package jie.android.ip;

import java.util.HashMap;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.Disposable;

public class Sounder implements Disposable {

	private final Resources resources; 
	
	private HashMap<String, Sound> mapSound = new HashMap<String, Sound>();
	private boolean enabled = true;
	
	public Sounder(final Resources resources) {
		this.resources = resources;
	}

	@Override
	public void dispose() {
	}

	public void play(final String sound) {
		if (!enabled) {
			return;
		}
		
		Sound inst = mapSound.get(sound);
		if (inst == null) {
			inst = resources.getAssetManager().get(sound);
			mapSound.put(sound, inst);
		}
		inst.play();		
	}
	
	public void enabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	public boolean enabled() {
		return this.enabled;
	}
}
