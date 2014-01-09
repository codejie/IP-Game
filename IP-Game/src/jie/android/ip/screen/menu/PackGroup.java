package jie.android.ip.screen.menu;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import jie.android.ip.CommonConsts.ScreenConfig;
import jie.android.ip.CommonConsts.ScreenPackConfig;
import jie.android.ip.common.actor.BaseGroup;
import jie.android.ip.common.actor.ImageActor;
import jie.android.ip.common.actor.LabelActor;
import jie.android.ip.screen.menu.MenuConfig.Const;
import jie.android.ip.screen.menu.MenuConfig.Image;
import jie.android.ip.screen.menu.MenuRenderer.ItemClickListener;

public class PackGroup extends BaseGroup {

	private class ItemActor extends BaseGroup {
		
		private final String title;
		private final String info;
		
		public ItemActor(final String title, int all, int pass) {
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
	
	private static final int NUM_PACK = 6;
	
	private final MenuScreen screen;
	private final TextureAtlas textureAtlas;
	private final BitmapFont bitmapFont;
	private final ItemClickListener packClickListener, itemClickListener;
	
	private final ItemActor[] itemActors;
	
	public PackGroup(final MenuScreen screen, final ItemClickListener packClickListener, final ItemClickListener itemClickListener) {
		this.screen = screen;
		this.textureAtlas = screen.getGame().getResources().getTextureAtlas(ScreenPackConfig.SCREEN_MENU);
		this.bitmapFont = screen.getGame().getResources().getBitmapFont(24);
		this.packClickListener = packClickListener;
		this.itemClickListener = itemClickListener;

		this.itemActors = new ItemActor[NUM_PACK];
		
		this.setBounds(0, 0, ScreenConfig.WIDTH, ScreenConfig.HEIGHT);		
		this.screen.addActor(this);
	}

	@Override
	protected void initStage() {		
	}
	
	public void load(final ArrayList<Pack> packs) {
		for (final Pack pack : packs) {
			ItemActor actor = new ItemActor(pack.getTitle(), pack.getTotal_all(), pack.getTotal_pass());
			setPackBounds(pack.getId(), actor);
			actor.addListener(new ClickListener() {

				@Override
				public void clicked(InputEvent event, float x, float y) {
					packClickListener.onClick(pack.getId());
				}
				
			});
			this.addActor(actor);
		}
	}

	private void setPackBounds(int id, final ItemActor actor) {
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
	
	

}
