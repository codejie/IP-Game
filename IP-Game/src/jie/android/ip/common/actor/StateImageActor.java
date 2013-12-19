package jie.android.ip.common.actor;

import java.util.HashMap;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class StateImageActor extends ImageActor {

	private HashMap<Integer, Drawable> stateMap = new HashMap<Integer, Drawable>();	
	private int state;
	
	public StateImageActor() {
		super();
	}
	
	public StateImageActor(int state, Drawable drawable) {
		addState(state, drawable);
		setState(state);
	}
	
	public StateImageActor(int state, TextureRegion region) {
		addState(state, region);	
		setState(state);	
	}	
	
	public void addState(int state, Drawable drawable) {
		stateMap.put(Integer.valueOf(state), drawable);
	}
	
	public void addState(int state, TextureRegion region) {
		stateMap.put(Integer.valueOf(state), new TextureRegionDrawable(region));
	}

	public boolean setState(int state) {
		Integer key = Integer.valueOf(state);
		Drawable drawable = stateMap.get(key);
		if (drawable != null) {
			this.state = state;
			setDrawable(drawable);
			return true;
		}
		return false;		
	}
	
	public int getState() {
		return state;
	}
}
