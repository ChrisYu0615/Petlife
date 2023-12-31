//撈資料
$(document).on("change","#month",function(){
	var checkMonth=$("#month").val();
	var list = checkMonth.split('-');
	var year = parseInt(list[0]);
	var month = parseInt(list[1]) - 1;
	$('#mydate').glDatePicker(
	{
		showAlways : true,       // 預設為 false
		cssName: 'default',      // 可用 'default' 或  'darkneon' 或  'flatwhite'
		format: 'yyyy-mm-dd',    // 預設
		dowOffset: 0,            // 預設
		allowMonthSelect: false, // 預設
		allowYearSelect: true,   // 預設
		prevArrow: '\u25c4',     // 預設
		nextArrow: '\u25ba',     // 預設
		dowNames : [ '<font color=red>星期日</font>', '星期一', '星期二', '星期三', '星期四', '星期五', '<font color=red>星期六</font>' ], //自定
		monthNames: ['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月'], //自定
// ====================================================================================================              
//		以下的'月'為陣列index，加1才為正確的幾月
		selectedDate: new Date(year, month,1),
	    selectableYears:  [year],	//可選的年份
	    selectableMonths: [month],  //可選的月份

	});
	var checkMonth=$("#month").val();
	var checkbookingend= checkMonth + "-01";
	var checkbookingstart = checkMonth + "-31";
	var shelterId =$("input[name='shelterId']").val();
	var dataURL = `../project/shelterbooking.do?action=getAvalibleBookings&checkbookingstart=${checkbookingstart}&checkbookingend=${checkbookingend}&shelterId=${shelterId}`;
				
	$.ajax({
		url: dataURL,
		method: "post",
		async: false,
		success: res => {
			 for(var j =0;j<res.length;j++){	
				var dateString = res[j].shelterBookingDate;
				dateString = dateString.split(',')[0].replace('月 ', '/');
				var month = dateString.split('/')[0].padStart(2, '0');
				var day = dateString.split('/')[1].padStart(2, '0');
				dateString= month + '/'+ day;
				console.log(dateString);	
				for(var i = 0 ;i<document.getElementsByTagName('a').length;i++){
					var a_element = document.getElementsByTagName('a')[i];
					if(dateString === a_element.textContent){
						if(res[j].shelterBookingTime.includes('AM')){
							var button = a_element.closest("div.parent").querySelector("button.make_reservation_btn_am");
							button.id = res[j].id;
							$(`#${res[j].id}`).addClass('on');
						}else{
							var button = a_element.closest("div.parent").querySelector("button.make_reservation_btn_pm");
							button.id = res[j].id;
							$(`#${res[j].id}`).addClass('on');
						}
					}
				}
			}
			
			
		}, error: function(jqXHR, textStatus, errorThrown) {
			try {
				console.log("Error code:", jqXHR.status);
				console.log("Error message:", jqXHR.responseText);
			} catch (e) {
				console.error("Error parsing JSON response:", e);
			}
		},
	});
})

//拿到使用者點擊欲預約的上午或下午按鈕及日期
//宣告全域變數
//var bookingDate;
//var bookingTime;
//$(document).on("click",".booking_btn",function(){
////	console.log('old = '+bookingdate);
////	console.log('old = '+bookingtime);
////	console.log($(this).val());
////	console.log($(this).siblings("div").text());
//	bookingdate = $(this).siblings("div").text();
//	bookingtime = $(this).val();
////	console.log('new = '+bookingdate);
////	console.log('new = '+bookingtime);
//});

//拿到使用者點擊欲預約的id
//宣告全域變數
var bookingId;
//$(document).on("click",".booking_btn",function(){
//	    // 獲取點擊的按鈕元素
//    var clickedButton = $(this);
//
//    // 設定點擊的按鈕背景顏色
//    clickedButton.css("background-color", "yellow");
//
//    // 存儲點擊的按鈕的ID
//    bookingId = clickedButton.attr("id");
//});

var lastClickedButton;

$(document).on("click", ".booking_btn", function () {
    // 如果存在上一次點擊的按鈕，清除其背景色
    if (lastClickedButton) {
        lastClickedButton.css("background-color", "");
    }

    // 獲取點擊的按鈕元素
    var clickedButton = $(this);

    // 設定點擊的按鈕背景色
    clickedButton.css("background-color", "yellow");

    // 存儲當前點擊的按鈕，以便下次清除其背景色
    lastClickedButton = clickedButton;

    // 儲存點擊的按鈕的ID
    bookingId = clickedButton.attr("id");

});

function booking() {

    var user = $("#userId").val();
	$("#bookingId").val(bookingId);	//由全域變數取值
    
    if (user != 'null') {
        // if有登入
        $("#bookingSubmit").submit();
    } else {
        // else沒登入
//        console.log("User is not logged in");
		alert("您沒有登入");
		window.location.href = "../login/member_login.jsp"
    }
}

