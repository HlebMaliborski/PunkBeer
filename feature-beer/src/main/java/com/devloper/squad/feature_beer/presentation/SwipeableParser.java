package com.devloper.squad.feature_beer.presentation;

import android.annotation.SuppressLint;
import android.util.Log;

/**
 * @author gleb.maliborsky
 */
public final class SwipeableParser {
	@SuppressLint("UnsafeOptInUsageError")
	public static void enterAction(Object fromState, Object toState, float fraction) {
		Log.d("Ura999", fromState.toString() + " " + toState.toString() + " " + fraction);
	}
}
