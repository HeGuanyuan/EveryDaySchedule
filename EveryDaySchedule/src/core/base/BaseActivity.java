package core.base;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

//FragmentActivity is a special activity provided in the Support Library to handle 
//fragments on system versions older than API level 11. If the lowest system version 
//you support is API level 11 or higher, then you can use a regular Activity.

public class BaseActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		initView();
	}


	/**
	 *@Functiuon 初始化界面
	 *@Authore Heguanyuan 2015-8-1 下午1:30:40
	 */
	private void initView() {
		
	}
}
