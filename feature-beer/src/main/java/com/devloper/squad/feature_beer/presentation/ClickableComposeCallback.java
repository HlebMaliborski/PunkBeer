package com.devloper.squad.feature_beer.presentation;

import androidx.compose.ui.semantics.Role;
import kotlin.jvm.functions.Function0;

/**
 * @author gleb.maliborsky
 */
public class ClickableComposeCallback implements Function0 {
	private static final String TAG = "ClickableCompose";
	private final Role role;
	private final Function0 function;
	private final String type;

	public ClickableComposeCallback(Function0 function, Role role, String type) {
		this.role = role;
		this.function = function;
		this.type = type;
	}

	public Object invoke() {

		return this.function.invoke();
	}
}