package com.example.customviews;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

import com.example.soundsamplerservice.R;

/**
 * Created by android on 19-01-2015.
 */
public class RibbenTextView extends TextView {

    int RibbenColor, RibbenSize;
    Paint RibbenPaint;

    public RibbenTextView(Context context) {
        super(context);
    }

    public RibbenTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.RibbenTextView, 0, 0);
        try {
            boolean TextColorEqualRibben = typedArray.getBoolean(R.styleable.RibbenTextView_ribbenColorAsTextColor, false);
            if (TextColorEqualRibben) {
                RibbenColor = getTextColor(context, typedArray, Color.WHITE);
            } else
                RibbenColor = typedArray.getColor(R.styleable.RibbenTextView_ribbenColor, Color.GRAY);

            RibbenSize = typedArray.getInt(R.styleable.RibbenTextView_ribbenSize, 10);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            typedArray.recycle();
        }
        init();
    }

    private void init() {
        RibbenPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        if (RibbenColor != 0)
            RibbenPaint.setColor(RibbenColor);
        RibbenPaint.setStyle(Paint.Style.FILL);

    }

    @Override
    protected void onDraw(Canvas canvas) {

        canvas.drawRect(0, 0, RibbenSize, getMeasuredHeight(), RibbenPaint);
       
        canvas.save();
        
        canvas.drawText(getText(),0,getText().length(),RibbenSize+getPaddingLeft(),getY(),getPaint());
        super.onDraw(canvas);
        canvas.restore();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

}
