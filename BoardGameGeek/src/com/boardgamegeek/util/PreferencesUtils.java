package com.boardgamegeek.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import android.text.TextUtils;

import com.boardgamegeek.pref.MultiSelectListPreference;
import com.boardgamegeek.provider.BggContract;

public class PreferencesUtils {
	private PreferencesUtils() {
	}

	public static boolean getExactSearch(Context context) {
		return getBoolean(context, "exactSearch", true);
	}

	public static boolean getSkipResults(Context context) {
		return getBoolean(context, "skipResults", true);
	}

	public static boolean showLogPlay(Context context) {
		return getBoolean(context, "logPlay", !getBoolean(context, "logHideLog", false));
	}

	public static boolean showQuickLogPlay(Context context) {
		return getBoolean(context, "quickLogPlay", !getBoolean(context, "logHideQuickLog", false));
	}

	public static boolean editPlayer(Context context) {
		return getBoolean(context, "logEditPlayer", false);
	}

	public static boolean showLogPlayQuantity(Context context) {
		return getBoolean(context, "logPlayQuantity", false);
	}

	public static boolean showLogPlayLength(Context context) {
		return getBoolean(context, "logPlayLength", !getBoolean(context, "logHideLength", true));
	}

	public static boolean showLogPlayLocation(Context context) {
		return getBoolean(context, "logPlayLocation", !getBoolean(context, "logHideLocation", true));
	}

	public static boolean showLogPlayIncomplete(Context context) {
		return getBoolean(context, "logPlayIncomplete", !getBoolean(context, "logHideIncomplete", true));
	}

	public static boolean showLogPlayNoWinStats(Context context) {
		return getBoolean(context, "logPlayNoWinStats", !getBoolean(context, "logHideNoWinStats", true));
	}

	public static boolean showLogPlayComments(Context context) {
		return getBoolean(context, "logPlayComments", !getBoolean(context, "logHideComments", true));
	}

	public static boolean showLogPlayPlayerList(Context context) {
		return getBoolean(context, "logPlayPlayerList", !getBoolean(context, "logHidePlayerList", false));
	}

	public static boolean showLogPlayerTeamColor(Context context) {
		return getBoolean(context, "logPlayerTeamColor", !getBoolean(context, "logHideTeamColor", true));
	}

	public static boolean showLogPlayerPosition(Context context) {
		return getBoolean(context, "logPlayerPosition", !getBoolean(context, "logHidePosition", true));
	}

	public static boolean showLogPlayerScore(Context context) {
		return getBoolean(context, "logPlayerScore", !getBoolean(context, "logHideScore", true));
	}

	public static boolean showLogPlayerRating(Context context) {
		return getBoolean(context, "logPlayerRating", !getBoolean(context, "logHideRating", true));
	}

	public static boolean showLogPlayerNew(Context context) {
		return getBoolean(context, "logPlayerNew", !getBoolean(context, "logHideNew", true));
	}

	public static boolean showLogPlayerWin(Context context) {
		return getBoolean(context, "logPlayerWin", !getBoolean(context, "logHideWin", true));
	}

	public static String[] getSyncStatuses(Context context) {
		return getStringArray(context, "syncStatuses", "");
	}

	public static boolean isSyncStatus(Context context, String status) {
		if (TextUtils.isEmpty(status)) {
			return false;
		}
		String[] statuses = getStringArray(context, "syncStatuses", "");
		if (statuses == null) {
			return false;
		}
		for (int i = 0; i < statuses.length; i++) {
			if (status.equals(statuses[i])) {
				return true;
			}
		}
		return false;
	}

	public static boolean getSyncPlays(Context context) {
		return getBoolean(context, "syncPlays", false);
	}

	public static boolean isSyncPlays(String key) {
		return "syncPlays".equals(key);
	}

	public static boolean getSyncBuddies(Context context) {
		return getBoolean(context, "syncBuddies", false);
	}

	public static boolean getSyncShowNotifications(Context context) {
		return getBoolean(context, "sync_notifications", false);
	}

	public static boolean getSyncOnlyCharging(Context context) {
		return getBoolean(context, "sync_only_charging", false);
	}

	public static boolean getSyncOnlyWifi(Context context) {
		return getBoolean(context, "sync_only_wifi", false);
	}

	public static boolean getForumDates(Context context) {
		return getBoolean(context, "advancedForumDates", false);
	}

	public static boolean getDebugInserts(Context context) {
		return getBoolean(context, "advancedDebugInsert", false);
	}

	public static boolean getPolls(Context context) {
		return getBoolean(context, "advancedPolls", true);
	}

	public static boolean getNotifyErrors(Context context) {
		return getBoolean(context, "advancedNotifyErrors", false);
	}

	public static int getNewPlayId(Context context, int oldPlayId) {
		return getInt(context, "playId" + oldPlayId, BggContract.INVALID_ID);
	}

	public static void putNewPlayId(Context context, int oldPlayId, int newPlayId) {
		SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
		Editor editor = sharedPreferences.edit();
		if (newPlayId == BggContract.INVALID_ID) {
			editor.remove("playId" + oldPlayId);
		} else {
			editor.putInt("playId" + oldPlayId, newPlayId);
		}
		editor.commit();
	}

	public static void removeNewPlayId(Context context, int oldPlayId) {
		putNewPlayId(context, oldPlayId, BggContract.INVALID_ID);
	}

	public static int getHIndex(Context context) {
		return getInt(context, "hIndex", -1);
	}

	public static boolean putHIndex(Context context, int hIndex) {
		return putInt(context, "hIndex", hIndex);
	}

	private static boolean putInt(Context context, String key, int hIndex) {
		SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
		Editor editor = sharedPreferences.edit();
		editor.putInt(key, hIndex);
		return editor.commit();
	}

	private static boolean getBoolean(Context context, String key, boolean defaultValue) {
		SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
		return sharedPreferences.getBoolean(key, defaultValue);
	}

	private static int getInt(Context context, String key, int defaultValue) {
		SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
		return sharedPreferences.getInt(key, defaultValue);
	}

	private static String getString(Context context, String key, String defaultValue) {
		SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
		return sharedPreferences.getString(key, defaultValue);
	}

	private static String[] getStringArray(Context context, String key, String defaultValue) {
		return MultiSelectListPreference.parseStoredValue(getString(context, key, defaultValue));
	}
}
