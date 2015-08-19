package com.dailyschedule;

public class GlobalConstants {

	public static final String PackageName = "com.dailyschedule";
	public static final String ColorSPName = "color";

	public interface UserConfig {
		public static final String SPName = "user_config";
		public static final String ShowRecordsSet = "ShowRecordsSet";
	}

	/************************************** 数据库相关 *************************************************/
	public interface DBConfig {
		public static final String fileName = "daily.db";
		public static final int VERSON = 1;
		public static final String filePath = "data/data/" + GlobalConstants.PackageName + "/database/" + fileName;
	}

	// 1
	public interface DailyTable {
		/** 表名 */
		public static final String tableName = "daily_table";
		/** 唯一index */
		public static final String identifier = "identifier";
		/** 时间信息 */
		public static final String year = "year";
		public static final String monthOfYear = "month_of_year";
		public static final String dayOfWeek = "day_of_week";
		public static final String dayOfMonth = "day_of_month";
		/** 事件id */
		public static final String thingIds = "thing_ids";
		/** 每日寄语 */
		public static final String wordsToday = "words_today";
		public static final String evaluation = "evaluation";
	}

	// 2
	public interface RecordsTable {
		/** 表名 */
		public static final String tableName = "records_table";
		/** 唯一index */
		public static final String identifier = "identifier";
		/** 当天排序 */
		public static final String index = "in_daily_index";
		/** 时间信息 */
		public static final String year = "year";
		public static final String monthOfYear = "month_of_year";
		public static final String dayOfWeek = "day_of_week";
		public static final String dayOfMonth = "day_of_month";
		/** 当日评价 */
		public static final String remark = "remark";
		public static final String evaluation = "evaluation";
		/** 事件信息 */
		public static final String thingId = "thing_id";
		public static final String thingName = "thing_name";
	}

	// 3
	public interface ThingEntityTable {
		/** 表名 */
		public static final String tableName = "thing_entity_table";
		/** 事件信息 */
		public static final String thingId = "thing_id";
		public static final String thingName = "thing_name";
		public static final String thingColor = "thing_color";
		/** 时间相关 */
		public static final String createTime = "create_time";
		public static final String endTime = "endTime";
	}

	// color
	public interface ColorTable {
		public static final String tableName = "color_table";
		public static final String colorName = "name";
		public static final String colorCode = "code";
		public static final String tag = "tag";
		public static final String isUsed = "is_used";

	}
}
