package ui.view;

import com.dailyschedule.R;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import core.base.BaseFragment;

public class MyDailyFragment extends BaseFragment{

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreateView(inflater, container, savedInstanceState);
		View view = inflater.inflate(R.layout.day_list_item_large, null);
		System.out.println("cv");
		return view;
	}
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		System.out.println("in f");
		super.onActivityCreated(savedInstanceState);
	}
}
