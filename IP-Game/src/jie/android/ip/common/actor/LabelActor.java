package jie.android.ip.common.actor;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
import com.badlogic.gdx.graphics.g2d.BitmapFontCache;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class LabelActor extends Actor {

	private String text;
//	private BitmapFont font;
	private BitmapFontCache cache;
	
	private final float oldX, oldY;
	
	public LabelActor(final String text, final BitmapFont font) {
		oldX = font.getScaleX();
		oldY = font.getScaleY();
		
		this.text = text;
		this.cache = new BitmapFontCache(font, true);
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		final BitmapFont font = cache.getFont();
		font.setScale(super.getScaleX(), super.getScaleY());

		cache.setColor(super.getColor());
		cache.setText(text, getX(), getY());
		cache.draw(batch, super.getColor().a * parentAlpha);
		
		font.setScale(oldX, oldY);
	}
	
	public void setText(final String text) {
		this.text = text;
	}
	
	public final TextBounds getBounds() {
		final BitmapFont font = cache.getFont();
		font.setScale(super.getScaleX(), super.getScaleY());

		TextBounds tb = cache.setText(text, getX(), getY());
		
		font.setScale(oldX, oldY);
		return tb;
	}
	
}
