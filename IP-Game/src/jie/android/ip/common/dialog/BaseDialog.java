package jie.android.ip.common.dialog;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import jie.android.ip.CommonConsts.PackConfig;
import jie.android.ip.common.actor.BaseGroup;
import jie.android.ip.common.actor.BaseGroupAccessor;
import jie.android.ip.common.actor.ButtonActor;
import jie.android.ip.common.actor.ImageActor;
import jie.android.ip.common.dialog.DialogConfig.Consts;
import jie.android.ip.common.dialog.DialogConfig.Image;
import jie.android.ip.screen.BaseScreen;
import jie.android.ip.screen.play.PlayConfig.Const;


public class BaseDialog extends BaseGroup {

	public static final int BUTTON_POSITIVE = 1;
	public static final int BUTTON_NEGATIVE = 2;
	public static final int BUTTON_MIDDLE = 3;
	
	public interface ButtonClickListener {
		public void onClick(int id);
	}
	
	private BaseScreen screen;
	private final TextureAtlas textureAtlas;
	private final Skin skin;
	private final TweenManager tweenManager;
	
	private ImageActor backGround;
	private ButtonActor btnPositive;
	private ButtonActor btnNegative;
//	private ImageActor btnModdle;
	
	public BaseDialog(final BaseScreen screen) {
		this.screen = screen;
		this.textureAtlas = this.screen.getGame().getResources().getTextureAtlas(PackConfig.DIALOG);
		this.skin = new Skin(this.textureAtlas);
		this.tweenManager = this.screen.getTweenManager();
		
		initStage();
	}
	
	@Override
	protected void initStage() {
		
		this.setBounds(Consts.BASE_X, Consts.BASE_Y, Consts.WIDTH, Consts.HEIGHT);
		this.setScale(0.01f);
		
		backGround = new ImageActor(textureAtlas.findRegion(Image.BACKGROUND));
		backGround.setBounds(0, 0, getWidth(), getHeight());
		this.addActor(backGround);
	}
	
	public void show() {
		Tween.to(this, BaseGroupAccessor.SCALE_XY, duration)
		
		this.screen.addActor(this);
	}

	public void dismiss() {
		close();
	}
	
	protected void close() {
		this.screen.removeActor(this);
	}
	
	public void setPositiveButton(final ButtonClickListener listener) {
		btnPositive = new ButtonActor(skin, Image.BUTTON_POSITIVE_UP, Image.BUTTON_POSITIVE_DOWN);
		btnPositive.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				if (listener != null) {
					listener.onClick(BUTTON_POSITIVE);
				}
			}			
		});
		setButtonBounds();
		this.addActor(btnPositive);
	}
	
	public void setNegativeButton(final ButtonClickListener listener) {
		btnNegative = new ButtonActor(skin, Image.BUTTON_POSITIVE_UP, Image.BUTTON_POSITIVE_DOWN);
		btnNegative.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				if (listener != null) {
					listener.onClick(BUTTON_NEGATIVE);
				}
			}			
		});
		setButtonBounds();
		this.addActor(btnNegative);		
	}

	private void setButtonBounds() {
		float x = 0, y = 0;
		if (btnPositive != null) {
			if (btnNegative == null) {
				x = (getWidth() - Consts.BUTTON_WIDTH) / 2;
				y = Consts.BUTTON_BASE_Y;
			} else {
				x = (getWidth() - Consts.BUTTON_SPACE_X) / 2 - Consts.BUTTON_WIDTH;
				y = Consts.BUTTON_BASE_Y;
				btnNegative.setBounds(x + Consts.BUTTON_WIDTH + Consts.BUTTON_SPACE_X, y, Consts.BUTTON_WIDTH, Consts.BUTTON_HEIGHT);
			}
			btnPositive.setBounds(x, y, Consts.BUTTON_WIDTH, Consts.BUTTON_HEIGHT);			
		} else {
			if (btnNegative != null) {
				x = (getWidth() - Consts.BUTTON_WIDTH) / 2;
				y = Consts.BUTTON_BASE_Y;

				btnNegative.setBounds(x, y, Consts.BUTTON_WIDTH, Consts.BUTTON_HEIGHT);
			}
		}
	}

}
