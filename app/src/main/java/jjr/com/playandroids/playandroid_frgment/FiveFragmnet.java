package jjr.com.playandroids.playandroid_frgment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import jjr.com.playandroids.R;
import jjr.com.playandroids.base.fragment.BaseFragment;
import jjr.com.playandroids.beans.fivelistbean.TreeListBean;
import jjr.com.playandroids.only.OnlyFive;
import jjr.com.playandroids.persenter.FivePresenter;
import jjr.com.playandroids.playandroid_frgment.litao.ProjectTabFragment;
import jjr.com.playandroids.utils.litao.TreeBean;
import jjr.com.playandroids.utils.litao.TreeDataUtils;
import jjr.com.playandroids.view.FiveView;


/**
 * Created by Administrator on 2019/2/27.
 */

public class FiveFragmnet extends BaseFragment<FiveView, FivePresenter<FiveView>> implements FiveView {

    @BindView(R.id.project_tab_layout)
    SlidingTabLayout projectTabLayout;
    @BindView(R.id.project_divider)
    View projectDivider;
    @BindView(R.id.project_viewpager)
    ViewPager projectViewpager;
    @BindView(R.id.normal_view)
    LinearLayout normalView;
    Unbinder unbinder;

    private List<Fragment> fragmentList = new ArrayList<>();

    @Override
    protected FivePresenter<FiveView> createPresenter() {
        return new FivePresenter<>();
    }

    @Override
    public int createLayoutId() {
        return R.layout.five_fragment;
    }

    @Override
    protected void initData() {
        /*SharedPreferences sharedPreferences = context.getSharedPreferences("project", Context.MODE_PRIVATE);
        boolean state = sharedPreferences.getBoolean("state", false);*/
       /* if (state) {
            presenter.getDataFiveP(OnlyFive.FIVE, "");
        }*/

        List<TreeBean> treeBeans = TreeDataUtils.getTreeInstance().selectAll();
        if (treeBeans.size() == 0) {
            presenter.getDataFiveP(OnlyFive.FIVE, "");
            setTab(treeBeans);
        } else {
            setTab(treeBeans);
        }


    }

    private void setTab(final List<TreeBean> list) {
        for (TreeBean t : list) {

            fragmentList.add(new ProjectTabFragment(t.getId().toString()));
        }
        projectViewpager.setAdapter(new FragmentStatePagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }

            @Override
            public int getCount() {
                return  list.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return list.get(position).getName().toString();
            }
        });


        projectViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //currentPage = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        projectTabLayout.setViewPager(projectViewpager);
        projectViewpager.setCurrentItem(0);

    }

    @Override
    public void showDataFive(Object o, String onlyOne) {


        if (o != null) {
            ArrayList<TreeBean> treeBeans = new ArrayList<>();
            TreeListBean treeListBean = (TreeListBean) o;
            for (TreeListBean.DataBean bean : treeListBean.getData()) {
                TreeBean treeBean = new TreeBean(null, bean.getName());
                treeBeans.add(treeBean);
            }
            TreeDataUtils.getTreeInstance().insert(treeBeans);
            setTab(TreeDataUtils.getTreeInstance().selectAll());
        }
       /* SharedPreferences sharedPreferences = context.getSharedPreferences("project", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putBoolean("state", true);
        edit.commit();*/

    }

    @Override
    public void showError(String error) {
        Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
    }

  /*  @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }*/

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //unbinder.unbind();
    }
}
