package com.nucllear.rn_materialcalendarview;

import android.annotation.SuppressLint;
import android.graphics.Color;

import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
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

    public static final String REACT_CLASS = "RCTMaterialCalendarView";
    private static final String COLOR_REGEX = "^#([0-9A-Fa-f]{6}|[0-9A-Fa-f]{8})$";

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
    public void setMinimumDate(ReactMaterialCalendarView view, String minimumDate) {
        if (minimumDate != null) {
            try {
                @SuppressLint("SimpleDateFormat") DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
                Date minDate = df.parse(minimumDate);
                view.state().edit()
                        .setMinimumDate(CalendarDay.from(minDate))
                        .commit();
            } catch (ParseException e) {
                throw new JSApplicationIllegalArgumentException("Invalid date format: " + minimumDate);
            }
        }
    }

    @ReactProp(name = "maximumDate")
    public void setMaximumDate(ReactMaterialCalendarView view, String maximumDate) {
        if (maximumDate != null) {
            try {
                @SuppressLint("SimpleDateFormat") DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
                Date maxDate = df.parse(maximumDate);
                view.state().edit()
                        .setMaximumDate(CalendarDay.from(maxDate))
                        .commit();
            } catch (ParseException e) {
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

    // todo doesn't work properly
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
    public void setCurrentDate(ReactMaterialCalendarView view, String currentDate) {
        if (currentDate != null) {
            try {
                @SuppressLint("SimpleDateFormat") DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
                Date curDate = df.parse(currentDate);
                view.setCurrentDate(curDate);
            } catch (ParseException e) {
                throw new JSApplicationIllegalArgumentException("Invalid date format: " + currentDate);
            }
        }
    }

    /**
     * Returns a map of config data passed to JS that defines eligible events that can be placed on
     * native views. This should return bubbling directly-dispatched event types and specify what
     * names should be used to subscribe to either form (bubbling/capturing).
     *
     * Returned map should be of the form:
     * {
     *   "onTwirl": {
     *     "phasedRegistrationNames": {
     *       "bubbled": "onTwirl",
     *       "captured": "onTwirlCaptured"
     *     }
     *   }
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