//搜尋按鈕ajax
$(document).on("click","#search_btn",function(){
	var dataURL = '../project/pet.do?action=getCompositePetsQuery2';

	var pet_type = $("select.pet_type").val();
	if(pet_type != "選擇種類"){
		dataURL = dataURL + `&type=${pet_type}`;
	}
	var pet_variety = $("select.pet_variety").val();
	if(pet_variety != "請先選擇種類" && pet_variety != "請選擇品種"){
		dataURL = dataURL + `&petVarietyId=${pet_variety}`;
	}
	var county = $("select.county").val();
	var shelter_name = $("select.shelter").val();
	if(county != "選擇縣市"){
		dataURL = dataURL + `&shelter_name=${shelter_name}`;
	}
	
		$.ajax({
		url: dataURL,
		method: "post",
		async: false,
		success: res => {
			var result = document.getElementById("result");
			result.innerHTML = res;
			
		}, error: function(jqXHR, textStatus, errorThrown) {
			try {
				console.log("Error code:", jqXHR.status);
				console.log("Error message:", jqXHR.responseText);
			} catch (e) {
				console.error("Error parsing JSON response:", e);
			}
		},
	});
	
	

})

$(document).on("change", "select.pet_type", function() {
	var type = $(this).val();
	var dataURL = `../project/pet?action=getCompositePetVarietiesQueryAsync&type=${type}`

	$.ajax({
		url: dataURL,
		method: "post",
		async: false,
		dataType: 'json',
		success: res => {
			var select = $('#petVarietyId');
			select.empty();
			$('<option>').val('請選擇品種').text('請選擇品種').appendTo(select);
			for (var i = 0; i < res.length; i++) {
				$('<option>').val(res[i].id).text(res[i].variety).appendTo(select);
			}
		}, error: function(jqXHR, textStatus, errorThrown) {
			try {
				console.log("Error code:", jqXHR.status);
				console.log("Error message:", jqXHR.responseText);
			} catch (e) {
				console.error("Error parsing JSON response:", e);
			}
		},
	});
})


//收容所縣市搜尋
$(function() {
    fetch('../assets/json/shelters_cities_final.json')
		.then(response => response.json())
		.then(data => {
			var countySelect = document.getElementById('county');
			for (var city in data) {
				var option = document.createElement('option');
				option.value = city;
				option.textContent = city;
				countySelect.appendChild(option);
			}

			// 在選擇縣市時會自動對應到該縣市有的收容所
			countySelect.addEventListener('change', function() {
				var selectedCity = countySelect.value;
				var shelterSelect = document.getElementById('shelter');
				shelterSelect.innerHTML = ''; // 清空行政區選項

				if (selectedCity in data) {
					var shelters = data[selectedCity];
					for (var i = 0; i < shelters.length; i++) {
						var shelterOption = document.createElement('option');
						shelterOption.value = shelters[i];
						shelterOption.textContent = shelters[i];
						shelterSelect.appendChild(shelterOption);
					}
				}
			});
		})

});
function redirectPage(newUrl) {
	window.location.href = newUrl;
}

//列表中點擊"更多資訊"
$(document).on("click",".getOnePet",function(e){
	e.preventDefault();
	var id = $(this).data("pet-id");
    $("#pet_id").val(id);
    $("#form").submit();	
});

//燈箱中的預約表

