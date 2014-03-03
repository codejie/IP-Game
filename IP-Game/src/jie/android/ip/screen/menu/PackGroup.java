package jie.android.ip.screen.menu;

import java.util.ArrayList;
import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import jie.android.ip.CommonConsts.ScreenConfig;
import jie.android.ip.CommonConsts.PackConfig;
import jie.android.ip.common.actor.BaseGroup;
import jie.android.ip.common.actor.BaseGroupAccessor;
import jie.android.ip.common.actor.ButtonActor;
import jie.android.ip.common.actor.ImageActor;
import jie.android.ip.common.actor.LabelActor;
import jie.android.ip.common.actor.ScreenGroup;
import jie.android.ip.common.dialog.SettingDialog;
import jie.android.ip.executor.Script;
import jie.android.ip.screen.BaseScreen;
import jie.android.ip.screen.menu.MenuConfig.Const;
import jie.android.ip.screen.menu.MenuConfig.Image;
import jie.android.ip.screen.menu.MenuRenderer.PackGroupEventListener;

public class PackGroup extends ScreenGroup {

	private class PackActor extends BaseGroup {

		private final int id;
		private final String title;
		private final String info;

		public PackActor(int id, final String title, int all, int pass) {
			this.id = id;
			this.title = title;
			this.info = String.format("%d/%d", pass, all);

			initStage();
		}

		@Override
		protected void initStage() {
			if (id != 1) {
				ImageActor bg = new ImageActor(textureAtlas.findRegion(Image.Pack.Bg));
				bg.setBounds(0, 0, Const.Pack.WIDTH, Const.Pack.HEIGHT);
				this.addActor(bg);
			} else {
				ImageActor bg = new ImageActor(textureAtlas.findRegion(Image.Pack.Bg_0));
				bg.setBounds(0, 0, Const.Pack.WIDTH, Const.Pack.HEIGHT);
				this.addActor(bg);
			}
			LabelActor tl = new LabelActor(title, titleBitmapFont);
			tl.setColor(new Color(0x00008Bff));
			tl.setPosition((Const.Pack.WIDTH - tl.getWidth()) / 2, Const.Pack.HEIGHT * 0.6f);// ,
																								// tb.width,
																								// tb.height);
			this.addActor(tl);

			LabelActor i = new LabelActor(info, infoBitmapFont);
			i.setColor(new Color(0x00008Bff));
			i.setPosition((Const.Pack.WIDTH - i.getWidth()) / 2, Const.Pack.HEIGHT * 0.2f);// ,
																							// tb.width,
																							// tb.height);
			this.addActor(i);
		}
	}

	//
	private class ItemActor extends BaseGroup {

		private final Pack.Item item;

		public ItemActor(final Pack.Item item) {
			this.item = item;

			initStage();
		}

		@Override
		protected void initStage() {
			final ImageActor bg = new ImageActor(textureAtlas.findRegion(Image.Item.Bg));
			bg.setBounds(0, 0, Const.Item.WIDTH, Const.Item.HEIGHT);
			this.addActor(bg);

			ImageActor top = new ImageActor(textureAtlas.findRegion(Image.Item.FRAME_TOP));
			// top.setBounds(4, ScreenConfig.HEIGHT - 16, ScreenConfig.WIDTH -
			// 4, 16);
			top.setBounds(Const.Item.FRAME_X, Const.Item.FRAME_Y_TOP, Const.Item.FRAME_WIDTH,
					Const.Item.FRAME_HEIGHT_TOP);
			this.addActor(top);

			ImageActor bottom = new ImageActor(textureAtlas.findRegion(Image.Item.FRAME_BOTTOM));
			// bottom.setBounds(4, 0, ScreenConfig.WIDTH - 4, 16);
			bottom.setBounds(Const.Item.FRAME_X, Const.Item.FRAME_Y_BOTTOM, Const.Item.FRAME_WIDTH,
					Const.Item.FRAME_HEIGHT_BOTTOM);
			this.addActor(bottom);

			renderScript();
		}

