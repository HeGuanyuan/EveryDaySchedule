package ui.view;

import ui.adapter.ThingListAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.dailyschedule.R;

import core.base.BaseFragment;

/**
 * @Class 加载事件列表
 * @Author He Guanyuan 2015-8-22 上午9:17:11
 */
public class ThingsFragment extends BaseFragment {

	private Button addBtn;
	private ListView thingListView;
	private ThingListAdapter adapter;


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		View view = inflater.inflate(R.layout.things_fragment_layout, null);
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		initView();
		setListener();
		initData();
	}

	@Override
	public void onResume() {
		super.onResume();
		adapter.refreshData();
	}

	@Override
	protected void initTitleBar() {
		// 没有titlebar
		// super.initBaseView();
	}

	private void initData() {
		adapter = new ThingListAdapter(getActivity());
		thingListView.setAdapter(adapter);
	}

	private void initView() {
		View view = getView();
		addBtn = (Button) view.findViewById(R.id.btn_add);
		thingListView = (ListView) view.findViewById(R.id._thing_listview);
	}

	private void setListener() {
		addBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(getActivity(), AddThingActivity.class);
				startActivity(i);
			}
		});

		thingListView.setOnItemClickListener(onItemClickLsn);
	}

	OnItemClickListener onItemClickLsn = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

		}
	};

	

}
