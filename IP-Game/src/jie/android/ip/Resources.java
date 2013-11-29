package jie.android.ip;

import jie.android.ip.CommonConsts.BoxConfig;
import jie.android.ip.CommonConsts.ResourceConfig;

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
//		initAssetManager();
		initSkin();
		initTextureAtlas();
		
		//for Debug
		loadAssetManager();
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
	
	public final void loadAssetManager() {
		assetManager = new AssetManager();
		assetManager.load(ResourceConfig.BOX_PACK_NAME, TextureAtlas.class);
		assetManager.load(ResourceConfig.FONT_18_NAME, BitmapFont.class);
		assetManager.load(ResourceConfig.CONSOLE_PACK_NAME, TextureAtlas.class);
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
