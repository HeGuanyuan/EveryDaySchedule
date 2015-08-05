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
		db = SQLiteDatabase.openOrCreateDatabase(DBConfig.filePath, null);
		this.db = db;
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

	public SQLiteDatabase getDataBase() {
		return db;
	}

	public String  writeData(DailyEntity e)  {
		if (e != null) {
			String id = e.getId();
			String s = "SELECT * FROM ? WHERE ? = ?";
			String[] params = { DailyTable.tableName, DailyTable.index, id };
			Cursor c = db.rawQuery(s, params);
			if (c.getCount() == 1) {
				ContentValues values = new ContentValues();
				// values.put(key, value)
				db.update(DailyTable.tableName, values, s, params);
				return "writeData--update";
			} else if (c.getCount() == 0){
				ContentValues cv = new ContentValues();
				db.insert(DailyTable.tableName, null, cv);
				return "writeData--insert";
			}else{
				return "writeData--c.getCount: " + c.getCount();
			}
		}else {
			return "entity is null";
		}
	}

	public void writeData(ThingEntity e) {

	}

	public void writeData(RecordEntity e) {

	}
}
// if (name.equals(dailyTableName)) {
//
// String sqlString =
// "creat table ? (? varchar(10), ? varchar(10), ? varchar(10), ? varchar(10), ? varchar(10), ? varchar(10),  )";
// String[] params = {};
// db.execSQL(sqlString, params);
// } else if (name.equals(recordsTableName)) {
//
// } else if (name.equals(entityTableName)) {
//
// }
// return null;
