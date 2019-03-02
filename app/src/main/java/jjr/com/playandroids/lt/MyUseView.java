package jjr.com.playandroids.lt;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

public class MyUseView extends ViewGroup {

    LinearLayout linearLayout;
    RelativeLayout relativeLayout;

    public MyUseView(Context context) {
        this(context, null);
    }

    public MyUseView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyUseView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //存储每一个的高度
    List<Integer> listHeight = new ArrayList<>();
    //存储每一行view
    List<List<View>> allList = new ArrayList<>();

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

      /*//获取父View的高度和模式
        int groupWidthMode = MeasureSpec.getMode(widthMeasureSpec);
        int groupHeightMode = MeasureSpec.getMode(heightMeasureSpec);
        int groupWidthSize = MeasureSpec.getSize(widthMeasureSpec);
        int groupHeightSize = MeasureSpec.getSize(heightMeasureSpec);

        //记录当前自定义View 的高和宽
        int measureWidth = 0;
        int measureHeight = 0;
        //铺满
        if(groupWidthMode == MeasureSpec.EXACTLY && groupHeightMode == MeasureSpec.EXACTLY){
            measureWidth = groupWidthSize;
            measureHeight = groupHeightSize;
        }
        //另外的模式
        //记录子View的宽和高
        int childWidth = 0;
        int childHeight = 0;
        //记录每一行的宽和高
        int lineWidth = 0;
        int lineHeight = 0;
        //获取子View的数量
        int childCount = getChildCount();
        //存储一行view的集合
        List<View> itemViewList=new ArrayList<>();
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            //测量每一个子View
            measureChild(childView,widthMeasureSpec,heightMeasureSpec);
            MarginLayoutParams layoutParams = (MarginLayoutParams) childView.getLayoutParams();
            childWidth = layoutParams.leftMargin+layoutParams.rightMargin+getMeasuredWidth();
            childHeight = layoutParams.bottomMargin+layoutParams.topMargin+getMeasuredHeight();
            //换行
            if(lineWidth+childWidth > groupWidthSize){
                //记录上一行的信息
                measureWidth = Math.max(measureWidth,lineWidth);
                measureHeight += lineHeight;
                //将行高存储
                listHeight.add(lineHeight);
                allList.add(itemViewList);

                lineHeight=childHeight;
                lineWidth=childWidth;

                itemViewList=new ArrayList<>();
                itemViewList.add(childView);
            }else{
                //不换行
                //宽度叠加
                lineWidth+=childWidth;
                //取当前View的高度和行高  取最大
                lineHeight=Math.max(lineHeight,childHeight);
                itemViewList.add(childView);
            }
        }
        //设置View的宽和高
        setMeasuredDimension(measureWidth,measureHeight);*/
        //获取父View的宽高以及模式
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        //记录当前自定义View的宽高
        int measureWidth = 0;
        int measureHeight = 0;
        //match_parent 具体值
        if (widthMode == MeasureSpec.EXACTLY && heightMode == MeasureSpec.EXACTLY) {
            measureWidth = widthSize;
            measureHeight = heightSize;
        }
        //记录子View的宽高
        int childWidth = 0;
        int childHeight = 0;

        //记录每一行宽和行高
        int lineWidth = 0;
        int lineHeight = 0;


        //wrap_content
        int iCount = getChildCount();
        //存储一行view的集合
        List<View> itemList = new ArrayList<>();
        for (int i = 0; i < iCount; i++) {
            View childView = getChildAt(i);
            //测量每一个子View
            measureChild(childView, widthMeasureSpec, heightMeasureSpec);
            MarginLayoutParams layoutParams = (MarginLayoutParams) childView.getLayoutParams();
            childWidth = childView.getMeasuredWidth() + layoutParams.leftMargin + layoutParams.rightMargin;
            childHeight = childView.getMeasuredHeight() + layoutParams.topMargin + layoutParams.bottomMargin;
            //换行
            if (lineWidth + childWidth > widthSize) {
                //记录上一行的信息
                measureWidth = Math.max(measureWidth, lineWidth);
                measureHeight += lineHeight;
                //将行高存储
                listHeight.add(lineHeight);
                allList.add(itemList);

                lineHeight = childHeight;
                lineWidth = childWidth;

                itemList = new ArrayList<>();
                itemList.add(childView);

            } else {
                //宽度叠加
                lineWidth += childWidth;
                //取当前View的高度和行高  取最大
                lineHeight = Math.max(lineHeight, childHeight);
                itemList.add(childView);
            }

            if (i == iCount - 1) {
                measureHeight += lineHeight;
                measureWidth = Math.max(measureWidth, lineWidth);
                listHeight.add(lineHeight);
                allList.add(itemList);
            }


        }

        //设置View的宽和高
        setMeasuredDimension(measureWidth, measureHeight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int left, top, right, bottom;
        int cutleft = 0;
        int cutTop = 0;
        for (int i = 0; i < allList.size(); i++) {
            List<View> views = allList.get(i);
            for (int j = 0; j < views.size(); j++) {
                View childView = views.get(j);
                MarginLayoutParams layoutParams = (MarginLayoutParams) childView.getLayoutParams();
                left = cutleft + layoutParams.leftMargin;
                right = left + childView.getMeasuredWidth();
                top = cutTop + layoutParams.topMargin;
                bottom = top + childView.getMeasuredHeight();
                childView.layout(left, top, right, bottom);
                cutleft += childView.getMeasuredWidth() + layoutParams.leftMargin + layoutParams.rightMargin;
            }
            cutleft = 0;
            cutTop += listHeight.get(i);


        }
        listHeight.clear();
        allList.clear();


    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }


}


