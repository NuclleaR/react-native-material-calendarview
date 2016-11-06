package com.nucllear.rn_materialcalendarview;

import android.annotation.SuppressLint;
import android.graphics.Color;

import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.nucllear.rn_materialcalendarview.Utils.getDayOfWeekFromString;

public class ReactMaterialCalendarViewManager extends SimpleViewManager<ReactMaterialCalendarView> {

    private static final String REACT_CLASS = "RCTMaterialCalendarView";
    private static final String DATE_FORMAT = "yyyy/MM/dd";
    private static final String COLOR_REGEX = "^#([0-9A-Fa-f]{6}|[0-9A-Fa-f]{8})$";
    private static final String DATE_REGEX = "^(19|20)\\d\\d[/](0[1-9]|1[012])[/](0[1-9]|[12][0-9]|3[01])";

    @SuppressLint("SimpleDateFormat")
    private static final DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);

    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @Override
    protected ReactMaterialCalendarView createViewInstance(final ThemedReactContext reactContext) {
//        ReactMaterialCalendarView rmcv = new ReactMaterialCalendarView(reactContext);
        return new ReactMaterialCalendarView(reactContext);
    }


    // Tile size

    @ReactProp(name = "tileWidth")
    public void setTileWidth(ReactMaterialCalendarView view, Integer size) {
        if (size != null) {
            view.setTileWidthDp(size);
        }
    }

    @ReactProp(name = "tileHeight")
    public void setTileHeight(ReactMaterialCalendarView view, Integer size) {
        if (size != null) {
            view.setTileHeightDp(size);
        }
    }

    @ReactProp(name = "tileSize")
    public void setTileSize(ReactMaterialCalendarView view, Integer size) {
        if (size != null) {
            view.setTileSizeDp(size);
        }
    }

    // Toolbar options

    @ReactProp(name = "topbarVisible")
    public void setTopbarVisible(ReactMaterialCalendarView view, boolean visible) {
        view.setTopbarVisible(visible);
    }

    @ReactProp(name = "arrowColor")
    public void setArrowColor(ReactMaterialCalendarView view, String color) {
        if (color != null) {
            if (color.matches(COLOR_REGEX)) {
                view.setArrowColor(Color.parseColor(color));
            } else {
                throw new JSApplicationIllegalArgumentException("Invalid arrowColor property: " + color);
            }
        }
    }

    // Calendar config

    @ReactProp(name = "firstDayOfWeek")
    public void setFirstDayOfWeek(ReactMaterialCalendarView view, String firstDayOfWeek) {
        if (firstDayOfWeek != null) {
            view.state().edit()
                    .setFirstDayOfWeek(getDayOfWeekFromString(firstDayOfWeek))
                    .commit();
        }
    }

    @ReactProp(name = "minimumDate")
    public void setMinimumDate(ReactMaterialCalendarView view, String minimumDate) throws ParseException {
        if (minimumDate != null) {
            if (minimumDate.matches(DATE_REGEX)) {
                Date minDate = dateFormat.parse(minimumDate);
                view.state().edit()
                        .setMinimumDate(CalendarDay.from(minDate))
                        .commit();
            } else {
                throw new JSApplicationIllegalArgumentException("Invalid date format: " + minimumDate);
            }
        }
    }

    @ReactProp(name = "maximumDate")
    public void setMaximumDate(ReactMaterialCalendarView view, String maximumDate) throws ParseException {
        if (maximumDate != null) {
            if (maximumDate.matches(DATE_REGEX)) {
                Date maxDate = dateFormat.parse(maximumDate);
                view.state().edit()
                        .setMaximumDate(CalendarDay.from(maxDate))
                        .commit();

            } else {
                throw new JSApplicationIllegalArgumentException("Invalid date format: " + maximumDate);
            }
        }
    }

    @ReactProp(name = "datesSelection")
    public void setDatesSelection(ReactMaterialCalendarView view, String sMode) {
        if (sMode != null) {
            int mode;
            switch (sMode) {
                case "single":
                    view.setSelectionMode(MaterialCalendarView.SELECTION_MODE_SINGLE);
                    break;
                case "range":
                    view.setSelectionMode(MaterialCalendarView.SELECTION_MODE_RANGE);
                    break;
                case "multiple":
                    view.setSelectionMode(MaterialCalendarView.SELECTION_MODE_MULTIPLE);
                    break;
                case "none":
                    view.setSelectionMode(MaterialCalendarView.SELECTION_MODE_NONE);
                    break;
                default:
                    throw new JSApplicationIllegalArgumentException("Unknown datesSelection property: " + sMode);
            }
        }
    }

    @ReactProp(name = "showOtherDates")
    public void setShowOtherDates(ReactMaterialCalendarView view, String showDate) {
        if (showDate != null) {
            switch (showDate) {
                case "all":
                    view.setShowOtherDates(MaterialCalendarView.SHOW_ALL);
                    break;
                case "none":
                    view.setShowOtherDates(MaterialCalendarView.SHOW_NONE);
                    break;
                case "default":
                    view.setShowOtherDates(MaterialCalendarView.SHOW_DEFAULTS);
                    break;
                case "decoratedDisabled":
                    view.setShowOtherDates(MaterialCalendarView.SHOW_DECORATED_DISABLED);
                    break;
                default:
                    throw new JSApplicationIllegalArgumentException("Unknown showOtherDates property: " + showDate);
            }
        }
    }

    // Set date

    @ReactProp(name = "currentDate")
    public void setCurrentDate(ReactMaterialCalendarView view, String currentDate) throws ParseException {
        if (currentDate != null) {
            if (currentDate.matches(DATE_REGEX)) {
                Date curDate = dateFormat.parse(currentDate);
                view.setCurrentDate(curDate);
            } else {
                throw new JSApplicationIllegalArgumentException("Invalid date format: " + currentDate);
            }
        }
    }

    @ReactProp(name = "selectedDates")
    public void setSelectedDates(ReactMaterialCalendarView view, ReadableArray dates) throws ParseException {
        ArrayList<Date> selectedDates = new ArrayList<Date>();
        for (int i = 0; i < dates.size(); i++) {
            String type = dates.getType(i).name();
            if("String".equals(type) && dates.getString(i).matches(DATE_REGEX)){
                Date date = dateFormat.parse(dates.getString(i));
                selectedDates.add(date);
            } else {
                throw new JSApplicationIllegalArgumentException("Invalid date format: " + dates.getString(i));
            }
        }
        for (Date date : selectedDates) {
            view.setDateSelected(date, true);
        }
    }

    @ReactProp(name = "eventsDates")
    public void setEventsDates(ReactMaterialCalendarView view, ReadableArray dates) throws ParseException {
        ArrayList<CalendarDay> decorated = new ArrayList<CalendarDay>();
        for (int i = 0; i < dates.size(); i++) {
            String type = dates.getType(i).name();
            if("String".equals(type) && dates.getString(i).matches(DATE_REGEX)){
                Date date = dateFormat.parse(dates.getString(i));
                decorated.add(CalendarDay.from(date));
            } else {
                throw new JSApplicationIllegalArgumentException("Invalid date format: " + dates.getString(i));
            }
        }
        if (decorated.size() > 0) {
            view.getEvents().setDates(decorated);
        }
    }



    // Color customizations

    @ReactProp(name = "selectionColor")
    public void setSelectionColor(ReactMaterialCalendarView view, String color) {
        if (color != null) {
            if (color.matches(COLOR_REGEX)) {
                view.setSelectionColor(Color.parseColor(color));
                view.setTodayColor(color);
            } else {
                throw new JSApplicationIllegalArgumentException("Invalid selectionColor property: " + color);
            }
        }
    }

    @ReactProp(name = "weekendsColor")
    public void setWeekendsColor(ReactMaterialCalendarView view, String color) {
        if (color != null) {
            if (color.matches(COLOR_REGEX)) {
                view.setWeekEndsColor(color);
            } else {
                throw new JSApplicationIllegalArgumentException("Invalid weekendsColor property: " + color);
            }
        }
    }

    @ReactProp(name = "eventsColor")
    public void setEventsColor(ReactMaterialCalendarView view, String color) {
        if (color != null) {
            if (color.matches(COLOR_REGEX)) {
                view.setEventsColor(color);
            } else {
                throw new JSApplicationIllegalArgumentException("Invalid weekendsColor property: " + color);
            }
        }
    }

    // Events

    /**
     * Returns a map of config data passed to JS that defines eligible events that can be placed on
     * native views. This should return bubbling directly-dispatched event types and specify what
     * names should be used to subscribe to either form (bubbling/capturing).
     * <p>
     * Returned map should be of the form:
     * {
     * "onTwirl": {
     * "phasedRegistrationNames": {
     * "bubbled": "onTwirl",
     * "captured": "onTwirlCaptured"
     * }
     * }
     * }
     */
    public Map<String, Object> getExportedCustomBubblingEventTypeConstants() {

        Map<String, Object> eventsMap = new HashMap<>();
        List<String> events = new ArrayList<>();

        events.add("DateChange");
        events.add("MonthChange");

        for (int i = 0; i < events.size(); i++) {
            Map<String, Map<String, String>> topEvent = new HashMap<>();
            topEvent.put("phasedRegistrationNames", new HashMap<String, String>());
            topEvent.get("phasedRegistrationNames").put("bubbled", "on" + events.get(i));
            topEvent.get("phasedRegistrationNames").put("captured", "on" + "on" + events.get(i) + "Captured");
            eventsMap.put("top" + events.get(i), topEvent);
        }

        return eventsMap;
    }
}
