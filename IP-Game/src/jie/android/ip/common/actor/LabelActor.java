package jie.android.ip.common.actor;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.HAlignment;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
import com.badlogic.gdx.graphics.g2d.BitmapFontCache;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class LabelActor extends Actor {

	private String text;
	private BitmapFontCache cache;
	
	private boolean wrap = false;
	private float wrapWidth;
	private HAlignment lineAlign = HAlignment.CENTER;//.LEFT; 
	
	private final float oldX, oldY;
	
	private boolean sizeInvalid = true;
	
	private final TextBounds bounds = new TextBounds();
	
	public LabelActor(final String text, final BitmapFont font) {
		oldX = font.getScaleX();
		oldY = font.getScaleY();
		
		this.text = text;
		this.cache = new BitmapFontCache(font, true);
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		
		if (sizeInvalid) {
			this.getBounds();
		}
		
		final BitmapFont font = cache.getFont();
		font.setScale(super.getScaleX(), super.getScaleY());

		cache.setColor(super.getColor());
		//cache.setText(text, getX(), getY());
		if (!wrap) {
			cache.setMultiLineText(text, getX(), getY());
		} else {
			cache.setWrappedText(text, getX(), getY(), wrapWidth, lineAlign);
		}
		cache.draw(batch, super.getColor().a * parentAlpha);
		
		font.setScale(oldX, oldY);
	}
	
	public void setText(final String text) {
		this.text = text;
	}
	
	private void computeSize() {

		final BitmapFont font = cache.getFont();
		font.setScale(super.getScaleX(), super.getScaleY());
		if (!wrap) {
			bounds.set(font.getMultiLineBounds(text));//  cache.setText(text, getX(), getY());		
		} else {
			bounds.set(font.getWrappedBounds(text, wrapWidth));
		}
		font.setScale(oldX, oldY);
		
		super.setWidth(bounds.width);
		super.setHeight(bounds.height);
		
		sizeInvalid = false;
	}
	
	public final TextBounds getBounds() {
		if (sizeInvalid) {
			computeSize();
		}
		return this.bounds;	
	}

	@Override
	public void setPosition(float x, float y) {
		super.setPosition(x, y + getHeight());
	}
	
	public void setWrap(boolean wrap) {
		this.wrap = wrap;
		sizeInvalid = true;
	}
	
	public void setWrapWidth(float width) {
		this.wrapWidth = width;
		sizeInvalid = true;
	}
	
	public void setTextAlign(final HAlignment align) {
		this.lineAlign = align;
	}	

	@Override
	public float getWidth() {
		if (sizeInvalid) {
			computeSize();
		}
		return super.getWidth();
	}

	@Override
	public float getHeight() {
		if (sizeInvalid) {
			computeSize();
		}
		return super.getHeight();
	}
		
}
