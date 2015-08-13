package core.entity;

import java.util.ArrayList;

import com.dailyschedule.GlobalConstants.DailyTable;

import android.content.ContentValues;

import utils.ToContentValues;

public class DailyEntity implements ToContentValues {

	private String id = "";
	private ArrayList<ThingEntity> thingList;
	private boolean isExpanded = false;

	private String wordsToday = "";
	private String evaluation = "";

	/** 时间信息 */
	private String year = "";
	private String month = "";
	private String dayOfWeek = "";
	private String dayOfMonth = "";
	private String timeStamp = "";

	@Override
	public ContentValues toContentValues() {
		// TODO Auto-generated method stub
		ContentValues cv = new ContentValues();

		cv.put("identifier", this.id);

		cv.put(DailyTable.year, year);
		cv.put(DailyTable.monthOfYear, month);
		cv.put(DailyTable.dayOfMonth, dayOfMonth);
		cv.put(DailyTable.dayOfWeek, dayOfWeek);

		cv.put(DailyTable.thingIds, year);
		cv.put(DailyTable.year, year);
		
		cv.put(DailyTable.evaluation, evaluation);
		cv.put(DailyTable.wordsToday, wordsToday);
		
		return cv;
	}

	public String getThingIdsString() {
		if (thingList != null) {
			String s = "";
			for (ThingEntity e : thingList) {
				s += e.getId();
				s += ",";
			}
			s = s.substring(0, s.length() - 1);
			return s;

		} else {
			return "";
		}
	}

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

	public String getId() {
		String idString = year + month + dayOfMonth;
		return idString;
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
