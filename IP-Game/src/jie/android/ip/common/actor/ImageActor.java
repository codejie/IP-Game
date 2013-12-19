package jie.android.ip.common.actor;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class ImageActor extends Actor {

	private Drawable drawable;
	private Object data;
	
	public ImageActor(Drawable drawable) {
		setDrawable(drawable);
	}
	
	public ImageActor() {
		this((Drawable)null);
	}
	
	public ImageActor(TextureRegion region) {
		this(new TextureRegionDrawable(region));
	}
	
	private float getMinWidth() {
		if (drawable != null) {
			return drawable.getMinWidth();
		}
		return 0;
	}
	
	private float getMinHeight() {
		if (drawable != null) {
			return drawable.getMinHeight();			
		}
		return 0;
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {

		if (drawable == null) {
			return;
		}
		
		Color color = getColor();
		batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);

		float x = getX();
		float y = getY();
		float scaleX = getScaleX();
		float scaleY = getScaleY();
		
		if (drawable.getClass() == TextureRegionDrawable.class) {
			TextureRegion region = ((TextureRegionDrawable)drawable).getRegion();
			float rotation = getRotation();
			if (scaleX == 1 && scaleY == 1 && rotation == 0)
				batch.draw(region, x, y, getWidth(), getHeight());
			else {
				batch.draw(region, x, y, getOriginX(), getOriginY(), getWidth(), getHeight(),
					scaleX, scaleY, rotation);
			}
		} else {
			drawable.draw(batch, x, y, getWidth() * scaleX, getHeight() * scaleY);
		}
	}
	
	public void setDrawable(Drawable drawable) {
		this.drawable = drawable;
		setWidth(getMinWidth());
		setHeight(getMinHeight());		
	}
	
	public void setData(final Object data) {
		this.data = data;
	}
	
	public final Object getData() {
		return this.data;
	}
}
