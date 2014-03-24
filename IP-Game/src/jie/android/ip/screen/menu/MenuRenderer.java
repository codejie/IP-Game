package jie.android.ip.screen.menu;

import jie.android.ip.AudioPlayer;
import jie.android.ip.CommonConsts.AudioConfig;
import jie.android.ip.CommonConsts.PackConfig;
import jie.android.ip.common.actor.ImageActor;
import jie.android.ip.common.dialog.BaseDialog;
import jie.android.ip.common.dialog.SettingDialog;
import jie.android.ip.screen.menu.MenuConfig.Const;
import jie.android.ip.screen.menu.MenuConfig.Image;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class MenuRenderer {
	
	public interface PackGroupEventListener {
		public void onPackClick(int id);
		public void onPackItemClick(int pack, int id);
		public void onBtnBackClicked(int pack);
		public void onBtnNextClicked(int pack);
		public void onBtnPrevCllicked(int pack);
	}
	
	private final MenuScreen screen;
	final TextureAtlas textureAtlas;
	private final AudioPlayer audioPlayer;
//	private final Sound soundClick;
	
	private CmdPanelGroup groupCmd;
	
	private MenuScreenListener.RendererEventListener rendererListener;
	
	private PackGroup groupPack;
	
	private final MenuScreenListener.ManagerEventListener managerListener = new MenuScreenListener.ManagerEventListener() {
		
		@Override
		public void onPackLoadCompleted(final Pack[] packs) {
			groupPack.loadPacks(packs);
		}

		@Override
		public void onPackItemLoadCompleted(final Pack pack, int itemStart) {
			groupPack.loadPackItem(pack);
		}
	};
	
	private final PackGroupEventListener packGroupListener = new PackGroupEventListener() {

		@Override
		public void onPackClick(int id) {
			playClick();
			if (rendererListener != null) {
				rendererListener.onPackClicked(id);
			}
		}

		@Override
		public void onPackItemClick(int pack, int id) {
			playClick();
			if (rendererListener != null) {
				rendererListener.onPackItemClicked(pack, id);
			}
		}

		@Override
		public void onBtnBackClicked(int curPack) {
			playClick();
			if (rendererListener != null) {
				rendererListener.onLoadPack();
			}
		}

		@Override
		public void onBtnNextClicked(int curPack) {
			playClick();
			if (rendererListener != null) {
				rendererListener.onPackClicked(curPack);
			}			
		}

		@Override
		public void onBtnPrevCllicked(int curPack) {
			playClick();
			if (rendererListener != null) {
				rendererListener.onPackClicked(curPack);
			}
		}
		
	};
	
	
	public MenuRenderer(final MenuScreen screen) {
		this.screen = screen;
		this.textureAtlas = this.screen.getGame().getResources().getTextureAtlas(PackConfig.SCREEN_MENU);
		
		this.audioPlayer = this.screen.getGame().getAudioPlayer();
		
		initBackgroup();		
		initGroups();
		initTitle();
//		initCmdPanel();
	}

	private void initTitle() {
		final ImageActor title = new ImageActor(textureAtlas.findRegion(Image.TITLE));
		title.setBounds(Const.TITLE_X, Const.TITLE_Y, Const.TITLE_WIDTH, Const.TITLE_HEIGHT);
		screen.addActor(title);
		title.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				onTitleClicked();
			}
		});	
	}

	private void initBackgroup() {		
		final ImageActor background = new ImageActor(textureAtlas.findRegion(Image.BACKGROUND));
		background.setBounds(Const.BG_X, Const.BG_Y, Const.BG_WIDTH, Const.BG_HEIGHT);
		screen.addActor(background);
	}

	protected void onTitleClicked() {
		showCmdPanel();
		
		
//		new SettingDialog(this.screen).show();
	}

	private void showCmdPanel() {
		if (groupCmd == null) {
			groupCmd = new CmdPanelGroup(this.screen);
		}
		groupCmd.show();		
	}
	
	private void initGroups() {
		groupPack = new PackGroup(screen, packGroupListener);
	}

	public void setEventListener(final MenuScreenListener.RendererEventListener listener) {
		this.rendererListener = listener;
	}
	
	public final MenuScreenListener.ManagerEventListener getManagerEventListener() {
		return managerListener;
	}
	
	protected void playClick() {
		audioPlayer.playSound(AudioConfig.MENU_CLICK);
	}	
}