;(function() {
	$.fn.glDatePicker = function(options) {
		var pluginName = 'glDatePicker';

		return this.each(function() {
			return $(this).data(pluginName, new glDatePicker(this, options));
		});
	};

	// Default options
	$.fn.glDatePicker.defaults =
	{
		// Format
		format: 'yyyy-mm-dd',
		
		// Style to use for the calendar.  This name must match the name used in
		// the stylesheet, using the class naming convention "gldp-cssName".
		cssName: 'default',

		// The z-index for the calendar control.
		zIndex: 1000,

		// Thickness of border (in pixels)
		borderSize: 1,

		// The number of pixels to offset the calendar's position on the page.
		calendarOffset: { x: 0, y: 1 },

		// Set to true if you want the calendar to be visible at all times.
		// NOTE: If your target element is hidden, the calendar will be hidden as well.
		showAlways: false,

		// Hide the calendar when a date is selected (only if showAlways is set to false).
		hideOnClick: true,

		// Allow selection of months by clicking on the month in the title.
		allowMonthSelect: true,

		// Allow selection of years by clicking on the year in the title.
		allowYearSelect: true,

		// The date that will be treated as 'today'.
		todayDate: new Date(),

		// The date that will appear selected when the calendar renders.
		// By default it will be set to todayDate.
		selectedDate: null,

		// Arrows used for the Previous and Next month buttons on the title.
		// Set these to blank to hide the arrows completely.
		prevArrow: '\u25c4',
		nextArrow: '\u25ba',

		// A collection of dates that can be selectable by the user.
		// The dates can be a one-time selection or made repeatable by setting
		// the repeatYear or repeatMonth flag to true.
		// By default repeatYear and repeatMonth are false.
		//
		// This example creates 4-individual dates that can be selected;
		// The first date will repeat every year, the second date will repeat every
		// month and year, the third date will repeat every month and the fourth date
		// will only be selectable one-time and not repeat:
		//
		//    selectableDates: [
		//        { date: new Date(0, 8, 5), repeatYear: true },
		//        { date: new Date(0, 0, 14), repeatMonth: true, repeatYear: true },
		//        { date: new Date(2013, 0, 24), repeatMonth: true },
		//        { date: new Date(2013, 11, 25) },
		//    ]
		selectableDates: null,

		// A collection of date ranges that are selectable by the user.
		// The ranges can be made to repeat by setting repeatYear to true
		// (repeatMonth is not supported).
		//
		// This example will create 3-sets of selectable date ranges with
		// specific from and to ranges.  The 4th and 5th ranges don't specify
		// the "to" date in which case the "to" date will be the maximum days for
		// the month specified in "from".  The 4th and 5th ranges also repeat every year:
		//
		//     selectableDateRange: [
		//         { from: new Date(2013, 1, 1), to: newDate (2013, 2, 1) },
		//         { from: new Date(2013, 4, 1), to: newDate (2013, 8, 1) },
		//         { from: new Date(2013, 7, 10), to: newDate (2013, 9, 10) },
		//         { from: new Date(0, 8, 10), repeatYear: true }
		//         { from: new Date(0, 9, 1), repeatYear: true }
		//     ]
		selectableDateRange: null,

		// Mark certain dates as special dates.  Similar to selectableDates, this
		// property supports both repeatYear and repeatMonth flags.
		// Each special date can be styled using custom style names and can have
		// data attached to it that will be returned in the onClick callback.
		// The data field can be any custom (JSON style) object.
		//
		// This example creates two (repeatable by year) dates with special data in them.
		// The first date also assigns a special class (which you will have to define).
		//    specialDates: [
		//        {
		//            date: new Date(0, 8, 5),
		//            data: { message: 'Happy Birthday!' },
		//            repeatYear: true,
		//            cssClass: 'special-bday'
		//        },
		//        {
		//            date: new Date(2013, 0, 8),
		//            data: { message: 'Meeting every day 8 of the month' },
		//            repeatMonth: true
		//        }
		//    ]
		specialDates: null,

		// List of months that can be selectable, including when the user clicks
		// on the title to select from the dropdown.
		// This example only makes two months visible; September and December:
		//    selectableMonths: [8, 11]
		selectableMonths : null,

		// List of selectable years.  If not provided, will default to 5-years
		// back and forward.
		// This example only allows selection of dates that have year 2012, 2013, 2015
		//    selectableYears: [2012, 2013, 2015]
		selectableYears: null,

		// List of selectable days of the week.  0 is Sunday, 1 is Monday, and so on.
		// This example allows only Sunday, Tuesday, Thursday:
		//    selectableDOW: [0, 2, 4]
		selectableDOW : null,

		// Names of the month that will be shown in the title.
		// Will default to long-form names:
		//     January, February, March, April, May, June, July,
		//     August, September, October, November, December
		monthNames: null,

		// Names of the days of the Week that will be shown below the title.
		// Will default to short-form names:
		//     Sun, Mon, Tue, Wed, Thu, Fri, Sat
		dowNames: null,

		// The day of the week to start the calendar on.  0 is Sunday, 1 is Monday and so on.
		dowOffset: 0,

		// Callback that will trigger when the user clicks a selectable date.
		// Parameters that are passed to the callback:
		//     el : The input element the date picker is bound to
		//   cell : The cell on the calendar that triggered this event
		//   date : The date associated with the cell
		//   data : Special data associated with the cell (if available, otherwise, null)
		onClick: (function(el, cell, date, data) {
			//el.val(date.toLocaleDateString());
			el.val(date.format(this.format));
			
		}),

		// Callback that will trigger when the user hovers over a selectable date.
		// This callback receives the same set of parameters as onClick.
		onHover: function(el, cell, date, data) {},

		// Callback that will trigger when the calendar needs to show.
		// You can use this callback to animate the opening of the calendar.
		onShow: function(calendar) { calendar.show(); },

		// Callback that will trigger when the calendar needs to hide.
		// You can use this callback to animate the hiding of the calendar.
		onHide: function(calendar) { calendar.hide(); },

		// First date of the month.
		firstDate: null
	};

	// Our plugin object
	var glDatePicker = (function() {
		// Main entry point.  Initialize the plugin
		function glDatePicker(element, userOptions) {
			// Grab handle to this
			var self = this;

			// Save bound element to el
			self.el = $(element);
			var el = self.el;

			// Merge user options into default options
			self.options = $.extend(true, {}, $.fn.glDatePicker.defaults, userOptions);
			var options = self.options;

			// Find the calendar element if the user provided one
			self.calendar = $($.find('[gldp-el=' + el.attr('gldp-id') + ' ]'));

			// Default first date to selected
			options.selectedDate = options.selectedDate || options.todayDate;
			options.firstDate = (new Date((options.firstDate || options.selectedDate)))._first();

			if(!(el.attr('gldp-id') || '').length) {
				el.attr('gldp-id', 'gldp-' + Math.round(Math.random() * 1e10))
			}

			// Show the plugin on focus
			el
				.addClass('gldp-el')
				.bind('click', function(e) { self.show(e); })
				.bind('focus', function(e) { self.show(e); });

			// If the user is defining the container and it exists, hide it on initial creation.
			// The update function will handle showing if it's showAlways = true
			if(self.calendar.length && !options.showAlways) {
				self.calendar.hide();
			}

			// Hide the plugin on mouse up outside of the plugin
			$(document).bind('mouseup', function(e) {
				var target = e.target;
				var calendar = self.calendar;

				if(!el.is(target) && !calendar.is(target) && calendar.has(target).length === 0 && calendar.is(':visible')) {
					self.hide();
				}
			});

			// Render calendar
			self.render();
		};

		// Public methods
		glDatePicker.prototype =
		{
			show: function() {
				// Hide others and show this calendar
				$.each($('.gldp-el').not(this.el), function(i, o) {
					if(o.length) { o.options.onHide(o.calendar) ; }
				});

				// Show this calendar
				this.options.onShow(this.calendar);
			},

			hide: function() {
				if(this.options && !this.options.showAlways) {
					this.options.onHide(this.calendar);
				}
			},

			// Render the calendar
			render: function(renderCalback) {
				var self = this;
				var el = self.el;
				var options = self.options;
				var calendar = self.calendar;

				// Build a core class (with border) that every element would have
				var coreClass = ' core border ';
				var cssName = 'gldp-' + options.cssName;

				// Get today
				var todayVal = options.todayDate._val();
				var todayTime = todayVal.time;

				// Constants
				var maxRow = 6;
				var maxCol = 7;
				var borderSize = options.borderSize + 'px';

				// Helper function to build selectable list
				var getSelectableList = function(min, max, userList) {
					// Build a default list using min/max
					var resultList = [];
					for(var i = min; i <= max; i++) { resultList.push(i); }

					// If user provided a collection, sanitize list by ensuring it's within range and unique
					if(userList) {
						var newList = [];
						$.each(userList, function(i, v) {
							if(v >= min && v <= max && newList.indexOf(v) < 0) {
								newList.push(v);
							}
						});

						resultList = newList.length ? newList : resultList;
					};

					// Sort the values before returning it
					resultList.sort();

					return resultList;
				};

				// Selectable (constants)
				var selectableMonths = getSelectableList(0, 11, options.selectableMonths);
				var selectableYears = getSelectableList(todayVal.year - 5, todayVal.year + 5, options.selectableYears);
				var selectableDOW = getSelectableList(0, 6, options.selectableDOW);
				var dowNames = options.dowNames || [ 'Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat' ];
				var monthNames = options.monthNames || [ 'January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December' ];

				// Create cell width based on el size
				var containerWidth = el.outerWidth();
				var containerHeight = containerWidth;

				// Create cell size based on container size
				var getCellSize = function(_size, _count) {
					return (_size / _count) + ((options.borderSize / _count) * (_count - 1));
				};
				var cellWidth = getCellSize(containerWidth, maxCol);
				var cellHeight = getCellSize(containerHeight, maxRow + 2);

				// If calendar doesn't exist, create it and re-assign it to self
				if(!calendar.length) {
					self.calendar = calendar = $('<div/>')
						.attr('gldp-el', el.attr('gldp-id'))
						.data('is', true)
						.css(
						{
							display: (options.showAlways ? undefined : 'none'),
							zIndex: options.zIndex,
							width: (cellWidth * maxCol) + 'px'
						});

					$('body').append(calendar);
				}
				else {
					if(!eval(calendar.data('is'))) {
						containerWidth = calendar.outerWidth();
						containerHeight = calendar.outerHeight();

						cellWidth = getCellSize(containerWidth, maxCol);
						cellHeight = getCellSize(containerHeight, maxRow + 2);
					}
				}

				// Hide calendar if the target element isn't visible
				if(!el.is(':visible')) { calendar.hide(); }

				// Add core classes and remove calendar's children
				calendar
					.removeClass()
					.addClass(cssName)
					.children().remove();

				// Bind to resize event to position calendar
				var onResize = function() {
					var elPos = el.offset();
					calendar.css(
					{
						top: (elPos.top + el.outerHeight() + options.calendarOffset.y) + 'px',
						left: (elPos.left + options.calendarOffset.x) + 'px'
					});
				};
				$(window).resize(onResize);
				onResize();

				// Create variables for cells
				var cellCSS =
				{
					width: cellWidth + 'px',
					height: cellHeight + 'px',
					lineHeight: cellHeight + 'px'
				};

				// Helper function to setDate
				var setFirstDate = function(_date) {
					if(_date) {
						// Get first date
						options.firstDate = _date;

						// Update the calendar
						self.render();
					}
				};

				var getFirstDate = function(_offset) {
					// Create start date as the first date of the month
					var _date = new Date(options.firstDate);

					// Default to no offset
					_offset = _offset || 0;

					// Find out which months are selectable
					while(true) {
						// Adjust date for month offset
						_date.setMonth(_date.getMonth() + _offset);
						_date.setDate(Math.min(1, _date._max()));

						// If not an offset, break out of the loop
						if(_offset == 0) { break; }

						// Get _date's value
						var dateVal = _date._val();

						// Get local vars
						var dateMonth = dateVal.month;
						var dateYear = dateVal.year;

						// Find the month first
						if(selectableMonths.indexOf(dateMonth) != -1) {
							// If year is in our collection, break...
							if(selectableYears.indexOf(dateYear) != -1) {
								break;
							}
							else {
								// ...otherwise, if it's out of bounds, exit loop
								if(dateYear < selectableYears[0] || dateYear > selectableYears[selectableYears.length - 1]) {
									return null;
								}
							}
						}
					}

					return _date;
				};

				// Get the previous, next first dates
				var prevFirstDate = getFirstDate(-1);
				var nextFirstDate = getFirstDate(1);

				// Get the first date for the current month being rendered
				var firstDate = (options.firstDate = getFirstDate());
				var firstDateVal = firstDate._val();
				var firstDateMonth = firstDateVal.month;
				var firstDateYear = firstDateVal.year;

				// Get the start date in the calendar
				var startDate = new Date(firstDate);

				// Sanitize days of the week offset
				var dowOffset = Math.abs(Math.min(6, Math.max(0, options.dowOffset)));

				// Offset weekdays
				var startOffset = startDate.getDay() - dowOffset;
//				startOffset = startOffset < 1 ? - 7 - startOffset : -startOffset;
				startOffset = -startOffset;
				dowNames = (dowNames.concat(dowNames))
							.slice(dowOffset, dowOffset + 7);

				// Offset the start date
				startDate._add(startOffset);

				// Gather flags for prev/next arrows
				var showPrev = (prevFirstDate);
				var showNext = (nextFirstDate);

				// Create the arrows and title
				var monyearClass = coreClass + 'monyear ';

				var prevCell = $('<div/>')
								.addClass(monyearClass)
								.css(
									$.extend({}, cellCSS,
									{
										borderWidth: borderSize + ' 0 0 ' + borderSize,
										display: 'none'
									})
								)
								.append(
									$('<a/>')
										.addClass('prev-arrow' + (showPrev ? '' : '-off'))
										.html(options.prevArrow)
								)
								.mousedown(function() { return false; })
								.click(function(e) {
									if(options.prevArrow != '' && showPrev) {
										e.stopPropagation();
										setFirstDate(prevFirstDate);
									}
								});

				var titleCellCount = maxCol - 2;
				var titleWidth = (cellWidth * titleCellCount) - (titleCellCount * options.borderSize) + (options.borderSize);
				var titleCell = $('<div/>')
								.addClass(monyearClass + 'title')
								.css(
									$.extend({}, cellCSS,
									{
										width: titleWidth + 'px',
										borderTopWidth: borderSize,
										marginLeft: '-' + (borderSize),
										display: 'none'
									})
								);

				var nextCell = $('<div/>')
								.addClass(monyearClass)
								.css(
									$.extend({}, cellCSS,
									{
										marginLeft: '-' + (borderSize),
										borderWidth: borderSize + ' ' + borderSize + ' 0 0',
										display: 'none'
									})
								)
								.append(
									$('<a/>')
										.addClass('next-arrow' + (showNext ? '' : '-off'))
										.html(options.nextArrow)
								)
								.mousedown(function() { return false; })
								.click(function(e) {
									if(options.nextArrow != '' && showNext) {
										e.stopPropagation();
										setFirstDate(nextFirstDate);
									}
								});

				// Add cells for prev/title/next
				calendar
					.append(prevCell)
					.append(titleCell)
					.append(nextCell);

				// Add all the cells to the calendar
				if(options.selectedDate.getDay()<5) maxRow = maxRow - 1;
				for(var row = 0, cellIndex = 0; row < maxRow + 1; row++) {
					for(var col = 0; col < maxCol; col++, cellIndex++) {
						var cellDate = new Date(startDate);
						var cellClass = 'day';
						var cellZIndex = options.zIndex + (cellIndex);
						var cell = $('<div/>')

						if(!row) {
							cellClass = 'dow';
							cell.html(dowNames[col]);
							cellDate = null;
						}
						else {
							// Get the new date for this cell
							cellDate._add(col + ((row - 1) * maxCol));

							// Get value for this date
							var cellDateVal = cellDate._val();
							var cellDateTime = cellDateVal.time;

							// Variable to hold special data
							var specialData = null;

							// Determine if this date is selectable
							var isSelectable = true;

							// Helper function to get repeat friendly date against current date
							var getRepeatDate = function(v, date) {
								// If repeating, set the date's year and month accordingly
								if(v.repeatYear === true) { date.setYear(cellDateVal.year); }
								if(v.repeatMonth === true) { date.setMonth(cellDateVal.month); }

								return date._val();
							};

							// Assign date for the cell
							cell.html(cellDateVal.date);

                            // If we have selectable date ranges
							if(options.selectableDateRange) {
								isSelectable = false;
								$.each(options.selectableDateRange, function(i, v) {
									var dateFrom = v.from;
									var dateTo = (v.to || null);

									// If to is not specified, default to max days in the from month
									dateTo = dateTo || new Date(v.from.getFullYear(), v.from.getMonth(), v.from._max());

									// If repeating year, set the from and two to the current date's year
									dateFrom = getRepeatDate(v, dateFrom);
									dateTo = getRepeatDate(v, dateTo);

									// Test to see if this date is selectable
									if(cellDateTime >= dateFrom.time && cellDateTime <= dateTo.time) {
										isSelectable = true;
										return true;
									}
								});
							}

							// Handle date ranges and collections
							if(options.selectableDates) {
								if((options.selectableDateRange && !isSelectable) || (isSelectable && !options.selectableDateRange)) {
									isSelectable = false;
								}
								$.each(options.selectableDates, function(i, v) {
									var vDate = getRepeatDate(v, v.date);

									if(vDate.time == cellDateTime) {
										return (isSelectable = true);
									}
								});
							}

							// If not active or if not within selectableMonths, set to noday otherwise evaluate accordingly
							if(!isSelectable ||
								selectableYears.indexOf(cellDateVal.year) < 0 ||
								selectableMonths.indexOf(cellDateVal.month) < 0 ||
								selectableDOW.indexOf(cellDateVal.day) < 0) {
								cell.html("");
								cellClass = 'noday';
							}
							else {
								// Handle active dates and weekends
								cellClass = ([ 'sun', 'mon', 'tue', 'wed', 'thu', 'fri', 'sat' ])[cellDateVal.day];

								// Handle today or selected dates
								if(firstDateMonth != cellDateVal.month) { cellClass += ' outday'; }
//								if(todayTime == cellDateTime) { cellClass = 'today'; cellZIndex += 50; }
//								if(options.selectedDate._time() == cellDateTime) { cellClass = 'selected'; cellZIndex += 51; }

								// Handle special dates
								if(options.specialDates) {
									$.each(options.specialDates, function(i, v) {
										var vDate = getRepeatDate(v, v.date);

										if(vDate.time == cellDateTime) {
											cellClass = (v.cssClass || 'special');
											cellZIndex += 52;
											specialData = v.data;
										}
									});
								}

								cell
									.mousedown(function() { return false; })
									.hover(function(e) {
										e.stopPropagation();

										// Get the data from this cell
										var hoverData = $(this).data('data');

										// Call callback
										options.onHover(el, cell, hoverData.date, hoverData.data);
									})
//									.click(function(e) {
//										e.stopPropagation();
//
//										// Get the data from this cell
//										var clickedData = $(this).data('data');
//
//										// Save date to selected and first
//										options.selectedDate = options.firstDate = clickedData.date;
//
//										// Update calendar (and auto-hide if necessary)
//										self.render(function() {
//											if(!options.showAlways && options.hideOnClick) {
//												self.hide();
//											}
//										});
//
//										// Call callback
//										options.onClick(el, $(this), clickedData.date, clickedData.data);
//									})
									; //原656行
// =================================================================================================================== 
//								var select_page  = "select_page_Basic.jsp";
//								var select_page2 = "https://tw.yahoo.com/index.html";
//								var select_page2 = "http://localhost:8081/glDatePicker-master-upgrade1/select_page_Basic.jsp";
								var theDate = cellDateVal.year + '-' + (((cellDateVal.month+1)<10)? "0"+(cellDateVal.month+1):(cellDateVal.month+1)) + '-' + ((cellDateVal.date<10)? "0"+cellDateVal.date:cellDateVal.date);
								var a_content = (((cellDateVal.month+1)<10)? "0"+(cellDateVal.month+1):(cellDateVal.month+1))+"/"+((cellDateVal.date<10)? "0"+cellDateVal.date:cellDateVal.date);
								
								cell.html("<div class='parent'>"
	                           		 + "<button type='button' class='make_reservation_btn_am booking_btn' value='am'><p>\u4E0A\u5348</p></button>"
	                           		 + "<button type='button' class='make_reservation_btn_pm booking_btn' value='pm'><p>\u4E0B\u5348</p></button>"
//	                           		 + "<iframe src=\'" +select_page+ "?theDate=" + theDate + "\'" 
//	                           		 + " style='background-color: white; border-color: black; border: 1px 1px 1px 1px;'"
//	                           		 + " width=95% height=43% scrolling=no></iframe>"
	                           		 + "<div class='date' id='date'>"
	                           		 + "<a>"
	                           		 +  a_content
//                                     +  (cellDateVal.month+1)+"/"+cellDateVal.date
	                           		 + "</a>"
	                           		 + "</div>"
	                           		 + "</div>");
// =================================================================================================================== 
							}//原657行
						}

						// Update the css for the cell
						$.extend(cellCSS,
						{
							borderTopWidth: borderSize,
							borderBottomWidth: borderSize,
							borderLeftWidth: (row > 0 || (!row && !col)) ? borderSize : 0,
							borderRightWidth: (row > 0 || (!row && col == 6)) ? borderSize : 0,
							marginLeft: (col > 0) ? '-' + (borderSize) : 0,
							marginTop: (row > 0) ? '-' + (borderSize) : 0,
							zIndex: cellZIndex
						});

						// Assign other properties to the cell
						cell
							.data('data', { date: cellDate, data: specialData})
							.addClass(coreClass + cellClass)
							.css(cellCSS);

						// Add cell to calendar
						calendar.append(cell);
					}
				}

				// Render the month / year title

				// Helper function for toggling select and text
				var toggleYearMonthSelect = function(showYear) {
					var show = 'inline-block';
					var hide = 'none';

					if(options.allowMonthSelect) {
						monthText.css({ display: !showYear ? hide : show });
						monthSelect.css({ display: !showYear ? show : hide });
					}

					if(options.allowYearSelect) {
						yearText.css({ display: showYear ? hide : show });
						yearSelect.css({ display: showYear ? show : hide });
					}
				};

				// Helper function when select is updated
				var onYearMonthSelect = function() {
					options.firstDate = new Date(yearSelect.val(), monthSelect.val(), 1);
					self.render();
				};

				// Build month selector
				var monthSelect = $('<select/>')
									.hide()
									.change(onYearMonthSelect);

				// Build year selector
				var yearSelect = $('<select/>')
									.hide()
									.change(onYearMonthSelect);

				// Build month label
				var monthText = $('<span/>')
									.html(monthNames[firstDateMonth])
									.mousedown(function() { return false; })
									.click(function(e) {
										e.stopPropagation();
										toggleYearMonthSelect(false);
									});

				// Build year label
				var yearText = $('<span/>')
									.html(firstDateYear)
									.mousedown(function() { return false; })
									.click(function(e) {
										e.stopPropagation();
										toggleYearMonthSelect(true);
									});

				// Populate month select
				$.each(monthNames, function(i, v) {
					if(options.allowMonthSelect && selectableMonths.indexOf(i) != -1) {
						var o = $('<option/>').html(v).attr('value', i);
						if(i == firstDateMonth) { o.attr('selected', 'selected');}
						monthSelect.append(o);
					}
				});

				// Populate year select
				$.each(selectableYears, function(i, v) {
					if(options.allowYearSelect) {
						var o = $('<option/>').html(v).attr('value', v);
						if(v == firstDateYear) { o.attr('selected', 'selected'); }
						yearSelect.append(o);
					}
				});

				var titleYearMonth = $('<div/>')
										.append(monthText)
										.append(monthSelect)
										.append(yearText)
										.append(yearSelect);

				// Add to title
				titleCell.children().remove();
				titleCell.append(titleYearMonth);

				// Run the callback signaling end of the render
				renderCalback = renderCalback || (function() {});
				renderCalback();
			}
		};

		// Return the plugin
		return glDatePicker;
	})();

	var dateFormat = function () {
		var	token = /d{1,4}|m{1,4}|yy(?:yy)?|([HhMsTt])\1?|[LloSZ]|"[^"]*"|'[^']*'/g,
			timezone = /\b(?:[PMCEA][SDP]T|(?:Pacific|Mountain|Central|Eastern|Atlantic) (?:Standard|Daylight|Prevailing) Time|(?:GMT|UTC)(?:[-+]\d{4})?)\b/g,
			timezoneClip = /[^-+\dA-Z]/g,
			pad = function (val, len) {
				val = String(val);
				len = len || 2;
				while (val.length < len) val = "0" + val;
				return val;
			};
	
		// Regexes and supporting functions are cached through closure
		return function (date, mask, utc) {
			var dF = dateFormat;
	
			// You can't provide utc if you skip other args (use the "UTC:" mask prefix)
			if (arguments.length == 1 && Object.prototype.toString.call(date) == "[object String]" && !/\d/.test(date)) {
				mask = date;
				date = undefined;
			}
	
			// Passing date through Date applies Date.parse, if necessary
			date = date ? new Date(date) : new Date;
			if (isNaN(date)) throw SyntaxError("invalid date");
	
			mask = String(dF.masks[mask] || mask || dF.masks["default"]);
	
			// Allow setting the utc argument via the mask
			if (mask.slice(0, 4) == "UTC:") {
				mask = mask.slice(4);
				utc = true;
			}
	
			var	_ = utc ? "getUTC" : "get",
				d = date[_ + "Date"](),
				D = date[_ + "Day"](),
				m = date[_ + "Month"](),
				y = date[_ + "FullYear"](),
				H = date[_ + "Hours"](),
				M = date[_ + "Minutes"](),
				s = date[_ + "Seconds"](),
				L = date[_ + "Milliseconds"](),
				o = utc ? 0 : date.getTimezoneOffset(),
				flags = {
					d:    d,
					dd:   pad(d),
					ddd:  dF.i18n.dayNames[D],
					dddd: dF.i18n.dayNames[D + 7],
					m:    m + 1,
					mm:   pad(m + 1),
					mmm:  dF.i18n.monthNames[m],
					mmmm: dF.i18n.monthNames[m + 12],
					yy:   String(y).slice(2),
					yyyy: y,
					h:    H % 12 || 12,
					hh:   pad(H % 12 || 12),
					H:    H,
					HH:   pad(H),
					M:    M,
					MM:   pad(M),
					s:    s,
					ss:   pad(s),
					l:    pad(L, 3),
					L:    pad(L > 99 ? Math.round(L / 10) : L),
					t:    H < 12 ? "a"  : "p",
					tt:   H < 12 ? "am" : "pm",
					T:    H < 12 ? "A"  : "P",
					TT:   H < 12 ? "AM" : "PM",
					Z:    utc ? "UTC" : (String(date).match(timezone) || [""]).pop().replace(timezoneClip, ""),
					o:    (o > 0 ? "-" : "+") + pad(Math.floor(Math.abs(o) / 60) * 100 + Math.abs(o) % 60, 4),
					S:    ["th", "st", "nd", "rd"][d % 10 > 3 ? 0 : (d % 100 - d % 10 != 10) * d % 10]
				};
	
			return mask.replace(token, function ($0) {
				return $0 in flags ? flags[$0] : $0.slice(1, $0.length - 1);
			});
		};
	}();
	
	// Some common format strings
	dateFormat.masks = {
		"default":      "ddd mmm dd yyyy HH:MM:ss",
		shortDate:      "m/d/yy",
		mediumDate:     "mmm d, yyyy",
		longDate:       "mmmm d, yyyy",
		fullDate:       "dddd, mmmm d, yyyy",
		shortTime:      "h:MM TT",
		mediumTime:     "h:MM:ss TT",
		longTime:       "h:MM:ss TT Z",
		isoDate:        "yyyy-mm-dd",
		isoTime:        "HH:MM:ss",
		isoDateTime:    "yyyy-mm-dd'T'HH:MM:ss",
		isoUtcDateTime: "UTC:yyyy-mm-dd'T'HH:MM:ss'Z'"
	};
	
	// Internationalization strings
	dateFormat.i18n = {
		dayNames: [
			"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat",
			"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"
		],
		monthNames: [
			"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec",
			"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"
		]
	};

	// One time initialization of useful prototypes
	(function() {
		Date.prototype._clear = function() {
			this.setHours(0);
			this.setMinutes(0);
			this.setSeconds(0);
			this.setMilliseconds(0);

			return this;
		};

		// For convenience...
		Date.prototype.format = function (mask, utc) {
			return dateFormat(this, mask, utc);
		};

		Date.prototype._time = function() {
			return this._clear().getTime();
		};

		Date.prototype._max = function() {
			var isLeapYear = (new Date(this.getYear(), 1, 29).getMonth() == 1) ? 1 : 0;
			var days = [31, 28 + isLeapYear, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];

			return days[this.getMonth()];
		};

		Date.prototype._add = function(days) {
			this.setDate(this.getDate() + days);
		};

		Date.prototype._first = function() {
			var date = new Date(this);
				date.setDate(1);

			return date;
		};

		Date.prototype._val = function() {
			this._clear();

			return {
				year: this.getFullYear(),
				month: this.getMonth(),
				date: this.getDate(),
				time: this.getTime(),
				day: this.getDay()
			};
		}
	})();
})();



