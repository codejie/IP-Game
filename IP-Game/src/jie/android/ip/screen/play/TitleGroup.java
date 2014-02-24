package jie.android.ip.screen.play;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import jie.android.ip.CommonConsts.PackConfig;
import jie.android.ip.common.actor.BaseGroupAccessor;
import jie.android.ip.common.actor.ImageActor;
import jie.android.ip.common.actor.LabelActor;
import jie.android.ip.common.actor.ScreenGroup;
import jie.android.ip.screen.BaseScreen;
import jie.android.ip.screen.play.PlayConfig.Const;
import jie.android.ip.screen.play.PlayConfig.Image;
import jie.android.ip.screen.play.PlayScreenListener.RendererInternalEventListener;

public class TitleGroup extends ScreenGroup {

	private final TextureAtlas textureAtlas;
	private final TweenManager tweenManager;
	
	private ImageActor frame;
	private LabelActor packTitle;
	private LabelActor scriptTitle;
	
	private boolean moved = false;
	
	public TitleGroup(final BaseScreen screen, final PlayScreenListener.RendererInternalEventListener internalListener) {
		super(screen);
		this.textureAtlas = super.resources.getTextureAtlas(PackConfig.SCREEN_PLAY);
		this.tweenManager = this.screen.getTweenManager();		
		
		initStage();
	}

	@Override
	protected void initStage() {
		this.setBounds(Const.Title.A_X, Const.Title.A_Y, Const.Title.WIDTH, Const.Title.HEIGHT);
		frame = new ImageActor(textureAtlas.findRegion(Image.Title.FRAME));
		frame.setBounds(0, 0, Const.Title.WIDTH, Const.Title.HEIGHT);
		this.addActor(frame);
		
		super.screen.addActor(this);
	}
	
	public void setScript(final String pack, final String script) {
		packTitle = new LabelActor(pack, super.resources.getBitmapTrueFont(45));
		packTitle.setPosition(Const.Title.X_PACK, Const.Title.Y_PACK);
		this.addActor(packTitle);
		
		scriptTitle = new LabelActor(pack, super.resources.getBitmapTrueFont(45));
		scriptTitle.setPosition(Const.Title.X_SCRIPT, Const.Title.Y_SCRIPT);
		this.addActor(scriptTitle);		
	}
	
	public void toggle() {
		if (moved) {
			Tween.to(this, BaseGroupAccessor.POSITION_XY, 0.1f).target(Const.Title.A_X, Const.Title.A_Y).start(tweenManager);
		} else {
			Tween.to(this, BaseGroupAccessor.POSITION_XY, 0.1f).target(Const.Title.B_X, Const.Title.B_Y).start(tweenManager);
		}
		
		moved = !moved;
	}

}
