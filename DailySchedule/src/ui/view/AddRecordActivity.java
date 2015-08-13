package ui.view;

import android.os.Bundle;
import core.base.BaseActivity;

/**
 * @Class AddRecordActivity 添加每日记录
 * @Author He Guanyuan 2015-8-10 下午5:42:12
 */
public class AddRecordActivity extends BaseActivity {

	private AddRecordFragment fragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		fragment = new AddRecordFragment();
		switchContent(fragment);
	}
}
