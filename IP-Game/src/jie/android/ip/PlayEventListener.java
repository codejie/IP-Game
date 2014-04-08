package jie.android.ip;

import jie.android.ip.database.DBAccess;
import jie.android.ip.playservice.PlayService;

public class PlayEventListener {

	private final IPGame game;
	private final PlayService playService;
	private final DBAccess dbAccess;
	
	public PlayEventListener(final IPGame game) {
		this.game = game;
		
		playService = this.game.getPlayService();
		dbAccess = this.game.getDBAccess();
	}
	
	public void onPackItemPlaySucc(int packId, int itemId, int score) {
		playService.submitLeaderboardScore(getLeaderboardIdByItemId(itemId), score);
		onLeaderboardUpdate(packId, itemId, score);				

		if (dbAccess.isPackAllUnlock(packId)) {
			playService.unlockAchievement(getAchievementIdByPackId(packId));
			onAchievementUnlock(packId);
		}
	}
	
	public void onTrackerDone(final PlayServiceTracker tracker) {
		
	}

	private void onAchievementUnlock(int packId) {
		
	}
	
	private void onLeaderboardUpdate(int packId, int itemId, int score) {
		
	}
	
	private final String getAchievementIdByPackId(int packId) {
		return dbAccess.getPlayServiceId(packId, 0);
	}

	private final String getLeaderboardIdByItemId(int itemId) {
		return dbAccess.getPlayServiceId(itemId, 1);
	}
	
	
}
