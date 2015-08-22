package ui.view;

import java.util.ArrayList;
import java.util.Calendar;

import ui.adapter.AddRecordGridAdapter;
import ui.adapter.DailyListAdapter;
import ui.adapter.DailyListAdapter.onSubItemClickListener;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.dailyschedule.R;
import core.base.BaseFragment;
import core.db.DBManager;
import core.entity.DailyEntity;
import core.entity.ThingEntity;

/**
 * @Class MyDailyFragment 以列表陈列每天的事件
 * @Author He Guanyuan 2015-8-4 上午9:09:01
 */
public class MyDailyFragment extends BaseFragment implements onSubItemClickListener {

	private Button floatBtn;
	private DailyListAdapter adapter;
	private ListView dailyListView;
	private ArrayList<DailyEntity> dailyList;
	private PopupWindow popWindow;
	private DBManager dbManager;

	// 记录选择的时间
	private int Year;
	private int MonthOfYear;
	private int DayOfMonth;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		View view = inflater.inflate(R.layout.daily_layout, null);
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initView();
		setListener();
	}

	@Override
	protected void initTitleBar() {
		// super.initBaseView();
		System.out.println("MyDaily----> //super.initBaseView();");
	}

	private void initView() {
		View view = getView();
		dailyListView = (ListView) view.findViewById(R.id.daily_list);
		floatBtn = (Button) view.findViewById(R.id.btn_float);

		dailyList = new ArrayList<DailyEntity>();

		// DailyEntity e = new DailyEntity();
		// ThingEntity tE = new ThingEntity();
		// tE.setName("sketch");
		// ArrayList<ThingEntity> tl = new ArrayList<ThingEntity>();
		// tl.add(tE);
		// e.setThingList(tl);
		//
		// dailyList.add(e);

		loadRecords();

		adapter = new DailyListAdapter(getActivity());
		adapter.setList(dailyList);
		dailyListView.setAdapter(adapter);

		DBManager m = new DBManager(getActivity());
		// m.getWritableDatabase();
		// testDB();
	}

	private void setListener() {
		// dailyListView.setOnItemClickListener(onListItemClick);
		floatBtn.setOnClickListener(this);
	}

	OnItemClickListener onListItemClick = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			// pos从0开始,结合显示的范围确定
			Log.d("item", position + "");
		}
	};

	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch (v.getId()) {
		case R.id.btn_float:
			// 悬浮Button
			floatBtnOnclick();
			break;

		default:
			break;
		}
	}

	private void floatBtnOnclick() {
		showMenu();
	}

	@Override
	public void onSubItemClick(String timeStamp, int index) {

	}

	private void testDB() {
		dbManager = new DBManager(getActivity());
		DailyEntity e = new DailyEntity();
		e.setDate("2015", "08", "12");
		e.setEvaluation("3");
		e.setId("20150810");
		e.setWordsToday("starter");

		String s = dbManager.writeData(e);
		System.out.println(s);
	}

	private void loadRecords() {
		String s = getCurrentRecordsSet();
		// Calendar c = Calendar.getInstance();
		// String monthOfYear = toString(c.get(Calendar.MONTH));
		// String year = toString(c.get(Calendar.YEAR));

		if (dailyList == null)
			dailyList = new ArrayList<DailyEntity>();
		Calendar c = Calendar.getInstance();
		for (int i = 0; i < 31; i++) {
			c.set(2015, 7, i);
			String week = toString(c.get(Calendar.DAY_OF_WEEK) - 1);
			// Log.d("cal", "week: " + week + "," + i + 1);
			DailyEntity e = new DailyEntity();
			e.setDate("2015", "08", (i + 1) + "");
			e.setDayOfWeek(week);
			ThingEntity tE = new ThingEntity();
			tE.setName("sketch" + (i + 1));
			ArrayList<ThingEntity> tl = new ArrayList<ThingEntity>();
			tl.add(tE);
			e.setRecordsList(tl);
			dailyList.add(e);
		}
	}

	/**
	 * @Functiuon 显示菜单
	 * @Author Heguanyuan 2015-8-22 下午12:23:12
	 */
	public void showMenu() {
		View convertView = getActivity().getLayoutInflater().inflate(R.layout.popwindow_layout, null);

		TextView addTv = (TextView) convertView.findViewById(R.id.add);
		addTv.setOnClickListener(menuItemOnClickListener);

		popWindow = new PopupWindow(convertView, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, true);
		// popWindow.setOnDismissListener(onDismissListener)
		popWindow.setTouchable(true);
		Drawable d = getActivity().getResources().getDrawable(R.drawable.ic_launcher);
		// 这个并没有显示backgroundrawble
		popWindow.setBackgroundDrawable(d);
		/** 偏移向左上是 + */
		popWindow.showAtLocation(floatBtn, Gravity.RIGHT | Gravity.BOTTOM, 50, 30);
		// popWindow.showAsDropDown();
	}

	OnClickListener menuItemOnClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.add:
				/** 添加一天 */
				addDaily();
				break;

			default:
				break;
			}
		}
	};

	/**
	 * @Functiuon 添加一条记录
	 * @Author Heguanyuan 2015-8-22 下午3:51:12
	 */
	private String addRecord() {

		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		/** view */
		View v = LayoutInflater.from(getActivity()).inflate(R.layout.add_record_layout, null);
		builder.setView(v);

		GridView gv = (GridView) v.findViewById(R.id.add_record_grid);
		AddRecordGridAdapter a = new AddRecordGridAdapter(getActivity());
		gv.setAdapter(a);
		/** item点击事件 */
		gv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

			}
		});

		builder.setTitle("选择事件");
		AlertDialog dialog = builder.create();

		dialog.show();
		return "";
	}

	/**
	 * @Functiuon 添加一天
	 * @Author Heguanyuan 2015-8-22 下午6:39:49
	 */
	@SuppressWarnings("deprecation")
	private String addDaily() {

		Calendar c = Calendar.getInstance();
		Year = c.get(Calendar.YEAR);
		MonthOfYear = c.get(Calendar.MONTH);
		DayOfMonth = c.get(Calendar.DAY_OF_MONTH);

		DatePickerDialog dialog = new DatePickerDialog(getActivity(), new OnDateSetListener() {

			@Override
			public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
				Year = year;
				MonthOfYear = monthOfYear;
				DayOfMonth = dayOfMonth;
				
				DailyEntity e = new DailyEntity(Year, MonthOfYear, DayOfMonth);
				if (dbManager == null)
					dbManager = new DBManager(getActivity());
				String info = dbManager.writeData(e);
				Log.d("write daily", info);
				
			}
		}, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));

		dialog.setTitle("选择日期");
		dialog.show();
		return "";
	}

}
