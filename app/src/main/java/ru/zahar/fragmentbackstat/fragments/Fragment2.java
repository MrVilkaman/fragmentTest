package ru.zahar.fragmentbackstat.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

public class Fragment2 extends BaseFragment {


	@Override
	protected String getText() {
		return "2";
	}

	@Override
	protected void onCreateView(View view, Bundle savedInstanceState) {

	}

	@Override
	protected BaseFragment nextFragment() {
		return new Fragment3();
	}


}
