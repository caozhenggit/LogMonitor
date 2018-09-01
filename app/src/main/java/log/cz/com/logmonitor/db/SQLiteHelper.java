package log.cz.com.logmonitor.db;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author caozheng
 * @date 2018/9/1
 * <p>
 * describe:继承自SQLiteOpenHelper，扩展实现自定义db的生成路径
 */

public class SQLiteHelper extends SQLiteOpenHelper {

    /** 数据库配置 */
    private SQLiteDBConfig mConfig;
    private SQLiteDatabase mSQLiteDatabase;

    public SQLiteHelper(SQLiteDBConfig config) {
        super(config.getContext(), config.getDbName(), null, config.getDbVersion());
        this.mConfig = config;
    }

    @Override
    public SQLiteDatabase getWritableDatabase() {
        if(mSQLiteDatabase != null) {
            return mSQLiteDatabase;
        }
        return super.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.mSQLiteDatabase = db;
        mConfig.createTables(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        this.mSQLiteDatabase = db;
        mConfig.onUpgradeHandler(db, oldVersion, newVersion);
    }
}
