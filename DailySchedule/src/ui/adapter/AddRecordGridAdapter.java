package ui.adapter;

import java.util.ArrayList;

import utils.ViewHolder;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.dailyschedule.R;
import core.db.DBManager;
import core.entity.ThingEntity;

public class AddRecordGridAdapter extends BaseAdapter {

	private Context context;
	private ArrayList<ThingEntity> thingList;
	private DBManager manager;

	public AddRecordGridAdapter(Context context) {
		this.context = context;
		manager = new DBManager(context);
		thingList = manager.getThingArray();
	}

	@Override
	public int getCount() {
		if (thingList != null) {
			return thingList.size();
		}
		return 0;
	}

	public ThingEntity getThingEntity (int position){
		if(thingList != null && position < thingList.size()){
			return thingList.get(position);
		}
		return null;
	}
	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ThingEntity entity = thingList.get(position);
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(R.layout.record_item, null);
		}

		TextView itemTv = ViewHolder.get(convertView, R.id.thing_item);
		itemTv.setText(entity.getName());
		itemTv.setBackgroundColor(Color.parseColor("#" + entity.getColor()));

		return convertView;
	}

}
