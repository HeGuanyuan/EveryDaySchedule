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
import android.widget.TextView;

public class DailyListAdapter extends BaseAdapter implements OnClickListener {

	private ThingItemForExpandedAdapter subAdapter;
	private ThingItemAdapter smallAdapter;
	private ArrayList<DailyEntity> dailyEntityList;
	private Context context;
	private static String TAG = "DailyListAdapter";
	private onSubItemClickListener itemListener;

	public DailyListAdapter(Context context) {
		this.context = context;
		subAdapter = new ThingItemForExpandedAdapter(context);
		smallAdapter = new ThingItemAdapter(context);
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
		Log.d("getView", "in dailylistadapter");
		
		if (entity.isExpanded()) {
			/** 展开的list item */
			if (convertView == null) {
				convertView = LayoutInflater.from(context).inflate(R.layout.day_list_item_large, null);
			}
			subAdapter.clear();
			subAdapter.setList(entity.getThingList());
			LinearLayout subListLayout = ViewHolder.get(convertView, R.id.thing_list_layout);
			subListLayout.removeAllViews();
			for (int i = 0; i < subAdapter.getCount(); i++) {
				/** sublist item */
				View view = subAdapter.getView(i, convertView, parent);
				subListLayout.addView(view);
				view.setOnClickListener(this);
				String Tag = entity.getTimeStamp() + "," + position;
				view.setTag(Tag);
			}

		} else {
			/** 未展开的*/
			if (convertView == null) {
				convertView = LayoutInflater.from(context).inflate(R.layout.day_list_item_small, null);
			}
			/** name */
			LinearLayout contentLayout = ViewHolder.get(convertView, R.id.things_layout);
			smallAdapter.clear();
			smallAdapter.setList(entity.getThingList());
			for (int i = 0; i < smallAdapter.getCount(); i++) {
				/** 获取sublist的item*/
				View view = smallAdapter.getView(i, null, parent);
				contentLayout.addView(view);
//				view.setOnClickListener()
//				view.setTag(tag)
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
		 *@Functiuon 每天事件展开列表的点击事件
		 *@Author Heguanyuan 2015-8-4 下午3:44:22
		 */
		public void onSubItemClick(String timeStamp, int index);
	}
}
