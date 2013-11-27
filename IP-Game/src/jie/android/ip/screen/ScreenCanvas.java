package jie.android.ip.screen;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;

public class ScreenCanvas  implements Disposable {

	private final SpriteBatch spriteBatch;
	
	private ArrayList<Sprite> spriteArray = new ArrayList<Sprite>();
	
	public ScreenCanvas(SpriteBatch spriteBatch) {
		this.spriteBatch = spriteBatch;
	}

	@Override
	public void dispose() {
		spriteArray.clear();
	}

	public void addSprite(Sprite sprite) {
		spriteArray.add(sprite);
	}

	public void render(float delta) {
		spriteBatch.begin();
		for (final Sprite sprite : spriteArray) {
			sprite.draw(spriteBatch);
		}
		spriteBatch.end();
	}

}
