package com.devloper.squad.feature_beer.presentation;

/**
 * @author gleb.maliborsky
 */
enum ClickType {
	LONG_CLICK("Long click"),
	CLICK("Click"),
	DOUBLE_CLICK("Double click");

	private final String value;

	ClickType(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return value;
	}
}

