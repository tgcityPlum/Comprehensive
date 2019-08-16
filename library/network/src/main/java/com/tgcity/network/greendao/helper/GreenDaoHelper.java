package com.tgcity.network.greendao.helper;


import com.tgcity.network.base.NetworkApplication;
import com.tgcity.network.greendao.DaoMaster;
import com.tgcity.network.greendao.DaoSession;

/*
* 作者：TGCity by Administrator on 2018/7/23
*
* GreenDao帮助类
*
* */
public class GreenDaoHelper {

    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;
    private static GreenDaoHelper mInstance; //单例

    private GreenDaoHelper() {
        if (mInstance == null) {
            DaoMaster.DevOpenHelper devOpenHelper = new
                    DaoMaster.DevOpenHelper(NetworkApplication.getInstances().getApplicationContext(), "youzy-db", null);//此处为自己需要处理的表
            mDaoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());
            mDaoSession = mDaoMaster.newSession();
        }
    }

    /*
    * 双层判断加锁
    * */
    public static GreenDaoHelper getInstance() {
        if (mInstance == null) {
            synchronized (GreenDaoHelper.class) {//保证异步处理安全操作
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

    public void clear(){
        mDaoSession.clear();
    }
}