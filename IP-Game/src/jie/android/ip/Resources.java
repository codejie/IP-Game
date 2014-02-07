package jie.android.ip;

import jie.android.ip.CommonConsts.FontConfig;
import jie.android.ip.CommonConsts.PackConfig;
import jie.android.ip.CommonConsts.SoundConfig;
import jie.android.ip.common.ttf.BitmapTrueFont;
import jie.android.ip.common.ttf.BitmapTrueFontLoader;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Disposable;

public class Resources implements Disposable {

	private AssetManager assetManager;
	private BitmapTrueFont bitmapTrueFont;
	
	public Resources() {		
		initAssetManager();		
		//for Debug
		//loadAssetManager();
	}

	@Override
	public void dispose() {
//		if (bitmapTrueFont != null) {
//			bitmapTrueFont.dispose();
//		}
//		
		if (assetManager != null) {
			assetManager.dispose();
		}
	}

	private void initAssetManager() {
		assetManager = new AssetManager();
		assetManager.setLoader(BitmapTrueFont.class, new BitmapTrueFontLoader(new InternalFileHandleResolver()));
		assetManager.load(PackConfig.SCREEN_START, TextureAtlas.class);
		assetManager.finishLoading();
	}
	
	public final AssetManager getAssetManager() {
		return assetManager;
	}
	
	public final void loadAssetManager() {
		
		assetManager.load(PackConfig.SCREEN_PLAY, TextureAtlas.class);
		assetManager.load(PackConfig.SCREEN_MENU, TextureAtlas.class);
		assetManager.load(PackConfig.SCREEN_DIALOG, TextureAtlas.class);
		assetManager.load(FontConfig.FONT_18, BitmapFont.class);
		assetManager.load(FontConfig.FONT_20, BitmapFont.class);
		assetManager.load(FontConfig.FONT_24, BitmapFont.class);
//		assetManager.load(ResourceConfig.CONSOLE_PACK_NAME, TextureAtlas.class);
		
		assetManager.load(SoundConfig.MENU_CLICK, Sound.class);
		
		assetManager.load(FontConfig.FONT_TRUE, BitmapTrueFont.class, new BitmapTrueFont.BitmapTrueFontParameter(FontConfig.FONT_TRUE_CHARS));
		
		assetManager.finishLoading();
	}
	
	public final TextureAtlas getTextureAtlas(final String name) {
		return assetManager.get(name, TextureAtlas.class);
	}
	
	public final BitmapFont getBitmapFont(int size) {
		BitmapFont font = null;
		if (size == 18) {
			 font = assetManager.get(FontConfig.FONT_18, BitmapFont.class);
		} else if (size == 20) {
			font = assetManager.get(FontConfig.FONT_20, BitmapFont.class);
		} else {
			font = assetManager.get(FontConfig.FONT_24, BitmapFont.class);
		}
		
		if (font != null) {
			font.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
		}
		return font;
	}
	
	public final Sound getSuond(final String name) {
		return assetManager.get(name, Sound.class);
	}
	
	public final BitmapFont getBitmapTrueFont(int size) {
		if (bitmapTrueFont == null) {
			bitmapTrueFont = assetManager.get(FontConfig.FONT_TRUE, BitmapTrueFont.class);
		}
		return bitmapTrueFont.getBitmapFont(size);
	}
}
