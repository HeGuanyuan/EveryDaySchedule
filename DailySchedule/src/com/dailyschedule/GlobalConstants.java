package com.dailyschedule;

public class GlobalConstants {

	public static final String PackageName = "com.dailyschedule";

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
		public static final String tableName = "DailyTable";
		/** 唯一index */
		public static final String identifier = "identifier";
		/** 时间信息 */
		public static final String year = "year";
		public static final String monthOfYear = "monthOfYear";
		public static final String dayOfMonth = "dayOfMonth";
		public static final String dayOfWeek = "dayOfWeek";
		/** 事件id */
		public static final String thingIds = "thingIds";
		/** 每日寄语 */
		public static final String wordsToday = "wordsToday";
		public static final String evaluation = "evaluation";
	}

	// 2
	public interface RecordsTable {
		/** 表名 */
		public static final String tableName = "RecordsTable";
		/** 唯一index */
		public static final String identifier = "identifier";
		/** 当天排序 */
		public static final String index = "inDailyIndex";
		/** 时间信息 */
		public static final String year = "year";
		public static final String monthOfYear = "monthOfYear";
		public static final String dayOfWeek = "dayOfWeek";
		public static final String dayOfMonth = "dayOfMonth";
		/** 当日评价 */
		public static final String remark = "remark";
		public static final String evaluation = "evaluation";
		/** 事件信息 */
		public static final String thingId = "thingId";
		public static final String thingName = "thingName";
	}

	// 3
	public interface ThingEntityTable {
		/** 表名 */
		public static final String tableName = "ThingEntityTable";
		/** 事件信息 */
		public static final String thingId = "thingId";
		public static final String thingName = "thingName";
		public static final String thingColor = "thingColor";
		/** 时间相关 */
		public static final String createTime = "createTime";
		public static final String endTime = "endTime";
	}
}
