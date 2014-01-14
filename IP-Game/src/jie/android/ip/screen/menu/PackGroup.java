package jie.android.ip.screen.menu;

import java.util.ArrayList;
import java.util.Map.Entry;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import jie.android.ip.CommonConsts.ScreenConfig;
import jie.android.ip.CommonConsts.ScreenPackConfig;
import jie.android.ip.common.actor.BaseGroup;
import jie.android.ip.common.actor.BaseGroupAccessor;
import jie.android.ip.common.actor.ImageActor;
import jie.android.ip.common.actor.LabelActor;
import jie.android.ip.executor.Script;
import jie.android.ip.screen.menu.MenuConfig.Const;
import jie.android.ip.screen.menu.MenuConfig.Image;
import jie.android.ip.screen.menu.MenuRenderer.PackGroupEventListener;
import jie.android.ip.screen.play.Box;
import jie.android.ip.utils.Extended.Pair;

public class PackGroup extends BaseGroup {

	private class PackActor extends BaseGroup {
		
		private final String title;
		private final String info;
		
		public PackActor(final String title, int all, int pass) {
			this.title = title;
			this.info = String.format("%d/%d", pass, all);
			
			initStage();
		}
		
		@Override
		protected void initStage() {
			ImageActor bg = new ImageActor(textureAtlas.findRegion(Image.Pack.Item_Bg));
			bg.setBounds(0, 0, Const.Pack.WIDTH, Const.Pack.HEIGHT);
			this.addActor(bg);
			
			LabelActor tl = new LabelActor(title, bitmapFont);
			tl.setColor(Color.BLUE);
			tl.setScale(2.8f);
			TextBounds tb = tl.getBounds();
			tl.setBounds((Const.Pack.WIDTH - tb.width) / 2, Const.Pack.HEIGHT * 0.8f, tb.width, tb.height);
			this.addActor(tl);
			
			LabelActor i = new LabelActor(info, bitmapFont);
			i.setScale(2.1f);
			tb = i.getBounds();
			i.setBounds((Const.Pack.WIDTH - tb.width) / 2, Const.Pack.HEIGHT * 0.33f, tb.width, tb.height);
			this.addActor(i);
		}		
	}
	
	//
	private class ItemActor extends BaseGroup {

		private final int id;
		private final int status;
		private final int score;
		private final String script;
		
		public ItemActor(int id, int status, int score, final String script) {
			this.id = id;
			this.status = status;
			this.score = score;
			this.script = script;
			
			initStage();
		}
		
		@Override
		protected void initStage() {
			ImageActor bg = new ImageActor(textureAtlas.findRegion(Image.Item.Item_Bg));
			bg.setBounds(0, 0, Const.Item.WIDTH, Const.Item.HEIGHT);
			this.addActor(bg);

			final Script st = new Script(id); 
			if (!st.loadString(script)) {
				return;
			}						
			renderScript(st);
		}
		
		private void renderScript(final Script st) {
			ImageActor top = new ImageActor(textureAtlas.findRegion(Image.Item.FRAME));
			top.setBounds(Const.Item.FRAME_TOP_X, Const.Item.FRAME_TOP_Y, Const.Item.FRAME_WIDTH, Const.Item.FRAME_HEIGHT);
			this.addActor(top);
			
			ImageActor bottom = new ImageActor(textureAtlas.findRegion(Image.Item.FRAME));
			bottom.setBounds(Const.Item.FRAME_BOTTOM_X, Const.Item.FRAME_BOTTOM_Y, Const.Item.FRAME_WIDTH, Const.Item.FRAME_HEIGHT);
			this.addActor(bottom);
			
			loadBlock(st.getSource());
			loadTray(st.getTray());
		}
		
		private int colToBlockX(int col) {
			return Const.Item.COL_BASE + (col - 1) * (Const.Item.BLOCK_WIDTH + Const.Item.COL_SPACE);
		}
		
		private int rowToBlockY(int row) {
			return Const.Item.ROW_BASE + (row) * (Const.Item.BLOCK_HEIGHT + Const.Item.ROW_SPACE);
		}
		
		private int colToTrayX(int col) {
			return Const.Item.TRAY_SPACE + (col - 1) * (Const.Item.TRAY_WIDTH);
		}
		
		private int rowToTrayY() {
			return Const.Item.TRAY_BASE;
		}
		
		private void loadBlock(final ArrayList<Script.BlockData> blockData) {
			for (final Script.BlockData block : blockData) {
				ImageActor actor = makeActor(block.value, block.style);
				actor.setBounds(colToBlockX(block.col), rowToBlockY(block.row), Const.Item.BLOCK_WIDTH, Const.Item.BLOCK_HEIGHT);
				
				this.addActor(actor);				
			}
		}
		
		private void loadTray(final Script.TrayData tray) {
			ImageActor actor = new ImageActor(textureAtlas.findRegion(Image.Item.TRAY));// adapter.getResources().getSkin().getRegion("t"));
			actor.setBounds(colToTrayX(tray.col), rowToTrayY(), Const.Item.TRAY_WIDTH, Const.Item.TRAY_HEIGHT);
			
			this.addActor(actor);			
		}
		
		private final ImageActor makeActor(int value, int style) {
			if (value == 0) {
				return new ImageActor(textureAtlas.findRegion(Image.Item.BOX_0));// adapter.getResources().getSkin().getRegion("ic"));
			} else {
				return null;
			}
		}		
	}
	
	//
	private final MenuScreen screen;
	private final TextureAtlas textureAtlas;
	private final BitmapFont bitmapFont;
	private final TweenManager tweenManager;
	
