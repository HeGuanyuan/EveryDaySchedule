package core.entity;

import java.util.ArrayList;

public class DailyEntity {

	private ArrayList<ThingEntity> thingList;
	private String timeStamp;
	private String evaluation;
	private String id;
	
	public ArrayList<ThingEntity> getThingList() {
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

}
