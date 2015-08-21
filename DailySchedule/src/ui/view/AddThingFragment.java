package ui.view;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import ui.adapter.ColorGridAdapter;
import ui.adapter.ColorGridAdapter.colorGridItemOnClickListener;
import android.annotation.SuppressLint;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
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
import core.db.DBManager;
import core.entity.ColorEntity;
import core.entity.ThingEntity;

/**
 * @Class AddThingFragment 添加事件
 * @Author He Guanyuan 2015-8-13 下午3:55:18
 */
public class AddThingFragment extends BaseFragment implements OnClickListener, colorGridItemOnClickListener {

	private EditText nameTv;
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

	private DBManager dbManager;

	/** color */
	private GridView colorGridView;
	private ColorGridAdapter colorAdapter;

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
		dbManager = new DBManager(getActivity());
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

						SimpleDateFormat format = new SimpleDateFormat("HH:mm");
						Calendar c = Calendar.getInstance();
						c.set(2015, 8, 21, hourOfDay, minute);
						remindTimeString = format.format(c.getTime());

						// remindTimeString = hourOfDay + ":" + minute + "";
						remindTimeTxt.setText(remindTimeString);
						remindTv.setText("提醒时间: ");
					}
				}, 21, 0, true);
				picker.show();
			}
		});

		/** 颜色选择 */
		// colorGridView.setOnItemClickListener(onColorGridClick);

		cancelBtn.setOnClickListener(this);
		addBtn.setOnClickListener(this);

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

	private void c() {
		ThingEntity entity = new ThingEntity();
		String name;
		if (nameTv != null) {
			name = nameTv.getText().toString();
		} else {

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

		dbManager.writeData(entity);
	}

	private void addThingOperation() {
		String name;
		String colorCode;
		String creatTime = toString(Calendar.getInstance().getTimeInMillis() / 1000);
		// String isCyslical = "";
		String remindDayOfWeek = "";
		String remindTime = remindTimeString;

		ThingEntity entity = new ThingEntity();
		/** name */
		if (nameTv.getText().toString().length() > 0) {
			name = nameTv.getText().toString();
			entity.setName(name);
		} else {
			showToast(getActivity(), "请输入事件名称");
			return;
		}

		/** 颜色 */
		colorCode = colorAdapter.getColorEntityChecked().getCode();
		entity.setColor(colorCode);

		/** 创建时间 */
		entity.setCreatTime(creatTime);

		/** 周期 */
		if (isCyclicalCb.isChecked()) {
			entity.setCyclical(true);

			/** remindDayOfWeek */
			for (int i = 0; i < checkboxStata.length; i++) {
				if (checkboxStata[i] == true) {
					remindDayOfWeek += toString(i);
					remindDayOfWeek += ",";
				}
			}
			remindDayOfWeek = remindDayOfWeek.substring(0, remindDayOfWeek.length() - 1);
			if (remindDayOfWeek.length() < 1) {
				showToast(getActivity(), "请选择提醒日期");
				return;
			}
			entity.setRemindDayofWeek(remindDayOfWeek);

			/** remindTime */
			if (!isEmpty(remindTime)) {
				entity.setRemindTime(remindTime);
			} else {
				showToast(getActivity(), "请选择提醒时间");
				return;
			}

		} else {
			entity.setCyclical(false);
		}

		/** 标签 */

		dbManager.writeData(entity);
		ColorEntity color = new ColorEntity(colorCode, "1");
		String res = dbManager.updateColorInfo(color);
		Log.d("colorinfo", res);
		showToast(getActivity(), "添加成功");
		getActivity().finish();
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
