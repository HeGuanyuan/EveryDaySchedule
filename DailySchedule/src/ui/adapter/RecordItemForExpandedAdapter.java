package ui.adapter;

import java.util.ArrayList;

import ui.view.ThingsFragment;
import utils.ViewHolder;
import com.dailyschedule.R;

import core.entity.RecordEntity;
import core.entity.ThingEntity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class RecordItemForExpandedAdapter extends BaseAdapter {

	private ArrayList<RecordEntity> recordList;
	private Context context;

	public RecordItemForExpandedAdapter(Context context) {
		this.context = context;
	}

	@Override
	public int getCount() {
		if (recordList != null) {
			return recordList.size();
		} else
			return 0;
	}

	@Override
	public Object getItem(int position) {
		if (recordList != null) {
			if (position >= 0 && position < recordList.size()) {
				return recordList.get(position);
			} else
				return null;
		} else
			return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	public void setList(ArrayList<RecordEntity> list) {
		this.recordList = list;
	}

	public void clear() {
		if (recordList != null)
			this.recordList.clear();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Log.d("RecordItemForExpandedAdapter", "getView: " + position);
		RecordEntity entity = recordList.get(position);

		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(R.layout.day_sub_list_item, null);
		}
		/** 事件name */
		TextView nameTV = ViewHolder.get(convertView, R.id.thing_contnet);
		nameTV.setText(entity.getThingName());

		/** 评价text */
		TextView evaluationTv = ViewHolder.get(convertView, R.id.thing_judge);
		evaluationTv.setText(entity.getRemark());
		
		/** 评价 layout */
		LinearLayout evaluationLayout = ViewHolder.get(convertView, R.id.ranks);
		evaluationLayout.setVisibility(View.INVISIBLE);

		return convertView;
	}
}
