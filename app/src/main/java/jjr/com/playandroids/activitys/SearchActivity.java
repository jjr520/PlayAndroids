package jjr.com.playandroids.activitys;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Random;

import butterknife.BindView;

import jjr.com.playandroids.R;
import jjr.com.playandroids.adapter.lt.SearchDetailsAdapter;
import jjr.com.playandroids.base.activity.BaseActivity;
import jjr.com.playandroids.beans.fivelistbean.HotSearch;
import jjr.com.playandroids.beans.fivelistbean.SearchDetails;
import jjr.com.playandroids.lt.MyUseView;
import jjr.com.playandroids.only.OnlyFive;
import jjr.com.playandroids.persenter.FivePresenter;
import jjr.com.playandroids.utils.CircularAnimUtil;
import jjr.com.playandroids.utils.CommonUtils;
import jjr.com.playandroids.utils.litao.Search;
import jjr.com.playandroids.view.FiveView;

public class SearchActivity extends BaseActivity<FiveView, FivePresenter<FiveView>> implements FiveView, TextWatcher, View.OnClickListener {
    @BindView(R.id.search_back)
    ImageView searchBack;
    @BindView(R.id.search_edit)
    EditText searchEdit;
    @BindView(R.id.search_tint_tv)
    TextView searchTintTv;
    @BindView(R.id.search_tv)
    TextView searchTv;
    @BindView(R.id.search_history_clear_all_tv)
    TextView searchHistoryClearAllTv;
    @BindView(R.id.search_history_null_tint_tv)
    TextView searchHistoryNullTintTv;
    @BindView(R.id.search_history_rv)
    RecyclerView searchHistoryRv;
    @BindView(R.id.search_scroll_view)
    NestedScrollView searchScrollView;
    @BindView(R.id.search_floating_action_btn)
    FloatingActionButton searchFloatingActionBtn;
    @BindView(R.id.search_coordinator_group)
    CoordinatorLayout searchCoordinatorGroup;
    @BindView(R.id.myUseviewsearch)
    MyUseView myUseviewsearch;
    private LinearLayout mLinearLayouts;
    private TextView mTextViews;
    private SearchDetailsAdapter mSearchDetailsAdapters;

    @Override
    protected FivePresenter<FiveView> createPresenter() {
        return new FivePresenter<>();
    }