		private void renderScript() {
			loadBlock(item.getScript().getSource());
			//loadBlock(item.getScript().getTarget());
			loadTray(item.getScript().getTray());

			// title
			final LabelActor title = new LabelActor(item.getScript().getTitle(), titleBitmapFont);
			if (item.getStatus() == 0) {
				title.setColor(new Color(0xA52A2Af0));
			} else {
				title.setColor(new Color(0xD2691Ef0));
			}
			title.setPosition((Const.Pack.WIDTH - title.getWidth()) / 2, Const.Pack.HEIGHT * 0.6f);
			this.addActor(title);
			// score
			if (item.getStatus() != 0) {
				final LabelActor sc = new LabelActor(String.format("%d/%d", item.getScore(), item.getBaseScore()),
						infoBitmapFont);
				sc.setColor(new Color(0xD2691Ef0));
				sc.setPosition((Const.Pack.WIDTH - sc.getWidth()) / 2, Const.Pack.HEIGHT * 0.2f);
				this.addActor(sc);
			}
		}

		private int colToBlockX(int col) {
			return Const.Item.COL_BASE + (col - 1) * (Const.Item.BLOCK_WIDTH + Const.Item.COL_SPACE);
		}

		private int rowToBlockY(int row) {
			return Const.Item.ROW_BASE + (row) * (Const.Item.BLOCK_HEIGHT + Const.Item.ROW_SPACE);
		}

		private int colToTrayX(int col) {
			return Const.Item.TRAY_COL_BASE + (col - 1) * (Const.Item.TRAY_WIDTH + Const.Item.TRAY_SPACE);
		}

		private int rowToTrayY() {
			return Const.Item.TRAY_ROW_BASE;
		}

		private void loadBlock(final ArrayList<Script.BlockData> blockData) {
			for (final Script.BlockData block : blockData) {
				ImageActor actor = makeActor(block.value, block.style);
				actor.setBounds(colToBlockX(block.col), rowToBlockY(block.row), Const.Item.BLOCK_WIDTH,
						Const.Item.BLOCK_HEIGHT);

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
			}
			if (value == 1) {
				return new ImageActor(textureAtlas.findRegion(Image.Item.BOX_1));
			}
			if (value == 2) {
				return new ImageActor(textureAtlas.findRegion(Image.Item.BOX_2));
			}
			if (value == 3) {
				return new ImageActor(textureAtlas.findRegion(Image.Item.BOX_3));
			} else {
				return null;
			}
		}
	}

	//
	private class ActorCache {

		private final BaseGroup parent;

		private BaseGroup[] actorCache1 = new BaseGroup[Pack.NUM_PACK];
		private BaseGroup[] actorCache2 = new BaseGroup[Pack.NUM_PACK];

		public ActorCache(final BaseGroup parent) {
			this.parent = parent;
		}

		public void addActor(int i, final BaseGroup actor) {
			actorCache1[i] = actor;
		}

		public void swapCache() {
			for (int i = 0; i < Pack.NUM_PACK; ++i) {
				final BaseGroup actor = actorCache2[i];
				if (actor != null) {
					parent.removeActor(actor);
				}
				actorCache2[i] = actorCache1[i];
			}
		}

		public final BaseGroup[] getOutActors() {
			return actorCache2;
		}

		public final BaseGroup[] getInActors() {
			return actorCache1;
		}
	}

	//
	private final TextureAtlas textureAtlas;
	private final Skin skin;
	private final BitmapFont titleBitmapFont, infoBitmapFont;
	private final TweenManager tweenManager;

	private final PackGroupEventListener listener;

	private int curPack = -1;
	private int itemStart = 0;

	private final ActorCache cacheActor;

	private ImageActor title;
	private ImageActor background;
	private ButtonActor btnBack, btnNext, btnPrev;

	public PackGroup(final BaseScreen screen, final PackGroupEventListener listener) {
		super(screen);
		this.textureAtlas = super.resources.getTextureAtlas(PackConfig.SCREEN_MENU);
		this.skin = new Skin(this.textureAtlas);
		this.titleBitmapFont = super.resources.getBitmapTrueFont(54);// .getBitmapFont(24);
		this.infoBitmapFont = super.resources.getBitmapTrueFont(45);

		this.tweenManager = screen.getTweenManager();
		this.listener = listener;

		this.cacheActor = new ActorCache(this);

		this.setBounds(0, 0, ScreenConfig.WIDTH, ScreenConfig.HEIGHT);
		this.screen.addActor(this);

		initStage();
	}

