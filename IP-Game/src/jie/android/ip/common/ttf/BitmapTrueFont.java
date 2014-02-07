package jie.android.ip.common.ttf;

import java.util.HashMap;

import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.utils.Disposable;

public class BitmapTrueFont implements Disposable {

	private final HashMap<Integer, BitmapFont> fontMap = new HashMap<Integer, BitmapFont>();
	
	private final FreeTypeFontGenerator generator;
	private final String chars;
	
	public BitmapTrueFont(final FreeTypeFontGenerator generator, final BitmapTrueFontParameter parameter) {
		this.generator = generator;
		this.chars = parameter.getChars();
	}
	
	@Override
	public void dispose() {
		for (final BitmapFont font : fontMap.values()) {
			if (font != null) {
				font.dispose();
			}
		}

		if (generator != null) {
			generator.dispose();
		}
	}
	
	public final BitmapFont getBitmapFont(int size) {
		if (generator == null) {
			return null;
		}
		
		BitmapFont font = fontMap.get(Integer.valueOf(size));
		if (font == null) {
			if (chars == null) {
				font = generator.generateFont(size);
			} else {
				font = generator.generateFont(size, chars, false);
			}
			fontMap.put(Integer.valueOf(size), font);
		}
		return font;
	}
	
	static public class BitmapTrueFontParameter extends AssetLoaderParameters<BitmapTrueFont> {
		private String chars = null;
		
		public BitmapTrueFontParameter() {			
		}
		
		public BitmapTrueFontParameter(final String chars) {
			this.chars = chars;
		}
		
		public final String getChars() {
			return chars;
		}
	}	
}
