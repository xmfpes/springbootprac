$(document).ready(function() {
	/* initialize the calendar
	-----------------------------------------------------------------*/
	$('#calendar').fullCalendar({
		header : {
			left : 'prev,next today',
			center : 'title',
			right : 'month,agendaWeek,agendaDay'
		},
		buttonText : {
			today : '오늘',
			month : '월간 계획',
			week : '주간 계획',
			day : '일간 계획'
		},
		timeFormat : "HH:mm",
		monthNames: ["1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월"],
		monthNamesShort: ["1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월"],
		dayNames: ["일요일", "월요일", "화요일", "수요일", "목요일", "금요일", "토요일"],
		dayNamesShort: ["일", "월", "화", "수", "목", "금", "토"],
		editable : true,
		droppable : true, // this allows things to be dropped onto the calendar
		drop : function(date, allDay) {
            // is the "remove after drop" checkbox checked?
            if ($('#drop-remove').is(':checked')) {
                // if so, remove the element from the "Draggable Events" list
                $(this).remove();
            }
		},
		eventReceive: function(event){
			
		},
		eventDrop: function(event, delta, revertFunc) {
	        alert(event.title + "일정을 " + event.start.format() + "날짜로 옮깁니다.");

	        if (!confirm("일정을 변경하시겠어요?")) {
	            revertFunc();
	        }

	    },
	    eventAfterRender:function(event, element, view){
		    	var _id = event._id,
				expr = /_fc\d/;
			var match = expr.exec(_id);	
			var is_fc = expr.test(_id);
			if(!event.id && is_fc){
				var new_event = {};
				new_event.title = event.title;
				console.log(new_event.title);
				new_event.start = event.start;
				console.log(new_event.start);
				new_event.end = event.end;
				console.log(new_event.end);
				new_event.backgroundColor = event.backgroundColor;
				console.log(new_event.backgroundColor);
				new_event.borderColor = event.borderColor;
				console.log(new_event.borderColor);
				new_event.editable = event.editable;
				console.log(new_event.editable);
				new_event.durationEditable = event.durationEditable;
				console.log(new_event.durationEditable);
				//new_event.owner_id = $_SESS['user_id']		
				console.log(event);
				console.log(new_event);
			}
	    },
	    eventResizeStop :function( event, jsEvent, ui, view ) { 
	    		alert("일정 변경");
	    },
		eventDragStop: function(event, jsEvent, ui, view) {
            alert("zz");
            if(isEventOverDiv(jsEvent.clientX, jsEvent.clientY)) {
                $('#calendar').fullCalendar('removeEvents', event._id);
            }
        },
        eventRender: function(event, element) {
			
	    }
	});
	
	/* initialize the external events
	-----------------------------------------------------------------*/
	var currColor = "#f6504d";
	$('#external-events .fc-event').each(function() {

		// store data so the calendar knows to render an event upon drop
		$(this).data('event', {
			title : $.trim($(this).text()), // use the element's text as the event title
			stick : true,
			backgroundColor : $(this).css('background-color'),
			borerColor : $(this).css('border-color')
		// maintain when user navigates (see docs on the renderEvent method)
		});

		// make the event draggable using jQuery UI
		$(this).draggable({
			zIndex : 999,
			revert : true, // will cause the event to go back to its
			revertDuration : 0
		//  original position after the drag
		});
	});
	
	//external event form
	var externalEvents = $('#external-events');
	// Color switchers
	var eventColorSelector = $('.external-event-color-selector .point');
	
	/* ColorSelector click event*/
	eventColorSelector.click(function(e) {
		e.preventDefault();
		var $this = $(this);
		// Save color
		currColor = $this.css('background-color');
		// De-select all and select the current one
		eventColorSelector.removeClass('selected');
		$this.addClass('selected');
	});
	/* colorPicker Button Event*/
	var eventNameInput = $('.external-event-name');
	var eventAddButton = $('.external-event-add-btn');
	eventAddButton.click(function(e){
		e.preventDefault();
		// Get event name from input
		var val = eventNameInput.val();
		// Dont allow empty values
		if ($.trim(val) === '')
			return;
		
		// Create new event element
		var newEvent = $('<div/>').css({
			'background-color' : currColor,
			'border-color' : currColor,
			'color' : '#fff'
		}).addClass("fc-event")
		newEvent.html(val);
		
		addDraggable(newEvent);
		// Prepends to the external events list
		externalEvents.prepend(newEvent);
		// Initialize the new event element
		
		// Clear input
		eventNameInput.val('');
	});
	
	function addDraggable(event){
		alert(event);
		event.data('event', {
			title : $.trim(event.text()), // use the element's text as the event title
			stick : true,
			backgroundColor : event.css('background-color'),
			borderColor : event.css('border-color')
		// maintain when user navigates (see docs on the renderEvent method)
		
		});
		
		event.draggable({
			zIndex : 999,
			revert : true, // will cause the event to go back to its
			revertDuration : 0
		//  original position after the drag
		});
	}

    var isEventOverDiv = function(x, y) {

        var external_events = $( '#external-events' );
        var offset = external_events.offset();
        offset.right = external_events.width() + offset.left;
        offset.bottom = external_events.height() + offset.top;

        // Compare
        if (x >= offset.left
            && y >= offset.top
            && x <= offset.right
            && y <= offset .bottom) { return true; }
        return false;

  	}
});