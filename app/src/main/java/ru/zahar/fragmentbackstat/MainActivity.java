package ru.zahar.fragmentbackstat;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import ru.zahar.fragmentbackstat.fragments.BaseFragment;
import ru.zahar.fragmentbackstat.fragments.Fragment1;

public class MainActivity extends AppCompatActivity {

	private BaseFragment nextFragment;

	private boolean backStack;
	private boolean isRoot;
	private boolean forceLoad;
	private boolean doubleBackToExitPressedOnce;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		FragmentManager fm = getSupportFragmentManager();
		Fragment contentFragment = fm.findFragmentById(getContainerID());
		if (contentFragment == null) {
			contentFragment = createStartFragment();
			loadRootFragment(new Fragment1(), true, true, false);
//			FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//			fragmentTransaction.add(getContainerID(), contentFragment);
//			fragmentTransaction.addToBackStack(null);
//			fragmentTransaction.commit();
//			Log.i("MYTAG", "Add " + contentFragment.getClass().getSimpleName());
		}

	}

	private Fragment createStartFragment() {
		return new Fragment1();
	}

	protected int getContainerID() {
		return R.id.main;
	}
	public void loadRootFragment(BaseFragment fragment, boolean addToBackStack, boolean isRoot, boolean forceLoad) {
		nextFragment = fragment;
		backStack = addToBackStack;
		this.isRoot = isRoot;
		this.forceLoad = forceLoad;
		nextFragment();
	}

	private void exit() {
		if (doubleBackToExitPressedOnce) {
			finish();
			return;
		}

		this.doubleBackToExitPressedOnce = true;
		Toast.makeText(this, "Еще раз", Toast.LENGTH_SHORT).show();

		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				doubleBackToExitPressedOnce = false;
			}
		}, 1000);
	}

	protected boolean hasChild() {
		return 1 < getSupportFragmentManager().getBackStackEntryCount();
	}


	@Override
	public void onBackPressed() {
		FragmentManager supportFragmentManager = getSupportFragmentManager();
		BaseFragment current = (BaseFragment) supportFragmentManager.findFragmentById(getContainerID());
		if (current != null && current.getPreviousFragment() != null) {
			supportFragmentManager.popBackStack(current.getPreviousFragment(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
		} else {
			exit();
		}
	}


	void nextFragment() {
		if (nextFragment != null) {
			BaseFragment currentFragment = (BaseFragment) getSupportFragmentManager().findFragmentById(getContainerID());
			boolean hasOldFragment = currentFragment != null;
			boolean isAlreadyLoaded = false;
			if (hasOldFragment) {
				isAlreadyLoaded = currentFragment.getClass().getSimpleName().equals(nextFragment.getClass().getSimpleName());
			}

			if (!(hasOldFragment && isAlreadyLoaded)) {
				if (isRoot) {
					clearBackStack();
				}
				FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
				boolean b = backStack || isRoot;
				fragmentTransaction.replace(getContainerID(), nextFragment,nextFragment.getClass().getSimpleName());
				if (currentFragment != null && !isRoot) {
					nextFragment.setPreviousFragment(b ? currentFragment.getClass().getSimpleName() : currentFragment.getPreviousFragment());
					fragmentTransaction.addToBackStack(currentFragment.getClass().getSimpleName());
				}else{
					nextFragment.setPreviousFragment(null);
					fragmentTransaction.addToBackStack(null);
				}
				fragmentTransaction.commit();
						Log.i("MYTAG", nextFragment.getClass().getSimpleName() +" "+ b);
			}
			nextFragment = null;
		}
	}


	private void clearBackStack() {
		FragmentManager fragmentManager = getSupportFragmentManager();

		if (0 < fragmentManager.getBackStackEntryCount()) {
			int id = fragmentManager.getBackStackEntryAt(0).getId();
			fragmentManager.popBackStackImmediate(id, FragmentManager.POP_BACK_STACK_INCLUSIVE);
		}

		List<Fragment> fragments = fragmentManager.getFragments();
		if (fragments == null) {
			return;
		}
		FragmentTransaction trans = fragmentManager.beginTransaction();
		for (Fragment fragment : fragments) {
			if (fragment != null) {
				trans.remove(fragment);
			}
		}
		trans.commit();

	}

}
