package ru.zahar.fragmentbackstat.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

public class Fragment3 extends BaseFragment {


	@Override
	protected String getText() {
		return "3";
	}

	@Override
	protected void onCreateView(View view, Bundle savedInstanceState) {

	}

	@Override
	protected BaseFragment nextFragment() {
		return new Fragment4();
	}


}
