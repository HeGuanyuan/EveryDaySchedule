package core.entity;

import com.dailyschedule.GlobalConstants.ColorTable;

import android.content.ContentValues;
import utils.ToContentValues;

public class ColorEntity implements ToContentValues {

	private String name;
	private String colorId;
	private String code;
	private String tag;
	private boolean isUsed;
	private boolean isChecked;
	public ColorEntity() {
	}

	public ColorEntity(String code) {
		this.code = code;
	}

	public ColorEntity(String code, String isUsedString) {
		this.code = code;
		if (isUsedString.equals("0")) {
			this.setUsed(false);
		} else {
			this.setUsed(true);
		}
	}

	@Override
	public ContentValues toContentValues() {

		ContentValues cv = new ContentValues();
		cv.put(ColorTable.colorName, "");
		cv.put(ColorTable.colorCode, code);
		cv.put(ColorTable.isUsed, isUsedString());
		cv.put(ColorTable.tag, tag);
		return cv;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getColorId() {
		return colorId;
	}

	public void setColorId(String colorId) {
		this.colorId = colorId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public boolean isUsed() {
		return isUsed;
	}

	public void setUsed(boolean isUsed) {
		this.isUsed = isUsed;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String isUsedString() {
		if (isUsed) {
			return "1";
		} else
			return "0";
	}

	public void setUsedByString(String used) {
		if (used.equals("0")) {
			this.setUsed(true);
		} else {
			this.setUsed(false);
		}
	}

	public boolean isChecked() {
		return isChecked;
	}

	public void setChecked(boolean isChecked) {
		this.isChecked = isChecked;
	}
}
