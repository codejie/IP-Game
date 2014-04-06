package jie.android.ip.playservice;

public abstract class PlayService {
	
	protected PlayServiceListener playServiceListener = null;

	public void setPlayServiceListener(final PlayServiceListener listener) {
		playServiceListener = listener;
	}
	
	public abstract void create();
	public abstract void connect();
	public abstract void disconnect();
	public abstract boolean isSignedIn();
	
	public abstract void onActivityResult(int request, int result);
	public abstract void showErrorDialog();

	public abstract void showAchievements();
	public abstract void showAllLeaderboards();
	public abstract void showLeaderboard(final String id);
	
	public abstract void unlockAchievement(final String id);
	public abstract void submitLeaderboardScore(final String id, int score);
	
}
