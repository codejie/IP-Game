package jie.android.ip.screen.play;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import jie.android.ip.Resources;
import jie.android.ip.CommonConsts.ScreenConfig;
import jie.android.ip.CommonConsts.PackConfig;
import jie.android.ip.common.actor.BaseGroup;
import jie.android.ip.common.actor.BaseGroupAccessor;
import jie.android.ip.common.actor.ImageActor;
import jie.android.ip.common.actor.ScreenGroup;
import jie.android.ip.screen.BaseScreen;
import jie.android.ip.screen.play.PlayConfig.Const;
import jie.android.ip.screen.play.PlayConfig.Image;

public class CodeLineGroup extends ScreenGroup {

	protected static final String Tag = CodeLineGroup.class.getSimpleName();
	
	public enum LineState {
		SMALL, BIG;
	}
	
	private class LineButtonActor {
		public ImageActor smallActor;
		public ImageActor bigActor;
	}
	
	private class LineButton {

		private final BaseGroup group;
		
		private LineButtonActor[] node = new LineButtonActor[Code.NUM_CODE_PER_LINE];
		
		public LineButton(final BaseGroup group) {
			this.group = group;
			
		}
		
		public void clear() {
			for (int i = 0; i < node.length; ++ i) {
				if (node[i] != null) {
					if (node[i].smallActor != null) {
						group.removeActor(node[i].smallActor);
					}
					if (node[i].bigActor != null) {
						group.removeActor(node[i].bigActor);
					}
				}
			}
		}
		
		public void setActor(int pos, final ImageActor smallActor, final ImageActor bigActor) {
			if (node[pos] == null) {
				node[pos] = new LineButtonActor();
			} else {
				if (node[pos].smallActor != null) {
					group.removeActor(node[pos].smallActor);
				}
				if (node[pos].bigActor != null) {
					group.removeActor(node[pos].bigActor);
				}				
			}
			
			node[pos].smallActor = smallActor;
			group.addActor(smallActor);
			if (pos % 2 != 0) {
				if (node[pos - 1].smallActor != null) {
					node[pos - 1].smallActor.setZIndex(node[pos].smallActor.getZIndex() + 1);
				}
			} else {
				if (node[pos + 1] != null && node[pos + 1].smallActor != null) {
					node[pos].smallActor.setZIndex(node[pos + 1].smallActor.getZIndex() + 1);
				}
			}
			node[pos].bigActor = bigActor;				
			group.addActor(bigActor);
		}
		
		public void showActor(boolean small) {
			for (int i = 0; i < node.length; ++ i) {
				node[i].smallActor.setVisible(small);
				node[i].bigActor.setVisible(!small);
			}
		}
		
		public void showActor(int pos, boolean small) {
			node[pos].smallActor.setVisible(small);
			node[pos].bigActor.setVisible(!small);			
		}
	}
	
	private class LineGroup extends BaseGroup {
		
		private final int index;
		private final TextureAtlas textureAtlas;		
		private final Code.OnButtonListener onClickListener;
		
		private LineState state = LineState.SMALL;		
		
		private ImageActor smallBg, bigBg;
		private ImageActor smallTitle, bigTitle;		
		private LineButton lineButton;
		
		public LineGroup(int index, final Resources resources, final TextureAtlas textureAtlas, final Code.OnButtonListener listener) {
			this.index = index;
			this.textureAtlas = textureAtlas;
			this.onClickListener = listener;
			
			init();
			
			initStage();
		}
		
		private void init() {
			final Vector2 v = getCodeLinePosition();
			this.setBounds(v.x, v.y, Const.Lines.Small.WIDTH_BG, Const.Lines.Small.HEIGHT_BG);
			
			this.addListener(new ClickListener() {
				@Override
				public void clicked(InputEvent event, float x, float y) {
					if (LineGroup.this.getState() == CodeLineGroup.LineState.SMALL || LineGroup.this.hit(x, y, true) == LineGroup.this) {
						if (onClickListener != null) {
							onClickListener.onLineGroupClick(LineGroup.this.getIndex());
						}
					}
				}				
			});
			
			lineButton = new LineButton(this);
		}

		public final Vector2 getCodeLinePosition() {
			Vector2 vct = new Vector2();
			vct.x =	Const.Lines.BASE_X;
			vct.y = Const.Lines.BASE_Y + (Code.NUM_CODE_LINES - index - 1) * (Const.Lines.Small.HEIGHT_BG + Const.Lines.SPACE_LILNES);
			return vct;
		}

