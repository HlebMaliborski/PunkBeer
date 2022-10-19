package com.devloper.squad.feature_beer.presentation;

import android.util.Log;
import androidx.compose.ui.semantics.Role;
import androidx.compose.ui.state.ToggleableState;
import kotlin.jvm.functions.Function0;

/**
 * @author gleb.maliborsky
 */
public class ToggleableComposeCallback implements Function0 {
	private final Role role;
	private final Function0 function;
	private final String state1;

	public ToggleableComposeCallback(Function0 function, Role role, ToggleableState state) {
		Log.d("Ura666", " State: " + state);
		this.role = role;
		this.function = function;
		this.state1 = state.name();
		Log.d("Ura777", " State: " + state.getClass().hashCode());

	}

	public Object invoke() {
		String roleString = "";
		if (role != null) {
			roleString = role.toString();
		}

		Log.d("Ura666", "Role: " + roleString + "  Class name: " + function.getClass().getName() + " State: " + state1);

		return this.function.invoke();
	}
}