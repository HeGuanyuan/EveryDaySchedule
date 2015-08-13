package core.entity;

import java.util.ArrayList;
import java.util.List;

import com.dailyschedule.GlobalConstants.ThingEntityTable;

import android.content.ContentValues;

import utils.ToContentValues;

/**
 * @Class ThingEntity 事件实体类
 * @Author He Guanyuan 2015-8-1 下午7:59:17
 */
public class ThingEntity implements ToContentValues {
	private String id;
	private String name;
	private String color;

	private String creatTime;
	private String endTime;

	private List<String> remarks;
	private List<String> evaluations;

	public ThingEntity() {
	}

	@Override
	public ContentValues toContentValues() {
		ContentValues cv = new ContentValues();
		
		cv.put(ThingEntityTable.thingId, id);
		cv.put(ThingEntityTable.thingName, name);
		cv.put(ThingEntityTable.thingColor, color);
		
		cv.put(ThingEntityTable.createTime, creatTime);
		cv.put(ThingEntityTable.endTime, endTime);
		
		return cv;
	}

	public String getCreatTime() {
		return creatTime;
	}

	public void setCreatTime(String creatTime) {
		this.creatTime = creatTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<String> getRemarks() {
		if (this.remarks == null)
			this.remarks = new ArrayList<String>();
		return remarks;
	}

	public void setRemarks(List<String> remarks) {
		this.remarks = remarks;
	}

	public List<String> getEvaluations() {
		if (this.evaluations == null)
			this.evaluations = new ArrayList<String>();
		return evaluations;
	}

	public void setEvaluations(List<String> evaluations) {
		this.evaluations = evaluations;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

}
