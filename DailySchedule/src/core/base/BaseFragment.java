package core.base;

import com.dailyschedule.R;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @Class BaseFragment 基础Fragment
 * @Author He Guanyuan 2015-8-3 下午1:01:16
 */
public class BaseFragment extends Fragment implements OnClickListener {

	/** 标题栏基础Views */
	private RelativeLayout titleBarLayout;
	private TextView leftBtn;
	private ImageView leftBtnImage;
	private LinearLayout leftBtnLayout;
	private TextView rightBtn;
	private TextView titleTv;
	private LinearLayout baseCenterLayout;
	private LinearLayout homeCenterLayout;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (savedInstanceState != null) {
			String FRAGMENTS_TAG = "android:support:fragments";
			// remove掉保存的Fragment
			savedInstanceState.remove(FRAGMENTS_TAG);
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return super.onCreateView(inflater, container, savedInstanceState);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		initView();
		setListener();
	}

	private void initView() {
		View view = getView();
		titleBarLayout = (RelativeLayout) view.findViewById(R.id.title_bar_layout);
		leftBtn = (TextView) view.findViewById(R.id.left_btn_txt);
		leftBtnImage = (ImageView) view.findViewById(R.id.left_btn_drawble);
		leftBtnLayout = (LinearLayout) view.findViewById(R.id.left_btn_layout);
		rightBtn = (TextView) view.findViewById(R.id.right_btn_txt);
		titleTv = (TextView) view.findViewById(R.id.title_txt);
	}

	/********************************************** 监听 点击事件 ******************************************/
	/**
	 * @Functiuon 绑定监听器
	 * @Author Heguanyuan 2015-8-3 下午2:15:36
	 */
	private void setListener() {
		if (leftBtn != null) {
			leftBtn.setOnClickListener(this);
		}
		if (rightBtn != null) {
			rightBtn.setOnClickListener(this);
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.left_btn_txt:
			leftBtnOnclick();
			break;
		case R.id.right_btn_txt:
			rightBtnOnclick();
		default:
			break;
		}
	}

	/********************************************** 标题栏 ******************************************/
	/**
	 * @Functiuon 设置标题text
	 * @Authore Heguanyuan 2015-8-3 下午1:39:50
	 */
	public void setTitleTxt(String title) {
		titleTv.setText(title);
	}

	public void setTitltVisible(boolean visible) {

	}

	/********************************************** 左侧Button ******************************************/
	/**
	 * @Functiuon 设置左侧Button 可见性
	 * @Author Heguanyuan 2015-8-3 下午1:47:02
	 */
	public void setLeftBtnVisiblity(int visibility) {
		leftBtn.setVisibility(visibility);
	}

	/**
	 * @Functiuon 设置左侧button文字
	 * @Author Heguanyuan 2015-8-3 下午2:06:18
	 */
	public void setLeftBtnTxt(String txt) {
		leftBtn.setText(txt);
	}

	/**
	 * @Functiuon 设置左侧button 图标
	 * @Author Heguanyuan 2015-8-3 下午2:06:43
	 */
	public void setLeftBtnDrawble(int id) {
		leftBtn.setVisibility(View.GONE);
		leftBtn.setText("");
		leftBtn.setBackgroundResource(id);
	}

	/**
	 * @Functiuon 左侧button单击事件
	 * @Author Heguanyuan 2015-8-3 下午2:11:51
	 */
	public void leftBtnOnclick() {

	}

	/********************************************** 右侧Button ******************************************/
	/**
	 * @Functiuon 设置右侧Button visibility
	 * @Author Heguanyuan 2015-8-3 下午1:47:42
	 */
	public void setRightBtnVisiblity(int visibility) {
		rightBtn.setVisibility(visibility);
	}

	/**
	 * @Functiuon 设置左侧button文字
	 * @Author Heguanyuan 2015-8-3 下午2:06:18
	 */
	public void setRightBtnTxt(String txt) {
		rightBtn.setText(txt);
	}

	/**
	 * @Functiuon 设置左侧button 图标
	 * @Author Heguanyuan 2015-8-3 下午2:06:43
	 */
	public void setRightBtnDrawble(int id) {
		rightBtn.setVisibility(View.GONE);
		rightBtn.setText("");
		rightBtn.setBackgroundResource(id);
	}

	/**
	 * @Functiuon 右侧button单击事件
	 * @Author Heguanyuan 2015-8-3 下午2:11:51
	 */
	public void rightBtnOnclick() {

	}

	/********************************************** utils ******************************************/

	public void showToast(String s) {
		Toast t = new Toast(getActivity());
		t.setText(s);
		t.show();
	}
}