		@Override
		protected void initStage() {
			//Small
			smallBg = new ImageActor(textureAtlas.findRegion(Image.Lines.Small.BG));
			smallBg.setBounds(0, 0, Const.Lines.Small.WIDTH_BG, Const.Lines.Small.HEIGHT_BG);
			smallBg.addListener(new ClickListener() {
				@Override
				public void clicked(InputEvent event, float x, float y) {
					if (LineGroup.this.getState() == CodeLineGroup.LineState.BIG && onClickListener != null) {
						onClickListener.onLineGroupClick(LineGroup.this.getIndex());
					}
				}				
			});
			
			smallBg.setVisible(state == LineState.SMALL);
			this.addActor(smallBg);
			
			if (index == 0) {
				smallTitle = new ImageActor(textureAtlas.findRegion(Image.Lines.Small.TITLE_0));
			} else if (index == 1) {
				smallTitle = new ImageActor(textureAtlas.findRegion(Image.Lines.Small.TITLE_1));
			} else if (index == 2) {
				smallTitle = new ImageActor(textureAtlas.findRegion(Image.Lines.Small.TITLE_2));
			} else {
				smallTitle = new ImageActor(textureAtlas.findRegion(Image.Lines.Small.TITLE_3));
			}

			smallTitle.setBounds(0, 0, Const.Lines.Small.WIDTH_TITLE, Const.Lines.Small.HEIGHT_TITLE);
			smallTitle.addListener(new ClickListener() {
				@Override
				public void clicked(InputEvent event, float x, float y) {
					if (LineGroup.this.getState() == LineState.BIG && onClickListener != null) {
						onClickListener.onLineGroupClick(LineGroup.this.getIndex());
					}
				}				
			});
			
			smallTitle.setVisible(state == LineState.SMALL);
			this.addActor(smallTitle);
			
			//Big
			bigBg = new ImageActor(textureAtlas.findRegion(Image.Lines.Big.BG));
			bigBg.setBounds(0, 0, Const.Lines.Big.WIDTH_BG, Const.Lines.Big.HEIGHT_BG);
//				bigBg.addListener(new ClickListener() {
//					@Override
//					public void clicked(InputEvent event, float x, float y) {
//						if (CodeLineGroup.this.getState() == CodeLineGroup.State.BIG && onClickListener != null) {
//							onClickListener.onClick(Code.OnButtonListener.Which.CODE_GROUP, CodeLineGroup.this.getIndex(), null);
//						}
//					}				
//				});
			
			bigBg.setVisible(state == LineState.BIG);
			this.addActor(bigBg);
		
			if (index == 0) {
				bigTitle = new ImageActor(textureAtlas.findRegion(Image.Lines.Big.TITLE_0));
			} else if (index == 1) {
				bigTitle = new ImageActor(textureAtlas.findRegion(Image.Lines.Big.TITLE_1));
			} else if (index == 2) {
				bigTitle = new ImageActor(textureAtlas.findRegion(Image.Lines.Big.TITLE_2));
			} else {
				bigTitle = new ImageActor(textureAtlas.findRegion(Image.Lines.Big.TITLE_3));
			}
			bigTitle.setBounds(0, 0, Const.Lines.Big.WIDTH_TITLE, Const.Lines.Big.HEIGHT_TITLE);
			bigTitle.addListener(new ClickListener() {
				@Override
				public void clicked(InputEvent event, float x, float y) {
					if (LineGroup.this.getState() == LineState.BIG && onClickListener != null) {
						onClickListener.onLineGroupClick(LineGroup.this.getIndex());
					}
				}				
			});
			
			bigTitle.setVisible(state == LineState.BIG);
			this.addActor(bigTitle);
		}
		
		public int getIndex() {
			return index;
		}

		public final LineState getState() {
			return state;
		}
		
		private void setState(final LineState state) {
			if (this.state == state) {
				return;
			}

			lineButton.showActor(state == LineState.SMALL);
			smallBg.setVisible(state == LineState.SMALL);
			smallTitle.setVisible(state == LineState.SMALL);
			bigBg.setVisible(state == LineState.BIG);
			bigTitle.setVisible(state == LineState.BIG);
			
			this.state = state;
		}
	
		public final void loadButtons(final Code.Type[] nodes) {
			
			lineButton.clear();
			
			for (int i = 0; i < nodes.length; ++ i) {
				final ImageActor small = makeImageActor(nodes[i], i, true);
				final ImageActor big = makeImageActor(nodes[i], i, false);
				lineButton.setActor(i, small, big);			
			}
		}
		
