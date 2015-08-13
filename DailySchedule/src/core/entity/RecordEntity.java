package core.entity;

import utils.ToContentValues;
import android.content.ContentValues;

import com.dailyschedule.GlobalConstants.RecordsTable;

public class RecordEntity implements ToContentValues {

	private String index;
	private String id;

	/** 事件信息 */
	public String thingId;
	public String thingName;

	/** 时间信息 */
	public String year;
	public String monthOfYear;
	public String dayOfWeek;
	public String dayOfMonth;

	/** 评价 */
	public String remark;
	public String evaluation;

	public void setDate(String y, String m, String d) {
		this.setYear(y);
		this.setMonthOfYear(m);
		this.setDayOfMonth(d);
	}

	@Override
	public ContentValues toContentValues() {
		ContentValues cv = new ContentValues();

		cv.put(RecordsTable.identifier, id);
		cv.put(RecordsTable.index, index);
		
		cv.put(RecordsTable.year, year);
		cv.put(RecordsTable.monthOfYear, monthOfYear);
		cv.put(RecordsTable.dayOfMonth, dayOfMonth);
		cv.put(RecordsTable.dayOfWeek, dayOfWeek);
		
		cv.put(RecordsTable.thingId, thingId);
		cv.put(RecordsTable.thingName, thingName);
		
		cv.put(RecordsTable.remark, remark);
		cv.put(RecordsTable.evaluation, evaluation);
		
		return cv;
	}

	public String getThingId() {
		return thingId;
	}

	public void setThingId(String thingId) {
		this.thingId = thingId;
	}

	public String getThingName() {
		return thingName;
	}

	public void setThingName(String thingName) {
		this.thingName = thingName;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public String getId() {
		return id;
	}

	public void setId(String identifier) {
		this.id = identifier;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonthOfYear() {
		return monthOfYear;
	}

	public void setMonthOfYear(String monthOfYear) {
		this.monthOfYear = monthOfYear;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getEvaluation() {
		return evaluation;
	}

	public void setEvaluation(String evaluation) {
		this.evaluation = evaluation;
	}

}
