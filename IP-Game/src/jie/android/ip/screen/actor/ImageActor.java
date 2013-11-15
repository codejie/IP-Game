package jie.android.ip.screen.actor;

import jie.android.ip.utils.Utils;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class ImageActor extends Image {
	
	private final String name;
	
	private boolean isSelected = false;
	private OnImageActorInputListener onInputListener;

	public ImageActor(final String name, TextureRegion region) {
		super(region);
		this.name = name;
		
		initListener();		
	}
	
	public final String getName() {
		return name;
	}

	private void initListener() {
		this.addListener(new ClickListener() {
//			@Override
//			public void clicked(InputEvent event, float x, float y) {
//				Utils.logDebug("clicked - event = " + event.toString() + " x = " + x + " y = " + y);
//			}

			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return super.touchDown(event, x, y, pointer, button);
			}
			
		});
	}

	public void tweenToX(TweenManager manager, float target, float duration) {
		Tween.to(this, ImageActorAccessor.POSITION_X, duration).target(target).start(manager);
	}
	
	public void setSelected(boolean selected) {
		isSelected = selected;
	}
	
	public boolean isSelected() {
		return isSelected;
	}
	
	public void setInputListener(OnImageActorInputListener listener) {
		onInputListener = listener;
	}
}
