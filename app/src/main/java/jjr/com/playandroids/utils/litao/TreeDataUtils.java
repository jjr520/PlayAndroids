package jjr.com.playandroids.utils.litao;


import java.util.List;

import jjr.com.playandroids.beans.fivelistbean.TreeBean;
import jjr.com.playandroids.dao.DaoMaster;
import jjr.com.playandroids.dao.DaoSession;
import jjr.com.playandroids.dao.TreeBeanDao;
import jjr.com.playandroids.global.MyApp;

public class TreeDataUtils {

    private static TreeDataUtils treeDataUtils;
    private final TreeBeanDao mTreeBeanDaos;

    public TreeDataUtils() {
        DaoMaster.DevOpenHelper openHelper = new DaoMaster.DevOpenHelper(MyApp.getApplication(), "tree.db");
        //获取可读写数据库
        DaoMaster daoMaster = new DaoMaster(openHelper.getWritableDatabase());
        //获取表管理器
        DaoSession daoSession = daoMaster.newSession();
        //获取我们要操作表的工具类
        mTreeBeanDaos = daoSession.getTreeBeanDao();
    }

    public static TreeDataUtils getTreeInstance() {
        if (treeDataUtils == null) {
            synchronized (TreeDataUtils.class) {
                if (treeDataUtils == null) {
                    treeDataUtils = new TreeDataUtils();
                }
            }
        }
        return treeDataUtils;
    }

    public void insert(List<TreeBean> treeBeans) {
        mTreeBeanDaos.insertInTx(treeBeans);
    }




    public List<TreeBean> selectAll() {
        return mTreeBeanDaos.queryBuilder().list();
    }


}
