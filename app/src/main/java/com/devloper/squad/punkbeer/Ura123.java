package com.devloper.squad.punkbeer;

import android.util.Log;
import androidx.compose.ui.semantics.Role;
import kotlin.jvm.functions.Function0;

/**
 * @author gleb.maliborsky
 */
public class Ura123 implements Function0 {
	private String role;
	private Function0 a;

	public Ura123(Function0 var1, String role) {
		this.role = role;
		this.a = var1;
	}

	public Object invoke() {
		Log.d("Ura555", "Role: " + role + "  Class name: " + a.getClass().getName());
		a.getClass().getName();
		return this.a.invoke();
	}
}