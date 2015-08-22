package ui.adapter;

import java.util.ArrayList;

import utils.ViewHolder;

import com.dailyschedule.R;

import core.db.DBManager;
import core.entity.ThingEntity;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ThingListAdapter extends BaseAdapter {

	private Context context;
	private ArrayList<ThingEntity> thingList;

	private DBManager m;

	public ThingListAdapter(Context context) {
		this.context = context;
		if (context != null) {
			m = new DBManager(context);
			thingList = m.getThingArray();
		}

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		if (thingList != null) {
			return thingList.size();
		}
		return 0;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
	
	public String refreshData(){
		thingList = m.getThingArray();
		notifyDataSetChanged();
		return "";
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
//		Log.d("getView", position + "");
		// TODO Auto-generated method stub
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(R.layout.thing_list_item, null);
		}
		TextView nameTv = ViewHolder.get(convertView, R.id.name_txt);
		nameTv.setText(thingList.get(position).getName());
		return convertView;
	}

}
