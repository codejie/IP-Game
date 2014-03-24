package jie.android.ip.screen.menu;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import jie.android.ip.CommonConsts.PackConfig;
import jie.android.ip.common.actor.BaseGroupAccessor;
import jie.android.ip.common.actor.ButtonActor;
import jie.android.ip.common.actor.ScreenGroup;
import jie.android.ip.screen.BaseScreen;


public class CmdPanelGroup extends ScreenGroup {

	private final TextureAtlas textureAtlas;
	private final Skin skin;
	private final TweenManager tweenManager;
	
	public CmdPanelGroup(final BaseScreen screen) {
		super(screen);
		
		this.textureAtlas = super.resources.getTextureAtlas(PackConfig.SCREEN_PLAY);
		this.skin = new Skin(this.textureAtlas);
		this.tweenManager = this.screen.getTweenManager();		
	}

	@Override
	protected void initStage() {
		this.setBounds(Const.Cmd.BASE_X, Const.Cmd.BASE_Y, Const.Cmd.WIDTH, Const.Cmd.HEIGHT);
		
		final ButtonActor share = new ButtonActor(new Button.ButtonStyle(skin.getDrawable(Image.Cmd.SHARE_UP), skin.getDrawable(Image.Cmd.SHARE_DOWN), null));
		share.setPosition(Const.Cmd.X_SHARE, Const.Cmd.Y_SHARE);
		share.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				onShareClicked();
			}
			
		});
		this.addActor(share);
		
		final ButtonActor setting = new ButtonActor(new Button.ButtonStyle(skin.getDrawable(Image.Cmd.SETTING_UP), skin.getDrawable(Image.Cmd.SETTING_DOWN), null));
		setting.setPosition(Const.Cmd.X_SETTING, Const.Cmd.Y_SETTING);
		setting.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				onSettingClicked();
			}
			
		});		
		this.addActor(setting);
		
		this.screen.addActor(this);
	}
	
	public void show(boolean show) {
		if (show) {
			Tween.to(this, BaseGroupAccessor.POSITION_X, 0.2f).target(Const.Cmd.TARGET_X).start(tweenManager);
		} else {
			Tween.to(this, BaseGroupAccessor.POSITION_X, 0.2f).target(Const.Cmd.BASE_X).start(tweenManager);
		}
	}

	protected void onShareClicked() {
	}

	protected void onSettingClicked() {
	}
}
