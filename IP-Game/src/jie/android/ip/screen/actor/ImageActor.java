package jie.android.ip.screen.actor;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class ImageActor extends Image {

	private final String name;

	private boolean isSelected = false;
	private OnActorInputListener onInputListener;

	public ImageActor(final String name, TextureRegion region) {
		super(region);
		this.name = name;

		initListener();
	}

	public final String getName() {
		return name;
	}

	private void initListener() {
		final ImageActor actor = this;
		this.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				if (onInputListener != null) {
					onInputListener.onTouchDown(actor, x, y, pointer, button);
				}
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				if (onInputListener != null) {
					onInputListener.onTouchUp(actor, x, y, pointer, button);
				}
			}

			@Override
			public void touchDragged(InputEvent event, float x, float y, int pointer) {
				if (onInputListener != null) {
					onInputListener.onTouchDragged(actor, x, y, pointer);
				}
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

	public void setInputListener(OnActorInputListener listener) {
		onInputListener = listener;
	}
}
