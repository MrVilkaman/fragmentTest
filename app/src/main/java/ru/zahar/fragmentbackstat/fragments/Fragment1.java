package ru.zahar.fragmentbackstat.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import butterknife.OnClick;
import ru.zahar.fragmentbackstat.R;

public class Fragment1 extends BaseFragment {


	@Override
	protected String getText() {
		return "1";
	}

	@Override
	protected void onCreateView(View view, Bundle savedInstanceState) {

	}

	@Override
	protected BaseFragment nextFragment() {
		return new Fragment2();
	}


}
