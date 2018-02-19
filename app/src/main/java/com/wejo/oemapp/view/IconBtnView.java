package com.wejo.oemapp.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wejo.oemapp.R;

/**
 * Created by BenJohnson on 09/10/2017.
 */

public class IconBtnView extends RelativeLayout {

    private String text;
    private Drawable image;
    private View view;

    public IconBtnView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.ButtonText, 0, 0);
        text = ta.getString(R.styleable.ButtonText_buttonName);
        image = ta.getDrawable(R.styleable.ButtonText_buttonImage);
        init();
    }

    public IconBtnView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.ButtonText, 0, 0);
        text = ta.getString(R.styleable.ButtonText_buttonName);
        image = ta.getDrawable(R.styleable.ButtonText_buttonImage);
        init();
    }

    public IconBtnView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.ButtonText, 0, 0);
        text = ta.getString(R.styleable.ButtonText_buttonName);
        image = ta.getDrawable(R.styleable.ButtonText_buttonImage);
        init();
    }

    private void init(){
        view = LayoutInflater.from(getContext()).inflate(R.layout.view_icon_btn,this);
        ((TextView) view.findViewById(R.id.tv_btn_name)).setText(text);
        ((ImageView) view.findViewById(R.id.iv_btn_img)).setImageDrawable(image);
    }

    @Override
    public void setOnClickListener(@Nullable OnClickListener l) {
        super.setOnClickListener(l);
    }
}
