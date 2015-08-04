package ui.view;

import com.dailyschedule.R;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import core.base.BaseFragment;

/**
 * @Class MyDailyFragment 以列表陈列每天的事件
 * @Author He Guanyuan 2015-8-4 上午9:09:01
 */
public class MyDailyFragment extends BaseFragment{

	private ListView dailyList;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		View view = inflater.inflate(R.layout.daily_layout, null);
		return view;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}
	
	private void initView (){
		
	}
}
