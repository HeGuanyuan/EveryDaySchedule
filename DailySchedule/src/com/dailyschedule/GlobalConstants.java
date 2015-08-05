package com.dailyschedule;

public class GlobalConstants {

	public static final String PackageName = "com.dailyschedule";

	public interface Config {

	}

	
	/************************************** 数据库相关 *************************************************/
	public interface DBConfig {
		public static final String fileName = "daily.db";
		public static final int VERSON = 1;
		public static final String filePath = "data/data/" + GlobalConstants.PackageName + "/database/" + fileName;
	}

	//1
	public interface DailyTable {
		/** 表名 */
		public static final String tableName = "DailyListTable";
		/** 唯一index */
		public static final String index = "index";
		/** 时间信息 */
		public static final String year = "year";
		public static final String month = "monthOfYear";
		public static final String dayOfWeek = "dayOfWeek";
		public static final String dayOfMonth = "dayOfMonth";
		/** 事件id */
		public static final String thingIds = "thingIds";
		/** 每日寄语 */
		public static final String wordsToday = "wordsToday";
	}

	//2
	public interface RecordsTable {
		/** 表名 */
		public static final String tableName = "tableName";
		/** 唯一index */
		public static final String index = "index";
		/** 时间信息 */
		public static final String year = "year";
		public static final String month = "month";
		public static final String dayOfWeek = "dayOfWeek";
		public static final String dayOfMonth = "dayOfMonth";
		/** 当日评价 */
		public static final String remark = "remark";

	}

	//3
	public interface ThingEntityTable {
		/** 表名 */
		public static final String tableName = "ThingEntityTable";
		/** 事件编号 */
		public static final String index = "index";
		/** 事件title */
		public static final String name = "name";

	}
}