		public void updateButton(int pos, final Code.Type type) {
			final ImageActor small = makeImageActor(type, pos, true);
			final ImageActor big = makeImageActor(type, pos, false);
			lineButton.setActor(pos, small, big);
			lineButton.showActor(pos, (state == LineState.SMALL));
		}
		
		private ImageActor makeImageActor(final Code.Type type, final int pos,  boolean small) {
			//final Code.Type type = btn.type;
			ImageActor ret = null;
			
			if (type == Code.Type.NULL) {
				ret = new ImageActor(textureAtlas.findRegion(small ? Image.Lines.Small.CODE_NULL : Image.Lines.Big.CODE_NULL));
			} else if (type == Code.Type.IF_NULL) {
				ret = new ImageActor(textureAtlas.findRegion(small ? Image.Lines.Small.CODE_IF_NULL : Image.Lines.Big.CODE_IF_NULL));			
			} else if (type == Code.Type.RIGHT) {
				ret = new ImageActor(textureAtlas.findRegion(small ? Image.Lines.Small.CODE_RIGHT : Image.Lines.Big.CODE_RIGHT));
			} else if (type == Code.Type.LEFT) {
				ret = new ImageActor(textureAtlas.findRegion(small ? Image.Lines.Small.CODE_LEFT : Image.Lines.Big.CODE_LEFT));
			} else if (type == Code.Type.ACT) {
				ret = new ImageActor(textureAtlas.findRegion(small ? Image.Lines.Small.CODE_ACT : Image.Lines.Big.CODE_ACT));
			} else if (type == Code.Type.IF_0) {
				ret = new ImageActor(textureAtlas.findRegion(small ? Image.Lines.Small.CODE_IF_0 : Image.Lines.Big.CODE_IF_0));
			} else if (type == Code.Type.IF_1) {
				ret = new ImageActor(textureAtlas.findRegion(small ? Image.Lines.Small.CODE_IF_1 : Image.Lines.Big.CODE_IF_1));
			} else if (type == Code.Type.IF_2) {
				ret = new ImageActor(textureAtlas.findRegion(small ? Image.Lines.Small.CODE_IF_2 : Image.Lines.Big.CODE_IF_2));
			} else if (type == Code.Type.IF_3) {
				ret = new ImageActor(textureAtlas.findRegion(small ? Image.Lines.Small.CODE_IF_3 : Image.Lines.Big.CODE_IF_3));
			} else if (type == Code.Type.IF_ANY) {
				ret = new ImageActor(textureAtlas.findRegion(small ? Image.Lines.Small.CODE_IF_ANY : Image.Lines.Big.CODE_IF_ANY));
			} else if (type == Code.Type.IF_NONE) {
				ret = new ImageActor(textureAtlas.findRegion(small ? Image.Lines.Small.CODE_IF_NONE : Image.Lines.Big.CODE_IF_NONE));
			} else if (type == Code.Type.CALL_0) {
				ret = new ImageActor(textureAtlas.findRegion(small ? Image.Lines.Small.CODE_CALL_0 : Image.Lines.Big.CODE_CALL_0));
			} else if (type == Code.Type.CALL_1) {
				ret = new ImageActor(textureAtlas.findRegion(small ? Image.Lines.Small.CODE_CALL_1 : Image.Lines.Big.CODE_CALL_1));
			} else if (type == Code.Type.CALL_2) {
				ret = new ImageActor(textureAtlas.findRegion(small ? Image.Lines.Small.CODE_CALL_2 : Image.Lines.Big.CODE_CALL_2));
			} else if (type == Code.Type.CALL_3) {
				ret = new ImageActor(textureAtlas.findRegion(small ? Image.Lines.Small.CODE_CALL_3 : Image.Lines.Big.CODE_CALL_3));
			} else {
				return null;
			}

			if (ret != null) {
				if (!small) {
					ret.addListener(new ClickListener() {
						
						@Override
						public void clicked(InputEvent event, float x, float y) {
							if (LineGroup.this.getState() == CodeLineGroup.LineState.BIG && onClickListener != null) {
								onClickListener.onLineClick(LineGroup.this.getIndex(), pos);
							}
						}				
					});			
				}
				
				setButtonBounds(ret, pos, type.isJudge(), small);
				ret.setVisible(small ? state == LineState.SMALL : state == LineState.BIG);
			}
			return ret;	
		}
		
