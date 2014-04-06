package jie.android.ip.screen.play;

import com.badlogic.gdx.graphics.Color;

import jie.android.ip.AudioPlayer;
import jie.android.ip.CommonConsts.AudioConfig;
import jie.android.ip.common.dialog.AlertDialog;
import jie.android.ip.common.dialog.BaseDialog;
import jie.android.ip.common.dialog.DialogConfig;
import jie.android.ip.common.dialog.ScriptInfoDialog;
import jie.android.ip.common.dialog.SettingDialog;
import jie.android.ip.screen.play.Cmd.State;

public class PlayRenderer {
	
	private final PlayScreen screen;
	private final AudioPlayer audioPlayer;
	
	private PlayScreenListener.RendererEventListener rendererListener;
	
	private BoxGroup groupBox;
	private TitleGroup groupTitle;
	private CodeLineGroup groupCodeLine;
	private CmdPanelGroup groupCmdPanel;
	private PlayServicePanelGroup groupPlayServicePanel;
	private ResultGroup groupResult;
	
	private LessonGroup groupLesson;
	
	private boolean debugEnabled = false;
	
	private String scriptAuthor;
	private String scriptComment;
	
	private final PlayScreenListener.ManagerEventListener managerListener = new PlayScreenListener.ManagerEventListener() {

		@Override
		public void onBoxLoadCompleted(final Box.Tray tray, final Box.BlockArray source, final Box.BlockArray target) {
			groupBox.load(tray, source, target);
		}
		@Override
		public void onBoxPreReload(final Box.Tray tray, final Box.BlockArray source, final Box.BlockArray target) {
			groupCmdPanel.setChecked(Cmd.Type.RUN, false);
			groupBox.clearActors(tray, source, target);			
		}

		@Override
		public void onCodeLineLoadCompleted(final Code.Lines lines) {
			groupCodeLine.load(lines);
		}

		@Override
		public void onBoxMoved(final Box.Tray tray, final Box.Block block, int col, int row, int tcol, int trow) {
			if (tray == null) {
				if (row > trow) {
					playTrayCatch();
				} else {
					playTrayRelease();
				}
			}
			groupBox.move(tray, block, col, row, tcol, trow);
		}
		
		@Override
		public void onBoxMoveEmpty() {
			internalListener.onBoxMoveEnd();
		}
		
		@Override
		public void onCodeLineUpdated(final Code.Lines lines, int index, int pos) {
			groupCodeLine.update(lines, index, pos);
		}
		
		@Override
		public void onCodeLineResetCompleted(final Code.Lines lines) {
			groupCodeLine.reset(lines);
		}
		
		@Override
		public void onExecuteSucc(int base_score, int score, int execStep) {
			if (groupLesson != null) {
				groupLesson.onExecuteEnd(true);
			}
			
			playResultSucc();
			groupBox.showSourceClone();
			groupCmdPanel.showMenu(Cmd.Layer.THIRD);
			groupPlayServicePanel.show();
			groupResult.showSuccStage(base_score, score, execStep);			
		}
		
		@Override
		public void onExecuteFail() {
			playResultFail();
			groupResult.showFailStage();
		}
		
		@Override
		public void onExecuteFinished() {
			playResultFail();
			groupResult.showFinishedStage();
		}
		
		@Override
		public void onExecuteOverflow() {
			playResultFail();
			groupResult.showOverflowStage();			
		}
		
		@Override
		public void onScriptLoaded(int packId, int scriptId, final String packTitle, final int scriptSelfId, final String scriptTitle, final String author, final String comment) {
			//groupTitle.setTitle(packTitle, scriptTitle);
			groupTitle.setTitle(packTitle, String.format("Stage %d",  scriptSelfId));
			scriptAuthor = author;
			scriptComment = comment;
			
			groupPlayServicePanel.setId(packId, scriptId);
		}
		@Override
		public void onCodeCalled(int type, int index, int pos) {
			groupCodeLine.setHighlight(type, index, pos);
		}
		@Override
		public boolean onExecutePause() {
			if (debugEnabled) {
				if (!groupCmdPanel.isDebugOver()) {
					groupCmdPanel.setDisabled(Cmd.Type.DEBUG, false);
					return true;
				}
			}
			return false;
		}
	};	
	
