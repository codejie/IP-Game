package jie.android.ip.common.dialog;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import jie.android.ip.CommonConsts.SystemConfig;
import jie.android.ip.common.actor.ButtonActor;
import jie.android.ip.common.actor.LabelActor;
import jie.android.ip.common.dialog.DialogConfig.Const;
import jie.android.ip.common.dialog.DialogConfig.Image;
import jie.android.ip.database.DBAccess;
import jie.android.ip.screen.BaseScreen;

public class SettingDialog extends BaseDialog {

	private final BitmapFont font;
	
	public SettingDialog(BaseScreen screen) {
		super(screen);
		
		font = super.resources.getBitmapTrueFont(30);
		
		setPositiveButton(new BaseDialog.ButtonClickListener() {
			
			@Override
			public void onClick(int id) {
				SettingDialog.this.dismiss();
			}
		});
		
		initSpeedItem();
	}

//	@Override
//	protected void initStage() {
//		super.initStage();
//
//		initSpeedItem();
////		initMusicItem();
////		initSoundItem();
//	}

	private void initSpeedItem() {
		final LabelActor title = new LabelActor("Speed", font);
		title.setPosition(Const.Setting.X_SPEED_TITLE, Const.Setting.Y_SPEED_TITLE);
		this.addActor(title);
		
//		final ButtonActor slow = new ButtonActor(skin, Image.Setting.SLOW_UP, Image.Setting.SLOW_DOWN, Image.Setting.SLOW_CHECK);
//		slow.addListener(new ClickListener() {
//			@Override
//			public void clicked(InputEvent event, float x, float y) {
//				settingChanged(SystemConfig.SYS_ATTR_SPEED, 1);
//			}
//			
//		});
//		slow.setPosition(Const.Setting.X_SPEED_BUTTON_SLOW, Const.Setting.Y_SPEED_BUTTON_SLOW);
//		this.addActor(slow);
//		
//		final ButtonActor fast = new ButtonActor(skin, Image.Setting.FAST_UP, Image.Setting.FAST_DOWN, Image.Setting.NORMAL_CHECK);
//		fast.addListener(new ClickListener() {
//			@Override
//			public void clicked(InputEvent event, float x, float y) {
//				settingChanged(SystemConfig.SYS_ATTR_SPEED, 2);
//			}
//			
//		});		
//		fast.setPosition(Const.Setting.X_SPEED_BUTTON_FAST, Const.Setting.Y_SPEED_BUTTON_FAST);		
//		this.addActor(fast);

		final ButtonActor normal = new ButtonActor(skin, Image.Setting.NORMAL_UP, Image.Setting.NORMAL_DOWN, Image.Setting.NORMAL_CHECK);
		normal.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				settingChanged(SystemConfig.SYS_ATTR_SPEED, 3);
			}
			
		});
		normal.setPosition(Const.Setting.X_SPEED_BUTTON_NORMAL, Const.Setting.Y_SPEED_BUTTON_NORMAL);
		this.addActor(normal);		
	}

	private void initMusicItem() {
		final LabelActor title = new LabelActor("Music", font);
		title.setPosition(Const.Setting.X_MUSIC_TITLE, Const.Setting.Y_MUSIC_TITLE);
		this.addActor(title);
		
		final ButtonActor open = new ButtonActor(skin, Image.Setting.OPEN_UP, Image.Setting.OPEN_DOWN, Image.Setting.OPEN_CHECK);
		open.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				settingChanged(SystemConfig.SYS_ATTR_MUSIC, 1);
			}
			
		});
		open.setPosition(Const.Setting.X_SPEED_BUTTON_MUSIC_OPEN, Const.Setting.Y_SPEED_BUTTON_MUSIC_OPEN);
		this.addActor(open);
		
		final ButtonActor close = new ButtonActor(skin, Image.Setting.CLOSE_UP, Image.Setting.CLOSE_DOWN, Image.Setting.CLOSE_CHECK);
		close.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				settingChanged(SystemConfig.SYS_ATTR_MUSIC, 0);
			}
			
		});		
		close.setPosition(Const.Setting.X_SPEED_BUTTON_MUSIC_CLOSE, Const.Setting.Y_SPEED_BUTTON_MUSIC_CLOSE);
		this.addActor(close);
	}

	private void initSoundItem() {
		final LabelActor title = new LabelActor("Sound", font);
		title.setPosition(Const.Setting.X_SOUND_TITLE, Const.Setting.Y_SOUND_TITLE);
		this.addActor(title);
		
		final ButtonActor open = new ButtonActor(skin, Image.Setting.OPEN_UP, Image.Setting.OPEN_DOWN, Image.Setting.OPEN_CHECK);
		open.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				settingChanged(SystemConfig.SYS_ATTR_SOUND, 1);
			}
			
		});		
		open.setPosition(Const.Setting.X_SPEED_BUTTON_SOUND_OPEN, Const.Setting.Y_SPEED_BUTTON_SOUND_OPEN);
		this.addActor(open);
		
		final ButtonActor close = new ButtonActor(skin, Image.Setting.CLOSE_UP, Image.Setting.CLOSE_DOWN, Image.Setting.CLOSE_CHECK);
		close.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				settingChanged(SystemConfig.SYS_ATTR_SOUND, 0);
			}
			
		});		
		close.setPosition(Const.Setting.X_SPEED_BUTTON_SOUND_CLOSE, Const.Setting.Y_SPEED_BUTTON_SOUND_CLOSE);
		this.addActor(close);
	}

	private void settingChanged(int id, int value) {
		final DBAccess dbAccess = super.screen.getGame().getDBAccess();
		dbAccess.setSysData(id, value, null);
	}
	
}
