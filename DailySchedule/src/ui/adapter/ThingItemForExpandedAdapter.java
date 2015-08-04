package ui.adapter;

import java.util.ArrayList;

import ui.view.ThingsFragment;
import utils.ViewHolder;
import com.dailyschedule.R;
import core.entity.ThingEntity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ThingItemForExpandedAdapter extends BaseAdapter {

	private ArrayList<ThingEntity> thingList;
	private Context context;

	public ThingItemForExpandedAdapter(Context context) {
		this.context = context;
	}

	@Override
	public int getCount() {
		if (thingList != null) {
			return thingList.size();
		} else
			return 0;
	}

	@Override
	public Object getItem(int position) {
		if (thingList != null) {
			if (position >= 0 && position < thingList.size()) {
				return thingList.get(position);
			} else
				return null;
		} else
			return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	public void setList(ArrayList<ThingEntity> list) {
		this.thingList = list;
	}

	public void clear() {
		if (thingList != null)
			this.thingList.clear();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		ThingEntity entity = thingList.get(position);

		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(R.layout.day_sub_list_item, null);
		}
		/** 事件name */
		TextView nameTV = ViewHolder.get(convertView, R.id.thing_contnet);
		nameTV.setText(entity.getName());

		/** 评价text */
		TextView evaluationTv = ViewHolder.get(convertView, R.id.thing_judge);
		//
		evaluationTv.setText(entity.getRemarks().get(0));
		/** 评价 layout */
		LinearLayout evaluationLayout = ViewHolder.get(convertView, R.id.ranks);
		evaluationLayout.setVisibility(View.INVISIBLE);

		return convertView;
	}
}
