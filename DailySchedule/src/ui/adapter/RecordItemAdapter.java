package ui.adapter;

import java.util.ArrayList;

import utils.ViewHolder;

import com.dailyschedule.R;

import core.entity.ThingEntity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class RecordItemAdapter extends BaseAdapter {
	private Context context;
	private ArrayList<ThingEntity> thingList;

	public RecordItemAdapter(Context context) {
		this.context = context;
		thingList = new ArrayList<ThingEntity>();
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

	public void setList(ArrayList<ThingEntity> list) {
		this.thingList = list;
	}

	public void clear() {
		if (thingList != null)
			this.thingList.clear();
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ThingEntity entity = thingList.get(position);
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(R.layout.thing_item_small, null);
		}

		/** name */
		TextView nameTv = ViewHolder.get(convertView, R.id.thing_item);
		nameTv.setText(entity.getName());
		// nameTv.setBackground(background)
		return convertView;
	}

}
