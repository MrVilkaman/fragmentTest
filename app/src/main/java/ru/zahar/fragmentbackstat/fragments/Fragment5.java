package ru.zahar.fragmentbackstat.fragments;

import android.os.Bundle;
import android.view.View;

public class Fragment5 extends BaseFragment {


	@Override
	protected String getText() {
		return "5";
	}

	@Override
	protected void onCreateView(View view, Bundle savedInstanceState) {

	}

	@Override
	protected BaseFragment nextFragment() {
		return new Fragment5();
	}


}
