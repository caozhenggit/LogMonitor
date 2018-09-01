package log.cz.com.logmonitor.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * @author 10744
 * @date 2018/9/1
 * <p>
 * describe:
 */
public class SQLiteDBConfig {

    private Context mContext;
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "database.db";

    public SQLiteDBConfig(Context context){
        mContext = context;
    }

    public Context getContext(){
        return mContext;
    }

    public int getDbVersion(){
        return DATABASE_VERSION;
    }

    public String getDbName(){
        return DATABASE_NAME;
    }

    /**
     * 数据库创建表
     * @param db
     */
    public void createTables(SQLiteDatabase db){
        SQLExecuteManager sqlExecuteManager = new SQLExecuteManager(db);
    }

    /**
     * 数据库升级
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    public void onUpgradeHandler(SQLiteDatabase db, int oldVersion, int newVersion){

    }
}
