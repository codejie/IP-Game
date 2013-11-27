package jie.android.ip;

import jie.android.ip.CommonConsts.BoxConfig;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Disposable;

public class Resources implements Disposable {

	private Skin skin;
	private TextureAtlas textureAtlas;
	
	private AssetManager assetManager;
	
	public Resources() {
		initAssetManager();
		initSkin();
		initTextureAtlas();
	}

	@Override
	public void dispose() {
		if (assetManager != null) {
			assetManager.dispose();
		}
		skin.dispose();
	}
	
	public final AssetManager getAssetManager() {
		return assetManager;
	}
	
	private void initAssetManager() {
		assetManager = new AssetManager();
		assetManager.load("data/box.pack", TextureAtlas.class);
		assetManager.load("data/font/arial-18.fnt", BitmapFont.class);
		assetManager.finishLoading();
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