		private void setButtonBounds(final ImageActor actor, int pos, boolean judge, boolean small) {
			float x = 0, y = 0;
			float w = 0, h = 0;
			if (small) {
				if (pos % 2 == 0) {				
					x = Const.Lines.Small.WIDTH_TITLE + (Const.Lines.Small.WIDTH_BUTTON_ORDER + Const.Lines.Small.SPACE_X) * (pos / 2) + Const.Lines.Small.SPACE_X;
					y = Const.Lines.Small.SPACE_Y + Const.Lines.Small.HEIGHT_BUTTON_ORDER - Const.Lines.Small.HEIGHT_BUTTON_JUDGE;
					w = Const.Lines.Small.WIDTH_BUTTON_JUDGE;
					h = Const.Lines.Small.HEIGHT_BUTTON_JUDGE;				
				} else {
					//order
					x = Const.Lines.Small.WIDTH_TITLE + (Const.Lines.Small.WIDTH_BUTTON_ORDER + Const.Lines.Small.SPACE_X) * ((pos - 1) / 2) + Const.Lines.Small.SPACE_X;
					y = Const.Lines.Small.SPACE_Y;
					w = Const.Lines.Small.WIDTH_BUTTON_ORDER;
					h = Const.Lines.Small.HEIGHT_BUTTON_ORDER;
				}
			} else {
				if (pos % 2 == 0) {				
					x = Const.Lines.Big.WIDTH_TITLE + (Const.Lines.Big.WIDTH_BUTTON_JUDGE + Const.Lines.Big.SPACE_X) * (pos / 2) + Const.Lines.Big.SPACE_X;
					y = Const.Lines.Big.SPACE_Y + Const.Lines.Big.HEIGHT_BUTTON_ORDER;// - Const.Lines.Big.HEIGHT_BUTTON_JUDGE;
					w = Const.Lines.Big.WIDTH_BUTTON_JUDGE;
					h = Const.Lines.Big.HEIGHT_BUTTON_JUDGE;					
				} else {
					x = Const.Lines.Big.WIDTH_TITLE + (Const.Lines.Big.WIDTH_BUTTON_ORDER + Const.Lines.Big.SPACE_X) * ((pos - 1) / 2) + Const.Lines.Big.SPACE_X;
					y = Const.Lines.Big.SPACE_Y;
					w = Const.Lines.Big.WIDTH_BUTTON_ORDER;
					h = Const.Lines.Big.HEIGHT_BUTTON_ORDER;				
				}
			}
			
			actor.setBounds(x, y, w, h);
		}
		
		public void toggleState(final TweenManager tweenManager) {
			
			final TweenCallback callback = new TweenCallback() {
				@Override
				public void onEvent(int type, BaseTween<?> source) {
					if (type == TweenCallback.COMPLETE) {
						if (state == LineState.SMALL) {
							setScale(1.0f);
							setState(LineState.BIG);
						} else {
							setZIndex(0x00);
							setScale(1.0f);
							setState(LineState.SMALL);
						}
					} else if (type == TweenCallback.START) {
						internalListener.onLineGroupChangeBegin(state == LineState.SMALL);
					}
				}
			};
			
			final Vector2 vct = getCodeLinePosition();
			float scale = 0.6f;
			
			if (state == LineState.SMALL) {
				if (index == 0 || index == 1) {
					vct.y = vct.y - (Const.Lines.Big.HEIGHT_BG - Const.Lines.Small.HEIGHT_BG);
				} else {
				
				}
				setZIndex(0x0f);
				scale = 1.8f;			
			} else {
			}

			Timeline.createParallel()
				.push(Tween.to(this, BaseGroupAccessor.POSITION_XY, 0.1f).target(vct.x, vct.y))
				.push(Tween.to(this, BaseGroupAccessor.SCALE_XY, 0.1f).target(scale, scale))
				.setCallbackTriggers(TweenCallback.COMPLETE | TweenCallback.START)
				.setCallback(callback)
			.start(tweenManager);			
		}
	}
	//
	
	public enum PanelState {
		HIDE, JUDGE, ORDER;
		
		public int getId() {
			return this.ordinal();
		}
	}
	
	private class PanelGroup extends BaseGroup {

		private final TextureAtlas textureAtlas;		
		private final Code.OnButtonListener onClickListener;
		
		private final Code.Panel codePanel = new Code.Panel();
		
		private Group groupJudge;
		private Group groupOrder;
		
		private PanelState state = PanelState.HIDE;
		
		public PanelGroup(final Resources resources,  final TextureAtlas textureAtlas, final Code.OnButtonListener listener) {
			this.textureAtlas = textureAtlas;
			this.onClickListener = listener;
			
			initStage();
			
			initPanel();
		}

