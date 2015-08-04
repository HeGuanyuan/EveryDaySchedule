package ui.view;

import java.util.ArrayList;
import java.util.List;

import ui.adapter.MyFragmentAdapter;
import ui.adapter.MyViewPagerAdapter;

import com.dailyschedule.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import core.base.BaseFragment;

/**
 * @Class MyDailyFragment HomeFragment
 * @Author He Guanyuan 2015-8-3 下午9:35:26
 */
public class HomeFragment extends BaseFragment {

	private ViewPager viewPager;
	private List<View> viewList;
	private ArrayList<Fragment> fragmentList;
	private MyFragmentAdapter mAdapter;
	private ImageView cursor;
	private TextView t1, t2, t3;
	private int currentIndex;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		View view = inflater.inflate(R.layout.home_fragment_layout, null);
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initView();

	}

	@Override
	public void leftBtnOnclick() {
		super.leftBtnOnclick();
		System.out.println("leftBtnOnclick");
	}

	private void initView() {
		setTitleTxt("Home");
//		initViewPager();
		initFragmentPager();
	}

	/**
	 *@Functiuon 初始化ViewPager
	 *@Author Heguanyuan 2015-8-3 下午9:29:40
	 */
	private void initViewPager() {
		View view = getView();
		viewPager = (ViewPager) view.findViewById(R.id.view_pager);
		viewList = new ArrayList<View>();
		
		LayoutInflater mInflater = getActivity().getLayoutInflater();
		viewList.add(mInflater.inflate(R.layout.things_fragment_layout, null));
		viewList.add(mInflater.inflate(R.layout.daily_layout, null));
		viewPager.setAdapter(new MyViewPagerAdapter(viewList));
		viewPager.setCurrentItem(0);
		viewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub

			}
		});
	}
	
	private void initFragmentPager (){
		View view = getView();
		viewPager = (ViewPager) view.findViewById(R.id.view_pager);
		fragmentList = new ArrayList<Fragment>();
		
		Fragment daily = new MyDailyFragment();
		Fragment things = new ThingsFragment();
		fragmentList.add(daily);
		fragmentList.add(things);
		mAdapter = new MyFragmentAdapter(getActivity().getSupportFragmentManager());
		mAdapter.setFragmentList(fragmentList);
		viewPager.setAdapter(mAdapter);
	}
	
	
}









































