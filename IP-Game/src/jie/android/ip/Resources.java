package jie.android.ip;

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
		
//		skin.add("i", new TextureRegion(new Texture(Gdx.files.internal("data/I.png"))));
//		skin.add("am", new TextureRegion(new Texture(Gdx.files.internal("data/am.png"))));
//		skin.add("pro", new TextureRegion(new Texture(Gdx.files.internal("data/pro.png"))));
	}
	
	public final TextureAtlas getTextureAtlas() {
		return textureAtlas;
	}
	
	private void initTextureAtlas() {
		textureAtlas = new TextureAtlas(Gdx.files.internal("data/title.pack"));
	}
	
}
