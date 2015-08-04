package ui.adapter;

import java.util.ArrayList;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

	private ArrayList<Fragment> fragmentList;
	private FragmentManager fm;

	public MyFragmentPagerAdapter(FragmentManager fm) {
		super(fm);
		this.fm = fm;
	}

	public void setFragmentList(ArrayList<Fragment> list) {
		this.fragmentList = list;
	}

	@Override
	public Fragment getItem(int arg0) {
		if (fragmentList != null) {
			if (arg0 < fragmentList.size() && arg0 >= 0) {
				return fragmentList.get(arg0);
			} else
				return null;
		} else
			return null;
	}
	
	@Override
	public int getItemPosition(Object object) {
		// TODO Auto-generated method stub
		return super.getItemPosition(object);
	}

	@Override
	public int getCount() {
		return fragmentList == null ? 0 : fragmentList.size();
	}
	
	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		// TODO Auto-generated method stub
		return super.instantiateItem(container, position);
	}
	
	public void clearList(){
		if(fragmentList != null ){
			fragmentList.clear();
		}
	}

}