    @Override
    protected void initData() {
        presenter.getDataFiveP(OnlyFive.SEARCH, "");
        searchHistoryRv.setLayoutManager(new LinearLayoutManager(this));
        mSearchDetailsAdapters = new SearchDetailsAdapter(this, Search.getSearchInstance().selectAll());
        searchHistoryRv.setAdapter(mSearchDetailsAdapters);
        lookSearchData();
        searchBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        searchEdit.addTextChangedListener(this);
        searchTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!searchEdit.getText().toString().isEmpty()) {
                    List<SearchDetails> searchDetails1 = Search.getSearchInstance().selectAll();
                    if (searchDetails1.size() != 0) {
                        for (int j = 0; j < searchDetails1.size(); j++) {
                            if (Search.getSearchInstance().selectSingle(searchEdit.getText().toString()) == null) {
                                SearchDetails searchDetails = new SearchDetails(null, searchEdit.getText().toString(), searchEdit.getText().toString(), 123 + "");
                                Search.getSearchInstance().insert(searchDetails);
                            }
                        }
                    } else {
                        SearchDetails searchDetails = new SearchDetails(null, searchEdit.getText().toString(), searchEdit.getText().toString(), 123 + "");
                        Search.getSearchInstance().insert(searchDetails);
                    }
                    lookSearchData();
                    Intent intent2 = new Intent(SearchActivity.this, SearchDetailsActivity.class);
                    intent2.putExtra("cid", searchEdit.getText().toString());
                    CircularAnimUtil.startActivity(SearchActivity.this, intent2, searchTv, R.color.colorCard);
                  //  finish();
                }
            }
        });
        searchHistoryClearAllTv.setOnClickListener(this);
        mSearchDetailsAdapters.setOnItemClickListener(new SearchDetailsAdapter.OnItemClickListener() {
            @Override
            public void onClickListener(View v, int position) {
                Intent intent1 = new Intent(SearchActivity.this, SearchDetailsActivity.class);
                intent1.putExtra("cid", mSearchDetailsAdapters.list.get(position).getName());
                CircularAnimUtil.startActivity(SearchActivity.this, intent1, searchTv, R.color.colorCard);
               // finish();
            }
        });
    }

    @Override
    public int createLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    public void showDataFive(Object o, String onlyOne) {
        switch (onlyOne) {
            case "search":
                final HotSearch hotSearch = (HotSearch) o;
                MyUseView.LayoutParams myView = new MyUseView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                for (int i = 0; i < hotSearch.getData().size(); i++) {
                    mLinearLayouts = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.use, null);
                    mTextViews = mLinearLayouts.findViewById(R.id.commonItemTitle);
                    mTextViews.setText(hotSearch.getData().get(i).getName());
                    Random myRandom = new Random();
                    int ranColor = 0x55000000 | myRandom.nextInt(0x55ffffff);
                    mTextViews.setBackgroundColor(ranColor);
                    mTextViews.setTextColor(0xffffffff);
                    myUseviewsearch.addView(mLinearLayouts, i, myView);
                    final int finalI = i;

                    mTextViews.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            searchEdit.setText(hotSearch.getData().get(finalI).getName());
                            List<SearchDetails> searchDetails1 = Search.getSearchInstance().selectAll();
                            if (searchDetails1.size() != 0) {
                                for (int j = 0; j < searchDetails1.size(); j++) {
                                    if (Search.getSearchInstance().selectSingle(hotSearch.getData().get(finalI).getName()) == null) {
                                        SearchDetails searchDetails = new SearchDetails(null, hotSearch.getData().get(finalI).getName(), hotSearch.getData().get(finalI).getLink(), hotSearch.getData().get(finalI).getId() + "");
                                        Search.getSearchInstance().insert(searchDetails);
                                    }
                                }
                            } else {
                                SearchDetails searchDetails = new SearchDetails(null, hotSearch.getData().get(finalI).getName(), hotSearch.getData().get(finalI).getLink(), hotSearch.getData().get(finalI).getId() + "");
                                Search.getSearchInstance().insert(searchDetails);
                            }
                            lookSearchData();
                            Intent intent = new Intent(SearchActivity.this, SearchDetailsActivity.class);
                            intent.putExtra("cid", hotSearch.getData().get(finalI).getName());
                            CircularAnimUtil.startActivity(SearchActivity.this, intent, mTextViews, R.color.colorCard);
                          //  finish();
                        }
                    });
                }
                break;
        }
    }

    private void lookSearchData() {
        List<SearchDetails> searchDetails = Search.getSearchInstance().selectAll();
        if (searchDetails.size() != 0) {
            mSearchDetailsAdapters.setData(searchDetails);
            setHistoryTvStatus(View.GONE, R.color.search_grey, R.drawable.ic_clear_all);

        } else {
            mSearchDetailsAdapters.setData(searchDetails);
            setHistoryTvStatus(View.VISIBLE, R.color.search_grey_gone, R.drawable.ic_clear_all_gone);
        }
    }

    private void setHistoryTvStatus(int visibility, @ColorRes int textColor, @DrawableRes int clearDrawable) {
        Drawable drawable;
        searchHistoryNullTintTv.setVisibility(visibility);
        searchHistoryClearAllTv.setTextColor(ContextCompat.getColor(SearchActivity.this, textColor));
        drawable = ContextCompat.getDrawable(SearchActivity.this, clearDrawable);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        searchHistoryClearAllTv.setCompoundDrawables(drawable, null, null, null);
        searchHistoryClearAllTv.setCompoundDrawablePadding(CommonUtils.dp2px(6));
    }

    @Override
    public void showError(String error) {
        Toast.makeText(mActivity, error, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        String content = s.toString();
        if (content.isEmpty()) {
            searchTintTv.setText("搜索更多干货");
        } else {
            searchTintTv.setText("");
        }

    }

    @Override
    public void afterTextChanged(Editable s) {


    }

    @Override
    public void onClick(View v) {
        Search.getSearchInstance().deleteAll(Search.getSearchInstance().selectAll());
        mSearchDetailsAdapters.setData(Search.getSearchInstance().selectAll());
        setHistoryTvStatus(View.VISIBLE, R.color.search_grey_gone, R.drawable.ic_clear_all_gone);
    }
}