		@Override
		protected void initStage() {
			groupJudge = new Group();
			ImageActor bg = new ImageActor(this.textureAtlas.findRegion(Image.Lines.Panel.JUDGE_BG));
			bg.setBounds(0, 0, Const.Lines.Panel.Judge.WIDTH_BG, Const.Lines.Panel.Judge.HEIGHT_BG);
			groupJudge.addActor(bg);
			
			groupOrder = new Group();
			bg = new ImageActor(this.textureAtlas.findRegion(Image.Lines.Panel.ORDER_BG));
			bg.setBounds(0, 0, Const.Lines.Panel.Order.WIDTH_BG, Const.Lines.Panel.Order.HEIGHT_BG);
			groupOrder.addActor(bg);
			
			groupJudge.setVisible(false);
			groupOrder.setVisible(false);
			
			this.addActor(groupJudge);
			this.addActor(groupOrder);			
		}
		
		private void initPanel() {
			Code.Button buttons[] = codePanel.getJudgeButton();
			for (int i = 0; i < buttons.length; ++ i) {
				final Code.Button btn = buttons[i];
				btn.bigActor = makeImageActor(i, btn.type);
				btn.bigActor.addListener(new ClickListener() {
					@Override
					public void clicked(InputEvent event, float x, float y) {
						if (onClickListener != null) {
							onClickListener.onPanelClick(btn.type);
						}
					}
				});
				groupJudge.addActor(btn.bigActor);
			}
			
			buttons = codePanel.getOrderButton();
			for (int i = 0; i < buttons.length; ++ i) {
				final Code.Button btn = buttons[i];
				btn.bigActor = makeImageActor(i, btn.type);
				btn.bigActor.addListener(new ClickListener() {
					@Override
					public void clicked(InputEvent event, float x, float y) {
						if (onClickListener != null) {
							onClickListener.onPanelClick(btn.type);
						}
					}
				});
				groupOrder.addActor(btn.bigActor);
			}
		}		
		
		private final Actor makeImageActor(int pos, Code.Type type) {
			ImageActor ret = null;

			if (type == Code.Type.NULL) {
				ret = new ImageActor(this.textureAtlas.findRegion(Image.Lines.Panel.CODE_NULL));
			} else if (type == Code.Type.RIGHT) {
				ret = new ImageActor(this.textureAtlas.findRegion(Image.Lines.Panel.CODE_RIGHT));
			} else if (type == Code.Type.LEFT) {
				ret = new ImageActor(this.textureAtlas.findRegion(Image.Lines.Panel.CODE_LEFT));
			} else if (type == Code.Type.ACT) {
				ret = new ImageActor(this.textureAtlas.findRegion(Image.Lines.Panel.CODE_ACT));
			} else if (type == Code.Type.IF_0) {
				ret = new ImageActor(this.textureAtlas.findRegion(Image.Lines.Panel.CODE_IF_0));
			} else if (type == Code.Type.IF_1) {
				ret = new ImageActor(this.textureAtlas.findRegion(Image.Lines.Panel.CODE_IF_1));
			} else if (type == Code.Type.IF_2) {
				ret = new ImageActor(this.textureAtlas.findRegion(Image.Lines.Panel.CODE_IF_2));
			} else if (type == Code.Type.IF_3) {
				ret = new ImageActor(this.textureAtlas.findRegion(Image.Lines.Panel.CODE_IF_3));
			} else if (type == Code.Type.IF_ANY) {
				ret = new ImageActor(this.textureAtlas.findRegion(Image.Lines.Panel.CODE_IF_ANY));
			} else if (type == Code.Type.IF_NULL) {
				ret = new ImageActor(this.textureAtlas.findRegion(Image.Lines.Panel.CODE_IF_NULL));			
			} else if (type == Code.Type.IF_NONE) {
				ret = new ImageActor(this.textureAtlas.findRegion(Image.Lines.Panel.CODE_IF_NONE));
			} else if (type == Code.Type.CALL_0) {
				ret = new ImageActor(this.textureAtlas.findRegion(Image.Lines.Panel.CODE_CALL_0));
			} else if (type == Code.Type.CALL_1) {
				ret = new ImageActor(this.textureAtlas.findRegion(Image.Lines.Panel.CODE_CALL_1));
			} else if (type == Code.Type.CALL_2) {
				ret = new ImageActor(this.textureAtlas.findRegion(Image.Lines.Panel.CODE_CALL_2));
			} else if (type == Code.Type.CALL_3) {
				ret = new ImageActor(this.textureAtlas.findRegion(Image.Lines.Panel.CODE_CALL_3));
			} else {
				return null;
			}		
				
			float x = Const.Lines.Panel.SPACE_X + (Const.Lines.Panel.WIDTH_BUTTON + Const.Lines.Panel.SPACE_X) * pos;
			float y = Const.Lines.Panel.SPACE_Y;
			ret.setBounds(x, y, Const.Lines.Panel.WIDTH_BUTTON, Const.Lines.Panel.HEIGHT_BUTTON);
			
			return ret;
		}
		