	private final PackGroupEventListener listener;
	
	private int itemStart = 0;
	
	private BaseGroup[] actorCache1 = new BaseGroup[Pack.NUM_PACK]; 
	private BaseGroup[] actorCache2 = new BaseGroup[Pack.NUM_PACK];
	
	public PackGroup(final MenuScreen screen, final PackGroupEventListener listener) {
		this.screen = screen;
		this.textureAtlas = screen.getGame().getResources().getTextureAtlas(ScreenPackConfig.SCREEN_MENU);
		this.bitmapFont = screen.getGame().getResources().getBitmapFont(24);
		this.tweenManager = screen.getTweenManager();
		this.listener = listener;

		this.setBounds(0, 0, ScreenConfig.WIDTH, ScreenConfig.HEIGHT);		
		this.screen.addActor(this);
	}

	@Override
	protected void initStage() {		
	}
	
	public void loadPacks(final Pack[] packs) {
		for (int i = 0; i < packs.length; ++ i) {
			final Pack pack = packs[i];
			PackActor actor = new PackActor(pack.getTitle(), pack.getTotal_all(), pack.getTotal_pass());
			setPackBounds(pack.getId(), actor);
			actor.addListener(new ClickListener() {

				@Override
				public void clicked(InputEvent event, float x, float y) {
					listener.onPackClick(pack.getId());
				}
				
			});
			
			this.addActor(actor);
			actorCache1[i] = actor;
		}
		
		itemStart = 0;
	}

	private void setPackBounds(int id, final PackActor actor) {
		int x = 0, y = 0;
		switch(id) {
		case 1:
		case 2:
		case 3:
			x = Const.Pack.BASE_X + (id - 1)* (Const.Pack.WIDTH + Const.Pack.SPACE_X);
			y = Const.Pack.BASE_Y + Const.Pack.HEIGHT + Const.Pack.SPACE_Y;
			break;
		case 4:
		case 5:
		case 6:
			x = Const.Pack.BASE_X + (id - 4)* (Const.Pack.WIDTH + Const.Pack.SPACE_X);
			y = Const.Pack.BASE_Y;			
			break;
		default:
			return;
		}
		
		actor.setBounds(x, y, Const.Pack.WIDTH, Const.Pack.HEIGHT);
	}

	public void loadPackItem(final Pack pack, int start) {
		final Pack.Item items[] = pack.getItems();
		int end = (items.length - start) <= MenuConfig.Const.Item.NUM_TOTAL ? items.length - start : MenuConfig.Const.Item.NUM_TOTAL;  
		for (int i = 0; i < end; ++ i) {
			final Pack.Item item = items[start + i];
			ItemActor actor = new ItemActor(item.getId(), item.getStatus(), item.getScore(), item.getScript());
			setItemBounds(i, actor);
			actor.addListener(new ClickListener() {
				@Override
				public void clicked(InputEvent event, float x, float y) {
					listener.onPackItemClick(item.getId());
				}				
			});
			
			//actor.setVisible(false);
			this.addActor(actor);
			actorCache2[i] = actor;
		}
		
		itemStart = start + end;

		moveGroup();
		
		if (start == 0) {
			movePackGroup(true);
		} else {
			moveItemGroup(true);
		}
	}

	private void setItemBounds(int id, final ItemActor actor) {
		int x = 0, y = 0;
		switch(id) {
		case 1:
		case 2:
		case 3:
			x = Const.Item.BASE_X + (id - 1)* (Const.Item.WIDTH + Const.Item.SPACE_X);
			y = - (Const.Item.BASE_Y + Const.Item.HEIGHT + Const.Item.SPACE_Y);
			break;
		case 4:
		case 5:
		case 6:
			x = Const.Item.BASE_X + (id - 4)* (Const.Item.WIDTH + Const.Item.SPACE_X);
			y = - Const.Item.BASE_Y;			
			break;
		default:
			return;
		}
		
		actor.setBounds(x, y, Const.Item.WIDTH, Const.Item.HEIGHT);		
	}

	private void moveGroup() {
		final TweenCallback callback = new TweenCallback() {

			@Override
			public void onEvent(int type, BaseTween<?> source) {
				for (final BaseGroup actor : actorCache1) {
					PackGroup.this.removeActor(actor);
				}
				
				actorCache1 = actorCache2;
			}
			
		};
		
		// cache 1 out, cache 2 in
		Timeline timeline = Timeline.createParallel();
		for (int i = 0; i < actorCache1.length; ++ i) {
			final BaseGroup actor = actorCache1[i];
			timeline.push(Tween.to(actor, BaseGroupAccessor.POSITION_Y, 0.2f + 0.1f * i).targetRelative(ScreenConfig.HEIGHT));
		}
		timeline.delay(0.2f);
		for (int i = 0; i < actorCache2.length; ++ i) {
			final BaseGroup actor = actorCache2[i];
			if (actor != null) {
//				timeline.push(Tween.set(actor, BaseGroupAccessor.POSITION_Y).targetRelative(-Const.Item.HEIGHT));
				timeline.push(Tween.to(actor, BaseGroupAccessor.POSITION_Y, 0.2f + 0.1f * i).targetRelative(Const.Item.BASE_Y + Const.Item.HEIGHT + Const.Item.SPACE_Y));
			}
		}
		
		timeline.setCallback(callback);		
		timeline.start(tweenManager);
	}
	
	private void movePackGroup(boolean out) {
		
	}

	private void moveItemGroup(boolean out) {
		
	}
}
