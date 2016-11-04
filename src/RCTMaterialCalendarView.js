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
  'default',
  'none',
  'all',
  'decoratedDisabled',
];

var SELECTION_MODES = [
  'none',
  'single',
  'range',
  'multiple',
];


ReactMaterialCalendarView.propTypes = {
  ...View.propTypes,
  width: PropTypes.number.isRequired,
  height: PropTypes.number,
  tileWidth: PropTypes.number,
  tileHeight: PropTypes.number,
  tileSize: PropTypes.number,
  topbarVisible: PropTypes.bool,
  arrowColor: PropTypes.string,
  firstDayOfWeek: PropTypes.oneOf(FIRST_DAY_OF_WEEK),
  minimumDate: PropTypes.string,
  maximumDate: PropTypes.string,
  datesSelection: PropTypes.oneOf(SELECTION_MODES),
  showOtherDates: PropTypes.oneOf(SHOWING_DATE),
  currentDate: PropTypes.string,
  onDateChange: PropTypes.func,
  onMonthChange: PropTypes.func,
};

ReactMaterialCalendarView.defaultProps = {
  topbarVisible: true
};

export default ReactMaterialCalendarView;