		public final PanelState getState() {
			return state;
		}
		
		public boolean setState(final PanelState state) {
			if (this.state == state) {
				return false;
			}

			this.state = state;
			
			if (this.state == PanelState.JUDGE) {
				groupJudge.setVisible(true);
				groupJudge.setZIndex(0x0F);
				groupOrder.setVisible(false);
				groupOrder.setZIndex(0x00);
				
				this.setBounds(0, 0, Const.Lines.Panel.Judge.WIDTH_BG, Const.Lines.Panel.Judge.HEIGHT_BG);
			} else if (this.state == PanelState.ORDER) {
				groupJudge.setVisible(false);
				groupJudge.setZIndex(0x00);
				groupOrder.setVisible(true);
				groupOrder.setZIndex(0x0F);
				this.setBounds(0, 0, Const.Lines.Panel.Order.WIDTH_BG, Const.Lines.Panel.Order.HEIGHT_BG);
			}
			this.setVisible(this.state != PanelState.HIDE);
			
			return true;
		}	
		
		public final Code.Type getButtonType(int state, int pos) {
			
			if (state == PanelState.JUDGE.getId()) {
				final Code.Button[] buttons = codePanel.getJudgeButton();
				return buttons[pos].type;
			} else if (state == PanelState.ORDER.getId()) {
				final Code.Button[] buttons = codePanel.getOrderButton();
				return buttons[pos].type;
			}
			
			return null;
		}

		public void hide(int pos, final TweenManager tweenManager) {
			if (state != PanelState.HIDE) {
				
				if (pos != -1) {
					float x = groupPanel.getX() + (Const.Lines.Panel.SPACE_X + Const.Lines.Panel.WIDTH_BUTTON) * (pos + 0.5f);
					float y = groupPanel.getY() + Const.Lines.Panel.SPACE_Y + Const.Lines.Panel.HEIGHT_BUTTON * 0.5f;
					
					TweenCallback completeCallback = new TweenCallback() {
						@Override
						public void onEvent(int type, BaseTween<?> source) {
							setState(PanelState.HIDE);					
						}				
					};
					
					Timeline.createParallel()
						.push(Tween.to(groupPanel, BaseGroupAccessor.POSITION_XY, 0.2f).target(x, y))
						.push(Tween.to(groupPanel, BaseGroupAccessor.SCALE_XY, 0.2f).target(0.0f, 0.0f))
						.setCallback(completeCallback )
						.start(tweenManager);
				} else {
					setState(PanelState.HIDE);	
				}			
			}			
		}

