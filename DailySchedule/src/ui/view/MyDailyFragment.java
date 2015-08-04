package ui.view;

import java.util.ArrayList;

import ui.adapter.DailyListAdapter;
import ui.adapter.DailyListAdapter.onSubItemClickListener;

import com.dailyschedule.R;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import core.base.BaseFragment;
import core.entity.DailyEntity;
import core.entity.ThingEntity;

/**
 * @Class MyDailyFragment 以列表陈列每天的事件
 * @Author He Guanyuan 2015-8-4 上午9:09:01
 */
public class MyDailyFragment extends BaseFragment implements onSubItemClickListener {

	private Button floatBtn;
	private DailyListAdapter adapter;
	private ListView dailyListView;
	private ArrayList<DailyEntity> dailyList;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		View view = inflater.inflate(R.layout.daily_layout, null);
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initView();
		setListener();
	}

	@Override
	protected void initTitleBar() {
		// super.initBaseView();
		System.out.println("MyDaily----> //super.initBaseView();");
	}

	private void initView() {
		View view = getView();
		dailyListView = (ListView) view.findViewById(R.id.daily_list);
		floatBtn = (Button) view.findViewById(R.id.btn_float);
		
		dailyList = new ArrayList<DailyEntity>();
		
		DailyEntity e = new DailyEntity();
		ThingEntity tE = new ThingEntity();
		tE.setName("sketch");
		ArrayList<ThingEntity> tl = new ArrayList<ThingEntity>();
		tl.add(tE);
		e.setThingList(tl);
		
		dailyList.add(e);
		
		
		adapter = new DailyListAdapter(getActivity());
		adapter.setList(dailyList);
		dailyListView.setAdapter(adapter);
	}

	
	private void setListener() {
		floatBtn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch (v.getId()) {
		case R.id.btn_float:
			// 悬浮Button
			floatBtnOnclick();
			break;

		default:
			break;
		}
	}

	private void floatBtnOnclick() {

	}

	@Override
	public void onSubItemClick(String timeStamp, int index) {

	}
}
