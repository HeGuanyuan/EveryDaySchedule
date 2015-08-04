package core.entity;

import java.util.ArrayList;

public class DailyEntity {

	private ArrayList<ThingEntity> thingList;
	private String timeStamp;
	private String evaluation;
	private String id;
	private boolean isExpanded;

	public DailyEntity() {
		thingList = new ArrayList<ThingEntity>();
		timeStamp = "";
		evaluation = "";
		id = "";
		isExpanded = false;
	}

	public ArrayList<ThingEntity> getThingList() {
		if (thingList == null)
			thingList = new ArrayList<ThingEntity>();
		return thingList;
	}

	public void setThingList(ArrayList<ThingEntity> thingList) {
		this.thingList = thingList;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getEvalution() {
		return evaluation;
	}

	public void setEvalution(String evalute) {
		this.evaluation = evalute;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isExpanded() {
		return isExpanded;
	}

	public void setExpanded(boolean isExpanded) {
		this.isExpanded = isExpanded;
	}

}
