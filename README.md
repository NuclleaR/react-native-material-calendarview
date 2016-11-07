# React-native-material-calendarview

A simple material calendar for react native android

## Installation Android
- `npm i --save react-native-material-calendarview`

- In `android/settings.gradle`

    ```gradle
    ...
    include ':ReactMaterialCalendarView', ':app'
    project(':ReactMaterialCalendarView').projectDir = new File(rootProject.projectDir, '../node_modules/react-native-material-calendarview/app')
    ```

- In `android/app/build.gradle`

    ```gradle
    ...
    dependencies {
        ...
        compile project(':ReactMaterialCalendarView')
    }
    ```

- Register module (in MainApplication.java)

    ```java
    import com.nucllear.rn_materialcalendarview.ReactMaterialCalendarViewPackage; // <----- import package

    public class MainApplication extends Application implements ReactApplication {
        ...

        @Override
        protected List<ReactPackage> getPackages() {
          return Arrays.<ReactPackage>asList(
            new MainReactPackage(),
            new ReactMaterialCalendarViewPackage() // <------ add package here
          );
        }
    }
    ```

## Usage

```js

import Calendar from 'react-native-material-calendarview';
...

  render() {
    return (
      <Calendar
        width={(Dimensions.get('window').width)-32}
        height={280}
        tileHeight={35}
        style={{alignSelf: 'center'}}
        topbarVisible={true}
        datesSelection={'single'}
        firstDayOfWeek="monday"
        showOtherDates="none"
        currentDate={this.state.today}
        selectedDates={this.state.dates}
        eventsDates={["2016/11/20", "2016/11/29"]}
        eventsColor="#9C27B0"
        onDateChange={data => {
            console.log(data);
        }}
        onMonthChange={month => {
            console.log(month);
        }}
      />
    )
  }
```

## Props

* Size
  - *Integer* **width** (required)
  Provide the width of the calendar.

  - *Integer* **height**
  Provide the height. Default will be calculated based on width and ```topbarVisible```.

  - *Integer* **tileWidth**
  Provide the width of one day tile. Set width in DP.

  - *Integer* **tileHeight**
  Provide the height of one day tile. Set height in DP.

  - *Integer* **tileSize**
  Provide the size (width and height) of one day tile. Set size id DP.

* Toolbar options
  - *boolean* **topbarVisible** (default = true)
  Show/hide the top bar which contains the month's title and arrows to go to previous or next months.
  
  - *String* **arrowColor** (format #RRGGBB of #AARRGGBB)
  A string color. It changes color of the top bar's arrows.

* Calendar config
  - *String* **firstDayOfWeek** (default = 'sunday')
  Set the first day of the week.
  Should be one of [ ‘monday’, ‘tuesday’, ‘wednesday’, ‘thursday’, ‘friday’, ‘saturday’, ‘sunday’ ]
  
  - *String* **minimumDate** (format 'yyyy/mm/dd')
  Set minimum date for calendar.
  
  - *String* **maximumDate** (format 'yyyy/mm/dd')
  Set maximum date for calendar.
  
  - *String* **datesSelection**
  Set the selection mode.
      - *none*: you cannot select date
      - *range*: you can select range of dates
      - *single*: you can only select one date at a time
      - *multiple*: you can select multiple dates
  
  - *String* **showOtherDates** (default = 'current')
  Show dates from previous and current months or show dates within current month.
      - *all*: dates from previous and current months
      - *none*: show dates within current month

* Set date
  - *String* **currentDate** (format 'yyyy/mm/dd')
  Set the focus of the calendar.

  - *Array[String]* **selectedDates**
  Set selected dates to the calendar.

  - *Array[String]* **eventsDates**
  Set events dates to the calendar.
  
* Color customizations
  - *String* **selectionColor** (format #RRGGBB of #AARRGGBB)
  Set the color of the selection circle.
 
  - *String* **weekendsColor** (format #RRGGBB of #AARRGGBB)
  Set the color of the weekend.

  - *String* **eventsColor** (format #RRGGBB of #AARRGGBB)
  Set the color of the events.

## Event

- onDateChange
Called when user select/deselect a date. The returned data is
```{ date: 'yyyy/mm/dd', selected: boolean }```
- onMonthChanged
Called when user change a month. The returned data is
```{month: int}```

--------------------------------
Feel free to [open an issue](https://github.com/NuclleaR/react-native-material-calendarview/issues).
[Pull requests](https://github.com/NuclleaR/react-native-material-calendarview/pulls) are also welcome

A lot of thanks to @prolificinteractive for their awesome [Material Calendar View](https://github.com/prolificinteractive/material-calendarview)