	private final PlayScreenListener.RendererInternalEventListener internalListener = new PlayScreenListener.RendererInternalEventListener() {
		
		@Override
		public void onLineGroupChangeBegin(boolean fromSmall) {
			groupCmdPanel.showPanel(!fromSmall);
		}

		@Override
		public void onPanelButtonClicked(int index, int pos, final Code.Type type) {
			if (rendererListener != null) {
				rendererListener.onPanelButtonClicked(index, pos, type);
			}
		}

		@Override
		public void onCmdButtonClicked(final Cmd.Type type, final Cmd.State state) {

			if (type == Cmd.Type.RUN) {
				if (!onCmdRun(state)) {
					return;
				}
			} else if (type == Cmd.Type.DEBUG) {
				if (!onCmdDebug(state)) {
					return;
				}
			} else if (type == Cmd.Type.DEBUG_OVER) {
				if (!onCmdDebugOver(state)) {
					return;
				}
			} else if (type == Cmd.Type.ENABLE_DEBUG) {
				if (!onCmdEnableDebug(state)) {
					return;
				}
			} else if (type == Cmd.Type.MENU) {
				if (!onCmdMenu(state)) {
					return;
				}
			} else if (type == Cmd.Type.BACK || type == Cmd.Type.BACK2) {
				if (!onCmdBack(state)) {
					return;
				}
			} else if (type == Cmd.Type.SHARE) {
				if (!onCmdShare(state)) {
					return;
				}
			} else if (type == Cmd.Type.NEXT) {
				if (!onCmdNext(state)) {
					return;
				}
			} else if (type == Cmd.Type.CLEAR) {
				if(!onCmdClear(state)) {
					return;
				}
			} else if (type == Cmd.Type.INFO) {
				if (!onCmdInfo(state)) {
					return;
				}
			} else if (type == Cmd.Type.SETTING) {
				if (!onCmdSetting(state)) {
					return;
				}
			} else if (type == Cmd.Type.CLOSE || type == Cmd.Type.CLOSE2) {
				if (!onCmdClose(state)) {
					return;
				}
			}
			
			if (rendererListener != null) {
				rendererListener.onCmdButtonClicked(type, state);
			}
		}

		@Override
		public void onBoxMoveEnd() {
			if (rendererListener != null) {
				rendererListener.onBoxkMoveEnd();
			}
		}

		@Override
		public void onSourceFocused() {
			if (rendererListener != null) {
				rendererListener.onCmdButtonClicked(Cmd.Type.RUN, Cmd.State.NONE);
			}
		}

		@Override
		public void onLessonGroupAdded() {
		}

		@Override
		public void onLessonGroupRemoved() {
			groupLesson = null;
		}
	};
	
	public PlayRenderer(final PlayScreen screen) {
		this.screen = screen;
		this.audioPlayer = screen.getGame().getAudioPlayer();
		
		initGroups();
	}

	public void setSpeed(float speed) {
		// TODO Auto-generated method stub
		
	}		
	
	public final PlayScreenListener.ManagerEventListener getManagerEventListener() {
		return managerListener;
	}

	public void setEventListener(final PlayScreenListener.RendererEventListener listener) {
		this.rendererListener = listener;
	}

	public void initGroups() {
		groupBox = new BoxGroup(screen, internalListener);
		groupTitle = new TitleGroup(screen, internalListener);
		groupCodeLine = new CodeLineGroup(screen, internalListener);
		groupResult = new ResultGroup(screen, internalListener);		
		groupCmdPanel = new CmdPanelGroup(screen, internalListener);
		groupPlayServicePanel = new PlayServicePanelGroup(screen);
	}

	protected void changeRunStage(boolean show) {
		groupResult.hideStage();
		groupCmdPanel.focusRun(show);
		
		groupBox.focusSource(show);
		groupTitle.toggle();
		groupCodeLine.minimizeLines(show);
		groupCmdPanel.setChecked(Cmd.Type.RUN, show);
	}	
	
