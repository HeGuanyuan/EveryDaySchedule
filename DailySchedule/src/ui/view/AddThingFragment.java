package ui.view;

import ui.adapter.ColorGridAdapter;
import ui.adapter.ColorGridAdapter.colorGridItemOnClickListener;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import com.dailyschedule.R;
import core.base.BaseFragment;

/**
 * @Class AddThingFragment 添加事件
 * @Author He Guanyuan 2015-8-13 下午3:55:18
 */
public class AddThingFragment extends BaseFragment implements OnClickListener, colorGridItemOnClickListener {

	private EditText nameTv;
	private ColorGridAdapter colorAdapter;
	private CheckBox isCyclicalCb;
	private LinearLayout remindLayout;
	private HorizontalScrollView choseRemindDayLayout;
	private CheckBox CB1;
	private CheckBox CB2;
	private CheckBox CB3;
	private CheckBox CB4;
	private CheckBox CB5;
	private CheckBox CB6;
	private CheckBox CB7;
	private CheckBox checkboxList[];
	private boolean[] checkboxStata;
	private TextView remindTv;

	private String remindTimeString;
	private TextView remindTimeTxt;

	private Button addBtn;
	private Button cancelBtn;

	/** color */
	private GridView colorGridView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		View view = inflater.inflate(R.layout.add_thing_layout, null);
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		setTitleTxt("创建事件");
		initView();
		setListener();
	}

	private void initView() {
		View view = getView();
		nameTv = (EditText) view.findViewById(R.id.name_etv);

		isCyclicalCb = (CheckBox) view.findViewById(R.id.cb_period);
		choseRemindDayLayout = (HorizontalScrollView) view.findViewById(R.id.chose_day_layout);
		choseRemindDayLayout.setVisibility(View.GONE);

		CB1 = (CheckBox) view.findViewById(R.id.cb_1);
		CB2 = (CheckBox) view.findViewById(R.id.cb_2);
		CB3 = (CheckBox) view.findViewById(R.id.cb_3);
		CB4 = (CheckBox) view.findViewById(R.id.cb_4);
		CB5 = (CheckBox) view.findViewById(R.id.cb_5);
		CB6 = (CheckBox) view.findViewById(R.id.cb_6);
		CB7 = (CheckBox) view.findViewById(R.id.cb_7);
		checkboxList = new CheckBox[] { CB1, CB2, CB3, CB4, CB5, CB6, CB7 };
		checkboxStata = new boolean[] { false, false, false, false, false, false, false };

		remindLayout = (LinearLayout) view.findViewById(R.id.remind_layout);
		remindLayout.setVisibility(View.GONE);

		remindTv = (TextView) view.findViewById(R.id.remind_tv);
		remindTimeTxt = (TextView) view.findViewById(R.id.remind_time_tv);

		addBtn = (Button) view.findViewById(R.id.btn_add);
		cancelBtn = (Button) view.findViewById(R.id.btn_cancel);

		colorGridView = (GridView) view.findViewById(R.id.color_grid);
		colorAdapter = new ColorGridAdapter(getActivity());
		colorAdapter.setGridItemOnClickListener(this);
		colorGridView.setAdapter(colorAdapter);
	}

	private void setListener() {
		/** 是否重复 */
		isCyclicalCb.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (choseRemindDayLayout != null) {

					if (isChecked) {
						choseRemindDayLayout.setVisibility(View.VISIBLE);
						remindLayout.setVisibility(View.VISIBLE);
					} else {
						choseRemindDayLayout.setVisibility(View.GONE);
						remindLayout.setVisibility(View.GONE);
					}
				}
			}
		});
		/** 选择重复的日期 绑定监听器 */
		for (int i = 0; i < 7; i++) {
			if (checkboxList[i] == null) {
				System.out.println("return" + i);
				return;
			}
			checkboxList[i].setOnCheckedChangeListener(OnDayChoseListener);
			checkboxList[i].setTag(i);
		}

		/** 提醒时间 */
		remindTv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				/** 弹出时间选择窗口 */
				TimePickerDialog picker = new TimePickerDialog(getActivity(), new OnTimeSetListener() {

					@Override
					public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
						remindTimeString = hourOfDay + ":" + minute + "";
						remindTimeTxt.setText(remindTimeString);
						remindTv.setText("提醒时间: ");
					}
				}, 21, 0, true);
				picker.show();
			}
		});

		/** 颜色选择 */
		// colorGridView.setOnItemClickListener(onColorGridClick);

	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch (v.getId()) {
		case R.id.btn_add:
			addThingOperation();
			break;
		case R.id.btn_cancel:
			this.getActivity().finish();
			break;
		default:
			break;
		}

	};

	private void addThingOperation() {
		String name;
		if (nameTv != null) {
			name = nameTv.getText().toString();
		}

		boolean isCyclical;
		String remindDayofWeek;
		if (isCyclicalCb.isChecked() == false) {
			isCyclical = false;
		} else {
			isCyclical = true;
			remindDayofWeek = "";
			for (int i = 0; i < 7; i++) {
				checkboxStata[i] = checkboxList[i].isChecked();
				if (checkboxStata[i] == true) {
					remindDayofWeek += String.valueOf(i);
					remindDayofWeek += ",";
				}
			}
			remindDayofWeek = remindDayofWeek.substring(0, remindDayofWeek.length() - 1);
		}
		String color = "";

	}

	/** 选择每周重复的日期 的监听器 */
	OnCheckedChangeListener OnDayChoseListener = new OnCheckedChangeListener() {

		@Override
		public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
			int i = (Integer) buttonView.getTag();
			if (isChecked) {
				checkboxStata[i] = true;
			} else {
				checkboxStata[i] = false;
			}
		}
	};

	/** 颜色选择 */
	@Override
	public void itemOnClick(int pos) {
		// TODO Auto-generated method stub
		System.out.println("color item " + pos);
	}

}
