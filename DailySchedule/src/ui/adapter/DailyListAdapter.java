package ui.adapter;

import java.util.ArrayList;

import core.entity.DailyEntity;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class DailyListAdapter extends BaseAdapter {

	private ArrayList<DailyEntity> dailyEntityList;
	private Context context;

	public DailyListAdapter(Context context) {
		this.context = context;
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

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		return null;
	}

}
