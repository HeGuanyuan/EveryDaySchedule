package ui.adapter;

import utils.ViewHolder;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dailyschedule.R;

public class ColorGridAdapter extends BaseAdapter {

	private Context context;
	private String[] colorArray;

	public ColorGridAdapter(Context context) {
		this.context = context;
		if (context != null) {
			colorArray = context.getResources().getStringArray(R.array.color);
		} else {
			colorArray = new String[] {};
		}
	}

	@Override
	public int getCount() {
		if (colorArray != null)
			return colorArray.length;
		else
			return 0;
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
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(R.layout.color_grid_item, null);
		}
		TextView colorTv = ViewHolder.get(convertView, R.id.grid_item);
		/** 设置颜色 */
		if (position < colorArray.length)
			colorTv.setBackgroundColor(Color.parseColor(colorArray[position]));
		return convertView;
	}

}
