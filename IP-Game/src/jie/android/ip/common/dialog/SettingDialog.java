package jie.android.ip.common.dialog;

import com.badlogic.gdx.graphics.Color;
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
	
	public SettingDialog(final BaseScreen screen) {
		super(screen);
		
		font = super.resources.getBitmapTrueFont(55);
		
		setOKButton(new BaseDialog.ButtonClickListener() {
			
			@Override
			public void onClick(int id) {
				SettingDialog.this.dismiss();
			}
		});
		
		initSpeedItem();
		initMusicItem();
		initSoundItem();
	}

	private void initSpeedItem() {
		final LabelActor title = new LabelActor("Clock Speed", font);
		title.setColor(new Color(0.56f, 0.99f, 0.43f, 1.f));
		title.setPosition(Const.Setting.X_SPEED_TITLE, Const.Setting.Y_SPEED_TITLE);
		this.addActor(title);
		
		final ButtonActor slow = new ButtonActor(skin, Image.Setting.SLOW_UP, Image.Setting.SLOW_DOWN, Image.Setting.SLOW_CHECK);
		slow.setPosition(Const.Setting.X_SPEED_BUTTON_SLOW, Const.Setting.Y_SPEED_BUTTON_SLOW);
		this.addActor(slow);
		
		final ButtonActor fast = new ButtonActor(skin, Image.Setting.FAST_UP, Image.Setting.FAST_DOWN, Image.Setting.NORMAL_CHECK);
		fast.setPosition(Const.Setting.X_SPEED_BUTTON_FAST, Const.Setting.Y_SPEED_BUTTON_FAST);		
		this.addActor(fast);

		final ButtonActor normal = new ButtonActor(skin, Image.Setting.NORMAL_UP, Image.Setting.NORMAL_DOWN, Image.Setting.NORMAL_CHECK);
		normal.setPosition(Const.Setting.X_SPEED_BUTTON_NORMAL, Const.Setting.Y_SPEED_BUTTON_NORMAL);
		this.addActor(normal);
		
		int speed = getSettingValue(SystemConfig.SYS_ATTR_SPEED);
		switch(speed) {
		case 1:
			slow.setChecked(true);
			break;
		case 3:
			fast.setChecked(true);
			break;
		case 2:			
		default:
			normal.setChecked(true);
			break;			
		}
		
		slow.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				slow.setChecked(true);
				normal.setChecked(false);
				fast.setChecked(false);
				settingChanged(SystemConfig.SYS_ATTR_SPEED, 1);
			}
			
		});
		
		normal.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				normal.setChecked(true);
				slow.setChecked(false);
				fast.setChecked(false);
				settingChanged(SystemConfig.SYS_ATTR_SPEED, 2);
			}
			
		});

		fast.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				fast.setChecked(true);
				slow.setChecked(false);
				normal.setChecked(false);
				settingChanged(SystemConfig.SYS_ATTR_SPEED, 3);
			}
			
		});		
		
	}

	private void initMusicItem() {
		final LabelActor title = new LabelActor("Music", font);
		title.setColor(new Color(0.56f, 0.99f, 0.43f, 1.f));
		title.setPosition(Const.Setting.X_MUSIC_TITLE, Const.Setting.Y_MUSIC_TITLE);
		this.addActor(title);
		
		final ButtonActor open = new ButtonActor(skin, Image.Setting.OPEN_UP, Image.Setting.OPEN_DOWN, Image.Setting.OPEN_CHECK);
		open.setPosition(Const.Setting.X_SPEED_BUTTON_MUSIC_OPEN, Const.Setting.Y_SPEED_BUTTON_MUSIC_OPEN);
		this.addActor(open);
		
		final ButtonActor close = new ButtonActor(skin, Image.Setting.CLOSE_UP, Image.Setting.CLOSE_DOWN, Image.Setting.CLOSE_CHECK);
		close.setPosition(Const.Setting.X_SPEED_BUTTON_MUSIC_CLOSE, Const.Setting.Y_SPEED_BUTTON_MUSIC_CLOSE);
		this.addActor(close);
		
		int value = getSettingValue(SystemConfig.SYS_ATTR_MUSIC);
		close.setChecked(value == 0);
		open.setChecked(value != 0);
		
		open.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				open.setChecked(true);
				close.setChecked(false);
				settingChanged(SystemConfig.SYS_ATTR_MUSIC, 1);
			}
			
		});

		close.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				close.setChecked(true);
				open.setChecked(false);
				settingChanged(SystemConfig.SYS_ATTR_MUSIC, 0);
			}
			
		});		
		
	}

	private void initSoundItem() {
		final LabelActor title = new LabelActor("Sound", font);
		title.setColor(new Color(0.56f, 0.99f, 0.43f, 1.f));
		title.setPosition(Const.Setting.X_SOUND_TITLE, Const.Setting.Y_SOUND_TITLE);
		this.addActor(title);
		
		final ButtonActor open = new ButtonActor(skin, Image.Setting.OPEN_UP, Image.Setting.OPEN_DOWN, Image.Setting.OPEN_CHECK);
		open.setPosition(Const.Setting.X_SPEED_BUTTON_SOUND_OPEN, Const.Setting.Y_SPEED_BUTTON_SOUND_OPEN);
		this.addActor(open);
		
		final ButtonActor close = new ButtonActor(skin, Image.Setting.CLOSE_UP, Image.Setting.CLOSE_DOWN, Image.Setting.CLOSE_CHECK);
		close.setPosition(Const.Setting.X_SPEED_BUTTON_SOUND_CLOSE, Const.Setting.Y_SPEED_BUTTON_SOUND_CLOSE);
		this.addActor(close);
		
		int value = getSettingValue(SystemConfig.SYS_ATTR_SOUND);
		close.setChecked(value == 0);
		open.setChecked(value != 0);		
		
		open.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				open.setChecked(true);
				close.setChecked(false);
				settingChanged(SystemConfig.SYS_ATTR_SOUND, 1);
			}
			
		});
		
		close.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				close.setChecked(true);
				open.setChecked(false);
				settingChanged(SystemConfig.SYS_ATTR_SOUND, 0);
			}
			
		});		
		
	}

	private void settingChanged(int id, int value) {
		final DBAccess dbAccess = super.screen.getGame().getDBAccess();
		dbAccess.setSysData(id, value, null);
		
		screen.onSettingChanged(id, value, null);
	}
	
	private int getSettingValue(int id) {
		final DBAccess dbAccess = super.screen.getGame().getDBAccess();
		return dbAccess.getSysDataAsInt(id);
	}
	
}
