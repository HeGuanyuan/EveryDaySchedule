package ui.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.dailyschedule.R;

import core.base.BaseFragment;

public class ThingsFragment extends BaseFragment {

	private Button addBtn;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
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
	}

	@Override
	protected void initTitleBar() {
		// 没有titlebar
		// super.initBaseView();
	}

	private void initView() {
		View view = getView();
		addBtn = (Button) view.findViewById(R.id.btn_add);
	}
	
	private void setListener() {
		addBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getActivity(), AddThingActivity.class);
				startActivity(i);
			}
		});
	}
}
