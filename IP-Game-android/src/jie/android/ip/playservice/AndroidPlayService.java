package jie.android.ip.playservice;

import jie.android.ip.playservice.PlayService;
import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.google.android.gms.games.Games;
import com.google.example.games.basegameutils.GameHelper;

public class AndroidPlayService extends PlayService {

	protected static final String Tag = AndroidPlayService.class.getSimpleName();

	private final Activity activity;
	
	private GameHelper gameHelper;

	private GameHelper.GameHelperListener gameHelperListener = new GameHelper.GameHelperListener() {
		
		@Override
		public void onSignInSucceeded() {
			Log.d(Tag, "onSignInSucceeded()");
			if (playServiceListener != null) {
				playServiceListener.onSignInSucceeded();
			}
		}
		
		@Override
		public void onSignInFailed() {
			Log.d(Tag, "onSignInFailed()");
			if (playServiceListener != null) {
				playServiceListener.onSignInFailed();
			}
		}
	};
	
	public AndroidPlayService(Activity activity) {
		this.activity = activity;
	}
	
	@Override
	public void create() {
		gameHelper = new GameHelper(activity, GameHelper.CLIENT_GAMES);
		//gameHelper.enableDebugLog(true);
		gameHelper.setup(gameHelperListener);
	}

	@Override
	public void connect() {
		gameHelper.onStart(activity);
	}

	@Override
	public void disconnect() {
		gameHelper.onStop();
	}

	@Override
	public boolean isSignedIn() {
		return gameHelper.isSignedIn();
	}

	@Override
	public void onActivityResult(int request, int result) {
		gameHelper.onActivityResult(request, result, null);
	}
	
	@Override
	public void showAchievements() {
		if (gameHelper.isSignedIn()) {
            activity.startActivityForResult(Games.Achievements.getAchievementsIntent(gameHelper.getApiClient()), GameHelper.RC_UNUSED);
		}
	}

	@Override
	public void showAllLeaderboards() {
		if (gameHelper.isSignedIn()) {
			activity.startActivityForResult(Games.Leaderboards.getAllLeaderboardsIntent(gameHelper.getApiClient()), GameHelper.RC_UNUSED);
		}
	}

	@Override
	public void showLeaderboard(final String id) {
		if (gameHelper.isSignedIn()) {
			activity.startActivityForResult(Games.Leaderboards.getLeaderboardIntent(gameHelper.getApiClient(), id), GameHelper.RC_UNUSED);
		}
	}

	@Override
	public void unlockAchievement(final String id) {
		if (gameHelper.isSignedIn()) {
			Games.Achievements.unlock(gameHelper.getApiClient(), id);
		}
	}

	@Override
	public void submitLeaderboardScore(String id, int score) {
		if (gameHelper.isSignedIn()) {
			Games.Leaderboards.submitScore(gameHelper.getApiClient(), id, score);
		}
	}

	@Override
	public void showErrorDialog() {
		gameHelper.showFailureDialog();
	}

}
