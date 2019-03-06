package jjr.com.playandroids.utils.litao;

import java.util.List;

import jjr.com.playandroids.dao.CollectUtilsDao;
import jjr.com.playandroids.dao.DaoMaster;
import jjr.com.playandroids.dao.DaoSession;
import jjr.com.playandroids.dao.TreeBeanDao;
import jjr.com.playandroids.global.MyApp;

public class CollectData {

    private static CollectData collectData;

    private final CollectUtilsDao mCollectUtilsDaos;

    public CollectData() {
        DaoMaster.DevOpenHelper openHelper = new DaoMaster.DevOpenHelper(MyApp.getApplication(), "collect.db");
        //获取可读写数据库
        DaoMaster daoMaster = new DaoMaster(openHelper.getWritableDatabase());
        //获取表管理器
        DaoSession daoSession = daoMaster.newSession();
        //获取我们要操作表的工具类
        mCollectUtilsDaos = daoSession.getCollectUtilsDao();
    }

    public static CollectData getCollectDataInstance() {
        if (collectData == null) {
            synchronized (TreeDataUtils.class) {
                if (collectData == null) {
                    collectData = new CollectData();
                }
            }
        }
        return collectData;
    }

    public void insert(CollectUtils collectUtils) {
        mCollectUtilsDaos.insertInTx(collectUtils);
    }




    public List<CollectUtils> selectAll() {
        return mCollectUtilsDaos.queryBuilder().list();
    }




         public CollectUtils selectSingle(String name, String title) {
             return mCollectUtilsDaos.queryBuilder().where(CollectUtilsDao.Properties.Name.eq(name), CollectUtilsDao.Properties.Title.eq(title)).unique();
         }

          public void update(CollectUtils collectUtils) {
              mCollectUtilsDaos.update(collectUtils);
          }

}
