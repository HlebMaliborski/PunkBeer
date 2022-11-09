package com.devloper.squad.feature_beer.presentation;

import androidx.compose.ui.semantics.Role;
import androidx.compose.ui.state.ToggleableState;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

/**
 * @author gleb.maliborsky
 */
public class ToggleableComposeCallback1 implements Function1 {
	private static final String TAG = "ClickableCompose";
	private final Role role;
	private final Function1 function;
	private final boolean value;

	public ToggleableComposeCallback1(Function1 function, Role role, boolean state) {
		this.role = role;
		this.function = function;
		this.value = state;
	}

	@Override
	public Object invoke(Object o) {
		return null;
	}
}