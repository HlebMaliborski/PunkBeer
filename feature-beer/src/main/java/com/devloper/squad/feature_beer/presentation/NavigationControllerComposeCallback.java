package com.devloper.squad.feature_beer.presentation;

import androidx.navigation.NavDestination;

import com.dynatrace.android.agent.DTXAction;
import com.dynatrace.android.agent.Dynatrace;
import com.dynatrace.android.agent.Global;
import com.dynatrace.android.agent.util.Utility;

/**
 * @author gleb.maliborsky
 */
public final class NavigationControllerComposeCallback {
	private static final String TAG = "NavigationCompose";

	public static void navigate(NavDestination destination) {
		String from = "";
		String to = "";

		if (destination != null) {
			to = destination.getRoute();
		}

		if (Global.DEBUG) {
			Utility.zlogD(TAG,
					String.format("Transition to {%s}", to)
			);
		}

		DTXAction action = Dynatrace.enterAction(
				String.format("Transition to {%s}", to)
		);
		action.leaveAction();
	}
}
