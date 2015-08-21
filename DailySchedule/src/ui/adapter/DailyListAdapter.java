package ui.adapter;

import java.util.ArrayList;
import utils.ViewHolder;
import com.dailyschedule.R;
import core.entity.DailyEntity;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class DailyListAdapter extends BaseAdapter implements OnClickListener {

	private RecordItemForExpandedAdapter subAdapter;
	private ArrayList<DailyEntity> dailyEntityList;
	private Context context;
	private static String TAG = "DailyListAdapter";
	private onSubItemClickListener itemListener;

	public DailyListAdapter(Context context) {
		this.context = context;
		subAdapter = new RecordItemForExpandedAdapter(context);
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
//			if (convertView == null) {
				convertView = LayoutInflater.from(context).inflate(R.layout.day_list_item_large, null);
//			}
			/** list */
			ListView lv = (ListView) convertView.findViewById(R.id.thing_list_view);
			if (lv.getChildCount() == 0) {
				subAdapter = new RecordItemForExpandedAdapter(context);
				subAdapter.setList(entity.getThingList());
				lv.setAdapter(subAdapter);
			}
			/** wraper*/
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
			String dateString = entity.getYear() + "年 " + entity.getMonthOfYear() + "月 " 
					+ week[Integer.valueOf(entity.getDayOfWeek())];
			dateTv.setText(dateString);
			
			
//			subAdapter.clear();
//			subAdapter.setList(entity.getThingList());
//			LinearLayout subListLayout = ViewHolder.get(convertView, R.id.thing_list_layout);
//			subListLayout.removeAllViews();
//			for (int i = 0; i < subAdapter.getCount(); i++) {
//				/** sublist item */
//				View view = subAdapter.getView(i, convertView, parent);
//				subListLayout.addView(view);
//				view.setOnClickListener(this);
//				String Tag = entity.getTimeStamp() + "," + position;
//				view.setTag(Tag);
//			}

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
				smallAdapter.setList(entity.getThingList());
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
				TextView arrow = ViewHolder.get(convertView, R.id.arrow_down);
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
			itemListener.onSubItemClick(tag[0], Integer.valueOf(tag[1]));
		}
	}

	public interface onSubItemClickListener {

		/**
		 * @Functiuon 每天事件展开列表的点击事件
		 * @Author Heguanyuan 2015-8-4 下午3:44:22
		 */
		public void onSubItemClick(String timeStamp, int index);
	}
}
