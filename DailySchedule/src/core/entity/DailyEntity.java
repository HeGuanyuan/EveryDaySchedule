package core.entity;

import java.util.ArrayList;

public class DailyEntity {

	private String id = "";
	private ArrayList<ThingEntity> thingList;
	private boolean isExpanded = false;

	public String wordsToday = "";
	private String evaluation = "";

	/** 时间信息 */
	public String year = "";
	public String month = "";
	public String dayOfWeek = "";
	public String dayOfMonth = "";
	private String timeStamp = "";

	public DailyEntity() {

	}

	public void setDate(String y, String m, String d) {
		this.setYear(y);
		this.setMonthOfYear(m);
		this.setDayOfMonth(d);
	}

	public String getWordsToday() {
		return wordsToday;
	}

	public void setWordsToday(String wordsToday) {
		this.wordsToday = wordsToday;
	}

	public String getEvaluation() {
		return evaluation;
	}

	public void setEvaluation(String evaluation) {
		this.evaluation = evaluation;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonthOfYear() {
		return month;
	}

	public void setMonthOfYear(String month) {
		this.month = month;
	}

	public String getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(String dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	public String getDayOfMonth() {
		return dayOfMonth;
	}

	public void setDayOfMonth(String dayOfMonth) {
		this.dayOfMonth = dayOfMonth;
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
