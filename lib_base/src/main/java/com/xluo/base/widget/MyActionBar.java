package com.xluo.base.widget;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.xluo.base.R;
import com.xluo.base.lisener.OnBaseClick;

@SuppressLint("AppCompatCustomView")
public class MyActionBar extends RelativeLayout {
    private String title = "";
    private String rightMenuName = "";//右边的菜单名字
    private int rightIcon;//右边的菜单icon
    private int leftIcon;//左边默认要显示
    private int barBg;
    private int titleColor;
    private int rightMenuColor;
    protected View contentView;
    protected Context mContext;
    private boolean showLeftIcon = true;
    public TextView tv_title;
    public TextView tv_right_name;
    OnBaseClick<Integer> onItemLeftClick;
    OnBaseClick<Integer> onItemrightIconClick;
    OnBaseClick<Integer> onItemrightTextClick;
    public ImageView leftIconView;
    public ImageView rightIconView;
    public RelativeLayout action_bar_container;


    public MyActionBar(Context context) {
        this(context, null);
    }

    public MyActionBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }


    public MyActionBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //获取到设置的宽高比，然后设置自己的值
        mContext = getContext();
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.MyActionBar);
        title = array.getString(R.styleable.MyActionBar_batTitle);
        rightMenuName = array.getString(R.styleable.MyActionBar_rightmenuname);
        rightIcon = array.getResourceId(R.styleable.MyActionBar_righticon, 0);
        leftIcon = array.getResourceId(R.styleable.MyActionBar_barlefticon, R.drawable.base_fanhui);
        barBg = array.getColor(R.styleable.MyActionBar_action_bar_background, Color.BLACK);
        titleColor = array.getColor(R.styleable.MyActionBar_title_color, Color.WHITE);
        rightMenuColor = array.getColor(R.styleable.MyActionBar_rightmenuname_color, Color.WHITE);
        showLeftIcon = array.getBoolean(R.styleable.MyActionBar_show_left_icon, true);
        array.recycle();
        initView();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }


    /**
     * 设置左边的点击事件
     *
     * @param onItemClick
     */
    public void setLeftIconClick(OnBaseClick<Integer> onItemClick) {
        onItemLeftClick = onItemClick;
    }


    /**
     * 设置右边图片点击事件
     *
     * @param onItemClick
     */
    public void setRightIconClick(OnBaseClick<Integer> onItemClick) {
        onItemrightIconClick = onItemClick;
    }


    /**
     * 设置右边文字描述的点击shijian
     *
     * @param onItemClick
     */
    public void setRightTextClick(OnBaseClick<Integer> onItemClick) {
        onItemrightTextClick = onItemClick;
    }


    /**
     * 初始化布局
     */
    private void initView() {
        contentView = RelativeLayout.inflate(mContext, R.layout.base_action_bar_layout, this);
        tv_title = contentView.findViewById(R.id.tv_action_title);
        action_bar_container = contentView.findViewById(R.id.action_bar_container);
        tv_right_name = contentView.findViewById(R.id.right_text_btn);
        leftIconView = contentView.findViewById(R.id.back_close);
        rightIconView = contentView.findViewById(R.id.right_img_btn);
        tv_title.setText(title);
        leftIconView.setImageResource(leftIcon);
        action_bar_container.setBackgroundColor(barBg);
        if (showLeftIcon) {
            leftIconView.setVisibility(VISIBLE);
        } else {
            leftIconView.setVisibility(GONE);
        }
        tv_title.setTextColor(titleColor);
        if (rightMenuName == null || rightMenuName.equals("")) {
            tv_right_name.setVisibility(GONE);
        } else {
            tv_right_name.setVisibility(VISIBLE);
            tv_right_name.setText(rightMenuName);
            tv_right_name.setTextColor(rightMenuColor);
            tv_right_name.setOnClickListener(v -> {
                if (onItemrightTextClick != null) {
                    onItemrightTextClick.onClick(v.getId());
                }
            });
        }
        leftIconView.setOnClickListener(v -> {
            if (onItemLeftClick != null) {
                onItemLeftClick.onClick(v.getId());
            } else {
                ((Activity) mContext).finish();
            }
        });
        leftIconView.setImageResource(leftIcon);
        rightIconView.setImageResource(rightIcon);
        rightIconView.setVisibility(VISIBLE);
        rightIconView.setOnClickListener(v -> {
            if (onItemrightIconClick != null) {
                onItemrightIconClick.onClick(v.getId());
            }
        });
    }

    /**
     * 设置标题
     *
     * @param title
     */
    public void setTitle(String title) {
        tv_title.setText(title);
    }


    /**
     * 设置右边按钮是否可见
     *
     * @param status
     */
    public void setRightMenuNameIsShow(int status) {
        tv_right_name.setVisibility(status);
    }


    public void setRightDrawble(int rightDrawble) {
        try {
            Drawable drawable = getResources().getDrawable(rightDrawble);
            tv_right_name.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null);
        } catch (Exception e) {

        }
    }


    public void setRightMenuName(String name) {
        tv_right_name.setText(name);
    }


    public void setRightIcon(int resoureId) {
        if (rightIconView != null) {
            rightIconView.setVisibility(VISIBLE);
            rightIconView.setImageResource(resoureId);
            rightIconView.setOnClickListener(v -> {
                if (onItemrightIconClick != null) {
                    onItemrightIconClick.onClick(v.getId());
                }
            });
        }
    }

    public void setRightMenuColor(String color) {
        tv_right_name.setTextColor(Color.parseColor(color));
        tv_right_name.setVisibility(VISIBLE);
        tv_right_name.setOnClickListener(v -> {
            if (onItemrightTextClick != null) {
                onItemrightTextClick.onClick(0);
            }
        });
    }


    public void hideRightIcon() {
        if (rightIconView != null) {
            rightIconView.setVisibility(GONE);
        }
    }


    /**
     * 置灰按钮 不可点击
     */
    public void disableAddFriend() {
        rightIconView.setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_IN);
        rightIconView.setOnClickListener(null);
    }

}
