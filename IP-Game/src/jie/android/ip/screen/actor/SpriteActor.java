package jie.android.ip.screen.actor;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import jie.android.ip.screen.BaseActor;

public class SpriteActor extends BaseActor {

	private Sprite sprite;
	
	public SpriteActor() {
		super();
		
		sprite = new Sprite();
	}
	
	public SpriteActor(final TextureRegion region) {
		sprite = new Sprite(region);
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		sprite.draw(batch, parentAlpha);		
	}
	
	
	
}
