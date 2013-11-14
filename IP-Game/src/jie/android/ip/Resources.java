package jie.android.ip;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Disposable;

public class Resources implements Disposable {

	private Skin skin;
	
	public Resources() {
		initSkin();
	}

	@Override
	public void dispose() {
		skin.dispose();
	} 
	
	public final Skin getSkin() {
		return skin;
	}
	
	private void initSkin() {
		skin = new Skin();
		
		TextureRegion rt = new TextureRegion(new Texture(Gdx.files.internal("data/ic.png")));
		
		skin.add("ic", rt);
	}
	
}
