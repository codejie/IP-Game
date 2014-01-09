package jie.android.ip;

import jie.android.ip.CommonConsts.ScreenFontConfig;
import jie.android.ip.CommonConsts.ScreenPackConfig;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Disposable;

public class Resources implements Disposable {

	private AssetManager assetManager;
	
	public Resources() {		
		initAssetManager();		
		//for Debug
		loadAssetManager();
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
		
		assetManager.load(ScreenPackConfig.SCREEN_BOX, TextureAtlas.class);
		assetManager.load(ScreenFontConfig.FONT_18, BitmapFont.class);
		assetManager.load(ScreenFontConfig.FONT_20, BitmapFont.class);
		assetManager.load(ScreenFontConfig.FONT_24, BitmapFont.class);
//		assetManager.load(ResourceConfig.CONSOLE_PACK_NAME, TextureAtlas.class);
		assetManager.finishLoading();
	}
	
	public final TextureAtlas getTextureAtlas(final String name) {
		return assetManager.get(name, TextureAtlas.class);
	}
	
	public final BitmapFont getBitmapFont(int size) {
		BitmapFont font = null;
		if (size == 18) {
			 font = assetManager.get(ScreenFontConfig.FONT_18, BitmapFont.class);
		} else if (size == 20) {
			font = assetManager.get(ScreenFontConfig.FONT_20, BitmapFont.class);
		} else {
			font = assetManager.get(ScreenFontConfig.FONT_24, BitmapFont.class);
		}
		
		if (font != null) {
			font.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
		}
		return font;
	}
}
