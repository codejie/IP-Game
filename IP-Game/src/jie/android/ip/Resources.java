package jie.android.ip;

import jie.android.ip.CommonConsts.BoxConfig;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Disposable;

public class Resources implements Disposable {

	private Skin skin;
	private TextureAtlas textureAtlas;
	
	public Resources() {
		initSkin();
		initTextureAtlas();
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
		skin.add("t", new TextureRegion(new Texture(Gdx.files.internal("data/t.png")), BoxConfig.TRAY_WIDTH, BoxConfig.TRAY_HEIGHT));
		
	}
	
	public final TextureAtlas getTextureAtlas() {
		return textureAtlas;
	}
	
	private void initTextureAtlas() {
		textureAtlas = new TextureAtlas(Gdx.files.internal("data/title.pack"));
	}
	
}