		public void show(int index, int pos, float lx, float ly, final TweenManager tweenManager) {

			float x = 0, y = 0;
			
			if (pos % 2 == 0) {			
				x = Const.Lines.Big.WIDTH_TITLE + (Const.Lines.Big.WIDTH_BUTTON_JUDGE + Const.Lines.Big.SPACE_X) * (pos / 2) / 1.5f + Const.Lines.Big.SPACE_X;
				if (x + Const.Lines.Panel.Judge.WIDTH_BG > ScreenConfig.WIDTH) {
					x = ScreenConfig.WIDTH - Const.Lines.Panel.Judge.WIDTH_BG;
				}				
			} else {
				x = Const.Lines.Big.WIDTH_TITLE + (Const.Lines.Big.WIDTH_BUTTON_ORDER + Const.Lines.Big.SPACE_X) * ((pos - 1) / 2) / 1.5f + Const.Lines.Big.SPACE_X;
				if (x + Const.Lines.Panel.Order.WIDTH_BG > ScreenConfig.WIDTH) {
					x = ScreenConfig.WIDTH - Const.Lines.Panel.Order.WIDTH_BG;
				}			
			}
			
			if (index == 0 || index == 1) {
				if (pos % 2 == 0) {				
					y = ly + Const.Lines.Small.HEIGHT_BG + 2;
				} else {
					y = ly - (Const.Lines.Big.HEIGHT_BG - Const.Lines.Small.HEIGHT_BG) - Const.Lines.Panel.Order.HEIGHT_BG;
				}
			} else if (index == 2) {
				if (pos % 2 == 0) {				
					y = ly + Const.Lines.Big.HEIGHT_BG + 2;
				} else {
					y = ly - Const.Lines.Panel.Order.HEIGHT_BG;
				}
			} else {
				y = ly + Const.Lines.Big.HEIGHT_BG + 2;
			}
			
			float sx = x + (Const.Lines.Panel.SPACE_X + Const.Lines.Panel.WIDTH_BUTTON) * (3 + 0.5f);
			float sy = y + Const.Lines.Panel.SPACE_Y + Const.Lines.Panel.HEIGHT_BUTTON * 0.5f;
			
			boolean changed = groupPanel.setState((pos % 2 == 0) ? PanelState.JUDGE : PanelState.ORDER);
			
			if (changed) {			
				Timeline.createParallel()
					.push(Tween.set(groupPanel, BaseGroupAccessor.POSITION_XY).target(sx, sy))
					.push(Tween.set(groupPanel, BaseGroupAccessor.SCALE_XY).target(0.0f, 0.0f))
					.push(Tween.to(groupPanel, BaseGroupAccessor.POSITION_XY, 0.2f).target(x, y))
					.push(Tween.to(groupPanel, BaseGroupAccessor.SCALE_XY, 0.2f).target(1.0f, 1.0f))
					.start(tweenManager);
			} else {
				Tween.to(groupPanel, BaseGroupAccessor.POSITION_XY, 0.1f).target(x, y).start(tweenManager);
			}
		}
	}
	
	//
	private final TextureAtlas textureAtlas;
	private final TweenManager tweenManager;
	private final PlayScreenListener.RendererInternalEventListener internalListener;
	
	private final LineGroup[] groupLine = new LineGroup[Code.NUM_CODE_LINES];
	private PanelGroup groupPanel;
	
	private int cacheLineIndex = -1;
	private int cacheLinePos = -1;
	
	private boolean isMinimized = false;
	
	private final Code.OnButtonListener buttonlistener = new Code.OnButtonListener() {

		@Override
		public void onBaseGroupClick() {
			if (isMinimized) {
				return;
			}

			onBaseGroupClicked();			
		}

		@Override
		public void onLineGroupClick(int index) {
			if (isMinimized) {
				return;
			}

			onLineGroupClicked(index);
		}

		@Override
		public void onLineClick(int index, int pos) {
			if (isMinimized) {
				return;
			}
			
			onLineClicked(index, pos);			
		}

		@Override
		public void onPanelClick(final Code.Type type) {
			if (isMinimized) {
				return;
			}
			
			onPanelClicked(type);
		}
	};
	
	public CodeLineGroup(final BaseScreen screen, final PlayScreenListener.RendererInternalEventListener internalListener) {
		super(screen);
		this.textureAtlas = super.resources.getTextureAtlas(PackConfig.SCREEN_PLAY);
		this.tweenManager = this.screen.getTweenManager();
		this.internalListener = internalListener;

		initGroups();
	}

	@Override
	protected void initStage() {
		// TODO Auto-generated method stub
	}
	
//	public void setRendererEventListener(final PlayScreenListener.RendererEventListener listener) {
//		rendererListener = listener;
//	}

