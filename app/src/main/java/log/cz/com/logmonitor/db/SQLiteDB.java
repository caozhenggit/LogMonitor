package log.cz.com.logmonitor.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * @author 10744
 * @date 2018/9/1
 * <p>
 * describe:
 */
public class SQLiteDB {

    private volatile static SQLiteDB mInstance;

    private Context mContext;

    /** 数据库操作类 */
    private SQLiteDatabase mSQLiteDatabase;
    /** SQL语句执行管理器 */
    private static SQLExecuteManager mSQLExecuteManager;

    private SQLiteDB() {

    }

    public static SQLiteDB getInstance() {
        if (mInstance == null) {
            synchronized (SQLiteDB.class) {
                if (mInstance == null) {
                    mInstance = new SQLiteDB();
                }
            }
        }
        return mInstance;
    }

    /**
     * 初始化
     * @param mContext
     */
    public void init(Context mContext){
        this.mContext = mContext;

        initDb();
    }

    public static SQLExecuteManager getSQLExecuteManager(){
        return mSQLExecuteManager;
    }

    /**
     * 数据库初始化
     */
    private void initDb(){
        //数据库配置
        SQLiteDBConfig config = new SQLiteDBConfig(mContext);
        //打开数据库
        mSQLiteDatabase = new SQLiteHelper(config).getWritableDatabase();
        if(mSQLiteDatabase == null) {
            throw new NullPointerException("创建数据库对象失败");
        }
        //SQL语句执行管理器
        mSQLExecuteManager = new SQLExecuteManager(mSQLiteDatabase);
    }

    /**
     * 关闭当前数据库
     */
    public void close() {
        this.mSQLiteDatabase.close();
    }

    /**
     * 判断当前数据库是否打开
     * @return
     */
    public boolean isOpen() {
        return this.mSQLiteDatabase.isOpen();
    }
}
