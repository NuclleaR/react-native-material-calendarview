package com.nucllear.rn_materialcalendarview.decorators;

import android.graphics.Color;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.spans.DotSpan;

import java.util.Collection;
import java.util.HashSet;

public class EventDecorator implements DayViewDecorator {

    private String name;
    private int color;
    private HashSet<CalendarDay> dates;

    public EventDecorator(String name) {
        this.name = name;
    }

    public EventDecorator(String name, int color) {
        this.name = name;
        this.color = color;
    }

    public EventDecorator(String name, Collection<CalendarDay> dates) {
        this.dates = new HashSet<>(dates);
    }

    public EventDecorator(String name, int color, Collection<CalendarDay> dates) {
        this.color = color;
        this.dates = new HashSet<>(dates);
    }

    public void setDates(Collection<CalendarDay> dates) {
        this.dates = new HashSet<>(dates);
    }

    public void setColor(String color) {
        this.color = Color.parseColor(color);
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        return dates.contains(day);
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.addSpan(new DotSpan(5, color));
    }
}