	private void initGroups() {
		final Group base = this;
		base.setBounds(0, 0, ScreenConfig.WIDTH, ScreenConfig.HEIGHT);
		base.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				if (base.hit(x, y, true) == base) {
					buttonlistener.onBaseGroupClick();
				}
			}
			
		});
		
		for (int i = 0; i < groupLine.length; ++ i) {
			groupLine[i] = new LineGroup(i, super.resources, textureAtlas, buttonlistener);
			
			base.addActor(groupLine[i]);
		}
		
		groupPanel = new PanelGroup(super.resources, textureAtlas, buttonlistener);
		base.addActor(groupPanel);
		
		screen.addActor(base);

	}
	
	public void load(final Code.Lines lines) {
		for (int i = 0; i < groupLine.length; ++ i) {
			groupLine[i].loadButtons(lines.getFuncNode(i));
		}
	}

	public void update(final Code.Lines lines, int index, int pos) {
		groupLine[index].updateButton(pos, lines.getNode(index, pos));
	}
	
	public void reset(final Code.Lines lines) {
		for (int i = 0; i < groupLine.length; ++ i) {
			groupLine[i].loadButtons(lines.getFuncNode(i));
		}
	}
	
	public void minimizeLines(boolean show) {
		if (show) {
			final Vector2 vct2 = groupLine[2].getCodeLinePosition();
			final Vector2 vct3 = groupLine[3].getCodeLinePosition();	

			Timeline.createParallel()			
				.push(Tween.to(groupLine[0], BaseGroupAccessor.POSITION_Y, 0.1f).target(vct2.y + Const.Lines.Small.HEIGHT_BG / 2))
				.push(Tween.to(groupLine[1], BaseGroupAccessor.POSITION_Y, 0.1f).target(vct3.y + Const.Lines.Small.HEIGHT_BG / 2))
				.push(Tween.to(groupLine[0], BaseGroupAccessor.SCALE_XY, 0.1f).target(0.8f, 0.8f))
				.push(Tween.to(groupLine[1], BaseGroupAccessor.SCALE_XY, 0.1f).target(0.8f, 0.8f))
				.push(Tween.to(groupLine[2], BaseGroupAccessor.POSITION_X, 0.1f).targetRelative(Const.Lines.Small.WIDTH_BG * 0.8f + Const.Lines.Small.SPACE_X))
				.push(Tween.to(groupLine[3], BaseGroupAccessor.POSITION_X, 0.1f).targetRelative(Const.Lines.Small.WIDTH_BG * 0.8f + Const.Lines.Small.SPACE_X))
				.push(Tween.to(groupLine[2], BaseGroupAccessor.SCALE_XY, 0.1f).target(0.8f, 0.8f))				
				.push(Tween.to(groupLine[3], BaseGroupAccessor.SCALE_XY, 0.1f).target(0.8f, 0.8f))
				.start(tweenManager);
		} else {
			final Vector2 vct0 = groupLine[0].getCodeLinePosition();
			final Vector2 vct1 = groupLine[1].getCodeLinePosition();	

			Timeline.createParallel()
				.push(Tween.to(groupLine[0], BaseGroupAccessor.POSITION_Y, 0.1f).target(vct0.y))
				.push(Tween.to(groupLine[1], BaseGroupAccessor.POSITION_Y, 0.1f).target(vct1.y))
				.push(Tween.to(groupLine[0], BaseGroupAccessor.SCALE_XY, 0.1f).target(1.0f, 1.0f))
				.push(Tween.to(groupLine[1], BaseGroupAccessor.SCALE_XY, 0.1f).target(1.0f, 1.0f))
				.push(Tween.to(groupLine[2], BaseGroupAccessor.POSITION_X, 0.1f).targetRelative(-(Const.Lines.Small.WIDTH_BG * 0.8f + Const.Lines.Small.SPACE_X)))
				.push(Tween.to(groupLine[3], BaseGroupAccessor.POSITION_X, 0.1f).targetRelative(-(Const.Lines.Small.WIDTH_BG * 0.8f + Const.Lines.Small.SPACE_X)))
				.push(Tween.to(groupLine[2], BaseGroupAccessor.SCALE_XY, 0.1f).target(1.0f, 1.0f))
				.push(Tween.to(groupLine[3], BaseGroupAccessor.SCALE_XY, 0.1f).target(1.0f, 1.0f))				
				.start(tweenManager);			
		}
		
		isMinimized = show;
	}	
	
	protected void onLineGroupClicked(int index) {
		groupPanel.hide(-1, tweenManager);
		cacheLinePos = -1;
		
		if (cacheLineIndex != -1) {
			groupLine[cacheLineIndex].toggleState(tweenManager);
		}
		
		if (cacheLineIndex != index) {
			groupLine[index].toggleState(tweenManager);
			cacheLineIndex = index;
		} else {
			cacheLineIndex = -1;
		}
	}
	
	protected void onLineClicked(int index, int pos) {
		cacheLinePos = pos;

		final Vector2 vct = groupLine[index].getCodeLinePosition();
		groupPanel.show(index, pos, vct.x, vct.y, tweenManager);
	}

	protected void onPanelClicked(final Code.Type type) {
		
		internalListener.onPanelButtonClicked(cacheLineIndex, cacheLinePos, type);
		
		groupPanel.hide(cacheLineIndex, tweenManager);
		cacheLinePos = -1;
	}

	protected void onBaseGroupClicked() {
		groupPanel.hide(-1, tweenManager);
		if (cacheLineIndex != -1) {
			groupLine[cacheLineIndex].toggleState(tweenManager);
			cacheLineIndex = -1;
		}		
	}

}
