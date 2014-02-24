package jie.android.ip.common.dialog;

import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import jie.android.ip.CommonConsts.PackConfig;
import jie.android.ip.common.actor.ButtonActor;
import jie.android.ip.common.actor.ImageActor;
import jie.android.ip.common.actor.LabelActor;
import jie.android.ip.common.actor.ScreenGroup;
import jie.android.ip.common.dialog.DialogConfig.Const;
import jie.android.ip.common.dialog.DialogConfig.Image;
import jie.android.ip.screen.BaseScreen;


public class BaseDialog extends ScreenGroup {

	public static final int BUTTON_POSITIVE = 1;
	public static final int BUTTON_NEGATIVE = 2;
	public static final int BUTTON_MIDDLE = 3;
	
	public interface ButtonClickListener {
		public void onClick(int id);
	}
	
	private final TextureAtlas textureAtlas;
	private final Skin skin;
	private final TweenManager tweenManager;
	
	private ImageActor background;
	private ImageActor window;
	private ButtonActor btnPositive;
	private ButtonActor btnNegative;
//	private ImageActor btnModdle;
	
	private boolean isShow = false;
	
	public BaseDialog(final BaseScreen screen) {
		super(screen);
		this.textureAtlas = super.resources.getTextureAtlas(PackConfig.SCREEN_DIALOG);
		this.skin = new Skin(this.textureAtlas);
		this.tweenManager = this.screen.getTweenManager();
		
		initStage();
	}
	 
	@Override
	protected void initStage() {
		
		this.setBounds(Const.BASE_X, Const.BASE_Y, Const.WIDTH, Const.HEIGHT);
		//this.setScale(0.01f);
		
		background = new ImageActor(textureAtlas.findRegion(Image.BACKGROUND));
		background.setBounds(Const.BASE_X, Const.BASE_Y, Const.WIDTH, Const.HEIGHT);
		this.addActor(background);		
		
		window = new ImageActor(textureAtlas.findRegion(Image.WINDOW));
		window.setBounds(Const.BASE_X_WINDOW, Const.BASE_Y_WINDOW, Const.WIDTH_WINDOW, Const.HEIGHT_WINDOW);
		this.addActor(window);
	}
	
	public void show() {
		this.screen.addActor(this);
		this.setZIndex(0x1f);
		isShow = true;
	}

	public void dismiss() {
		close();
	}
	
	protected void close() {
		this.screen.removeActor(this);
		isShow = false;
	}
	
	public boolean isShow() {
		return isShow;
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
		btnNegative = new ButtonActor(skin, Image.BUTTON_NEGATIVE_UP, Image.BUTTON_NEGATIVE_DOWN);
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
				x = Const.BASE_X_WINDOW + (Const.WIDTH_WINDOW - Const.BUTTON_WIDTH) / 2;
				y = Const.BUTTON_BASE_Y;
			} else {
				x = Const.BASE_X_WINDOW + (Const.WIDTH_WINDOW - Const.BUTTON_SPACE_X) / 2 - Const.BUTTON_WIDTH;
				y = Const.BUTTON_BASE_Y;
				btnNegative.setBounds(x + Const.BUTTON_WIDTH + Const.BUTTON_SPACE_X, y, Const.BUTTON_WIDTH, Const.BUTTON_HEIGHT);
			}
			btnPositive.setBounds(x, y, Const.BUTTON_WIDTH, Const.BUTTON_HEIGHT);			
		} else {
			if (btnNegative != null) {
				x = Const.BASE_X_WINDOW + (Const.WIDTH_WINDOW - Const.BUTTON_WIDTH) / 2;
				y = Const.BUTTON_BASE_Y;

				btnNegative.setBounds(x, y, Const.BUTTON_WIDTH, Const.BUTTON_HEIGHT);
			}
		}
	}
	
	public void setTextImage(final String res) {
		ImageActor text = new ImageActor(textureAtlas.findRegion(res));
		float x = Const.BASE_X_WINDOW + (Const.WIDTH_WINDOW - text.getWidth()) / 2;
		float y = Const.TEXT_BASE_Y + (Const.TEXT_HEIGHT - text.getHeight()) / 2; 
			
		text.setPosition(x, y);// .setBounds(x, y, Const.TEXT_WIDTH, Const.TEXT_HEIGHT);
		this.addActor(text);
		text.setZIndex(0x0f);
	}
	
	public void setTextString(final String text, final BitmapFont font, final Color color) {
		LabelActor label = new LabelActor(text,font);
		label.setColor(color);
		
		float x = Const.BASE_X_WINDOW + (Const.WIDTH_WINDOW - label.getWidth()) / 2;
		float y = Const.TEXT_BASE_Y + (Const.TEXT_HEIGHT - label.getHeight()) / 2;
		
		label.setPosition(x, y);
		this.addActor(label);
		label.setZIndex(0x0f);
	}
	
	public void setTextString(int x, int y, final String text, final BitmapFont font, final Color color) {
		LabelActor label = new LabelActor(text,font);
		label.setColor(color);

		label.setPosition(x, y);
		this.addActor(label);		
	}
}
