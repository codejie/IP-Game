package jie.android.ip.common.actor;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
import com.badlogic.gdx.graphics.g2d.BitmapFontCache;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class LabelActor extends Actor {

	private String text;
	private BitmapFontCache cache;
	
	private final float oldX, oldY;
	
	public LabelActor(final String text, final BitmapFont font) {
		oldX = font.getScaleX();
		oldY = font.getScaleY();
		
		this.text = text;
		this.cache = new BitmapFontCache(font, true);
		
		this.getBounds();
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		final BitmapFont font = cache.getFont();
		font.setScale(super.getScaleX(), super.getScaleY());

		cache.setColor(super.getColor());
		cache.setText(text, getX(), getY());
		cache.draw(batch, super.getColor().a * parentAlpha);
		
		font.setScale(oldX, oldY);
	}
	
	public void setText(final String text) {
		this.text = text;
		this.getBounds();
	}
	
	public final TextBounds getBounds() {
		final BitmapFont font = cache.getFont();
		font.setScale(super.getScaleX(), super.getScaleY());

		TextBounds tb = cache.setText(text, getX(), getY());
		
		font.setScale(oldX, oldY);
		
		super.setWidth(tb.width);
		super.setHeight(tb.height);
		
		return tb;
	}

	@Override
	public void setScaleX(float scaleX) {
		super.setScaleX(scaleX);
		this.getBounds();
	}

	@Override
	public void setScaleY(float scaleY) {
		super.setScaleY(scaleY);
		this.getBounds();
	}

	@Override
	public void setScale(float scale) {
		super.setScale(scale);
		this.getBounds();
	}

	@Override
	public void setScale(float scaleX, float scaleY) {
		super.setScale(scaleX, scaleY);
		this.getBounds();
	}

	@Override
	public void scale(float scale) {
		super.scale(scale);
		this.getBounds();
	}

	@Override
	public void scale(float scaleX, float scaleY) {
		super.scale(scaleX, scaleY);
		this.getBounds();
	}

	@Override
	public void setPosition(float x, float y) {
		this.getBounds();		
		super.setPosition(x, y + getHeight());
	}
	
}
