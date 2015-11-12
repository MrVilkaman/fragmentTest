package ru.zahar.fragmentbackstat.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ru.zahar.fragmentbackstat.MainActivity;
import ru.zahar.fragmentbackstat.R;

public abstract class BaseFragment extends Fragment {

	@Bind(R.id.text)
	TextView textView;

	private boolean addToBackStack = true;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(getLayoutId(), container, false);
//		if (isWorkCall()) {
			ButterKnife.bind(this, view);
			onCreateView(view, savedInstanceState);
//		}
		textView.setText(getText());

		return view;
	}

	public boolean addToBackStack(){
		return addToBackStack;
	}

	public void setAddToBackStack(boolean addToBackStack) {
		this.addToBackStack = addToBackStack;
	}

	protected abstract String getText();

	protected int getLayoutId() {
		return R.layout.fragment_main;
	}

	protected abstract void onCreateView(View view, Bundle savedInstanceState);

	public void loadRootFragment(BaseFragment fragment, boolean addToBackStack, boolean isRoot, boolean forceLoad) {
		((MainActivity)getActivity()).loadRootFragment(fragment,addToBackStack,isRoot, false);
	}

	@OnClick(R.id.restart)
	void onRestart(){
		loadRootFragment(new Fragment1(), false, true, true);
	}

	@OnClick(R.id.with_back)
	void onWithBack(){
		loadRootFragment(nextFragment(),true,false,false);
	}

	protected abstract BaseFragment nextFragment();

	@OnClick(R.id.without_back)
	void onWithOutBack(){
		loadRootFragment(nextFragment(),false,false,false);
	}
}