	protected boolean onCmdRun(final Cmd.State state) {
		changeRunStage(state == Cmd.State.NONE);
		if (state == Cmd.State.NONE) {
			return false;
		}

		return true;
	}
	
	protected boolean onCmdDebug(final Cmd.State state) {
		groupCmdPanel.setDisabled(Cmd.Type.DEBUG, true);
		return true;
	}	

	protected boolean onCmdDebugOver(final Cmd.State state) {
		boolean over = groupCmdPanel.setDebugOver();
		if (over) {
			groupCmdPanel.setDisabled(Cmd.Type.DEBUG, true);
			return true;
		}
		return false;
	}
	
	protected boolean onCmdEnableDebug(final Cmd.State state) {
		
		debugEnabled = (state == State.NONE); 		
		groupCmdPanel.enabledDebug(debugEnabled);
		
		return false;
	}
	
	protected boolean onCmdMenu(final Cmd.State state) {
		groupCmdPanel.showMenu(Cmd.Layer.SECOND);
		groupPlayServicePanel.show();
		return true;
	}	

	protected boolean onCmdBack(final Cmd.State state) {
		groupCmdPanel.showMenu(Cmd.Layer.FIRST);
		if (groupCmdPanel.isChecked(Cmd.Type.RUN)) {
			changeRunStage(false);			
		}
		
		groupPlayServicePanel.show();
		return true;
	}

	private boolean onCmdShare(final Cmd.State state) {
		this.screen.getGame().getSetup().shareScreen();

		return false;
	}
	
	private boolean onCmdNext(final Cmd.State state) {
		//this.screen.setNextScreen();
		return true;
	}	

	protected boolean onCmdClear(final Cmd.State state) {
		
		final AlertDialog.ButtonClickListener listener = new AlertDialog.ButtonClickListener() {
			
			@Override
			public void onClick() {
				if (rendererListener != null) {
					rendererListener.onCmdButtonClicked(Cmd.Type.CLEAR, state);
				}
			}
		};
		
		final AlertDialog dlg = new AlertDialog(this.screen, "Do you want to remove your code ?", this.screen.getGame().getResources().getBitmapTrueFont(80), Color.YELLOW, listener, null);
		dlg.show();
		
		return false;
	}	

	protected boolean onCmdClose(final Cmd.State state) {
		//back to menu screen;
		return true;
	}

	protected boolean onCmdSetting(final Cmd.State state) {
		final SettingDialog dlg = new SettingDialog(this.screen);
		dlg.show();
		
		return false;
	}

	protected boolean onCmdInfo(final Cmd.State state) {
		
		final ScriptInfoDialog dlg = new ScriptInfoDialog(this.screen, this.scriptAuthor, this.scriptComment);
		dlg.show();

		return false;
	}

	public void loadLesson(int packId, int scriptId) {
		groupLesson = new LessonGroup(screen, internalListener);
		if (!groupLesson.loadLesson(scriptId)) {
			groupLesson.closeLesson();
		}
	}
	
	public void unloadLesson() {
		if (groupLesson != null) {
			groupLesson.closeLesson();
		}
	}

	public boolean onScreenTouchDown(int x, int y, int pointer, int button) {
		if (groupLesson != null) {
			return !groupLesson.hitTrap(x, y, false);
		}
		return false;
	}

	public boolean onScreenTouchUp(int x, int y, int pointer, int button) {
		if (groupLesson != null) {
			return !groupLesson.hitTrap(x, y, true);
		}
		return false;
	}

	protected void playTrayCatch() {
//		audioPlayer.playSound(AudioConfig.TRAY_CATCH);
	}
	
	protected void playTrayRelease() {
//		audioPlayer.playSound(AudioConfig.TRAY_RELEASE);
	}
	
	protected void playResultSucc() {
		audioPlayer.playSound(AudioConfig.RESULT_SUCC);
	}
	
	protected void playResultFail() {
		audioPlayer.playSound(AudioConfig.RESULT_FAIL);
	}
	
//	protected void setDebugEnabled(boolean enabled) {
//		debugEnabled = enabled; 
//	}
	
}
