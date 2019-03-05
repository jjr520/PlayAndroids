package jjr.com.playandroids.utils.litao;

import java.util.List;

import jjr.com.playandroids.beans.fivelistbean.SearchDetails;
import jjr.com.playandroids.dao.DaoMaster;
import jjr.com.playandroids.dao.DaoSession;
import jjr.com.playandroids.dao.SearchDetailsDao;
import jjr.com.playandroids.dao.TreeBeanDao;
import jjr.com.playandroids.global.MyApp;

public class Search {
    private static Search search;
    private final SearchDetailsDao mSearchDetailsDaos;


    public Search() {
        DaoMaster.DevOpenHelper openHelper = new DaoMaster.DevOpenHelper(MyApp.getApplication(), "search.db");
        //获取可读写数据库
        DaoMaster daoMaster = new DaoMaster(openHelper.getWritableDatabase());
        //获取表管理器
        DaoSession daoSession = daoMaster.newSession();
        //获取我们要操作表的工具类

        mSearchDetailsDaos = daoSession.getSearchDetailsDao();
    }

    public static Search getSearchInstance() {
        if (search == null) {
            synchronized (TreeDataUtils.class) {
                if (search == null) {
                    search = new Search();
                }
            }
        }
        return search;
    }

    public void insert(SearchDetails searchDetails) {
        mSearchDetailsDaos.insertInTx(searchDetails);
    }


    public List<SearchDetails> selectAll() {
        return mSearchDetailsDaos.queryBuilder().list();
    }


    public void deleteAll(List<SearchDetails> searchDetails) {
        mSearchDetailsDaos.deleteInTx(searchDetails);
    }

    public SearchDetails selectSingle(String name) {
        return mSearchDetailsDaos.queryBuilder().where(SearchDetailsDao.Properties.Name.eq(name)).unique();
    }


}
