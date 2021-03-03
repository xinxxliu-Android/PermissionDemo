package com.example.permissiondemo.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.ColorInt;

import com.example.permissiondemo.R;

public class TitleBar extends RelativeLayout {

    private static final String TAG = "TitleBar";
    private ImageButton mBackButton;
    private ImageButton mShareButton;
    private TextView mTitle;
    private OnBackClickListener mOnBackClickListener;
    private OnShareClickListener mOnShareClickListener;
    private onRuleClickListner mRuleClickListner;
    private View mBottomLine;
    private boolean mEnableShare;
    private int mTitleColorResId;
    private int mBackIconResId;
    private int mShareIconResId;
    private int mTitleTextResId;
    private boolean mShowBottomLine;
    private int mRuleResId;
    private ImageButton mRuleButton;
    private boolean mEnableRule;
    private static int TITLECOLOR = R.color.common_text_color;
    private int mTitleColor = TITLECOLOR;
    private float mTitleTextSize;
    private boolean mTitleBold;

    public interface OnBackClickListener {
        void onBackClick();
    }

    public interface OnShareClickListener {
        void onShareClick();
    }

    public interface onRuleClickListner {
        void onRuleClick();
    }

    public TitleBar(Context context) {
        super(context);
        initViews(context);
    }

    public TitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        handleAttributes(context, attrs);
        initViews(context);
    }

    public TitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        handleAttributes(context, attrs);
        initViews(context);
    }

    private void handleAttributes(Context context, AttributeSet attrs) {
        try {
            TypedArray styledAttrs = context.obtainStyledAttributes(attrs, R.styleable.TitleBar);
            mTitleColor = styledAttrs.getResourceId(R.styleable.TitleBar_title_color, TITLECOLOR);
            mEnableShare = styledAttrs.getBoolean(R.styleable.TitleBar_enable_share, false);
            mTitleColorResId = styledAttrs.getResourceId(R.styleable.TitleBar_title_color, R.color.common_text_color);
            mBackIconResId = styledAttrs.getResourceId(R.styleable.TitleBar_back_icon, R.drawable.ic_back);
            mShareIconResId = styledAttrs.getResourceId(R.styleable.TitleBar_share_icon, R.drawable.ic_share);
            mRuleResId = styledAttrs.getResourceId(R.styleable.TitleBar_integral_rule, R.drawable.ic_invalid_name);
            mTitleTextResId = styledAttrs.getResourceId(R.styleable.TitleBar_title_text, R.string.app_name);
            mEnableRule = styledAttrs.getBoolean(R.styleable.TitleBar_enable_rule, false);
            mShowBottomLine = styledAttrs.getBoolean(R.styleable.TitleBar_show_bottom_line, true);
            styledAttrs.recycle();
        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
        }
    }

    private void initViews(Context context) {
        LayoutInflater.from(context).inflate(R.layout.title_bar_layout, this);
        mBackButton = findViewById(R.id.title_back);
        mBackButton.setImageResource(mBackIconResId);
        mShareButton = findViewById(R.id.title_share);
        mRuleButton = findViewById(R.id.title_rule);
        mRuleButton.setImageResource(mRuleResId);
        mShareButton.setImageResource(mShareIconResId);
        if (mEnableShare) {
            mShareButton.setVisibility(VISIBLE);
        }
        if (mEnableRule) {
            mRuleButton.setVisibility(VISIBLE);
        }
        mTitle = findViewById(R.id.title_tv);
        mTitle.setText(mTitleTextResId);
        mTitle.setTextColor(context.getResources().getColor(mTitleColorResId));
        mBottomLine = findViewById(R.id.bottom_line);
        if (!mShowBottomLine) {
            mBottomLine.setVisibility(GONE);
        }
        mBackButton.setOnClickListener(mOnClickListener);
        mTitle.setOnClickListener(mOnClickListener);
        mShareButton.setOnClickListener(mOnClickListener);
        mRuleButton.setOnClickListener(mOnClickListener);
    }

    public void setTitleColor(@ColorInt int titleColor) {
        mTitle.setTextColor(titleColor);
    }

    public void setBackIconResId(int backIconResId) {
        mBackIconResId = backIconResId;
        mBackButton.setImageResource(backIconResId);
    }

    public void setShareIconResId(int shareIconResId) {
        mShareIconResId = shareIconResId;
        mShareButton.setImageResource(shareIconResId);
    }

    public void setOnBackClickListener(OnBackClickListener listener) {
        mOnBackClickListener = listener;
    }

    public void setOnShareClickListener(OnShareClickListener listener) {
        mOnShareClickListener = listener;
    }

    public void setOnRuleClickListener(onRuleClickListner listener) {
        mRuleClickListner = listener;
    }

    private OnClickListener mOnClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.title_back: {
                    if (mOnBackClickListener != null) {
                        mOnBackClickListener.onBackClick();
                    }
                    break;
                }
                case R.id.title_tv: {
                    break;
                }
                case R.id.title_share: {
                    if (mOnShareClickListener != null) {
                        mOnShareClickListener.onShareClick();
                    }
                    break;


                }
                case R.id.title_rule: {
                    if (mRuleClickListner != null) {
                        mRuleClickListner.onRuleClick();
                    }
                }
                default: {
                    break;
                }
            }
            return;
        }
    };

    public void setTitle(String title) {
        mTitle.setText(title);
    }

    public void setTitleVisibility(int visibility) {
        mTitle.setVisibility(visibility);
    }
}