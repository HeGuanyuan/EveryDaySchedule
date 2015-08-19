package ui.adapter;

import java.util.ArrayList;

import utils.ViewHolder;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dailyschedule.R;

import core.db.DBManager;
import core.entity.ColorEntity;

public class ColorGridAdapter extends BaseAdapter implements OnClickListener {

	private Context context;
	private ArrayList<ColorEntity> colorArray;
	private colorGridItemOnClickListener listener;
	private int lastPos;

	public ColorGridAdapter(Context context) {
		this.context = context;
		lastPos = -1;
		DBManager dbManager = new DBManager(context);
		if (context != null) {
			colorArray = dbManager.getColorArray();
		} else {
			colorArray = new ArrayList<ColorEntity>();
		}
	}

	@Override
	public int getCount() {
		if (colorArray != null)
			return colorArray.size();
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
		ColorEntity colorEntity = colorArray.get(position);
		TextView colorTv;

		if (colorEntity.isChecked()) {
			/** 被选中的view */
			convertView = LayoutInflater.from(context).inflate(R.layout.color_grid_item, null);
			colorTv = (TextView) convertView.findViewById(R.id.grid_item_checked);
			TextView another = (TextView) convertView.findViewById(R.id.grid_item);
			another.setVisibility(View.GONE);
		} else {
			convertView = LayoutInflater.from(context).inflate(R.layout.color_grid_item, null);
			colorTv = (TextView) convertView.findViewById(R.id.grid_item);
			TextView another = (TextView) convertView.findViewById(R.id.grid_item_checked);
			another.setVisibility(View.GONE);
		}

		colorTv.setTag(position);
		colorTv.setOnClickListener(this);
		colorTv.setVisibility(View.VISIBLE);

		/** 设置颜色 */
		if (position < colorArray.size())
			colorTv.setBackgroundColor(Color.parseColor("#" + colorArray.get(position).getCode()));
		return convertView;
	}

	@Override
	public void onClick(View v) {
		int pos = (Integer) v.getTag();
		if (lastPos == -1) {
			lastPos = pos;
		} else {
			colorArray.get(lastPos).setChecked(!colorArray.get(lastPos).isChecked());
		}
		colorArray.get(pos).setChecked(!colorArray.get(pos).isChecked());
		listener.itemOnClick(pos);
		lastPos = pos;
		notifyDataSetChanged();
	}

	public interface colorGridItemOnClickListener {
		void itemOnClick(int pos);
	}

	public void setGridItemOnClickListener(colorGridItemOnClickListener l) {
		this.listener = l;
	}

	public ColorEntity getColorEntity(int p) {
		if (colorArray != null && p < colorArray.size()) {
			return colorArray.get(p);
		} else {
			return null;
		}
	}

	/**
	 *@Functiuon 获取被选中的ColorEntity
	 *@Author Heguanyuan 2015-8-19 下午7:29:59
	 */
	public ColorEntity getColorEntityChecked() {
		if (colorArray != null) {
			for (int i = 0; i < colorArray.size(); i++) {
				if (colorArray.get(i).isChecked()) {
					return colorArray.get(i);
				}
			}
		}
		return null;
	}
}
