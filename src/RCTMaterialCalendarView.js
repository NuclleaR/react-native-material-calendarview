import React, { Component, PropTypes } from 'react';
import { requireNativeComponent, View } from 'react-native';

var RCTMaterialCalendarView = requireNativeComponent('RCTMaterialCalendarView', ReactMaterialCalendarView);

class ReactMaterialCalendarView extends Component {
  constructor() {
    super();
    this._onDateChange = this._onDateChange.bind(this);
    this._onMonthChange = this._onMonthChange.bind(this);
  }

  _onDateChange(event) {
    if (this.props.onDateChange) {
      this.props.onDateChange && this.props.onDateChange(event.nativeEvent);
    }
  }

  _onMonthChange(event) {
    if (this.props.onMonthChange) {
      this.props.onMonthChange && this.props.onMonthChange(event.nativeEvent);
    }
  }

  render() {
    var { style, ...rest } = this.props,
      width = rest.width,
      height = rest.height ? rest.height : rest.topbarVisible ? width / 7 * 8 : width;

    style = {
      ...style,
      width,
      height
    };

    return (
      <RCTMaterialCalendarView
        {...rest}
        style={style}
        onDateChange={this._onDateChange}
        onMonthChange={this._onMonthChange} />
    );
  }
}

var FIRST_DAY_OF_WEEK = [
  'monday',
  'tuesday',
  'wednesday',
  'thursday',
  'friday',
  'saturday',
  'sunday'
];

var SHOWING_DATE = [
  'all',
  'none'
];

var SELECTION_MODES = [
  'none',
  'range',
  'single',
  'multiple'
];

const colorType = function (props, propName, componentName, ...rest) {
  var checker = function () {
    var color = props[propName];
    var regex = /^#([0-9A-Fa-f]{6}|[0-9A-Fa-f]{8})$/;
    if (color && !regex.test(color)) {
      return new Error('Only accept color formats: #RRGGBB and #AARRGGBB');
    }
  };
  return PropTypes.string(props, propName, componentName, ...rest) || checker();
};

const ColorValidator = function (props, propName, componentName, ...rest) {
  var checker = function () {
    var color = props[propName];
    var regex = /^#([0-9A-Fa-f]{6}|[0-9A-Fa-f]{8})$/;
    if (color && !regex.test(color)) {
      return new Error('Color accept formats: #RRGGBB and #AARRGGBB');
    }
  };
  return PropTypes.string(props, propName, componentName, ...rest) || checker();
};

const DatesValidator = function (props, propName, componentName, ...rest) {
  console.log('DatesValidator');
  var checker = function () {
    var date = props[propName];
    var regex = /^(19|20)\d\d[/](0[1-9]|1[012])[/](0[1-9]|[12][0-9]|3[01])/;
    if (date && !regex.test(date)) {
      return new Error('Date should be: YYYY/MM/DD');
    }
  };
  return PropTypes.string(props, propName, componentName, ...rest) || checker();
};

ReactMaterialCalendarView.propTypes = {
  ...View.propTypes,
  width: PropTypes.number.isRequired,
  height: PropTypes.number,
  // Tile size
  tileWidth: PropTypes.number,
  tileHeight: PropTypes.number,
  tileSize: PropTypes.number,
  // Toolbar options
  topbarVisible: PropTypes.bool,
  arrowColor: ColorValidator,
  // Calendar config
  firstDayOfWeek: PropTypes.oneOf(FIRST_DAY_OF_WEEK),
  minimumDate: DatesValidator,
  maximumDate: DatesValidator,
  datesSelection: PropTypes.oneOf(SELECTION_MODES),
  showOtherDates: PropTypes.oneOf(SHOWING_DATE),
  // Set date
  currentDate: DatesValidator,
  selectedDates: PropTypes.arrayOf(DatesValidator),
  eventsDates: PropTypes.arrayOf(DatesValidator),
  // Color customizations
  selectionColor: ColorValidator,
  weekendsColor: ColorValidator,
  eventsColor: ColorValidator,
};

ReactMaterialCalendarView.defaultProps = {
  topbarVisible: true
};

export default ReactMaterialCalendarView;
