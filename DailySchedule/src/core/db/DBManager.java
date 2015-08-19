package core.db;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import com.dailyschedule.GlobalConstants;
import com.dailyschedule.GlobalConstants.ColorTable;
import com.dailyschedule.GlobalConstants.DBConfig;
import com.dailyschedule.GlobalConstants.DailyTable;
import com.dailyschedule.GlobalConstants.RecordsTable;
import com.dailyschedule.GlobalConstants.ThingEntityTable;
import com.dailyschedule.R;

import core.entity.ColorEntity;
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
		this.getWritableDatabase();

	}

	public DBManager(Context context) {
		super(context, BD_NAME, null, VERSON);
		this.context = context;
		this.getWritableDatabase();
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
		String c2 = "name TEXT,";
		String c3 = "code TEXT, is_used TEXT, tag TEXT";
		String creatColorTable = c1 + c2 + c3 + ")";
		db.execSQL(creatColorTable);

		// 检查更新颜色表
		refreshColorTable();
		// Log.d("COLOR", refreshColorTable());
	}

	/**
	 * @Functiuon 更新颜色列表
	 * @Author Heguanyuan 2015-8-19 下午4:13:45
	 */
	private String refreshColorTable() {
		// SQLiteDatabase db = this.getWritableDatabase();
		String sql = "SELECT * FROM " + ColorTable.tableName;
		Cursor cursor = db.rawQuery(sql, null);
		String[] colorArray = context.getResources().getStringArray(R.array.color);
		int colorNum = colorArray.length;
		if (cursor.getCount() < colorNum) {
			for (int i = 0; i < colorNum; i++) {
				System.out.println("----" + colorArray[i].toString() + "-------");
				// String str = "SELECT * FROM " + ColorTable.tableName +
				// " WHERE " + ColorTable.colorCode + " = " +
				// colorArray[i].toString().trim() + ";";
				String str = "SELECT * FROM " + ColorTable.tableName + " WHERE " + ColorTable.colorCode + " = ?";
				Cursor c = db.rawQuery(str, new String[] { colorArray[i].toString() });
				if (c.getCount() == 0) {
					ColorEntity e = new ColorEntity(colorArray[i]);
					ContentValues cv = e.toContentValues();
					db.insert(ColorTable.tableName, null, cv);
				}
			}
			return "add color ----- num: " + (colorNum - cursor.getCount());
		} else {
			return "add color ----- no new";
		}
	}

	/**
	 * @Functiuon 获取未使用的颜色列表
	 * @Author Heguanyuan 2015-8-19 下午4:13:21
	 */
	public ArrayList<ColorEntity> getColorArray() {
		ArrayList<ColorEntity> array = new ArrayList<ColorEntity>();

		String sql = "SELECT * FROM " + ColorTable.tableName + " WHERE " + ColorTable.isUsed + " = " + "'0'";
		String[] params = new String[] { ColorTable.tableName, ColorTable.isUsed };
		if (db == null) {
			db = this.getWritableDatabase();
		}
		Cursor c = db.rawQuery(sql, null);

		while (c.moveToNext()) {
			String code = c.getString(c.getColumnIndex(ColorTable.colorCode));
			String isUsed = c.getString(c.getColumnIndex(ColorTable.isUsed));
			ColorEntity e = new ColorEntity(code, isUsed);
			array.add(e);
		}
		return array;
	}

	/**
	 * @Functiuon 更新颜色信息 （设置 已/未 使用）
	 * @Author Heguanyuan 2015-8-19 下午4:17:40
	 */
	public String updateColorInfo(ColorEntity e) {

		String sql = "SELECT * FROM ? WHERE ?=" + e.getCode();
		String[] params = new String[] { ColorTable.tableName, ColorTable.colorCode };
		Cursor c = db.rawQuery(sql, params);
		if (c.getCount() == 1) {
			ContentValues cv = e.toContentValues();
			String whereClause = ColorTable.colorCode + " = " + e.getCode();
			int count = db.update(ColorTable.tableName, cv, whereClause, null);
			return "updateColorInfo----> updated " + count;
		} else {
			return "updateColorInfo----> failed c.getCount = " + c.getCount();
		}
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
