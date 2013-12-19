package jie.android.ip;

import jie.android.ip.CommonConsts.ScreenPackConfig;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Disposable;

public class Resources implements Disposable {

	private AssetManager assetManager;
	
	public Resources() {
		
		initAssetManager();
		
		//for Debug
		//loadAssetManager();
	}

	@Override
	public void dispose() {
		if (assetManager != null) {
			assetManager.dispose();
		}
	}

	private void initAssetManager() {
		assetManager = new AssetManager();
		assetManager.load(ScreenPackConfig.SCREEN_START, TextureAtlas.class);
		assetManager.finishLoading();
	}
	
	public final AssetManager getAssetManager() {
		return assetManager;
	}
	
	public final void loadAssetManager() {
//		assetManager = new AssetManager();
//		
//		assetManager.load(ResourceConfig.Pack.SCREEN_START, TextureAtlas.class);
		
		assetManager.load(ScreenPackConfig.SCREEN_BOX, TextureAtlas.class);
//		assetManager.load(ResourceConfig.FONT_18_NAME, BitmapFont.class);
//		assetManager.load(ResourceConfig.CONSOLE_PACK_NAME, TextureAtlas.class);
		assetManager.finishLoading();
	}
	
	public final TextureAtlas getTextureAtlas(final String name) {
		return assetManager.get(name, TextureAtlas.class);
	}
	
	
}
