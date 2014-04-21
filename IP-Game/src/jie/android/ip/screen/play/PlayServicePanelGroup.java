package jie.android.ip.screen.play;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
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
import jie.android.ip.database.DBAccess;
import jie.android.ip.playservice.PlayService;
import jie.android.ip.screen.BaseScreen;
import jie.android.ip.screen.play.PlayConfig.Const;
import jie.android.ip.screen.play.PlayConfig.Image;


public class PlayServicePanelGroup extends ScreenGroup {

	private final DBAccess dbAccess;
	private final TextureAtlas textureAtlas;
	private final Skin skin;
	private final TweenManager tweenManager;
	private final PlayService playService;
	
	private boolean show = false;
	
	private int packId = -1;
	private int scriptId = -1;
	
	public PlayServicePanelGroup(final BaseScreen screen) {
		super(screen);
		this.dbAccess = this.screen.getGame().getDBAccess();
		this.textureAtlas = super.resources.getTextureAtlas(PackConfig.SCREEN_PLAY);
		this.skin = new Skin(this.textureAtlas);
		this.tweenManager = this.screen.getTweenManager();
		this.playService = this.screen.getGame().getPlayService();		
		
		initStage();		
	}

	@Override
	protected void initStage() {
		this.setBounds(Const.PlayService.BASE_X, Const.PlayService.BASE_Y, Const.PlayService.WIDTH, Const.PlayService.HEIGHT);
		
		final ButtonActor achieve = new ButtonActor(new Button.ButtonStyle(skin.getDrawable(Image.PlayService.ACHIEVE_UP), skin.getDrawable(Image.PlayService.ACHIEVE_DOWN), null));
		achieve.setPosition(Const.PlayService.X_ACHIEVE, Const.PlayService.Y_ACHIEVE);
		achieve.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				onAchieveClicked();
			}
			
		});
		this.addActor(achieve);
		
		final ButtonActor board = new ButtonActor(new Button.ButtonStyle(skin.getDrawable(Image.PlayService.BOARD_UP), skin.getDrawable(Image.PlayService.BOARD_DOWN), null));
		board.setPosition(Const.PlayService.X_BOARD, Const.PlayService.Y_BOARD);
		board.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				onBoardClicked();
			}
			
		});		
		this.addActor(board);
		
		this.screen.addActor(this);
	}

	public void setId(int packId, int scriptId) {
		this.packId = packId;
		this.scriptId = scriptId;
	}
	
	public boolean isShowing() {
		return show;
	}
	
	public void show() {
		if (show || (playService != null && playService.isSignedIn())) {
			show(null);
		}
	}

	public void show(final TweenCallback callback) {
		show = !show;

		if (show) {
			Tween.to(this, BaseGroupAccessor.POSITION_X, 0.1f).target(Const.PlayService.TARGET_X)
				.setCallback(callback)
				.start(tweenManager);
		} else {
			Tween.to(this, BaseGroupAccessor.POSITION_X, 0.1f).target(Const.PlayService.BASE_X)
				.setCallback(callback)
				.start(tweenManager);
		}		
	}
	
	protected void onAchieveClicked() {		
		playService.showAchievements();
	}

	protected void onBoardClicked() {
		final String id = dbAccess.getPlayServiceId(scriptId, 1);
		if (id != null) {
			playService.showLeaderboard(id);
		}
	}
	

}
