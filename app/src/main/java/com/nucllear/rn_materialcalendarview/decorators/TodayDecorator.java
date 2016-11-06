package com.nucllear.rn_materialcalendarview.decorators;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;

import com.nucllear.rn_materialcalendarview.R;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;

public class TodayDecorator implements DayViewDecorator {

    private final Drawable drawable;
    private CalendarDay date;
    private String color;

    public TodayDecorator(Context context) {
        date = CalendarDay.today();
        drawable = (Drawable) context.getResources().getDrawable(R.drawable.shape);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && color != null) {
            drawable.setTint(Color.parseColor(color));
        }
    }

    public TodayDecorator(Context context, int color) {
        date = CalendarDay.today();
        drawable = (Drawable) context.getResources().getDrawable(R.drawable.shape);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            drawable.setTint(color);
        }
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        return date != null && day.equals(date);
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.setBackgroundDrawable(drawable);
    }

    public void setColor(String color) {
        this.color = color;
    }
}
