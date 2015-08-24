package ui.adapter;

import java.util.ArrayList;
import utils.ViewHolder;
import com.dailyschedule.R;
import core.entity.DailyEntity;
import core.entity.RecordEntity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class DailyListAdapter extends BaseAdapter implements OnClickListener {

	private RecordItemForExpandedAdapter subAdapter;
	private ArrayList<DailyEntity> dailyEntityList;
	private Context context;
	private static String TAG = "DailyListAdapter";
	private onDailyItemClickListener itemListener;

	public DailyListAdapter(Context context) {
		this.context = context;
		subAdapter = new RecordItemForExpandedAdapter(context);
	}

	public void setOnDailyItemClickListener(onDailyItemClickListener l) {
		this.itemListener = l;
	}

	@Override
	public int getCount() {
		return dailyEntityList == null ? 0 : dailyEntityList.size();

	}

	@Override
	public Object getItem(int position) {
		if (dailyEntityList != null) {
			if (position < dailyEntityList.size() && position >= 0) {
				return dailyEntityList.get(position);
			} else
				return null;
		} else
			return null;
	}

	@Override
	public long getItemId(int position) {
		return position;

	}

	public void setList(ArrayList<DailyEntity> list) {
		this.dailyEntityList = list;
		// subAdapter.setList(list)
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		DailyEntity entity = dailyEntityList.get(position);

		if (entity.isExpanded()) {
			Log.d("getView", " 展 开 ");
			/** 展开的list item */
			convertView = LayoutInflater.from(context).inflate(R.layout.day_list_item_large, null);

			/** list */
			ListView lv = (ListView) convertView.findViewById(R.id.thing_list_view);
			/** 此处listview 要设置高度，否则listview只显示第一行 嵌套list的缘故 */
			/** http://blog.csdn.net/hcf_force/article/details/25316837 */
			if (lv.getChildCount() == 0) {
				ArrayList<RecordEntity> l = entity.getRecordsList();
				subAdapter = new RecordItemForExpandedAdapter(context);
				subAdapter.setList(l);
				int height = (int) context.getApplicationContext().getResources().getDimension(R.dimen.line_hight_m);
				lv.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, height * l.size()));
				// Log.d("rec nu", "size : " + l.size());
				lv.setAdapter(subAdapter);
			}

			/** wraper */
			LinearLayout wraper = (LinearLayout) convertView.findViewById(R.id.wraper);
			wraper.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					String id = (String) v.getTag();
					for (DailyEntity e : dailyEntityList) {
						if (e.getId().equals(id))
							e.setExpanded(false);
					}
					notifyDataSetChanged();
				}
			});
			wraper.setTag(entity.getId());

			/** 时间信息 */
			TextView dayOfMonthTv = (TextView) convertView.findViewById(R.id.day_of_mongth_tv_l);
			dayOfMonthTv.setText(entity.getDayOfMonth());
			TextView dateTv = (TextView) convertView.findViewById(R.id.date_tv);
			String[] week = context.getResources().getStringArray(R.array.week);
			String dateString = entity.getYear() + "年 " + entity.getMonthOfYear() + "月 " + week[Integer.valueOf(entity.getDayOfWeek()) - 1];
			dateTv.setText(dateString);

			/** 添加record button */
			ImageView addBtn = (ImageView) convertView.findViewById(R.id.add_record_btn);
			addBtn.setTag(position);
			addBtn.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					Log.d("addBtn", "clicked");
					int pos = (Integer) v.getTag();
					itemListener.onAddRecordBtnClick(pos);
				}
			});

			// subAdapter.clear();
			// subAdapter.setList(entity.getThingList());
			// LinearLayout subListLayout = ViewHolder.get(convertView,
			// R.id.thing_list_layout);
			// subListLayout.removeAllViews();
			// for (int i = 0; i < subAdapter.getCount(); i++) {
			// /** sublist item */
			// View view = subAdapter.getView(i, convertView, parent);
			// subListLayout.addView(view);
			// view.setOnClickListener(this);
			// String Tag = entity.getTimeStamp() + "," + position;
			// view.setTag(Tag);
			// }

		} else {
			/** 未展开的 */
			// if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(R.layout.day_list_item_small, null);
			// }

			/** wraper */
			RelativeLayout wraper = (RelativeLayout) convertView.findViewById(R.id.wraper);
			wraper.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// DailyEntity e = (DailyEntity) v.getTag();
					String id = (String) v.getTag();
					for (DailyEntity e : dailyEntityList) {
						if (e.getId().equals(id))
							e.setExpanded(true);
					}
					notifyDataSetChanged();
				}
			});
			wraper.setTag(entity.getId());

			/** index */
			TextView dayOfMonthTv = ViewHolder.get(convertView, R.id.day_of_month_tv_s);
			dayOfMonthTv.setText(entity.getDayOfMonth());

			/** name */
			LinearLayout contentLayout = ViewHolder.get(convertView, R.id.things_layout);

			if (contentLayout.getChildCount() == 0) {
				RecordItemAdapter smallAdapter = new RecordItemAdapter(context);
				smallAdapter.setList(entity.getRecordsList());
				for (int i = 0; i < smallAdapter.getCount(); i++) {
					/** 获取sublist的item */
					View view = smallAdapter.getView(i, null, parent);
					contentLayout.addView(view);
					// view.setOnClickListener()
					// view.setTag(tag)
				}
			}

			/** arrow */
			if (contentLayout.getChildCount() == 0) {
				ImageView arrow = ViewHolder.get(convertView, R.id.add_record_btn);
				arrow.setVisibility(View.INVISIBLE);
			}
		}

		return convertView;
	}

	@Override
	public void onClick(View v) {
		String Tag = (String) v.getTag();
		String[] tag = Tag.split(",");
		if (tag.length > 0) {
			itemListener.onRecordtemClick(tag[0], Integer.valueOf(tag[1]));
		}
	}

	public interface onDailyItemClickListener {

		/**
		 * @Functiuon 每天事件展开列表的点击事件
		 * @Author Heguanyuan 2015-8-4 下午3:44:22
		 */
		public void onRecordtemClick(String timeStamp, int index);

		/**
		 * @Functiuon 添加记录
		 * @Author Heguanyuan 2015-8-23 下午3:42:43
		 */
		public void onAddRecordBtnClick(int pos);
	}
}
