package ui.view;

import java.util.ArrayList;
import java.util.Calendar;

import ui.adapter.AddRecordGridAdapter;
import ui.adapter.DailyListAdapter;
import ui.adapter.DailyListAdapter.onDailyItemClickListener;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
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
import core.entity.RecordEntity;
import core.entity.ThingEntity;

/**
 * @Class MyDailyFragment 以列表陈列每天的事件
 * @Author He Guanyuan 2015-8-4 上午9:09:01
 */
public class MyDailyFragment extends BaseFragment implements onDailyItemClickListener {

	private Button floatBtn;
	private DailyListAdapter adapter;
	private ListView dailyListView;
	private ArrayList<DailyEntity> dailyList;
	private PopupWindow popWindow;
	private DBManager dbManager;

	private AddRecordGridAdapter addRecordAdapter;
	// 记录选择的时间
	private int Year;
	private int MonthOfYear;
	private int DayOfMonth;

	/** 用于添加记录 */
	private int dailyPosition = 0;
	private AlertDialog addRecordDialog;

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

		loadData();
		DBManager m = new DBManager(getActivity());

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

	private void loadData() {
		/** 获取显示记录的设置 */
		String s = getCurrentRecordsSet();

		if (dbManager == null) {
			dbManager = new DBManager(getActivity());
		}

		dailyList = dbManager.getDailyArray();
		adapter = new DailyListAdapter(getActivity());
		adapter.setOnDailyItemClickListener(this);
		adapter.setList(dailyList);
		dailyListView.setAdapter(adapter);

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
		Drawable d = getActivity().getResources().getDrawable(R.drawable.ic_launcher_a);
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
	private String addRecord(int pos) {

		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		/** view */
		View v = LayoutInflater.from(getActivity()).inflate(R.layout.add_record_layout, null);
		builder.setView(v);

		GridView gv = (GridView) v.findViewById(R.id.add_record_grid);
		addRecordAdapter = new AddRecordGridAdapter(getActivity());
		gv.setAdapter(addRecordAdapter);
		/** item点击事件 */
		gv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

				ThingEntity thingEntity = addRecordAdapter.getThingEntity(position);

				RecordEntity recordEntity = new RecordEntity();
				DailyEntity dailyEntity = dailyList.get(dailyPosition);

				recordEntity.setThingName(thingEntity.getName());
				recordEntity.setThingId(thingEntity.getId());

				recordEntity.setDayOfWeek(dailyEntity.getDayOfWeek());

				recordEntity.setDate(dailyEntity.getIdentifer());
				recordEntity.setIndex(dailyEntity.getRecordsList().size() + 1);
				recordEntity.setIdentifer();

				Log.d("addRecord", recordEntity.getIdentifer().toString());

//				dailyList.get(dailyPosition).getRecordsList().add(recordEntity);
//				adapter.notifyDataSetChanged();

				if (addRecordDialog != null) {
					addRecordDialog.dismiss();
				}

				String info = dbManager.writeData(recordEntity);
				Log.d("addrecord", info);
				
				/** 刷新UI */
				dailyList = dbManager.getDailyArray();
				adapter.setList(dailyList);
				adapter.notifyDataSetChanged();
				int count = dailyList.get(0).getRecordsList().size();
				Log.d("0.count", "count: " + count);

				showToast(getActivity(), "已添加");
			}
		});

		builder.setTitle("选择事件");
		addRecordDialog = builder.create();

		addRecordDialog.show();
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

	/** 添加记录 */
	@Override
	public void onAddRecordBtnClick(int pos) {
		Log.d("onAddRecordBtnClick", "添加record");
		dailyPosition = pos;
		addRecord(pos);
	}

	/** record 点击事件 */
	@Override
	public void onRecordtemClick(String timeStamp, int index) {

	}

}
