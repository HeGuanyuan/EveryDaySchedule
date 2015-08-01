package core.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.widget.RadioGroup;

import com.dailyschedule.DailyScheduleApplication;
import com.dailyschedule.R;

//FragmentActivity is a special activity provided in the Support Library to handle 
//fragments on system versions older than API level 11. If the lowest system version 
//you support is API level 11 or higher, then you can use a regular Activity.

public class BaseActivity extends FragmentActivity {

	public BaseFragment baseFragment;
	
	public RadioGroup rBtnGroup;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		initView();

		if (savedInstanceState != null) {
			String FRAGMENTS_TAG = "android:support:fragments";
			// remove掉保存的Fragment
			savedInstanceState.remove(FRAGMENTS_TAG);
		}
		this.setContentView(R.layout.main_activity_layout);
		((DailyScheduleApplication) getApplication()).addActivity(this);
		baseFragment = new BaseFragment();
		initView();
	}

	/**
	 * @Functiuon 初始化界面
	 * @Authore Heguanyuan 2015-8-1 下午1:30:40
	 */
	private void initView() {
		
	}

	/**
	 * @Functiuon 切换Fragment
	 * @Authore Heguanyuan 2015-8-1 下午3:49:53
	 */
	public void switchContent(Fragment f) {
		getSupportFragmentManager().beginTransaction().addToBackStack(null);
		getSupportFragmentManager().beginTransaction().replace(R.id.content, f).commit();
	}
}
