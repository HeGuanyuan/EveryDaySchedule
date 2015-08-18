package ui.view;

import android.os.Bundle;
import core.base.BaseActivity;

public class AddThingActivity extends BaseActivity {
	private AddThingFragment fragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		fragment = new AddThingFragment();
		switchContent(fragment);
	}
}