	@Override
	protected void initStage() {
		background = new ImageActor(textureAtlas.findRegion(Image.BACKGROUND));
		background.setBounds(Const.BG_X, Const.BG_Y, Const.BG_WIDTH, Const.BG_HEIGHT);
		this.addActor(background);
		background.setZIndex(0);

		title = new ImageActor(textureAtlas.findRegion(Image.TITLE));
		title.setBounds(Const.TITLE_X, Const.TITLE_Y, Const.TITLE_WIDTH, Const.TITLE_HEIGHT);
		this.addActor(title);
		title.setZIndex(0x0f);
		title.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				onTitleClicked();
			}
			
		});

		btnBack = new ButtonActor(new Button.ButtonStyle(skin.getDrawable(Image.Button.BACK_UP),
				skin.getDrawable(Image.Button.BACK_DOWN), null));
		btnBack.setBounds(Const.Button.BACK_X, Const.Button.BACK_Y, Const.Button.BACK_WIDTH, Const.Button.BACK_HEIGHT);
		btnBack.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				onBtnBackClicked();
			}

		});
		this.addActor(btnBack);

		btnNext = new ButtonActor(new Button.ButtonStyle(skin.getDrawable(Image.Button.NEXT_UP),
				skin.getDrawable(Image.Button.NEXT_DOWN), null));
		btnNext.setBounds(Const.Button.NEXT_X, Const.Button.NEXT_Y, Const.Button.NEXT_WIDTH, Const.Button.NEXT_HEIGHT);
		btnNext.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				onBtnNextClicked();
			}

		});
		this.addActor(btnNext);

		btnPrev = new ButtonActor(new Button.ButtonStyle(skin.getDrawable(Image.Button.PREV_UP),
				skin.getDrawable(Image.Button.PREV_DOWN), null));
		btnPrev.setBounds(Const.Button.PREV_X, Const.Button.PREV_Y, Const.Button.PREV_WIDTH, Const.Button.PREV_HEIGHT);
		btnPrev.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				onBtnPrevClicked();
			}

		});
		this.addActor(btnPrev);
	}

	protected void onTitleClicked() {
		new SettingDialog(this.screen).show();
	}

	public void loadPacks(final Pack[] packs) {

		renderPacks(packs);

		itemStart = 0;

		moveInPackGroup();
	}

	private void renderPacks(final Pack[] packs) {

		for (int i = 0; i < packs.length; ++i) {
			final Pack pack = packs[i];
			PackActor actor = new PackActor(pack.getId(), pack.getTitle(), pack.getTotal_all(), pack.getTotal_pass());
			setPackBounds(pack.getId(), actor);
			actor.addListener(new ClickListener() {

				@Override
				public void clicked(InputEvent event, float x, float y) {
					curPack = pack.getId();
					listener.onPackClick(curPack);
				}

			});

			this.addActor(actor);
			actor.setZIndex(0x01);
			cacheActor.addActor(i, actor);
		}
	}

	private void setPackBounds(int id, final PackActor actor) {
		int x = 0, y = 0;
		switch (id) {
		case 1:
		case 2:
		case 3:
			x = Const.Pack.BASE_X + (id - 1) * (Const.Pack.WIDTH + Const.Pack.SPACE_X);
			y = (ScreenConfig.HEIGHT + (Const.Pack.BASE_Y + Const.Pack.HEIGHT + Const.Pack.SPACE_Y));
			;
			break;
		case 4:
		case 5:
		case 6:
			x = Const.Pack.BASE_X + (id - 4) * (Const.Pack.WIDTH + Const.Pack.SPACE_X);
			y = (ScreenConfig.HEIGHT + Const.Pack.BASE_Y);
			break;
		default:
			return;
		}

		actor.setBounds(x, y, Const.Pack.WIDTH, Const.Pack.HEIGHT);
	}

	public void loadPackItem(final Pack pack) {
		final Pack.Item items[] = pack.getItems();
		int end = ((itemStart + MenuConfig.Const.Item.NUM_PER_PAGE) <= items.length) ? (itemStart + MenuConfig.Const.Item.NUM_PER_PAGE)
				: items.length;

		loadPackItem(items, itemStart, end);

		moveOutPackGroup(itemStart, end, items.length);
	}

	private void loadPackItem(final Pack.Item[] items, int start, int end) {
		for (int i = start; i < end; ++i) {
			final Pack.Item item = items[i];
			ItemActor actor = new ItemActor(item);
			setItemBounds((i - start), actor);
			actor.addListener(new ClickListener() {
				@Override
				public void clicked(InputEvent event, float x, float y) {
					listener.onPackItemClick(curPack, item.getId());
				}
			});

			this.addActor(actor);
			cacheActor.addActor(i - start, actor);
		}
	}

	private void setItemBounds(int pos, final ItemActor actor) {
		int x = 0, y = 0;
		switch (pos) {
		case 0:
		case 1:
		case 2:
			x = Const.Item.BASE_X + pos * (Const.Item.WIDTH + Const.Item.SPACE_X);
			y = -(ScreenConfig.HEIGHT - (Const.Item.BASE_Y + Const.Item.HEIGHT + Const.Item.SPACE_Y));
			break;
		case 3:
		case 4:
		case 5:
			x = Const.Item.BASE_X + (pos - 3) * (Const.Item.WIDTH + Const.Item.SPACE_X);
			y = -(ScreenConfig.HEIGHT - Const.Item.BASE_Y);
			break;
		default:
			return;
		}

		actor.setBounds(x, y, Const.Item.WIDTH, Const.Item.HEIGHT);
	}

	private void movePackGroup(float target, final TweenCallback callback) {
		Timeline timeline = Timeline.createParallel();
		final BaseGroup[] out = cacheActor.getOutActors();
		float delay = 0.0f;
		for (int i = 0; i < out.length; ++i) {
			final BaseGroup actor = out[i];
			if (actor != null) {
				timeline.push(Tween.to(actor, BaseGroupAccessor.POSITION_Y, 0.1f + 0.05f * i).targetRelative(target));
				delay += 0.02f;
			}
		}
		timeline.delay(delay);
		final BaseGroup[] in = cacheActor.getInActors();
		for (int i = 0; i < in.length; ++i) {
			final BaseGroup actor = in[i];
			if (actor != null) {
				timeline.push(Tween.to(actor, BaseGroupAccessor.POSITION_Y, 0.1f + 0.05f * i).targetRelative(target));
			}
		}
		timeline.setCallback(callback);
		timeline.start(tweenManager);
	}

	private void moveInPackGroup() {

		final TweenCallback callback = new TweenCallback() {

			@Override
			public void onEvent(int type, BaseTween<?> source) {
				cacheActor.swapCache();
			}
		};

		hideButtons();

		movePackGroup(-ScreenConfig.HEIGHT, callback);
	}

	private void moveOutPackGroup(final int start, final int end, final int total) {

		final TweenCallback callback = new TweenCallback() {

			@Override
			public void onEvent(int type, BaseTween<?> source) {
				cacheActor.swapCache();
				showButtons(start, end, total);
			}
		};

		movePackGroup(ScreenConfig.HEIGHT, callback);
	}

	protected void hideButtons() {
		btnBack.setVisible(false);
		btnNext.setVisible(false);
		btnPrev.setVisible(false);
	}

	protected void showButtons(int start, int end, int total) {
		btnBack.setVisible(true);
		btnPrev.setVisible(start != 0);
		btnNext.setVisible(end < total);
	}

	protected void onBtnBackClicked() {
		hideButtons();
		listener.onBtnBackClicked(curPack);
		curPack = -1;
	}

	protected void onBtnNextClicked() {
		hideButtons();
		itemStart = itemStart + MenuConfig.Const.Item.NUM_PER_PAGE;
		listener.onBtnNextClicked(curPack);
	}

	protected void onBtnPrevClicked() {
		hideButtons();
		itemStart = itemStart - MenuConfig.Const.Item.NUM_PER_PAGE;
		listener.onBtnPrevCllicked(curPack);
	}

}