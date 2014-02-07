package jie.android.ip.common.ttf;

import java.util.HashMap;

import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.utils.Disposable;

public class BitmapTrueFont implements Disposable {

	private final HashMap<Integer, BitmapFont> fontMap;
	
	private FreeTypeFontGenerator generator;
	
	public BitmapTrueFont() {
		
	}
	
	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	static public class BitmapTrueFontParameter extends AssetLoaderParameters<BitmapTrueFont> {

	}

	
}
