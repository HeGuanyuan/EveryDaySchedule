package com.dailyschedule;

import ui.view.MyDailyFragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import core.base.BaseActivity;

public class MainActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		System.out.println("i am in");
		initView();
	}

	private void initView() {
		Fragment f = new MyDailyFragment();
		switchContent(f);

	}
}
