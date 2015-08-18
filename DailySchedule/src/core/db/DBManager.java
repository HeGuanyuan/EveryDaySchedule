package core.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import com.dailyschedule.GlobalConstants;
import com.dailyschedule.GlobalConstants.DBConfig;
import com.dailyschedule.GlobalConstants.DailyTable;
import com.dailyschedule.GlobalConstants.RecordsTable;
import com.dailyschedule.GlobalConstants.ThingEntityTable;
import core.entity.DailyEntity;
import core.entity.RecordEntity;
import core.entity.ThingEntity;

public class DBManager extends SQLiteOpenHelper {

	private static final int VERSON = DBConfig.VERSON;
	private final static String BD_NAME = GlobalConstants.DBConfig.fileName;
	// private final static String dailyTableName = DailyTable.tableName;
	// private final static String recordsTableName = RecordsTable.tableName;
	// private final static String entityTableName = ThingEntityTable.tableName;
	private Context context;
	private SQLiteDatabase db;

	public DBManager(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
		this.context = context;
	}

	public DBManager(Context context) {
		super(context, BD_NAME, null, VERSON);
		this.context = context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// db = SQLiteDatabase.openOrCreateDatabase(DBConfig.filePath, null);
		this.db = db;
		System.out.println("db oncreat");

		// table 1
		String b1 = "CREATE TABLE daily_table(id INTEGER PRIMARY KEY autoincrement,";
		String b2 = "identifier TEXT,";
		String b3 = "year TEXT, month_of_year TEXT, day_of_month TEXT, day_of_week TEXT,";
		String b4 = "thing_ids TEXT, words_today TEXT, evaluation TEXT";
		String creatDailyTable = b1 + b2 + b3 + b4 + ")";
		db.execSQL(creatDailyTable);

		// table 2
		String s1 = "CREATE TABLE records_table(id INTEGER PRIMARY KEY autoincrement,";
		String s2 = "identifier TEXT, in_daily_index TEXT,";
		String s3 = "thing_id TEXT, thing_name TEXT,";
		String s4 = "year TEXT, month_of_year TEXT, day_of_month TEXT, day_of_week TEXT,";
		String s5 = "remark TEXT, evaluation TEXT";
		String creatRecordTable = s1 + s2 + s3 + s4 + s5 + ")";
		db.execSQL(creatRecordTable);

		// table 3
		String a1 = "CREATE TABLE thing_entity_table(id INTEGER PRIMARY KEY autoincrement,";
		String a2 = "thing_id TEXT,thing_name TEXT,";
		String a3 = "createTime TEXT, endTime TEXT";
		String creatEntityTable = a1 + a2 + a3 + ")";
		db.execSQL(creatEntityTable);
		
		// color
		String c1 = "CREATE TABLE color_table(id INTEGER PRIMARY KEY autoincrement,";
		String c2 = "color_id TEXT,color_name TEXT,";
		String c3 = "color_code TEXT,label TEXT";
		String creatColorTable = c1 + c2 + c3 + ")";
		db.execSQL(creatColorTable);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

	public SQLiteDatabase getDataBase() {
		return db;
	}

	/************************************************** 写入实体类 ********************************************************/
	public String writeData(DailyEntity e) {
		SQLiteDatabase db = this.getWritableDatabase();
		if (e != null) {
			String id = e.getId();
			String s = "SELECT * FROM " + DailyTable.tableName + " WHERE " + DailyTable.identifier + " = " + id;
			// String[] params = { DailyTable.tableName, DailyTable.identifier,
			// id };
			Cursor c = db.rawQuery(s, null);
			if (c.getCount() == 1) {
				ContentValues values = e.toContentValues();
				// values.put(key, value)
				/** update此处仅 " ? = ? " */
				String updateString = DailyTable.identifier + " = " + id;
				db.update(DailyTable.tableName, values, updateString, null);
				db.close();
				return "writeData--update";
			} else if (c.getCount() == 0) {
				ContentValues cv = e.toContentValues();
				db.insert(DailyTable.tableName, null, cv);
				db.close();
				return "writeData--insert";
			} else {
				db.close();
				return "writeData--c.getCount: " + c.getCount();
			}
		} else {
			return "writeData--entity is null";
		}
	}

	public String writeData(ThingEntity e) {
		SQLiteDatabase db = this.getWritableDatabase();
		if (e != null) {
			String id = e.getId();
			String s = "SELECT * FROM " + ThingEntityTable.tableName + " WHERE " + ThingEntityTable.thingId + " = " + id;

			Cursor c = db.rawQuery(s, null);
			if (c.getCount() == 1) {
				ContentValues values = e.toContentValues();
				// values.put(key, value)
				/** update此处仅 " ? = ? " */
				String updateString = ThingEntityTable.thingId + " = " + id;
				db.update(ThingEntityTable.tableName, values, updateString, null);
				db.close();
				return "writeData--update";
			} else if (c.getCount() == 0) {
				ContentValues cv = e.toContentValues();
				db.insert(ThingEntityTable.tableName, null, cv);
				db.close();
				return "writeData--insert";
			} else {
				db.close();
				return "writeData--c.getCount: " + c.getCount();
			}
		} else {
			return "writeData--entity is null";
		}
	}

	public String writeData(RecordEntity e) {

		SQLiteDatabase db = this.getWritableDatabase();
		if (e != null) {
			String id = e.getId();
			String s = "SELECT * FROM " + RecordsTable.tableName + " WHERE " + RecordsTable.thingId + " = " + id;

			Cursor c = db.rawQuery(s, null);
			if (c.getCount() == 1) {
				ContentValues values = e.toContentValues();
				// values.put(key, value)
				/** update此处仅 " ? = ? " */
				String updateString = RecordsTable.identifier + " = " + id;
				db.update(RecordsTable.tableName, values, updateString, null);
				db.close();
				return "writeData--update";
			} else if (c.getCount() == 0) {
				ContentValues cv = e.toContentValues();
				db.insert(RecordsTable.tableName, null, cv);
				db.close();
				return "writeData--insert";
			} else {
				db.close();
				return "writeData--cursor.getCount: " + c.getCount();
			}
		} else {
			return "writeData--entity is null";
		}
	}

}
