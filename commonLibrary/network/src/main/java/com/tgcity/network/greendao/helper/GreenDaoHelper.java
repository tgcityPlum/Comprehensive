package com.tgcity.network.greendao.helper;


import com.tgcity.network.base.NetworkApplication;
import com.tgcity.network.base.NetworkConstant;
import com.tgcity.network.greendao.DaoMaster;
import com.tgcity.network.greendao.DaoSession;

/**
 * @author TGCity
 * GreenDao帮助类
 */
public class GreenDaoHelper {

    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;
    /**
     * 单例
     */
    private static GreenDaoHelper mInstance;

    private GreenDaoHelper() {
        if (mInstance == null) {
            //此处为自己需要处理的表
            DaoMaster.DevOpenHelper devOpenHelper = new
                    DaoMaster.DevOpenHelper(NetworkApplication.getInstances().getApplicationContext(), NetworkConstant.GREEN_DAO_NAME, null);
            mDaoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());
            mDaoSession = mDaoMaster.newSession();
        }
    }

    /**
     * 双层判断加锁
     * */
    public static GreenDaoHelper getInstance() {
        if (mInstance == null) {
            synchronized (GreenDaoHelper.class) {
                //保证异步处理安全操作
                if (mInstance == null) {
                    mInstance = new GreenDaoHelper();
                }
            }
        }
        return mInstance;
    }

    public DaoMaster getMaster() {
        return mDaoMaster;
    }

    public DaoSession getSession() {
        mDaoSession.clear();
        return mDaoSession;
    }

    public void clear() {
        mDaoSession.clear();
    }
}