package jie.android.ip.common.ttf;

import jie.android.ip.common.ttf.BitmapTrueFont.BitmapTrueFontParameter;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.utils.Array;

public class BitmapTrueFontLoader extends AsynchronousAssetLoader<BitmapTrueFont, BitmapTrueFont.BitmapTrueFontParameter> {

//	private BitmapTrueFont font;
	
	public BitmapTrueFontLoader(FileHandleResolver resolver) {
		super(resolver);
	}

	@Override
	public void loadAsync(AssetManager manager, String fileName, FileHandle file, BitmapTrueFontParameter parameter) {
	}

	@Override
	public BitmapTrueFont loadSync(AssetManager manager, String fileName, FileHandle file, BitmapTrueFontParameter parameter) {
		return new BitmapTrueFont(new FreeTypeFontGenerator(file), parameter);
	}

	@Override
	public Array<AssetDescriptor> getDependencies(String fileName, FileHandle file, BitmapTrueFontParameter parameter) {
		return null;
	}
}
